package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "panier")
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    private double total;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignes = new ArrayList<>();

    public Panier() { this.dateCreation = new Date(); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Date getDateCreation() { return dateCreation; }
    public void setDateCreation(Date dateCreation) { this.dateCreation = dateCreation; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public List<LignePanier> getLignes() { return lignes; }
    public void setLignes(List<LignePanier> lignes) { this.lignes = lignes; }
}