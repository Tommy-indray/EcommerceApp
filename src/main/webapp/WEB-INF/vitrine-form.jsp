<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Vitrine</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>${empty vitrine ? 'Ajouter' : 'Modifier'} une vitrine</h2>
            <form method="post" action="VitrineServlet">
                <input type="hidden" name="action" value="${empty vitrine ? 'ajouter' : 'modifier'}"/>
                <c:if test="${not empty vitrine}">
                    <input type="hidden" name="id" value="${vitrine.id}"/>
                </c:if>

                <label>Nom</label>
                <input type="text" name="nom" value="${vitrine.nom}" required/>

                <label>URL</label>
                <input type="text" name="url" value="${vitrine.url}" required/>

                <button type="submit" class="btn">Enregistrer</button>
                <a href="VitrineServlet?action=lister" class="btn btn-secondary">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>