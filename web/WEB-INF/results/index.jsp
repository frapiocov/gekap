<%@ page import="model.Prodotto" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="HomePage"></jsp:param>
</jsp:include>

<section id="sezione_prodotti">
    <% for(int i=0;i<6;i++) { %>
        <fieldset>
            <figure onload="prodottiRandom(${prodotti})">
                <img src="" alt="copertina">
                <figcaption>""</figcaption>
            </figure>
        </fieldset>
    <% } %>
</section>
<%@include file="footer.html" %>