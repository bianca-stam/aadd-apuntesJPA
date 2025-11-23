package com.arquitecturajava;

import com.arquitecturajava.model.Persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Prueba1JPA {
	
	 public static void main(String[] args) {
        Persona yo = new Persona("Pedro", 25);
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadPersonas");
        EntityManager em = emf.createEntityManager();
        // Crear la tabla en la base de datos: 
        try {
            em.getTransaction().begin();
            em.persist(yo);
            em.getTransaction().commit();
            System.out.println("Todo ha ido bien.");
        } catch (Exception e) {
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error!");
        } finally {
            em.close();
            emf.close();
        }
	   }
	 
	 // mysql adat1 -u dam2 -passd asdf.1234 
}
