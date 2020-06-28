$(document).ready(function() {

    var btn = $("#goup");

    $(window).scroll(function() {
        if ($(window).scrollTop() > 300) {
            btn.css("visibility", "visible");
        } else {
            btn.css("visibility", "hidden");
        }
    });

    btn.on("click", function(e) {
        e.preventDefault();
        $("html, body").animate({scrollTop:0}, '300');
    });
});