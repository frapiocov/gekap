//funzioni per il cambio icone al passaggio del mouse
function inC(obj) { obj.innerHTML = "add_shopping_cart"; }
function outC(obj) { obj.innerHTML = "shopping_cart"; }
function inF(obj) { obj.innerHTML = "favorite_border"; }
function outF(obj) { obj.innerHTML = "favorite"; }
function inA(obj) { obj.innerHTML = "account_circle"; }
function outA(obj) { obj.innerHTML = "account_box"; }


function prodottiRandom(array) {
    var n=Math.floor(Math.random()*array.length);
    var img="images/" + array[n]["listaImmagini"];
    var desc=array[n]["nome"];

    document.getElementsByTagName("img").setAttribute(src,img);
    document.getElementsByTagName("figcaption").innerHTML=desc;
}