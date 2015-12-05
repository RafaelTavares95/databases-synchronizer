/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentpostgre;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.persistence.EntityManager;

/**
 *
 * @author Rafael
 */
public class TransLocalImpl extends UnicastRemoteObject implements TransLocal{
    private EntityManager em;
    
    public TransLocalImpl() throws RemoteException{
        super();
    }
    
    public TransLocalImpl(EntityManager entity) throws RemoteException{
        this.em = entity;
    }

    
    @Override
    public void prepare() throws RemoteException {
        if(!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    @Override
    public void commit() throws RemoteException {
        em.getTransaction().commit();
    }

    @Override
    public void rollback() throws RemoteException {
        em.getTransaction().rollback();
    }
    
}
