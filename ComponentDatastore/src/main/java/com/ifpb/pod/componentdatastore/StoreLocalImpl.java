/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentdatastore;

import ag.ifpb.pod.rmi.core.DatastoreService;
import com.ifpb.pod.core.entities.TeacherTO;
import com.ifpb.pod.core.dao.Dao;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class StoreLocalImpl extends UnicastRemoteObject implements StoreLocal{
    private MyTransaction t;
    private DatastoreService dt;
    
    public StoreLocalImpl() throws RemoteException, NotBoundException{
        Registry r = LocateRegistry.getRegistry("200.129.71.228", 9090);
        dt = (DatastoreService) r.lookup("DatastoreService");
    }  

    public ag.ifpb.pod.rmi.core.TeacherTO toTeacherAPI(TeacherTO to){
        ag.ifpb.pod.rmi.core.TeacherTO t = new ag.ifpb.pod.rmi.core.TeacherTO();
        t.setCode(to.getCode());
        t.setAbbrev(to.getAbbrev());
        t.setName(to.getName());
        t.setActive(to.isActive());
        return t;
    }

    public TeacherTO toTeacherProject(ag.ifpb.pod.rmi.core.TeacherTO to){
        TeacherTO t = new TeacherTO();
        t.setCode(to.getCode());
        t.setAbbrev(to.getAbbrev());
        t.setName(to.getName());
        t.setActive(to.isActive());
        return t;
    }

    @Override
    public void create(TeacherTO professor) throws RemoteException {
        dt.createTeacher(toTeacherAPI(professor));
    }

    @Override
    public void update(TeacherTO professor) throws RemoteException {
        dt.updateTeacher(toTeacherAPI(professor));
    }

    @Override
    public List<TeacherTO> list() throws RemoteException {
        List<ag.ifpb.pod.rmi.core.TeacherTO> listTeachers = dt.listTeachers();
        List<TeacherTO> list = new ArrayList<>();
        for (ag.ifpb.pod.rmi.core.TeacherTO teacherTO : listTeachers) {
            list.add(toTeacherProject(teacherTO));
        }
        return list;
    }
}
