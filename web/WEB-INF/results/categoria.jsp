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
                <img class="im_prodotto" src="images/${prodotto.listaImmagini}">
                <p>GENERE: ${prodotto.genere}<br>
                    DURATA: ${prodotto.durata} min</p>
            </div>
    </c:forEach>
</section>

<%@include file="footer.html" %>
