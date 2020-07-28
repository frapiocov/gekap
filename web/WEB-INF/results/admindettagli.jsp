<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/results/header.jsp">
    <jsp:param name="pageTitle" value="Dettagli Utente"/>
</jsp:include>

<div>
    <div id="profilo" style="margin-top: 50px; padding: 20px">
        <fieldset>
            <legend>ORDINI UTENTE</legend>
            <table>
                <c:forEach items="${ordini}" var="ordine">
                    <tr>
                        <td>
                            <p>Ordine numero ${ordine.id}, effettuato in data ${ordine.data},
                                costo: ${ordine.prezzoEuro} &euro;</p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>Prodotti acquistati:
                                <c:forEach items="${ordine.prodotti}" var="prodotto">
                                    ${prodotto.nome}:${prodotto.prezzoEuro}&euro;
                                </c:forEach>
                            </p>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <c:if test="${empty ordini}">
                <h3>Nessun ordine effettuato dall'utente scelto.</h3>
            </c:if>
        </fieldset>
    </div>
</div>

<%@include file="footer.html" %>
