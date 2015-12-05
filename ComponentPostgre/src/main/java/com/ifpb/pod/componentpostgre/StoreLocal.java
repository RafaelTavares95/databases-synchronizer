/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentpostgre;

import com.ifpb.pod.core.entities.TeacherTO;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface StoreLocal extends Remote{
    void create(TeacherTO professor) throws RemoteException;
    void update(TeacherTO professor) throws RemoteException;
    List<TeacherTO> list() throws RemoteException;
}
