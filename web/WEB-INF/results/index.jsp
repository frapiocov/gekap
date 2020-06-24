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

<section id="sezione_prodotti">
    <c:forEach items="${prodotti}" var="prodotto">
        <fieldset id="prodotti">
            <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>"><c:out value="${prodotto.nome}" /></a>
            <h3>prezzo</h3>
        </fieldset>
    </c:forEach>
</section>

<%@include file="footer.html" %>