//funzioni per il cambio icone al passaggio del mouse
function inC(obj) { obj.innerHTML = "add_shopping_cart"; }
function outC(obj) { obj.innerHTML = "shopping_cart"; }
function inF(obj) { obj.innerHTML = "favorite_border"; }
function outF(obj) { obj.innerHTML = "favorite"; }
function inA(obj) { obj.innerHTML = "account_circle"; }
function outA(obj) { obj.innerHTML = "account_box"; }

function mettiBordo(obj){
    obj.style.borderBottom="solid #cccccc 1px";
}
function togliBordo(obj){
    obj.style.borderBottom="none";
}
