/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentdatastore;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.persistence.EntityManager;

/**
 *
 * @author Rafael
 */
public class TransLocalImpl extends UnicastRemoteObject implements TransLocal{
    private MyTransaction t;
    
    public TransLocalImpl() throws RemoteException{
        super();
    }
    
    public TransLocalImpl(MyTransaction tt) throws RemoteException{
        this.t = tt;
    }

    
    @Override
    public void prepare() throws RemoteException {
        t.begin();
    }

    @Override
    public void commit() throws RemoteException {
        t.commit();
    }

    @Override
    public void rollback() throws RemoteException {
        t.rollback();
    }
    
}
