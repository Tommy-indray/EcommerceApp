package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vitrine")
public class Vitrine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String url;

    @ManyToMany(mappedBy = "vitrines")
    private List<Internaute> internautes = new ArrayList<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public List<Internaute> getInternautes() { return internautes; }
    public void setInternautes(List<Internaute> internautes) { this.internautes = internautes; }
}