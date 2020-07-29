//funzioni per il cambio icone al passaggio del mouse
function inC(obj) {obj.innerHTML = "add_shopping_cart";}
function outC(obj) { obj.innerHTML = "shopping_cart"; }
function inF(obj) { obj.innerHTML = "favorite_border"; }
function outF(obj) { obj.innerHTML = "favorite"; }

function mettiBordo(obj){
    obj.style.borderBottom="solid darkgoldenrod 1px";
}

function togliBordo(obj){
    obj.style.borderBottom="none";
}

//funzioni javascript per validare i campi del form per la registrazione

var borderOk = '2px solid #080';
var borderNo = '2px solid #f00';
var usernameOk = false;
var passwordOk = false;
var nomeOk = false;
var emailOk = false;
var cognomeOk = false;
var viaOk = false;
var cittaOk = false;
var provOk = false;
var capOk = false;

function validaUsername() {
    var input = document.forms['registrazione']['username'];
    if (input.value.length >= 6 && input.value.match(/^[0-9a-zA-Z]+$/)) {
        // verifica se esiste un utente con lo stesso username
        var xmlHttpReq = new XMLHttpRequest();
        xmlHttpReq.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200
                && this.responseText == '<ok/>') {
                usernameOk = true;
                input.style.border = borderOk;
            } else {
                input.style.border = borderNo;
                usernameOk = false;
            }
            cambiaStatoRegistrami();
        };
        xmlHttpReq.open("GET", "servlet_verifica_username?username=" + encodeURIComponent(input.value), true);
        xmlHttpReq.send();
    } else {
        input.style.border = borderNo;
        usernameOk = false;
        cambiaStatoRegistrami();
    }
}

function validaPassword() {
    var inputpw = document.forms['registrazione']['password'];
    var inputpwconf = document.forms['registrazione']['passwordConferma'];
    var password = inputpw.value;
    if (password.length >= 8 && password.toUpperCase() != password
        && password.toLowerCase() != password && /[0-9]/.test(password)) {
        inputpw.style.border = borderOk;

        if (password == inputpwconf.value) {
            inputpwconf.style.border = borderOk;
            passwordOk = true;
        } else {
            inputpwconf.style.border = borderNo;
            passwordOk = false;
        }
    } else {
        inputpw.style.border = borderNo;
        inputpwconf.style.border = borderNo;
        passwordOk = false;
    }
    cambiaStatoRegistrami();
}

function validaNome() {
    var input = document.forms['registrazione']['nome'];
    if (input.value.trim().length > 0
        && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {    //escludiamo caratteri accentati, numeri e caratteri speciali
        input.style.border = borderOk;
        nomeOk = true;
    } else {
        input.style.border = borderNo;
        nomeOk = false;
    }
    cambiaStatoRegistrami();
}

function validaCognome() {
    var input = document.forms['registrazione']['cognome'];
    if (input.value.trim().length > 0 && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.border = borderOk;
        cognomeOk = true;
    } else {
        input.style.border = borderNo;
        cognomeOk = false;
    }
    cambiaStatoRegistrami();
}

function validaVia() {
    var input = document.forms['registrazione']['via'];
    if (input.value.trim().length > 0
        && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.border = borderOk;
        viaOk = true;
    } else {
        input.style.border = borderNo;
        viaOk = false;
    }
    cambiaStatoRegistrami();
}

function validaCitta() {
    var input = document.forms['registrazione']['citta'];
    if (input.value.trim().length > 0
        && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.border = borderOk;
        cittaOk = true;
    } else {
        input.style.border = borderNo;
        cittaOk = false;
    }
    cambiaStatoRegistrami();
}

function validaProv() {
    var input = document.forms['registrazione']['prov'];
    if (input.value.trim().length > 0
        && input.value.match(/^[ a-zA-Z\u00C0-\u00ff]+$/)) {
        input.style.border = borderOk;
        provOk = true;
    } else {
        input.style.border = borderNo;
        provOk = false;
    }
    cambiaStatoRegistrami();
}

function validaCap() {
    var input = document.forms['registrazione']['cap'];
    if (input.value.trim().length === 5
        && input.value.match(/[0-9]/)) {
        input.style.border = borderOk;
        capOk = true;
    } else {
        input.style.border = borderNo;
        capOk = false;
    }
    cambiaStatoRegistrami();
}

function validaEmail() {
    var input = document.forms['registrazione']['email'];
    if (input.value.match(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w+)+$/)) {
        // verifica se esiste un utente con la stessa email
        var xmlHttpReq2 = new XMLHttpRequest();
        xmlHttpReq2.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200 && this.responseText == '<ok/>') {
                emailOk = true;
                input.style.border = borderOk;
            } else {
                input.style.border = borderNo;
                emailOk = false;
            }
            cambiaStatoRegistrami();
        };
        xmlHttpReq2.open("GET", "servlet_verifica_email?email=" + encodeURIComponent(input.value), true);
        xmlHttpReq2.send();
    }else {
        input.style.border = borderNo;
        emailOk = false;
        cambiaStatoRegistrami();
    }
}

function cambiaStatoRegistrami() {
    if (usernameOk && passwordOk && nomeOk && emailOk && cognomeOk && viaOk && provOk && cittaOk && capOk) {
        document.getElementById('registrami').disabled = false;
        document.getElementById('registramimessaggio').innerHTML = '';
    } else {
        document.getElementById('registrami').disabled = true;
        document.getElementById('registramimessaggio').innerHTML = 'Verifica che tutti i campi siano in verde.';
    }
}

function nascondiElemento() {
    document.getElementById("alert").style.display = "none";
}

//funzione per i suggerimenti la ricerca con ajax
function ricerca(valore) {
    var datalist = document.getElementById("ricerca_datalist");
    if(valore.length == 0) {        //nessun suggerimento, l'utente ha iniziato a digitare e poi ha cancellato
        datalist.innerHTML = "";
        return;
    }

    var xhttp = new XMLHttpRequest();
    xhttp.responseType = "json";
    xhttp.onreadystatechange = function () {
        if(this.readyState == 4 && this.status == 200) {
            datalist.innerHTML = "";        //cancello contenuto attuale del datalist

            for(var i in this.response) {
                var option = document.createElement("option");
                option.value = this.response[i];
                datalist.appendChild(option);
            }
        }
    };
    xhttp.open("GET","servlet_ricerca_ajax?query=" +encodeURIComponent(valore),true);
    xhttp.send();
}