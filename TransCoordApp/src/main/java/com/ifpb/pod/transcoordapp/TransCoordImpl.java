/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.transcoordapp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Rafael
 */
public class TransCoordImpl extends UnicastRemoteObject implements TransCoord{
    private TransLocal transA;
    private TransLocal transB;
    private TransLocal transC;
    
    public TransCoordImpl(TransLocal a, TransLocal b, TransLocal c)throws RemoteException{
        this.transA = a;
        this.transB = b;
        this.transC = c;
    }
    
    /***
     * Metodo que prepara todas as transações
     * @throws RemoteException 
     */
    @Override
    public void prepareAll() throws RemoteException {
        transA.prepare();
        transB.prepare();
        transC.prepare();
    }
    
    /**
     * Método que finaliza todas as transações
     * @throws RemoteException 
     */
    @Override
    public void commitAll() throws RemoteException {
        transA.commit();
        transB.commit();
        transC.commit();
    }
    
    /**
     * Método que desfaz todas as ações feitas na transação
     * @throws RemoteException 
     */
    @Override
    public void rollbackAll() throws RemoteException {
        transA.rollback();
        transB.rollback();
        transC.rollback();
    }
    
}
