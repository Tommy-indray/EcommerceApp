package com.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "ligne_panier")
public class LignePanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantite;
    private double prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "panier_id")
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
    public double getPrixUnitaire() { return prixUnitaire; }
    public void setPrixUnitaire(double prixUnitaire) { this.prixUnitaire = prixUnitaire; }
    public Panier getPanier() { return panier; }
    public void setPanier(Panier panier) { this.panier = panier; }
    public Produit getProduit() { return produit; }
    public void setProduit(Produit produit) { this.produit = produit; }
}