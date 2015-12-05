/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.transcoordapp;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Rafael
 */
public interface TransCoord extends Remote{
    public void prepareAll() throws RemoteException;
    public void commitAll() throws RemoteException;
    public void rollbackAll() throws RemoteException;
}
