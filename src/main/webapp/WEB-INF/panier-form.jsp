<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter au panier</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>Ajouter une ligne de panier</h2>
            <form method="post" action="PanierServlet">
                <input type="hidden" name="action" value="ajouter"/>
                <label>Internaute</label>
                <select name="internauteId" required>
                    <c:forEach var="i" items="${internautes}">
                        <option value="${i.id}">${i.nom}</option>
                    </c:forEach>
                </select>
                <label>Produit</label>
                <select name="produitId" required>
                    <c:forEach var="p" items="${produits}">
                        <option value="${p.id}">
                            ${p.nom} (<fmt:formatNumber value="${p.prix}" pattern="#,##0.00" /> MAD)
                        </option>
                    </c:forEach>
                </select>
                <label>Quantité</label>
                <input type="number" name="quantite" value="1" min="1" required/>
                <button type="submit" class="btn">Ajouter</button>
                <a href="PanierServlet?action=lister" class="btn btn-secondary">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>