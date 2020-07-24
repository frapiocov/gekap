<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/results/header.jsp">
    <jsp:param name="pageTitle" value="Profilo Utente"/>
</jsp:include>

<div>
    <div id="profilo" style="margin-top: 50px; padding: 20px">
        <i class="material-icons md-light md-48">account_circle</i>
        <h1>Benvenuta/o ${utente.nome}</h1>
        <fieldset style="width: 60%">
            <legend>DATI</legend>
            <p style="font-size: 20px">Username: ${utente.username}<br>
                E-mail: ${utente.email}<br>
                ${utente.nome} ${utente.cognome}<br>
                Data di Nascita: ${utente.dataDiNascita}<br>
                Sesso: ${utente.sesso}<br>
                Indirizzo di spedizione : Via ${utente.via} ${utente.nCivico}, ${utente.citta}
                (${utente.provincia})</p>
        </fieldset>
    </div>


    <%@include file="footer.html" %>
