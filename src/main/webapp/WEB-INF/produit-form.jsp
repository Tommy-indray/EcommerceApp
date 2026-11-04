<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Produit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>${empty produit ? 'Ajouter' : 'Modifier'} un produit</h2>
            <form method="post" action="ProduitServlet">
                <input type="hidden" name="action" value="${empty produit ? 'ajouter' : 'modifier'}"/>
                <c:if test="${not empty produit}">
                    <input type="hidden" name="id" value="${produit.id}"/>
                </c:if>
                <label>Nom</label>
                <input type="text" name="nom" value="${produit.nom}" required/>
                <label>Prix (MAD)</label>
                <input type="number" step="0.01" name="prix" value="${produit.prix}" required/>
                <label>Stock</label>
                <input type="number" name="stock" value="${produit.stock}" required/>
                <button type="submit" class="btn">Enregistrer</button>
                <a href="ProduitServlet?action=lister" class="btn btn-secondary">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>