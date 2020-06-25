<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="HomePage"></jsp:param>
</jsp:include>

<div id="sezione_prodotti">
    <c:forEach items="${prodotti}" var="prodotto">
        <div id="prodotti">
            <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                <img  class="imm_prod_index" src="images/${prodotto.listaImmagini}">
            </a>
        </div>
    </c:forEach>
</div>

<%@include file="footer.html" %>