/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.sincapp;

import com.ifpb.pod.transcoordapp.TransCoord;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe que executa a cada 5 minutos o sincronizador dos bancos.
 * @author Rafael
 */
public class Loader {
    private static final long TEMPO = (5000 * 60); 
    private static StoreLocal a = null;
    private static StoreLocal b = null;
    private static StoreLocal c = null;
    private static TransCoord tx = null;
    
    private static StoreLocal getA() throws RemoteException, NotBoundException{
        Registry registryA = LocateRegistry.getRegistry("localhost", 10099);
        return (StoreLocal) registryA.lookup("PostgreComponent");
    }
    private static StoreLocal getB() throws RemoteException, NotBoundException{
        Registry registryB = LocateRegistry.getRegistry("localhost", 10098);
        return(StoreLocal) registryB.lookup("MysqlComponent");
    }
    private static StoreLocal getC() throws RemoteException, NotBoundException{
        Registry registryC = LocateRegistry.getRegistry("localhost", 10097);
        return (StoreLocal) registryC.lookup("DatastoreComponent");
    }
    private static TransCoord getTx() throws RemoteException, NotBoundException{
        Registry registryTx = LocateRegistry.getRegistry("localhost", 10115);
        return (TransCoord) registryTx.lookup("TransCoord");
    }     
    
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Timer timer = null;  
        if (timer == null) {  
            timer = new Timer();  
            TimerTask tarefa = new TimerTask() {  
                public void run() {  
                    try {
                        tx = getTx();
                        a = getA();
                        b = getB();
                        c = getC();
                        Sincronizador s = new Sincronizador(tx, a, b, c);
                        s.sincronizar();
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            };  
            timer.scheduleAtFixedRate(tarefa, TEMPO, TEMPO);  
        }  
    }
}
