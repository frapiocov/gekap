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
    <link href="css/carrello.css" rel="stylesheet" type="text/css">
    <link href="css/prodotto.css" rel="stylesheet" type="text/css">
    <link href="css/adminprodotto.css" rel="stylesheet" type="text/css">
    <link href="css/adminutenti.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" ></script>
    <script src="script/funzioni.js"></script>
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
                <input type="text" placeholder="Cerca..." name="query" id="q" list="ricerca_datalist" onkeyup="ricerca(this.value)" onfocus="mettiBordo(this)" onblur="togliBordo(this)" value="<c:out value='${param.query}'/>">
                <datalist id="ricerca_datalist"></datalist>
                <button type="submit"><i class="material-icons md-24 md-light">search</i></button>
            </form>
        </div>

        <a href="servlet_carrello" style="float: right; padding-right: 40px"><i id="carrello" class="material-icons md-36 md-light" onmouseover="inC(this)" onmouseout="outC(this)">shopping_cart</i></a>
        <a href="servlet_preferiti" style="float: right; padding-right: 40px"><i id="favoriti" class="material-icons md-36 md-light" onmouseover="inF(this)" onmouseout="outF(this)">favorite</i></a>
        <div id="scelta_account">
            <a href="servlet_profilo" id="account" style="float: right; padding-right: 50px"><i class="material-icons md-36 md-light">account_circle</i></a>
            <c:choose>
                <c:when test="${utente == null}">
                    <div class="scelte_dropdown">
                        <form action="servlet_login" method="post" autocomplete="off">
                            <input class="textform" type="text" name="username" placeholder="Username"><br>
                            <input class="textform" type="password" name="password" placeholder="Password"><br>
                            <input class="bottone" type="submit" value="Login">
                        </form>
                        <hr style="border-color: darkgoldenrod">
                        <a style="padding: 8px; font-size: 20px" href="servlet_registrazione">Registrati</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="scelte_dropdown">
                        <h3>${utente.username}</h3>
                        <hr style="border-color:darkgoldenrod">

                        <c:if test="${utente.admin}">
                            <a style="padding-left: 8px" href="servlet_admin_prodotto">Aggiungi Prodotto</a>
                            <a style="padding-left: 8px" href="servlet_admin_utenti">Utenti</a>
                        </c:if>
                        <a style="padding-left: 8px" href="servlet_profilo">Il mio Profilo</a>

                        <form action="servlet_logout" style="text-align: center">
                            <input class="bottone" type="submit" value="Logout">
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>

        <div style="float: left">
            <button id="menu" onclick="apriMenu()"><i class="material-icons md-light md-36">menu</i></button>
        </div>

        <div class="barra_responsive">

            <c:forEach items="${categorie}" var="categoria">
                <a href="servlet_categoria?categoria=${categoria.idCat}">${categoria.nome}</a>
            </c:forEach>

            <a>
                <form action="servlet_ricerca" method="get">
                    <input type="text" placeholder="Cerca..." name="query" class="textform">
                    <button type="submit" style="background-color: #212121; border:none"><i class="material-icons md-24 md-light">search</i></button>
                </form>
            </a>

            <a href="servlet_carrello">Carrello</a>
            <a href="servlet_preferiti">Wishlist</a>
            <c:choose>
                <c:when test="${utente == null}">
                    <a>
                        <form action="servlet_login" method="post" autocomplete="off">
                            <input class="textform" type="text" name="username" placeholder="Username"><br>
                            <input class="textform" type="password" name="password" placeholder="Password"><br>
                            <input class="bottone" type="submit" value="Login">
                        </form>
                    </a>
                    <a href="servlet_registrazione">Registrati</a>
                </c:when>
                <c:otherwise>
                    <c:if test="${utente.admin}">
                        <a href="servlet_admin_prodotto">Aggiungi Prodotto</a>
                        <a href="servlet_admin_utenti">Utenti</a>
                    </c:if>
                    <a href="servlet_profilo">Il mio Profilo</a>

                    <a href="servlet_logout">Logout</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>

    <button id="goup"><i class="material-icons md-48 md-light">keyboard_arrow_up</i></button>

    <c:if test="${utente != null}">
        <br><br>
        <div id="alert">
            <span id="closebtn" onclick="nascondiElemento()">&times;</span>
            <h3>Ciao ${utente.nome}!</h3>
        </div>
    </c:if>


</header>

<script>
    $(document).ready(function () {
        var btn = $("#goup");

        $(window).scroll(function () {
            if ($(window).scrollTop() > 300) {
                btn.css("display", "block");
            } else {
                btn.css("display", "none");
            }
        });

        btn.on("click", function (e) {
            e.preventDefault();
            $("html,body").animate({scrollTop: 0}, '300');
        });
    });

    $(document).ready(function () {
        $("#menu").click(function () {
            $(".barra_responsive").toggle();
        });
    })
</script>