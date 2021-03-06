<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/results/header.jsp">
    <jsp:param name="pageTitle" value="Utenti"/>
</jsp:include>

<div id="contenitore_utenti">
    <div style="overflow-x: auto;">
    <table id="tutenti">
        <tr>
            <th class="bordi">id</th>
            <th class="bordi">Username</th>
            <th class="bordi">Nome</th>
            <th class="bordi">Cognome</th>
            <th class="bordi">Data di Nascita</th>
            <th class="bordi">Email</th>
            <th class="bordi">Sesso</th>
            <th class="bordi">Indirizzo</th>
            <th class="bordi">Admin</th>
            <th class="bordi">Ordini</th>
            <th class="bordi">Azioni</th>
            <th></th>
        </tr>
        <jsp:useBean id="utenti" scope="request" type="java.util.List"/>
        <c:forEach items="${utenti}" var="utente">
            <tr>
                <td class="bordi">${utente.idUser}</td>
                <td class="bordi">${utente.username}</td>
                <td class="bordi">${utente.nome}</td>
                <td class="bordi">${utente.cognome}</td>
                <td class="bordi">${utente.dataDiNascita}</td>
                <td class="bordi">${utente.email}</td>
                <td class="bordi">${utente.sesso}</td>
                <td class="bordi">Via ${utente.via} ${utente.nCivico}, ${utente.citta} (${utente.provincia})</td>
                <td class="bordi">${utente.admin ? "Si" : "No"}</td>
                <td class="bordi">
                    <form action="servlet_dettagli" method="get">
                        <input type="hidden" name="idUtente" value="${utente.idUser}">
                        <input type="submit" value="Dettagli" class="bottone">
                    </form>
                </td>
                <td class="bordi">
                    <form action="servlet_rimuovi_utente" method="post">
                        <input type="hidden" name="id" value="${utente.idUser}">
                        <input type="submit" class="bottone" name="rimuovi" value="Rimuovi">
                    </form>
                    <c:if test="${!utente.admin}">
                        <a href="servlet_rendi_admin?id=${utente.idUser}">
                            <button class="bottone">Rendi Admin</button>
                        </a>
                    </c:if>
                    <c:if test="${utente.admin}">
                        <a href="servlet_rendi_admin?id=${utente.idUser}">
                            <button class="bottone">Togli Admin</button>
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>

<div style="clear:both "></div>

<%@include file="footer.html" %>
