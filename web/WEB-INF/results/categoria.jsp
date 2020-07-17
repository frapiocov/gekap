<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${categoriaScelta.nome}"></jsp:param>
</jsp:include>
<link href="css/categoria.css" rel="stylesheet" type="text/css">

<h2>${categoriaScelta.nome}</h2>
<section class="sezione_prodotti">
    <c:forEach items="${prodottiCategoria}" var="prodotto">
            <div class="prodotti">
                <h1>${prodotto.nome}</h1>
                <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                    <img  class="imm_prod_index" src="images/${prodotto.codice}.jpg">
                </a>
                <p>GENERE: ${prodotto.genere}<br>
                    DURATA: ${prodotto.durata} min</p>
            </div>
    </c:forEach>
    <c:if test="${empty prodottiCategoria}">
        <div style="padding: 10px">
            <i class="material-icons md-light md-48">sentiment_very_dissatisfied</i>
            <h3>Nessun prodotto conforme alla ricerca</h3>
        </div>
    </c:if>
</section>

<%@include file="footer.html" %>
