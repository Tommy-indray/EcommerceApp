package com.ecommerce.servlets;

import com.ecommerce.entities.Internaute;
import com.ecommerce.entities.LignePanier;
import com.ecommerce.entities.Panier;
import com.ecommerce.entities.Produit;
import com.ecommerce.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/PanierServlet")
public class PanierServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "lister";

        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            switch (action) {
                case "ajouter": {
                    Long internauteId = Long.parseLong(request.getParameter("internauteId"));
                    Long produitId = Long.parseLong(request.getParameter("produitId"));
                    int quantite = Integer.parseInt(request.getParameter("quantite"));

                    tx.begin();
                    Internaute internaute = em.find(Internaute.class, internauteId);
                    Produit produit = em.find(Produit.class, produitId);

                    if (internaute != null && produit != null) {
                        Panier panier = internaute.getPanier();
                        if (panier == null) {
                            panier = new Panier();
                            internaute.setPanier(panier);
                            em.persist(panier);
                        }
                        LignePanier ligne = new LignePanier();
                        ligne.setQuantite(quantite);
                        ligne.setPrixUnitaire(produit.getPrix());
                        ligne.setProduit(produit);
                        ligne.setPanier(panier);
                        panier.getLignes().add(ligne);
                        panier.setTotal(panier.getTotal() + quantite * produit.getPrix());
                        em.persist(ligne);
                    }
                    tx.commit();
                    response.sendRedirect("PanierServlet?action=lister");
                    return;
                }
                case "supprimer": {
                    Long id = Long.parseLong(request.getParameter("id"));
                    tx.begin();
                    LignePanier ligne = em.find(LignePanier.class, id);
                    if (ligne != null) {
                        Panier panier = ligne.getPanier();
                        panier.setTotal(panier.getTotal() - ligne.getQuantite() * ligne.getPrixUnitaire());
                        em.remove(ligne);
                    }
                    tx.commit();
                    response.sendRedirect("PanierServlet?action=lister");
                    return;
                }
                case "editer": {
                    // ✅ CORRECTION
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        Long id = Long.parseLong(idParam);
                        LignePanier ligne = em.find(LignePanier.class, id);
                        request.setAttribute("ligne", ligne);
                    }
                    List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
                    List<Internaute> internautes = em.createQuery("SELECT i FROM Internaute i", Internaute.class).getResultList();
                    request.setAttribute("produits", produits);
                    request.setAttribute("internautes", internautes);
                    request.getRequestDispatcher("/WEB-INF/panier-form.jsp").forward(request, response);
                    return;
                }
                case "lister":
                default: {
                    List<LignePanier> lignes = em.createQuery("SELECT l FROM LignePanier l", LignePanier.class).getResultList();
                    request.setAttribute("lignes", lignes);
                    request.getRequestDispatcher("/WEB-INF/panier-list.jsp").forward(request, response);
                    return;
                }
            }
        } finally {
            em.close();
        }
    }
}