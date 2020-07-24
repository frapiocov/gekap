<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/results/header.jsp">
    <jsp:param name="pageTitle" value="Registrati"/>
</jsp:include>

<div class="contenitore_admin">
    <h1>Registrazione utente</h1>
    <h3>Riempi tutti i campi!</h3>
    <form name="registrazione" action="servlet_registra_utente" method="post">
        <table>
            <tr>
                <td><label>Username (almeno 6 caratteri, solo lettere e numeri)</label></td>
                <td><input type="text" name="username" class="textform" oninput="validaUsername()" placeholder="'username'/'user12'"></td>
            </tr>
            <tr>
                <td><label>Password (almeno 8 caratteri, deve contenere: una lettera maiuscola, una minuscola, un numero)</label></td>
                <td><input type="password" name="password" class="textform" oninput="validaPassword()" placeholder="Password1"></td>
            </tr>
            <tr>
                <td><label>Conferma Password</label></td>
                <td><input type="password" name="passwordConferma" class="textform" oninput="validaPassword()"></td>
            </tr>
            <tr>
                <td> <label>Nome</label></td>
                <td><input type="text" name="nome" class="textform" oninput="validaNome()"></td>
            </tr>
            <tr>
                <td><label>Cognome</label></td>
                <td><input type="text" name="cognome" class="textform" oninput="validaCognome()"></td>
            </tr>
            <tr>
                <td><label>Email</label></td>
                <td><input type="email" name="email" class="textform" oninput="validaEmail()" placeholder="prova@email.com"></td>
            </tr>
            <tr>
                <td><label>Data di nascita</label></td>
                <td><input type="date" class="textform" name="ddn"></td>
            </tr>
            <tr>
                <td> <label>Sesso</label>
                    <input type="radio" value="M" name="sex">
                    <label>UOMO</label>
                    <input type="radio" value="F" name="sex">
                    <label>DONNA</label></td>
                <td></td>
            </tr>
        </table>
        <br>
        <fieldset style="width: auto">
            <legend>INDIRIZZO</legend>
            <table>
                <tr>
                    <td><label style="margin-right: 10px"> Via (solo lettere e spazi)</label></td>
                    <td><input type="text" name="via" class="textform" oninput="validaVia()"></td>
                </tr>
                <tr>
                    <td><label> Numero Civico</label></td>
                    <td><input type="number" name="nc"><br></td>
                </tr>
                <tr>
                    <td><label> Citt&agrave; </label></td>
                    <td><input type="text" class="textform" name="citta" oninput="validaCitta()"></td>
                </tr>
                <tr>
                    <td><label> Provincia</label></td>
                    <td><input type="text" name="prov" class="textform" maxlength="2" oninput="validaProv()" placeholder="pv"></td>
                </tr>
                <tr>
                    <td><label>CAP</label></td>
                    <td><input type="text" name="cap" class="textform" maxlength="5" oninput="validaCap()"></td>
                </tr>
            </table>
        </fieldset>

        <input id="registrami" class="bottone" type="submit" value="Registrami" disabled>
        <span id="registramimessaggio"></span>
    </form>

</div>

<%@include file="footer.html" %>
