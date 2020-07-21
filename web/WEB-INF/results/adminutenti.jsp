<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="Utenti"/>
</jsp:include>
<div style="margin-top: 70px; padding: 10px">
    <table>
        <thead>
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
        </thead>

        <tbody>
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
                <td><a href="todo?id=${utente.idUser}"><button class="bottone">Dettagli</button></a></td>
                <td>
                    <form action="todo" method="post">
                        <input type="hidden" name="id" value="${utente.idUser}">
                        <input type="submit" class="bottone" name="rimuovi" value="Rimuovi">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="footer.html"%>
