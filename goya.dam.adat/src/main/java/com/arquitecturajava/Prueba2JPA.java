package com.arquitecturajava;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import com.arquitecturajava.model.Persona;

import java.util.List;

public class Prueba2JPA {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("UnidadPersonas");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Consultar la tabla completa:
            System.out.println("Leyendo tabla completa...");
            List<Persona> personas = em
                    .createQuery("SELECT p FROM Persona p", Persona.class)
                    .getResultList();

            for (Persona p : personas) {
                System.out.println("Nombre: " + p.getNombre() + ", Edad: " + p.getEdad());
            }

            
            // Consultar un registro en particular:
            System.out.println("\nLeyendo un registro en concreto...");
            Persona p1 = em.find(Persona.class, "Pedro");
            System.out.println("Pedro: Nombre: " + p1.getNombre()+ " - Edad: " + p1.getEdad());
            
            // Actualizar un registro:
            p1.setEdad(26);
            em.merge(p1);
            
            System.out.println("\nEdad de Pedro actualizada a " + p1.getEdad() + ": ");
            for (Persona p : personas) {
                System.out.println("Nombre: " + p.getNombre() + ", Edad: " + p.getEdad());
            }
            
            // Borrar un registro
            // Para que se muestren los cambios, puedes hacer otra lista con el registro eliminado, o borrarlo de la lista ya creada
            System.out.println("\nBorrando..." + p1.getNombre());
            em.remove(p1);
            
            List<Persona> personas2 = em
                    .createQuery("SELECT p FROM Persona p", Persona.class)
                    .getResultList();

            for (Persona p : personas2) {
                System.out.println("Nombre: " + p.getNombre() + ", Edad: " + p.getEdad());
            }
            

            em.getTransaction().commit();

        } catch (Exception e) { 
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
            emf.close();
        }
        
    }
}