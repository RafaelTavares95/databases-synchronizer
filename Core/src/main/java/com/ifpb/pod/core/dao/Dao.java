/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ifpb.pod.core.dao;

import com.ifpb.pod.core.entities.TeacherTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe de acesso aos dados.
 * @author Rafael
 */
public class Dao {
    private EntityManager entityManager;

    public Dao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU");
        entityManager = emf.createEntityManager();
    }
        
    public Dao(EntityManager em){
        this.entityManager = em;
    }
    
    public Object find(Object chave, Class entidade) {
        return entityManager.find(entidade, chave);   
    }
    
    public void updade(TeacherTO a) {
            entityManager.merge(find(a.getCode(), TeacherTO.class));
    }
    
    public List<TeacherTO> list(Class classe){
        return entityManager.createQuery("Select t from " + classe.getSimpleName() + " t").getResultList();
    }
    
    public void persist(Object object) {
            entityManager.persist(object);
    }
}
