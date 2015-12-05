/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.sincapp;

import com.ifpb.pod.core.entities.TeacherTO;
import com.ifpb.pod.sincapp.hash.GenerateHash;
import com.ifpb.pod.sincapp.mirror.MirrorManager;
import com.ifpb.pod.sincapp.repository.Repository;
import com.ifpb.pod.transcoordapp.TransCoord;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Rafael
 */
public class Sincronizador {
    private TransCoord tx;
    private StoreLocal postgre;
    private StoreLocal mysql;
    private StoreLocal datastore;
            
    private GenerateHash gh;
    private MirrorManager mrm;
    private Repository repo;

    public Sincronizador(TransCoord t, StoreLocal a, StoreLocal b, StoreLocal c) throws RemoteException {
        this.tx = t;
        this.postgre = a;
        this.mysql = b;
        this.datastore = c;
        
        this.gh = new GenerateHash();
        this.mrm = new MirrorManager();
        init(postgre.list());
        
    }
    
    
    /***
     * Metodo que inicia o repositorio com os dados hash do banco
     * @param list 
     */
    public void init(List<TeacherTO> list){
        mrm.setPropBancoHash("bancoHash", gh.hashBanco(list));
        mrm.setDatahash(repo.dataHash(list));
    }
    
    /***
     * Método que verifica se houve mudanças em um banco de dados
     * @param list
     * @return 
     */
    public Boolean mustChanges(List<TeacherTO> list){
        if(mrm.getProp("bancoHash").equals(gh.hashBanco(list)))
            return false;
        return true;
    }
    
    /***
     * Metodo que sincroniza os bancos de dados
     * @throws RemoteException 
     */
    public void sincronizar() throws RemoteException{
        List<TeacherTO> endidadesInseridas = new ArrayList<>();
        List<TeacherTO> endidadesAtualizadas = new ArrayList<>();
        List<TeacherTO> a = postgre.list();
        List<TeacherTO> b = mysql.list();
        List<TeacherTO> c = datastore.list();
        try{
            tx.prepareAll();
            if(mustChanges(a)){
                for (TeacherTO teacherTO : a) {
                    if(!b.contains(teacherTO) || !c.contains(teacherTO)){
                        endidadesInseridas.add(teacherTO);
                    }else{
                        endidadesAtualizadas.add(teacherTO);
                    }       
                }
                add(endidadesInseridas, mysql);
                add(endidadesInseridas, datastore);
                update(endidadesAtualizadas, mysql);
                update(endidadesAtualizadas, datastore);
            } else if(mustChanges(b)){
                for (TeacherTO teacherTO : b) {
                        endidadesAtualizadas.add(teacherTO);      
                }
                update(endidadesAtualizadas, postgre);
                update(endidadesAtualizadas, datastore);
            } else if(mustChanges(c)){
                for (TeacherTO teacherTO : c) {
                        endidadesAtualizadas.add(teacherTO);
                }
                update(endidadesAtualizadas, mysql);
                update(endidadesAtualizadas, postgre);
            }else{
                throw new RemoteException("Erro ao sincronizar bancos");
            }
            tx.commitAll();
            mrm.setPropBancoHash("bancoHash", gh.hashBanco(c));
        } catch(RemoteException e){
            e.printStackTrace();
            tx.rollbackAll();
        }    
    }
    
    /***
     * Método que adiciona entidades ao banco
     * @param list
     * @param sl
     * @throws RemoteException 
     */
    public void add(List<TeacherTO> list, StoreLocal sl) throws RemoteException{
        for (TeacherTO teacherTO : list) {
            sl.create(teacherTO);
        }
    }
    
    /***
     * Método que atualiza as entidades no banco
     * @param list
     * @param sl
     * @throws RemoteException 
     */
    public void update(List<TeacherTO> list, StoreLocal sl) throws RemoteException{
        for (TeacherTO teacherTO : list) {
            sl.update(teacherTO);
        }
    }
}
