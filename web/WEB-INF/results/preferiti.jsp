<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Preferiti"/>
</jsp:include>

<div style="padding: 20px; margin-top: 20px">
    <h1>WISHLIST</h1>
    <i class="material-icons md-48 md-light">favorite</i>
    <h3>Tutti i prodotti che hai intenzione di acquistare o a cui semplicemente sei interessato.</h3>
    <c:forEach items="${preferiti}" var="prodotto">
        <div class="prodotti">
            <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                <img class="imm_prod_index" src="images/${prodotto.listaImmagini}" alt="immagine prodotto">
            </a>
            <form method="get" action="servlet_rimuovi_preferiti">
                <input type="hidden" name="codiceProdotto" value="${prodotto.codice}">
                <input type="submit" class="bottone" value="Rimuovi">
            </form>
        </div>
    </c:forEach>

    <c:if test="${not empty preferiti}">
        <form method="get" action="servlet_rimuovi_preferiti">
            <input type="submit" class="bottone" name="svuota" value="Svuota">
        </form>
    </c:if>

    <c:if test="${empty preferiti}">
        <div style="padding: 10px">
            <h3>Nessun prodotto nella WishList.</h3>
            <a href="."><button class="bottone">Torna alla HOME</button></a>
        </div>
    </c:if>
</div>
<div style="clear: both"></div>

<%@include file="footer.html" %>
