<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Registrati"/>
</jsp:include>

<div>
    <h1>Registrazione utente</h1>
    <h5>Riempi tutti i campi</h5>
    <form name="registrazione" action="servlet_registrazione" method="post">
        <label>Username (almeno 6 caratteri, solo lettere e numeri, non utilizzato da altri utenti)</label>
        <input type="text" name="username" oninput="validaUsername()">
        <label>Password (almeno 8 caratteri, deve contenere: una lettera maiuscola, una minuscola, un numero)</label>
        <input type="password" name="password" oninput="validaPassword()">
        <label>Password (conferma)</label>
        <input type="password" name="passwordConferma" oninput="validaPassword()">
        <label>Nome (solo lettere e spazi)</label>
        <input type="text" name="nome" oninput="validaNome()">
        <label>Email (<b>TODO:</b> diversa da quella di utenti esistenti)</label>
        <input type="text" name="email" oninput="validaEmail()">
        <input id="registrami" type="submit" value="Registrami" disabled><span id="registramimessaggio"></span>
    </form>

</div>

<%@include file="footer.html" %>
