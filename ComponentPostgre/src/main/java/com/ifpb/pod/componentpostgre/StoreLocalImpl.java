/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentpostgre;

import com.ifpb.pod.core.entities.TeacherTO;
import com.ifpb.pod.core.dao.Dao;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Rafael
 */
public class StoreLocalImpl extends UnicastRemoteObject implements StoreLocal{
    
    private Dao dao;
    
    public StoreLocalImpl(EntityManager em) throws RemoteException{
        this.dao= new Dao(em);
    }  

    @Override
    public void create(TeacherTO professor) throws RemoteException {
        dao.persist(professor);
    }

    @Override
    public void update(TeacherTO professor) throws RemoteException {
        dao.updade(professor);
    }

    @Override
    public List<TeacherTO> list() throws RemoteException {
        return dao.list(TeacherTO.class);
    }
}
