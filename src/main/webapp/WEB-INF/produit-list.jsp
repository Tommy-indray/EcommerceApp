<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des produits</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>Liste des produits</h2>
            <a href="ProduitServlet?action=editer" class="btn">➕ Ajouter un produit</a>
            <table>
                <thead>
                    <tr><th>ID</th><th>Nom</th><th>Prix</th><th>Stock</th><th>Actions</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="p" items="${produits}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.nom}</td>
                            <td><fmt:formatNumber value="${p.prix}" pattern="#,##0.00" /> MAD</td>
                            <td>${p.stock}</td>
                            <td class="actions">
                                <a href="ProduitServlet?action=editer&id=${p.id}" class="btn btn-small">Modifier</a>
                                <a href="ProduitServlet?action=supprimer&id=${p.id}"
                                   class="btn btn-small btn-danger"
                                   onclick="return confirm('Supprimer ce produit ?')">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>