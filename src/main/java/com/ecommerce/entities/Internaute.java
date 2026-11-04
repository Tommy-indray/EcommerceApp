package com.ecommerce.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internaute")
public class Internaute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "panier_id")
    private Panier panier;

    @ManyToMany
    @JoinTable(
            name = "internaute_vitrine",
            joinColumns = @JoinColumn(name = "internaute_id"),
            inverseJoinColumns = @JoinColumn(name = "vitrine_id")
    )
    private List<Vitrine> vitrines = new ArrayList<>();

    public Internaute() {}
    public Internaute(String nom, String email, String motDePasse) {
        this.nom = nom; this.email = email; this.motDePasse = motDePasse;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
    public Panier getPanier() { return panier; }
    public void setPanier(Panier panier) { this.panier = panier; }
    public List<Vitrine> getVitrines() { return vitrines; }
    public void setVitrines(List<Vitrine> vitrines) { this.vitrines = vitrines; }
}