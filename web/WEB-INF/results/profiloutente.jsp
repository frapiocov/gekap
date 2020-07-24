<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Profilo Utente"/>
</jsp:include>

<div>
    <div id="profilo" style="margin-top: 50px; padding: 20px">
            <i class="material-icons md-light md-48">account_circle</i>
            <h1>Benvenuta/o ${utenteLoggato.nome}</h1>
        <fieldset style="width: 60%">
            <legend>DATI</legend>
            <p style="font-size: 20px">Username: ${utenteLoggato.username}<br>
                E-mail: ${utenteLoggato.email}<br>
                ${utenteLoggato.nome} ${utenteLoggato.cognome}<br>
                Data di Nascita: ${utenteLoggato.dataDiNascita}<br>
                Sesso: ${utenteLoggato.sesso}<br>
                Indirizzo di spedizione : Via ${utenteLoggato.via} ${utenteLoggato.nCivico}, ${utenteLoggato.citta}
                (${utenteLoggato.provincia})</p>
        </fieldset>
        <section id="ordini">
            <h1>Ordini effettuati</h1>

        </section>

    </div>
</div>


<%@include file="footer.html" %>
