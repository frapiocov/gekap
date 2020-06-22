<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="HomePage"></jsp:param>
</jsp:include>

<section id="sezione_prodotti">
    <c:forEach items="${prodotti}" var="prodotto">
        <fieldset id="prodotti">
            <h4><c:out value="${prodotto.nome}"/></h4>
            <h3><c:out value="${prodotto.prezzo}"/></h3>
        </fieldset>
    </c:forEach>
</section>

<%@include file="footer.html" %>