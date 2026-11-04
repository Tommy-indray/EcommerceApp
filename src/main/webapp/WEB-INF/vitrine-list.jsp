<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vitrines</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>Liste des vitrines</h2>
            <a href="VitrineServlet?action=editer" class="btn">➕ Ajouter une vitrine</a>
            <table>
                <thead>
                    <tr><th>ID</th><th>Nom</th><th>URL</th><th>Actions</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="v" items="${vitrines}">
                        <tr>
                            <td>${v.id}</td>
                            <td>${v.nom}</td>
                            <td>${v.url}</td>
                            <td class="actions">
                                <a href="VitrineServlet?action=editer&id=${v.id}" class="btn btn-small">Modifier</a>
                                <a href="VitrineServlet?action=supprimer&id=${v.id}"
                                   class="btn btn-small btn-danger"
                                   onclick="return confirm('Supprimer cette vitrine ?')">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>