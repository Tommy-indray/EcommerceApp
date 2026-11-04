package com.ecommerce.test;

import com.ecommerce.entities.*;
import jakarta.persistence.*;

public class TestJPA {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Produit p = new Produit("Laptop", 899.99, 10);
        em.persist(p);

        Internaute i = new Internaute("Alice", "alice@mail.com", "1234");
        Panier panier = new Panier();
        i.setPanier(panier);
        panier.setTotal(899.99);
        em.persist(i);

        em.getTransaction().commit();

        em.close();
        emf.close();
        System.out.println("✅ Tables générées et données insérées !");
    }
}