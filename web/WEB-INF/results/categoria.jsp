<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${categoria.nome}"></jsp:param>
</jsp:include>
<link href="css/categoria.css" rel="stylesheet" type="text/css">

<section>
    <c:forEach items="${prodotti}" var="prodotto">
        <fieldset>
            <div>
                <h1>${prodotto.nome}</h1>
                <img class="im_prodotto" src="images/${prodotto.listaImmagini}">
                <p>GENERE: ${prodotto.genere}<br>
                    DURATA: ${prodotto.durata} min</p>
            </div>
        </fieldset>
    </c:forEach>
</section>

<%@include file="footer.html" %>
