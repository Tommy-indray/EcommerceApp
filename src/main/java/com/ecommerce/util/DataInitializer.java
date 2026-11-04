package com.ecommerce.util;

import com.ecommerce.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DataInitializer {

    public static void init() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(p) FROM Produit p", Long.class).getSingleResult();
            if (count > 0) {
                System.out.println("✅ Données déjà présentes, initialisation ignorée.");
                return;
            }

            EntityTransaction tx = em.getTransaction();
            tx.begin();

            // ===== PRODUITS =====
            Produit p1 = new Produit("Tajine en terre cuite", 250.00, 30);
            Produit p2 = new Produit("Théière marocaine", 180.50, 50);
            Produit p3 = new Produit("Tapis berbère", 1200.00, 15);
            Produit p4 = new Produit("Babouches en cuir", 320.00, 40);
            Produit p5 = new Produit("Lampe en laiton", 450.00, 20);
            Produit p6 = new Produit("Argan oil 100ml", 95.00, 100);
            Produit p7 = new Produit("Djellaba traditionnelle", 650.00, 25);
            Produit p8 = new Produit("Tableau en cuivre", 380.00, 10);
            em.persist(p1); em.persist(p2); em.persist(p3); em.persist(p4);
            em.persist(p5); em.persist(p6); em.persist(p7); em.persist(p8);

            // ===== VITRINES =====
            Vitrine v1 = new Vitrine(); v1.setNom("Artisanat Marocain"); v1.setUrl("https://artisanat.ma");
            Vitrine v2 = new Vitrine(); v2.setNom("Souk de Marrakech"); v2.setUrl("https://souk-marrakech.ma");
            Vitrine v3 = new Vitrine(); v3.setNom("Tapis Berbères"); v3.setUrl("https://tapis-berberes.ma");
            Vitrine v4 = new Vitrine(); v4.setNom("Épices du Maroc"); v4.setUrl("https://epices-maroc.ma");
            em.persist(v1); em.persist(v2); em.persist(v3); em.persist(v4);

            // ===== INTERNAUTES =====
            Internaute i1 = new Internaute("Youssef El Amrani", "youssef.elamrani@gmail.com", "youssef123");
            Internaute i2 = new Internaute("Fatima Zahra Benali", "fatima.benali@yahoo.fr", "fatima456");
            Internaute i3 = new Internaute("Ahmed Tazi", "ahmed.tazi@gmail.com", "ahmed789");
            Internaute i4 = new Internaute("Salma Idrissi", "salma.idrissi@hotmail.com", "salma321");
            Internaute i5 = new Internaute("Karim Bennani", "karim.bennani@gmail.com", "karim654");

            i1.getVitrines().add(v1); i1.getVitrines().add(v3);
            i2.getVitrines().add(v2); i2.getVitrines().add(v4);
            i3.getVitrines().add(v1); i3.getVitrines().add(v2); i3.getVitrines().add(v4);
            i4.getVitrines().add(v3);
            i5.getVitrines().add(v1); i5.getVitrines().add(v4);

            // ===== PANIERS =====
            creerPanierAvecLignes(em, i1, new Object[][]{
                    {p1, 1}, {p6, 2}, {p4, 1}
            });
            creerPanierAvecLignes(em, i2, new Object[][]{
                    {p2, 2}, {p3, 1}
            });
            creerPanierAvecLignes(em, i3, new Object[][]{
                    {p5, 1}, {p8, 1}, {p7, 1}
            });
            creerPanierAvecLignes(em, i4, new Object[][]{
                    {p6, 3}
            });

            // i5 n'a pas de panier actif pour tester le cas "Aucun"

            em.persist(i1); em.persist(i2); em.persist(i3); em.persist(i4); em.persist(i5);

            tx.commit();
            System.out.println("✅ Jeu de données marocain inséré avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    private static void creerPanierAvecLignes(EntityManager em, Internaute i, Object[][] produits) {
        Panier panier = new Panier();
        double total = 0;
        for (Object[] entry : produits) {
            Produit p = (Produit) entry[0];
            int q = (Integer) entry[1];
            LignePanier ligne = new LignePanier();
            ligne.setProduit(p);
            ligne.setQuantite(q);
            ligne.setPrixUnitaire(p.getPrix());
            ligne.setPanier(panier);
            panier.getLignes().add(ligne);
            total += p.getPrix() * q;
        }
        panier.setTotal(total);
        i.setPanier(panier);
        em.persist(panier);
    }
}