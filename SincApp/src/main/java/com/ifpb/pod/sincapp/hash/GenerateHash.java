/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.sincapp.hash;

import com.ifpb.pod.core.entities.TeacherTO;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 *
 * @author Rafael
 */
public class GenerateHash {

    public GenerateHash() {
    }
    
    /***
     * retorna o hash a partir de uma string
     * @param msg
     * @return 
     */
    public String simpleHash(String msg){  
        String m = "";  
        MessageDigest md = null;  
        try {  
            md = MessageDigest.getInstance("MD5");  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        }  
        BigInteger hash = new BigInteger(1, md.digest(msg.getBytes()));  
        m = hash.toString(16);              
        return m;  
    }
    
    /**
     * Retorna o hash que representa o banco inteiro
     * @param list
     * @return 
     */
    public String hashBanco(List<TeacherTO> list){
        String value = "";
        for (TeacherTO professor : list) {
            value = value + professor.toString();
        }
        return simpleHash(value);
    }
}
