<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        gekap/${param.pageTitle}
    </title>
    <link rel="icon" href="images/favicon.png" type="image/png"/>
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="script/funzioni.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta charset="UTF-8">
</head>
<body>
<header>
    <nav class="barra">
        <a href="WEB-INF/results/index.jsp"><img id="logo" src="images/logoBianco.png" alt="logo"></a>
        <a>
            <select id="scelta_categorie">
                <c:forEach items="${categorie}" var="categoria">
                    <option value="${categoria.idCat}">${categoria.nome}</option>
                </c:forEach>
            </select>
        </a>
        <div class="ricerca">
            <form action="#">
                <input type="text" placeholder="Cerca..." name="cerca">
                <button type="submit"><i class="material-icons md-24 md-light">search</i></button>
            </form>
        </div>
        <div id="loghi">
            <a href="#account"><i id="account" class="material-icons md-36 md-light" onmouseover="inA(this)" onmouseout="outA(this)">account_box</i></a>
            <a href="#carrello"><i id="carrello" class="material-icons md-36 md-light" onmouseover="inC(this)" onmouseout="outC(this)">shopping_cart</i></a>
            <a href="#preferiti"><i id="preferiti" class="material-icons md-36 md-light" onmouseover="inF(this)" onmouseout="outF(this)">favorite</i></a>
        </div>
    </nav>

    <i id="goup" class="material-icons md-48 md-light" onclick="goUp()">keyboard_arrow_up</i>
</header>