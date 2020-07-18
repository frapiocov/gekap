<jsp:useBean id="prodotto" scope="request" type="model.Prodotto"/>
<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${prodotto.nome}"/>
</jsp:include>

    <div id="contenuto">

            <div class="colonna_L">
                <h3>${prodotto.nome}</h3>
                <img class="im_prodotto" alt="immagine prodotto" src="images/${prodotto.listaImmagini}">
                <p>GENERE: ${prodotto.genere}<br>
                    ANNO: ${prodotto.anno}<br>
                    LINGUA: ${prodotto.lingua}<br>
                    DURATA: ${prodotto.durata} min<br>
                    PREZZO: ${prodotto.prezzoEuro} &euro;</p>

                    <form method="post" action="servlet_carrello">
                        <label for="quantity">Quantità:</label>
                        <input type="number" name="quantity" id="quantity" min="1" max="10" value="1">
                        <input type="hidden" value="${prodotto.codice}" id="codice_prodotto" name="codice_prodotto">
                        <input class="bottone" type="submit" value="Aggiungi al carrello">
                    </form>
            </div>

            <div class="colonna_R">
                    <p id="trama">${prodotto.trama}</p>

                <table>
                    <jsp:useBean id="cast" scope="request" type="java.util.List"/>
                    <c:forEach items="${cast}" var="attore">
                    <tr>
                        <td style="padding-right:10px"><i>${attore.ruolo}</i></td>
                        <td>${attore.nome}</td>
                    </tr>
                    </c:forEach>
                </table>

                <a href="${prodotto.trailer}" target="_blank"><button class="bottone" value="trailer">Trailer</button></a>
            </div>
        <div style="clear:both"></div>
    </div>

<%@include file="footer.html" %>