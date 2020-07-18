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