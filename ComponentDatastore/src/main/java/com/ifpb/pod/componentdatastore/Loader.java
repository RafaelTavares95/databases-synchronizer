/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentdatastore;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Rafael
 */
public class Loader {
    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        System.setProperty("java.rmi.server.hostname", "localhost");
        Registry registry = LocateRegistry.createRegistry(10097);
        registry.bind("DatastoreComponent", new StoreLocalImpl());
        Registry reg = LocateRegistry.createRegistry(10113);
        reg.bind("TransLocal", new TransLocalImpl());
    }
}
