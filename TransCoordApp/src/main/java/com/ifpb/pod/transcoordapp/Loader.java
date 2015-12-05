/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.transcoordapp;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class Loader {
    public static void main(String[] args) {
        try {
            Registry regA = LocateRegistry.getRegistry("localhost", 10111);
            TransLocal a = (TransLocal) regA.lookup("TransLocal");
            
            Registry regB = LocateRegistry.getRegistry("localhost", 10112);
            TransLocal b = (TransLocal) regB.lookup("TransLocal");
            
            Registry regC = LocateRegistry.getRegistry("localhost", 10113);
            TransLocal c = (TransLocal) regC.lookup("TransLocal");
            
            TransCoord txCoord = new TransCoordImpl(a, b, c);
            
            System.setProperty("java.rmi.server.hostname", "localhost");
            Registry registryCoord = LocateRegistry.createRegistry(10115);
            registryCoord.bind("TransCoord", txCoord);
        } catch (RemoteException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
