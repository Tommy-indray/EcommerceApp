<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Internaute</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>${empty internaute ? 'Ajouter' : 'Modifier'} un internaute</h2>
            <form method="post" action="InternauteServlet">
                <input type="hidden" name="action" value="${empty internaute ? 'ajouter' : 'modifier'}"/>
                <c:if test="${not empty internaute}">
                    <input type="hidden" name="id" value="${internaute.id}"/>
                </c:if>

                <label>Nom</label>
                <input type="text" name="nom" value="${internaute.nom}" required/>

                <label>Email</label>
                <input type="email" name="email" value="${internaute.email}" required/>

                <label>Mot de passe</label>
                <input type="password" name="motDePasse" value="${internaute.motDePasse}" required/>

                <button type="submit" class="btn">Enregistrer</button>
                <a href="InternauteServlet?action=lister" class="btn btn-secondary">Annuler</a>
            </form>
        </div>
    </div>
</body>
</html>