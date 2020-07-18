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
            <a href="#">
                <img src="images/${prodQuant.prodotto.listaImmagini}" alt="immagine prodotto">
            </a>

            <h3>${prodQuant.prodotto.nome}</h3>
            <h5>QUANTITA': ${prodQuant.quantita}</h5>

            <c:choose>
                <c:when test="${prodQuant}==1">
                    <h5>PREZZO: ${prodQuant.prodotto.prezzoEuro} &euro;</h5>
                </c:when>
                <c:otherwise>
                    <h5>PREZZO SINGOLO: ${prodQuant.prodotto.prezzoEuro} &euro; </h5>
                    <h5>Prezzo totale: ${prodQuant.totEuro} &euro; </h5>
                </c:otherwise>
            </c:choose>

            <form action="#" method="post">
                <input type="hidden" name="codiceP" value="${prodQuant.prodotto.codice}">
                <input type="hidden" name="num" value="0">
                <input type="submit" value="Rimuovi">
            </form>
        </div>
    </c:forEach>

    <c:if test="${empty carrello.prodotti}">
        <h3>Nessun prodotto nel carrello.</h3>
    </c:if>
</section>

<section>
    <c:if test="${not empty carrello.prodotti}">
        <h2>TOTALE: ${carrello.totEuro} &euro; </h2>

        <form action="#">
            <input type="submit" value="Acquista">
        </form>
    </c:if>
</section>

<%@include file="footer.html" %>