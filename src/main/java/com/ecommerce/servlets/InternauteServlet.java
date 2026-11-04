package com.ecommerce.servlets;

import com.ecommerce.entities.Internaute;
import com.ecommerce.entities.Panier;
import com.ecommerce.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/InternauteServlet")
public class InternauteServlet extends HttpServlet {
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
                    String email = request.getParameter("email");
                    String motDePasse = request.getParameter("motDePasse");
                    tx.begin();
                    Internaute i = new Internaute(nom, email, motDePasse);
                    i.setPanier(new Panier());
                    em.persist(i);
                    tx.commit();
                    response.sendRedirect("InternauteServlet?action=lister");
                    return;
                }
                case "supprimer": {
                    Long id = Long.parseLong(request.getParameter("id"));
                    tx.begin();
                    Internaute i = em.find(Internaute.class, id);
                    if (i != null) em.remove(i);
                    tx.commit();
                    response.sendRedirect("InternauteServlet?action=lister");
                    return;
                }
                case "modifier": {
                    Long id = Long.parseLong(request.getParameter("id"));
                    tx.begin();
                    Internaute i = em.find(Internaute.class, id);
                    if (i != null) {
                        i.setNom(request.getParameter("nom"));
                        i.setEmail(request.getParameter("email"));
                        i.setMotDePasse(request.getParameter("motDePasse"));
                    }
                    tx.commit();
                    response.sendRedirect("InternauteServlet?action=lister");
                    return;
                }
                case "editer": {
                    // ✅ CORRECTION
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        Long id = Long.parseLong(idParam);
                        Internaute i = em.find(Internaute.class, id);
                        request.setAttribute("internaute", i);
                    }
                    request.getRequestDispatcher("/WEB-INF/internaute-form.jsp").forward(request, response);
                    return;
                }
                case "lister":
                default: {
                    List<Internaute> internautes = em.createQuery("SELECT i FROM Internaute i", Internaute.class).getResultList();
                    request.setAttribute("internautes", internautes);
                    request.getRequestDispatcher("/WEB-INF/internaute-list.jsp").forward(request, response);
                    return;
                }
            }
        } finally {
            em.close();
        }
    }
}