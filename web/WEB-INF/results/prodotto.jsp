<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.nome}"></jsp:param>
</jsp:include>

    <section>
        <fieldset class="contenuto_prodotto">
            <div class="colonna">
                <h1>${prodotto.nome}</h1>
                <img class="im_prodotto" src="images/${prodotto.listaImmagini}">
                <p>GENERE: ${prodotto.genere}<br>
                    ANNO: ${prodotto.anno}<br>
                    LINGUA: ${prodotto.lingua}<br>
                    PREZZO: cap e cazz &euro;<br>
                    DURATA: ${prodotto.durata} min</p>
            </div>
            <div class="colonna">
                    <p>${prodotto.trama}</p>
            </div>
            <div>
                <c:forEach items="${cast}" var="attore">
                    <h4>${attore.nome}</h4>
                    <h5>${attore.ruolo}</h5>
                </c:forEach>
            </div>
            <div>
                <iframe width="560" height="315" src="${prodotto.trailer}" frameborder="0" allowfullscreen autoplay></iframe>
            </div>
            <div style="clear:both;"></div>
        </fieldset>
    </section>

<%@include file="footer.html" %>