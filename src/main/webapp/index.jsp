<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ecommerce App</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/navbar.jspf" %>
    <div class="container">
        <div class="card">
            <h2>Bienvenue sur EcommerceApp</h2>
            <p>Gérez vos produits, internautes, paniers et vitrines.</p>
            <div class="home-grid">
                <a href="${pageContext.request.contextPath}/ProduitServlet?action=lister">
                    <span class="icon">📦</span>Produits
                </a>
                <a href="${pageContext.request.contextPath}/InternauteServlet?action=lister">
                    <span class="icon">👤</span>Internautes
                </a>
                <a href="${pageContext.request.contextPath}/PanierServlet?action=lister">
                    <span class="icon">🛒</span>Paniers
                </a>
                <a href="${pageContext.request.contextPath}/VitrineServlet?action=lister">
                    <span class="icon">🏬</span>Vitrines
                </a>
            </div>
        </div>
    </div>
</body>
</html>