/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.sincapp.mirror;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 *
 * Classe responsável por controlar o arquivo de propriedades que vai servir de 
 * espelho para os dados do banco
 * @author Rafael
 */
public class MirrorManager {
    private Properties mirror;

    public MirrorManager() {
        this.mirror = new Properties();
        InputStream inputStream = null;
        File f;
	try{
            f = new File("src/main/resources/mirror.properties");
            inputStream = new FileInputStream(f);
            mirror.load(inputStream);
            inputStream.close();
	}catch(IOException e){
            e.printStackTrace();
	}
    }
    
    /**
     * Método que retorna o valor de uma propriedade a partir da chave.
     * 
     * @param key
     * @return 
     */
    public String getProp(String key) {
        String value = this.mirror.getProperty(key);
        return value;
    }
    
    /***
     * Método que adiciona ou atualiza um dado no arquivo
     * @param key
     * @param value 
     */
    public void setPropBancoHash(String key, String value){
        mirror.setProperty(key, value);
    }
    
    
    /***
     * Método que adiciona ou atualiza o mapa de hash no arquivo
     * @param data 
     */
    public void setDatahash(Map<Integer, String> data){
        Set<Integer> keySet = data.keySet();
        for (Integer chave : keySet) {
            mirror.setProperty(String.valueOf(chave), data.get(chave));
        }
    }
    
    /***
     * Método que retorna um mapa com os hash das entidades
     * @return 
     */
    public Map<Integer, String> getDatahash(){
        Map<Integer, String> repo = new HashMap<>();
        Set<Object> keySet = mirror.keySet();
        for (Object object : keySet) {
            repo.put(Integer.parseInt(object.toString()), mirror.getProperty(object.toString()));
        }
        return repo;
    }
}
