<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Carrello"/>
</jsp:include>

<div id="carrello_contenitore">
    <h1>Carrello <i class="material-icons md-48 md-light">shopping_cart</i></h1>

    <c:if test="${empty carrello.prodotti}">
        <div id="messaggio">
            <h3>Nessun prodotto nel carrello.</h3>
        </div>
    </c:if>

    <c:if test="${not empty carrello.prodotti}">
        <div id="acquisto">
            <h2><b>TOTALE:</b>${carrello.totEuro} &euro; </h2>

            <form action="#">
                <input class="bottone" type="submit" value="Acquista">
            </form>
        </div>
    </c:if>
    
    <jsp:useBean id="carrello" scope="session" type="model.Carrello"/>
    <c:forEach items="${carrello.prodotti}" var="prodQuant">
        <div id="prodotti_carrello">
            <a href="servlet_prodotto?codice=${prodQuant.prodotto.codice}">
                <img class="imm_prod_ev" src="images/${prodQuant.prodotto.listaImmagini}" alt="immagine prodotto">
            </a>

            <h2>${prodQuant.prodotto.nome}</h2>
            <h5>QUANTITA': ${prodQuant.quantita}<br>
                PREZZO SINGOLO: ${prodQuant.prodotto.prezzoEuro} &euro;<br>
                PREZZO TOTALE: ${prodQuant.totEuro} &euro; </h5>
            <form action="servlet_carrello" method="post">
                <input type="hidden" name="codice_prodotto" value="${prodQuant.prodotto.codice}">
                <input type="hidden" name="numRim" value="0">
                <input type="submit" class="bottone" value="Rimuovi">
            </form>
        </div>
    </c:forEach>
</div>

<div style="clear: both"></div>

<%@include file="footer.html" %>