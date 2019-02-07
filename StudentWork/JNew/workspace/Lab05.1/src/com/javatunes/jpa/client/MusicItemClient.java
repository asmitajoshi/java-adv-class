/*
 * This code is sample code, provided as-is, and we make no
 * warranties as to its correctness or suitability for
 * any purpose.
 *
 * We hope that it's useful to you.  Enjoy.
 * Copyright LearningPatterns Inc.
 */
package com.javatunes.jpa.client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.javatunes.jpa.MusicItem;

public class MusicItemClient {

  public static void main(String[] args) {
    // create EMF
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("javatunes");
    // create EM
    EntityManager em = emf.createEntityManager();
    
    // NOTE: transactions not strictly needed unless doing DB updates
    try {
      em.getTransaction().begin();
      
      // valid ids range from 1-21
      MusicItem item = em.find(MusicItem.class, 19L);
      System.out.println(item);
      
      em.getTransaction().commit();
    }
    catch (Exception e) {
      e.printStackTrace();
      em.getTransaction().rollback();
    }
    finally {
      if (em != null) em.close();
      if (emf != null) emf.close();
    }
  }
}