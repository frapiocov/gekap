<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/results/header.jsp">
    <jsp:param name="pageTitle" value="Profilo Utente"/>
</jsp:include>

<div>
    <div id="profilo" style="margin-top: 50px; padding: 20px">
        <h1>Benvenuta/o ${utente.nome}</h1>
        <fieldset style="width: 90%">
            <legend>DATI</legend>
            <p style="font-size: 20px">Username: ${utente.username}<br>
                E-mail: ${utente.email}<br>
                ${utente.nome} ${utente.cognome}<br>
                Data di Nascita: ${utente.dataDiNascita}<br>
                Sesso: ${utente.sesso}<br>
                Indirizzo di spedizione : Via ${utente.via} ${utente.nCivico}, ${utente.citta}
                (${utente.provincia})</p>
        </fieldset>

        <h2>ORDINI</h2>
        <table>
            <c:forEach items="${ordini}" var="ordine">
                <tr>
                    <td>
                        <p>Ordine numero ${ordine.id}, effettuato in data ${ordine.data}, costo: ${ordine.prezzoEuro} &euro;</p>
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
            <h3>Nessun ordine effettuato caro/a ${utente.nome}</h3>
        </c:if>
    </div>
</div>

<%@include file="footer.html" %>