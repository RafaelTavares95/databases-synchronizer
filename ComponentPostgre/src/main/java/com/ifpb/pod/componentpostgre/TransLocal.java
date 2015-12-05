/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.componentpostgre;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Rafael
 */
public interface TransLocal extends Remote{
    public void prepare() throws RemoteException;
    public void commit() throws RemoteException;
    public void rollback() throws RemoteException;
}
