<%@ page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="operazione" value="${param.rimuovi != null ? 'Rimozione' : (prodotto == null ? 'Aggiungi' : 'Modifica')}"/>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="${operazione} prodotto"/>
</jsp:include>

    <div class="contenitore_admin">
        <h1>${operazione} prodotto</h1>
        <h2>${notifica}</h2>
        <c:if test="${param.rimuovi == null}">
            <c:if test="${notifica == null}">

            <form action="servlet_modifica_inserimento_prodotto" method="post" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${prodotto.codice}">

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
                        <td><input class="textform" type="text" maxlength="4" name="anno" value="${prodotto.anno}" required></td>
                    </tr>
                    <tr>
                        <td><h3>Prezzo (in centesimi)</h3></td>
                        <td><input type="text" class="textform" name="prezzo" value="${prodotto.prezzoCent}" min="0" placeholder="100"></td>
                    </tr>
                    <tr>
                        <td><h3>Durata (in minuti)</h3></td>
                        <td><input type="text" class="textform" name="durata" value="${prodotto.durata}" required></td>
                    </tr>
                    <tr>
                        <td><h3>Lingua (pattern: EN, IT, FR)</h3></td>
                        <td><input type="text" class="textform" name="lingua" value="${prodotto.lingua}" required></td>
                    </tr>
                    <tr>
                        <td> <h3>Path Trailer (url completo)</h3></td>
                        <td><input type="text" class="textform" name="trailer" value="${prodotto.trailer}" required></td>
                    </tr>
                    <tr>
                        <td><h3>Scegli la Categoria</h3></td>
                    </tr>
                    <tr><td><input type="radio" name="cat" value="1" checked><label>Film</label></td></tr>
                    <tr><td><input type="radio" name="cat" value="2"><label>Documentario</label></td></tr>
                    <tr><td><input type="radio" name="cat" value="3"><label>Stand-Up</label></td></tr>
                </table>

                <c:if test="${operazione.equals('Aggiungi')}">
                <table id="tright">
                    <tr>
                        <td><h3>Immagine</h3></td>
                        <td><input type="file" name="foto" value="${prodotto.listaImmagini}"></td>
                    </tr>
                    <tr>
                        <th>RUOLO</th>
                        <th>ATTORE</th>
                    </tr>
                    <tr>
                        <td>1<input type="text" class="textform" name="r1"></td>
                        <td><input type="text" class="textform" name="n1"></td>
                    </tr>
                    <tr>
                        <td>2<input type="text" class="textform" name="r2"></td>
                        <td><input type="text" class="textform" name="n2"></td>
                    </tr>
                    <tr>
                        <td>2<input type="text" class="textform" name="r3"></td>
                        <td><input type="text" class="textform" name="n3"></td>
                    </tr>
                    <tr>
                        <td>4<input type="text" class="textform" name="r4"></td>
                        <td><input type="text" class="textform" name="n4"></td>
                    </tr>
                    <tr>
                        <td>5<input type="text" class="textform" name="r5"></td>
                        <td><input type="text" class="textform" name="n5"></td>
                    </tr>
                </table>
                </c:if>

                <h3>Trama</h3>
                <textarea name="trama" required>${prodotto.trama}"</textarea><br>

                <input type="submit" class="bottone" value="${operazione}"><br>

            </form>
            </c:if>
        </c:if>
    </div>
    <div style="clear: both"></div>

<%@include file="footer.html" %>