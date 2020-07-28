<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/results/header.jsp">
    <jsp:param name="pageTitle" value="${operazione} prodotto"/>
</jsp:include>

<div id="contenitore_admin">
    <c:if test="${notifica != null}">
        <h1>${operazione} prodotto</h1>
        <h2>${notifica}</h2>
    </c:if>

    <c:if test="${notifica == null}">
        <h3>Riempi tutti i campi!</h3>
        <form action="servlet_modifica_inserimento_prodotto" method="post" enctype="multipart/form-data" autocomplete="on">
            <input type="hidden" name="id" value="${prodotto.codice}">
            <input type="hidden" name="listaIm" value="${prodotto.listaImmagini}">
            <input type="hidden" name="operazione" value="${operazione}">

            <table id="tleft">
                <tr>
                    <td><h3>Titolo</h3></td>
                    <td><input class="textform" type="text" name="nome" value="${prodotto.nome}" required></td>
                </tr>
                <tr>
                    <td><h3>Genere</h3></td>
                    <td><input class="textform" type="text" name="genere" value="${prodotto.genere}" required></td>
                </tr>
                <tr>
                    <td><h3>Anno</h3></td>
                    <td><input class="textform" type="number" maxlength="4" min="1950" max="2020" name="anno" value="${prodotto.anno}" required></td>
                </tr>
                <tr>
                    <td><h3>Prezzo (in centesimi)</h3></td>
                    <td><input type="number" class="textform" name="prezzo" min="0" value="${prodotto.prezzoCent}" required></td>
                </tr>
                <tr>
                    <td><h3>Durata (in minuti)</h3></td>
                    <td><input type="number" min="1" class="textform" name="durata" value="${prodotto.durata}" required>
                    </td>
                </tr>
                <tr>
                    <td><h3>Lingua</h3></td>
                    <td><input type="text" class="textform" name="lingua" placeholder="IT" pattern="[A-Z]{2}" title="Devi inserire due lettere maiuscole!" value="${prodotto.lingua}" required></td>
                </tr>
                <tr>
                    <td><h3>Path Trailer</h3></td>
                    <td><input type="url" class="textform" name="trailer" placeholder="http://youtube.com" value="${prodotto.trailer}" required></td>
                </tr>
                <tr>
                    <td><h3>Scegli la Categoria</h3></td>
                </tr>
                <tr>
                    <td><input type="radio" name="cat" value="1" checked><label>Film</label></td>
                </tr>
                <tr>
                    <td><input type="radio" name="cat" value="2"><label>Documentario</label></td>
                </tr>
                <tr>
                    <td><input type="radio" name="cat" value="3"><label>Stand-Up</label></td>
                </tr>
                <tr>
                    <table class="casttab"></table>
                </tr>

            <c:if test="${operazione.equalsIgnoreCase('inserimento')}">
                    <tr>
                        <td><h3>Immagine</h3></td>
                        <td><input type="file" name="foto"></td>
                    </tr>

                    <tr>
                        <table class="casttab">
                        <tr>
                            <th>RUOLO</th>
                            <th>ATTORE</th>
                        </tr>
                        <tr>
                            <td>1.<input type="text" class="textform" name="r1" required></td>
                            <td><input type="text" class="textform" name="n1" required></td>
                        </tr>
                        <tr>
                            <td>2.<input type="text" class="textform" name="r2" required></td>
                            <td><input type="text" class="textform" name="n2" required></td>
                        </tr>
                        <tr>
                            <td>3.<input type="text" class="textform" name="r3" required></td>
                            <td><input type="text" class="textform" name="n3" required></td>
                        </tr>
                        <tr>
                            <td>4.<input type="text" class="textform" name="r4" required></td>
                            <td><input type="text" class="textform" name="n4"></td>
                        </tr>
                        <tr>
                            <td>5.<input type="text" class="textform" name="r5" required></td>
                            <td><input type="text" class="textform" name="n5"></td>
                        </tr>
                        </table>
                    </tr>
            </c:if>
                    <tr>
                        <td> <textarea name="trama" placeholder="Aggiungi una descrizione..." required>${prodotto.trama}</textarea></td>
                    </tr>
                </table>



            <input style="margin-top: 30px; margin-left: 140px" type="submit" class="bottone" value="${operazione}"><br>
        </form>
    </c:if>

</div>
<div style="clear: both"></div>

<%@include file="footer.html" %>