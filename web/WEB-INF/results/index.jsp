<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Prodotto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp">
    <jsp:param name="pageTitle" value="HomePage"></jsp:param>
</jsp:include>

<div class="slidercontainer">
    <div class="showSlide fade">
        <img src="images/s1.jpg" />
        <div class="content">Vivi la magia del cinema Cult, tratto dal libro di Chuck Pahlaniuk: Fight Club</div>
    </div>
    <div class="showSlide fade">
        <img src="images/s2.jpg" />
        <div class="content">Dal regista Todd Phillips e dal premio Oscar Joaquin Phoenix: JOKER</div>
    </div>

    <div class="showSlide fade">
        <img src="images/s3.jpg" />
        <div class="content"></div>
    </div>
    <div class="showSlide fade">
        <img src="images/s4.jpg" />
        <div class="content">Fatti travolgere dall'ultima impresa targata Disney Pixar</div>
    </div>
    <!-- Navigation arrows -->
    <a class="left" onclick="nextSlide(-1)"><i class="material-icons md-light md-36">keyboard_arrow_left</i></a>
    <a class="right" onclick="nextSlide(1)"><i class="material-icons md-light md-36">keyboard_arrow_right</i></a>
</div>

<div class="sezione_prodotti">
    <c:forEach items="${prodotti}" var="prodotto">
        <div class="prodotti">
            <a href="servlet_prodotto?codice=<c:out value="${prodotto.codice}"/>">
                <img  class="imm_prod_index" src="images/${prodotto.codice}.jpg">
            </a>
        </div>
    </c:forEach>
</div>

<%@include file="footer.html" %>

<script>

    var slide_index = 1;
    displaySlides(slide_index);

    function nextSlide(n) {
        displaySlides(slide_index += n);
    }

    function currentSlide(n) {
        displaySlides(slide_index = n);
    }

    function displaySlides(n) {
        var i;
        var slides = document.getElementsByClassName("showSlide");
        if (n > slides.length) { slide_index = 1 }
        if (n < 1) { slide_index = slides.length }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slides[slide_index - 1].style.display = "block";
    }
</script>