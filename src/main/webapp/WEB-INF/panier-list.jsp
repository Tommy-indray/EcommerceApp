<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Panier</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>Lignes de panier</h2>
            <a href="PanierServlet?action=editer" class="btn">➕ Ajouter une ligne</a>
            <table>
                <thead>
                    <tr><th>ID</th><th>Internaute</th><th>Produit</th><th>Quantité</th><th>Prix unitaire</th><th>Actions</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="l" items="${lignes}">
                        <tr>
                            <td>${l.id}</td>
                            <td>${l.panier != null ? l.panier.id : ''}</td>
                            <td>${l.produit != null ? l.produit.nom : ''}</td>
                            <td>${l.quantite}</td>
                            <td><fmt:formatNumber value="${l.prixUnitaire}" pattern="#,##0.00" /> MAD</td>
                            <td class="actions">
                                <a href="PanierServlet?action=supprimer&id=${l.id}"
                                   class="btn btn-small btn-danger"
                                   onclick="return confirm('Supprimer cette ligne ?')">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>