package com.ecommerce.servlets;

import com.ecommerce.entities.Vitrine;
import com.ecommerce.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/VitrineServlet")
public class VitrineServlet extends HttpServlet {
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
                    String url = request.getParameter("url");
                    tx.begin();
                    Vitrine v = new Vitrine();
                    v.setNom(nom);
                    v.setUrl(url);
                    em.persist(v);
                    tx.commit();
                    response.sendRedirect("VitrineServlet?action=lister");
                    return;
                }
                case "supprimer": {
                    Long id = Long.parseLong(request.getParameter("id"));
                    tx.begin();
                    Vitrine v = em.find(Vitrine.class, id);
                    if (v != null) em.remove(v);
                    tx.commit();
                    response.sendRedirect("VitrineServlet?action=lister");
                    return;
                }
                case "modifier": {
                    Long id = Long.parseLong(request.getParameter("id"));
                    tx.begin();
                    Vitrine v = em.find(Vitrine.class, id);
                    if (v != null) {
                        v.setNom(request.getParameter("nom"));
                        v.setUrl(request.getParameter("url"));
                    }
                    tx.commit();
                    response.sendRedirect("VitrineServlet?action=lister");
                    return;
                }
                case "editer": {
                    // ✅ CORRECTION
                    String idParam = request.getParameter("id");
                    if (idParam != null && !idParam.isEmpty()) {
                        Long id = Long.parseLong(idParam);
                        Vitrine v = em.find(Vitrine.class, id);
                        request.setAttribute("vitrine", v);
                    }
                    request.getRequestDispatcher("/WEB-INF/vitrine-form.jsp").forward(request, response);
                    return;
                }
                case "lister":
                default: {
                    List<Vitrine> vitrines = em.createQuery("SELECT v FROM Vitrine v", Vitrine.class).getResultList();
                    request.setAttribute("vitrines", vitrines);
                    request.getRequestDispatcher("/WEB-INF/vitrine-list.jsp").forward(request, response);
                    return;
                }
            }
        } finally {
            em.close();
        }
    }
}