var token = "";
var jsonName = "";
var pdata = "";
var compName = "";
var URLBase = "";
var Token = "";
var conexao;

$(document).ready(function () {

    defineConexao();
});




function put(localName, obj) {
    localStorage.setItem(localName, obj);
}

function get(localName, obj) {
    var o = localStorage.getItem(localName);

    if (o == null)
        return null;

    return JSON.parse(o);
}

function defineConexao() {

    $.ajax({
        url: "config/connection.json",
        type: "get",
        async: false,
        success: function (result) {
            conexao = JSON.parse(result);
        },
        error: function () {

        }
    });

    if (conexao.producaoLigado) {
        URLBase = conexao.producaoURL;
        Token = conexao.chaveProd;
    }
    else {
        URLBase = conexao.homologacaoURL;
        console.log(URLBase);
        Token = conexao.chaveHomolog;
    }
}

function callTokenProd(callback) {

    $.ajax({
        async: true,
        url: URLBase + "/token",
        method: "POST",
        headers: {
            "Authorization": "Basic " + Token,
            "Cache-Control": "no-cache",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        data: {
            "grant_type": "client_credentials"
        },
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {
            swal("Ops!", "Erro na conexão, tente mais tarde", "error");
        }
    });
};