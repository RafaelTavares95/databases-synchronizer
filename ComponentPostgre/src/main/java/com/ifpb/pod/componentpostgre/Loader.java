/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentpostgre;

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
        registry.bind("PostgreComponent", new StoreLocalImpl(em));
        Registry reg = LocateRegistry.createRegistry(10111);
        reg.bind("Translocal", new TransLocalImpl(em));
    }
}
