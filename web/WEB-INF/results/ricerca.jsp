<jsp:useBean id="parolaCercata" scope="request" type="java.lang.String"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${parolaCercata}"/>
</jsp:include>

<div style="padding-top: 40px; padding-left: 10px"><h3>Parola cercata: ${parolaCercata}</h3></div>
<div class="sezione_prodotti">
    <jsp:useBean id="prodotti" scope="request" type="java.util.List"/>
    <c:forEach items="${prodotti}" var="prodotto">
        <div class="prodotti">
            <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                <img class="imm_prod_index" src="images/${prodotto.listaImmagini}" alt="immagine prodotto">
            </a>
        </div>
    </c:forEach>

    <c:if test="${empty prodotti}">
        <div style="padding: 10px">
            <i class="material-icons md-light md-48">sentiment_very_dissatisfied</i>
            <h3>Nessun prodotto conforme alla ricerca</h3>
        </div>
    </c:if>
</div>


<%@include file="footer.html" %>