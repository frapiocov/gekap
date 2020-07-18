<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>
        gekap/${param.pageTitle}
    </title>

    <link rel="icon" href="images/favicon.png" type="image/png"/>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/header_footer.css" rel="stylesheet" type="text/css">
    <link href="css/prodotto.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="script/funzioni.js"></script>
    <script src="script/jquery.js"></script>
    <script src="script/jquery-3.5.1.min.js"></script>
</head>
<body>
<header>
    <nav class="barra">
        <a href="." id="logo_contenitore"><img id="logo_gekap" src="images/logoBianco.png" alt="logo"></a>

        <div id="scelta_categorie">
            <button id="bottonecategorie">Categorie</button>
            <div class="scelte_dropdown">
                <jsp:useBean id="categorie" scope="application" type="java.util.List"/>
                <c:forEach items="${categorie}" var="categoria">
                    <a href="servlet_categoria?categoria=${categoria.idCat}">${categoria.nome}</a>
                </c:forEach>
            </div>
        </div>

        <div id="ricerca">
            <form action="servlet_ricerca" method="get">
                <label for="q"></label>
                <input id="q" type="text" placeholder="Cerca..." name="query" onfocus="mettiBordo(this)" onblur="togliBordo(this)">
                <button type="submit"><i class="material-icons md-24 md-light">search</i></button>
            </form>
        </div>

        <a href="#account" style="float: right"><i id="account" class="material-icons md-36 md-light"
                                                   onmouseover="inA(this)" onmouseout="outA(this)">account_box</i></a>

        <a href="#cart" style="float: right"><i id="carrello" class="material-icons md-36 md-light"
                                                onmouseover="inC(this)" onmouseout="outC(this)">shopping_cart</i></a>

        <a href="#favorite" style="float: right"><i id="favoriti" class="material-icons md-36 md-light"
                                                    onmouseover="inF(this)" onmouseout="outF(this)">favorite</i></a>
    </nav>

    <button id="goup" ><i class="material-icons md-48 md-light">keyboard_arrow_up</i></button>
</header>

<script>
    $(document).ready(function() {

        var btn = $("#goup");

        $(window).scroll(function() {
            if ($(window).scrollTop() > 300) {
                btn.css("display", "block");
            } else {
                btn.css("display", "none");
            }
        });

        btn.on("click", function(e) {
            e.preventDefault();
            $("html, body").animate({scrollTop:0}, '300');
        });
    });
</script>