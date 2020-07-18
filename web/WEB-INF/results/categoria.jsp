<jsp:useBean id="categoriaScelta" scope="request" type="model.Categoria"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${categoriaScelta.nome}"/>
</jsp:include>

<section class="sezione_prodotti">

    <h1 style="padding-left: 30px">${categoriaScelta.nome}</h1>

    <jsp:useBean id="prodottiCategoria" scope="request" type="java.util.List"/>
    <c:forEach items="${prodottiCategoria}" var="prodotto">
        <div class="prodotti">
            <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                <img alt="immagine prodotto" class="imm_prod_index" src="images/${prodotto.listaImmagini}">
            </a>
            <p style="text-align: left">
                GENERE: ${prodotto.genere}<br>
                DURATA: ${prodotto.durata} min<br>
                PREZZO: ${prodotto.prezzoEuro} &euro;
            </p>
        </div>
    </c:forEach>

    <c:if test="${empty prodottiCategoria}">
        <div style="padding: 10px">
            <i class="material-icons md-light md-48">sentiment_very_dissatisfied</i>
            <h3>Nessun prodotto conforme alla ricerca.</h3>
        </div>
    </c:if>

</section>

<%@include file="footer.html" %>
