package com.ecommerce.servlets;

import com.ecommerce.entities.Produit;
import com.ecommerce.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProduitServlet")
public class ProduitServlet extends HttpServlet {
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
                    String nom = request.getParameter("nom");
                    double prix = Double.parseDouble(request.getParameter("prix"));
                    int stock = Integer.parseInt(request.getParameter("stock"));
                    tx.begin();
                    em.persist(new Produit(nom, prix, stock));
                    tx.commit();
                    response.sendRedirect("ProduitServlet?action=lister");
                    return;
                }
                case "supprimer": {
                    Long id = Long.parseLong(request.getParameter("id"));
                    tx.begin();
                    Produit p = em.find(Produit.class, id);
                    if (p != null) em.remove(p);
                    tx.commit();
                    response.sendRedirect("ProduitServlet?action=lister");
                    return;
                }
                case "modifier": {
                    Long id = Long.parseLong(request.getParameter("id"));
                    tx.begin();
                    Produit p = em.find(Produit.class, id);
                    if (p != null) {
                        p.setNom(request.getParameter("nom"));
                        p.setPrix(Double.parseDouble(request.getParameter("prix")));
                        p.setStock(Integer.parseInt(request.getParameter("stock")));
                    }
                    tx.commit();
                    response.sendRedirect("ProduitServlet?action=lister");
                    return;
                }
                case "editer": {
                    // ✅ CORRECTION : id peut être null (ajout) ou non-null (édition)
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        Long id = Long.parseLong(idParam);
                        Produit p = em.find(Produit.class, id);
                        request.setAttribute("produit", p);
                    }
                    request.getRequestDispatcher("/WEB-INF/produit-form.jsp").forward(request, response);
                    return;
                }
                case "lister":
                default: {
                    List<Produit> produits = em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
                    request.setAttribute("produits", produits);
                    request.getRequestDispatcher("/WEB-INF/produit-list.jsp").forward(request, response);
                    return;
                }
            }
        } finally {
            em.close();
        }
    }
}