<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Utenti"/>
</jsp:include>

<div id="contenitore_admin">
    <table>
        <tr>
            <th>id</th>
            <th>Username</th>
            <th>Nome</th>
            <th>Cognome</th>
            <th>Data di Nascita</th>
            <th>Email</th>
            <th>Sesso</th>
            <th>Indirizzo</th>
            <th>Admin</th>
            <th>Ordini</th>
            <th>Azioni</th>

        </tr>
        <jsp:useBean id="utenti" scope="request" type="java.util.List"/>
        <c:forEach items="${utenti}" var="utente">
            <tr>
                <td>${utente.idUser}</td>
                <td>${utente.username}</td>
                <td>${utente.nome}</td>
                <td>${utente.cognome}</td>
                <td>${utente.dataDiNascita}</td>
                <td>${utente.email}</td>
                <td>${utente.sesso}</td>
                <td>Via ${utente.via} ${utente.nCivico}, ${utente.citta} (${utente.provincia})</td>
                <td>${utente.admin ? "Si" : "No"}</td>
                <td><a href="servlet_profilo?utente=${utente}">
                    <button class="bottone">Dettagli</button></a>
                </td>
                <td>
                    <form action="servlet_rimuovi_utente" method="post">
                        <input type="hidden" name="id" value="${utente.idUser}">
                        <input type="submit" class="bottone" name="rimuovi" value="Rimuovi">
                    </form>
                </td>
                <td>
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

<div style="clear:both "></div>

<%@include file="footer.html" %>
