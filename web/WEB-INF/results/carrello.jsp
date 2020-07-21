<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>

<div id="carrello_contenitore">
    <h1>Carrello <i class="material-icons md-48 md-light">shopping_cart</i></h1>

    <jsp:useBean id="carrello" scope="session" type="model.Carrello"/>
    <div>
        <c:forEach items="${carrello.prodotti}" var="prodQuant">
            <a href="servlet_prodotto?codice=${prodQuant.prodotto.codice}">
                <img class="imm_prod_ev" src="images/${prodQuant.prodotto.listaImmagini}" alt="immagine prodotto">
            </a>

            <h3>${prodQuant.prodotto.nome}</h3>
            <h5>QUANTITA': ${prodQuant.quantita}<br>

            <c:choose>
                <c:when test="${prodQuant.quantita == 1}">
                    PREZZO: ${prodQuant.prodotto.prezzoEuro} &euro;<br>
                </c:when>
                <c:otherwise>
                        PREZZO SINGOLO: ${prodQuant.prodotto.prezzoEuro} &euro;<br>
                        PREZZO TOTALE: ${prodQuant.totEuro} &euro; </h5>
                </c:otherwise>
            </c:choose>

            <form action="servlet_carrello" method="post">
                <input type="hidden" name="codice_prodotto" value="${prodQuant.prodotto.codice}">
                <input type="hidden" name="numRim" value="0">
                <input  type="submit" class="bottone" value="Rimuovi">
            </form>
    </c:forEach>
    </div>
    <div>
    <c:if test="${empty carrello.prodotti}">
        <h3 style="padding: 50px">Nessun prodotto nel carrello.</h3>
    </c:if>

        <c:if test="${not empty carrello.prodotti}">
            <h2>TOTALE: ${carrello.totEuro} &euro; </h2>

            <form action="#">
                <input class="bottone" type="submit" value="Acquista">
            </form>
        </c:if>
    </div>

</div>

<div style="clear: both"></div>

<%@include file="footer.html" %>