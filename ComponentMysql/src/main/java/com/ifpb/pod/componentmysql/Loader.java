/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentmysql;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Rafael
 */
public class Loader {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "localhost");
        EntityManager em = Persistence.createEntityManagerFactory("PU").createEntityManager();
        Registry registry = LocateRegistry.createRegistry(10098);
        registry.bind("MysqlComponent", new StoreLocalImpl(em));
        Registry reg = LocateRegistry.createRegistry(10112);
        reg.bind("TransLocal", new TransLocalImpl(em));
    }
}
