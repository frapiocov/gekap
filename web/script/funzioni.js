//funzioni per il cambio icone al passaggio del mouse
function inC(obj) { obj.innerHTML = "add_shopping_cart"; }
function outC(obj) { obj.innerHTML = "shopping_cart"; }
function inF(obj) { obj.innerHTML = "favorite_border"; }
function outF(obj) { obj.innerHTML = "favorite"; }
function inA(obj) { obj.innerHTML = "account_circle"; }
function outA(obj) { obj.innerHTML = "account_box"; }

jQuery(document).ready(function() {

    var btn = $('#goup');

    $(window).scroll(function() {
        if ($(window).scrollTop() > 300) {
            btn.addClass('show');
        } else {
            btn.removeClass('show');
        }
    });

    btn.on('click', function(e) {
        e.preventDefault();
        $('html, body').animate({scrollTop:0}, '300');
    });

});