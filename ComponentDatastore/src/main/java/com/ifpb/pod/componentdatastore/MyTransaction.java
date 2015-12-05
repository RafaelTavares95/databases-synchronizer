/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentdatastore;

import ag.ifpb.pod.rmi.core.DatastoreService;
import com.ifpb.pod.core.entities.TeacherTO;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa uma transação.
 * @author Rafael
 */
public class MyTransaction{
    private Boolean gerenciado;
    private TeacherTO to;
    private StoreLocalImpl dt;
    
    
    public MyTransaction(TeacherTO t, StoreLocalImpl datat) {
        this.gerenciado = false;
        this.to = t;
        this.dt = datat;
    }
    
    public void begin(){
        this.gerenciado = true;
    }
    
    public void commit(){
        if(gerenciado)
            try {
                dt.create(to);
        } catch (RemoteException ex) {
            Logger.getLogger(MyTransaction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void rollback(){
        gerenciado = false;
        to=null;
    }
}
