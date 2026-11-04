package com.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double prix;
    private int stock;

    public Produit() {}
    public Produit(String nom, double prix, int stock) {
        this.nom = nom; this.prix = prix; this.stock = stock;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}