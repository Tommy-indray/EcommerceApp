<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Internautes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>Liste des internautes</h2>
            <a href="InternauteServlet?action=editer" class="btn">➕ Ajouter un internaute</a>
            <table>
                <thead>
                    <tr><th>ID</th><th>Nom</th><th>Email</th><th>Panier</th><th>Actions</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="i" items="${internautes}">
                        <tr>
                            <td>${i.id}</td>
                            <td>${i.nom}</td>
                            <td>${i.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${i.panier != null}">
                                        <fmt:formatNumber value="${i.panier.total}" pattern="#,##0.00" /> MAD
                                    </c:when>
                                    <c:otherwise>Aucun</c:otherwise>
                                </c:choose>
                            </td>
                            <td class="actions">
                                <a href="InternauteServlet?action=editer&id=${i.id}" class="btn btn-small">Modifier</a>
                                <a href="InternauteServlet?action=supprimer&id=${i.id}"
                                   class="btn btn-small btn-danger"
                                   onclick="return confirm('Supprimer cet internaute ?')">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>