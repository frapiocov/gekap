<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="HomePage"/>
</jsp:include>

<div id="evidenza">
    <div class="prodotti_evidenza">
        <h1>Film in evidenza</h1>
        <jsp:useBean id="prodotti_evidenza" scope="request" type="java.util.List"/>
        <c:forEach items="${prodotti_evidenza}" var="prodotto">
                <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                    <img class="imm_prod_ev" src="images/${prodotto.listaImmagini}" alt="immagine_prodotto">
                </a>
        </c:forEach>
        <h3>Le ultime novit&agrave; aggiunte in Catalogo</h3>
    </div>
</div>

<div class="sezione_prodotti">
    <jsp:useBean id="prodotti" scope="request" type="java.util.List"/>
    <c:forEach items="${prodotti}" var="prodotto">
        <div class="prodotti">
            <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                <img class="imm_prod_index" src="images/${prodotto.listaImmagini}" alt="immagine_prodotto">
            </a>
        </div>
    </c:forEach>
</div>

<%@include file="footer.html" %>