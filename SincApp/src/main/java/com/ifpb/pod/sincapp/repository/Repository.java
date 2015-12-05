/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.sincapp.repository;

import com.ifpb.pod.core.entities.TeacherTO;
import com.ifpb.pod.sincapp.hash.GenerateHash;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rafael
 */
public class Repository {
    private Map<Integer, String> professorRepo;
    private GenerateHash gh;
    
    public Repository() {
        this.professorRepo = new HashMap<>();
        this.gh   = new GenerateHash();
        
    }
    /***
     * Retorna um mapa com o id da entidade e o seu hash
     * @param professores
     * @return 
     */
    public Map<Integer, String> dataHash(List<TeacherTO> professores){
        for (TeacherTO professor : professores) {
            professorRepo.put(professor.getCode(), gh.simpleHash(professor.toString()));
        }
        return professorRepo;
    }
    
    
    
}
