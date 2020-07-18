<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>

<section>
    <h1>Carrello</h1>

    <jsp:useBean id="carrello" scope="session" type="model.Carrello"/>

    <c:forEach items="${carrello.prodotti}" var="prodQuant">
        <div>
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

            <form action="#" method="post">
                <input type="hidden" name="codiceP" value="${prodQuant.prodotto.codice}">
                <input type="hidden" name="num" value="0">
                <input class="bottone" type="submit" value="Rimuovi">
            </form>
        </div>
    </c:forEach>

    <c:if test="${empty carrello.prodotti}">
        <h3 style="padding: 50px">Nessun prodotto nel carrello.</h3>
    </c:if>
</section>

<section>
    <c:if test="${not empty carrello.prodotti}">
        <h2>TOTALE: ${carrello.totEuro} &euro; </h2>

        <form action="#">
            <input class="bottone" type="submit" value="Acquista">
        </form>
    </c:if>
</section>

<div style="clear: both"></div>

<%@include file="footer.html" %>