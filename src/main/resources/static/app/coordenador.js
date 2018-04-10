﻿var token = "";
var jsonName = "";
var pdata = "";
var compName = "";
var URLBase = "";
var Token = "";
var conexao;

$(document).ready(function () {

    $("a[href='meus_dados.html']").hide()

    
    carregarDadosUsuario();

    $("#logout").click(function () {
        logout.removerRegistroLogin();
    });

    callTokenProd(function(dataToken){


    });

    defineConexao();

    $(".linkMenu").click(function () {
        localStorage.removeItem("propostaPf");
        localStorage.removeItem("proposta");
    });
});

function defineConexao() {
	
    $.ajax({
           url: "/access_token/url",
           type: "get",
           async: false,
           xhrFields: {
               withCredentials: true
           },
           success: function (result) {
               URLBase = eval(result).url;
               if(URLBase.indexOf("api-it1")!== -1) {
                   setPlanosHml();
                   
               } else {
                   setPlanosProd();
                   
               }
           },
           error: function () {

           }
       });
/*
   $.ajax({
       url: "config/connection.json",
       type: "get",
       async: false,
       success: function (result) {
           conexao = eval(result);
       },
       error: function () {

       }
   });

   if (conexao.producaoLigado) {
       URLBase = conexao.producaoURL;
       Token = conexao.chaveProd;
       setPlanosProd();
   }
   else {
       URLBase = conexao.homologacaoURL;
       console.log(URLBase);
       Token = conexao.chaveHomolog;
       setPlanosHml();
   }
   */
}

function callTokenProd(callback) {
	//TODO: Implementar chamada no controller
    console.log("callTokenProd - coordendador.js");
    $.ajax({
        async: true,
        url: "/token",
        method: "POST",
        headers: {
            "Cache-Control": "no-cache",
        },
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {
            swal("Ops!", "Erro na conexão, tente mais tarde", "error");
        }
    });
    
};

function callTokenProdSemMsgErro(callback) {

    $.ajax({
        async: true,
        url: "/access_token",
        method: "POST",
        headers: {
            "Cache-Control": "no-cache",
        },
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {

        }
    });
};

//$(function () {
//    var regex = new RegExp('[^ a-zA-ZÁÉÍÓÚÀÈÌÒÙàèìòùáéíóúâêîôûãõ\b]', 'g');
//    // repare a flag "g" de global, para substituir todas as ocorrências
//    $('.nome').bind('input', function () {
//        $(this).val($(this).val().replace(/^[a-zA-ZÁÉÍÓÚÀÈÌÒÙàèìòùáéíóúâêîôûãõ']+$/g, ''));
//    });
//});

$(function () {
    var regex = new RegExp('[^0-9\]', 'g');
    // repare a flag "g" de global, para substituir todas as ocorrências
    $('.numero').bind('input', function () {
        $(this).val($(this).val().replace(regex, ''));
    });
});

function validarData(data) {
    var bits = data.split('/');

    var y = bits[2],
        m = bits[1],
        d = bits[0];

    var anoAtual = new Date().getFullYear();

    if (anoAtual < y || y < 1890) return false;

    // Assume not leap year by default (note zero index for Jan)
    var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

    // If evenly divisible by 4 and not evenly divisible by 100,
    // or is evenly divisible by 400, then a leap year
    if ((!(y % 4) && y % 100) || !(y % 400)) {
        daysInMonth[1] = 29;
    }
    return !(/\D/.test(String(d))) && d > 0 && d <= daysInMonth[--m];
}


$(".data").blur(function () {

    if (validarData($(".data").val())) {

        $(this).css({ "border-color": "#3A94FB" });
        $(".nascimento").css("color", "#3A94FB");
        $(".label-nascimento").css("color", "#3A94FB");
        return;
    }

    $(".data").css("border-color", "red");
    $(".nascimento").css("color", "red");
    $(".label-nascimento").css("color", "red");

});

function ValidaNome(fieldValue) {

    var splittedName = fieldValue.trim().split(/[\ |\']+/) // Separa o nome por espaços e apóstrofos (')

    var splittedNameSpace = fieldValue.split(/[\ ]+/)

    var totalWords = splittedName.length

    let firstName = splittedName[0]

    let lastName = splittedName[totalWords - 1]

    if (!fieldValue) return false

    // Se o nome completo contiver um apóstrofo seguido de qualquer caractere que não seja a-z, é inválido
    if (fieldValue.match(/'[^a-zà-ÿ ]/i)) {
        return false
    }

    // Se o primeiro nome tiver só uma letra e ela não for D, I, O, U ou Y, é inválido
    if (firstName.length === 1 && !firstName.match(/[D|I|O|U|Y]/i)) {
        return false
    }

    // Se o último nome tiver só uma letra e ela não for I, O, U ou Y, é inválido
    if (lastName.length === 1 && !lastName.match(/[I|O|U|Y]/i)) {
        return false
    }

    // Se o nome possuir conectivos que não 'e' ou 'y', é inválido
    for (let i in splittedName) {

        // Se o nome estiver vazio, é invalido
        if (splittedName[i] == "") return false

        // Se o nome possuir caracteres especiais, exceto apostrofo, é invalido
        if (!splittedName[i].match(/^[a-zA-ZÁÉÍÓÚÀÈÌÒÙàèìòùáéíóúâêîôûãõçÇ']+$/g)) return false

        if (i === '0' || parseInt(i) === (totalWords - 1)) continue // Ignora o primeiro e o último nome

        var nameWithApostrophe = splittedName[i] + "'" + splittedName[parseInt(i) + 1]

        if (splittedName[i].length === 1 && !splittedName[i].match(/[E|Y]/i) && nameWithApostrophe != splittedNameSpace[i]) {
            return false
        }
    }

    // Se o nome completo contiver um apóstrofo e não houver pelo menos três palavras, é inválido
    if (fieldValue.match(/'/i) && totalWords < 3) {
        return false
    }

    // Se o nome tiver só uma palavra, é inválido
    if (totalWords === 1) {
        return false
    }
    return true
}


function setPlanosProd() {
    planos = [];

    plano = getRepository("plano");
    plano.cdPlano = 101;
    plano.nome = "Integral DOC LE";
    plano.valor = "24";
    plano.centavo = "93";
    plano.valorFloat = 24.93;
    plano.desc = "Modalidade Compulsório";
    plano.css = "colorSlick3";
    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 102;
    plano.nome = "Master LE";
    plano.valor = "101";
    plano.centavo = "10";
    plano.valorFloat = 101.10;
    plano.desc = "Modalidade Compulsório";
    plano.css = "colorSlick2";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 67;
    plano.nome = "DENTAL BEM-ESTAR";
    plano.valor = "45";
    plano.centavo = "60";
    plano.valorFloat = 45.60;
    plano.desc = "Mensal";
    plano.css = "colorSlick3";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 66;
    plano.nome = "DENTAL BEM-ESTAR";
    plano.valor = "456";
    plano.centavo = "00";
    plano.valorFloat = 456.00;
    plano.desc = "Anual";
    plano.css = "colorSlick3";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 68;
    plano.nome = "DENTAL BEM-ESTAR";
    plano.valor = "547";
    plano.centavo = "20";
    plano.valorFloat = 547.20;
    plano.desc = "Anual";
    plano.css = "colorSlick3";
    
    planos.push(plano);

    //plano = getRepository("plano");
    //plano.cdPlano = 6;
    //plano.nome = "DENTE DE LEITE DE 0 A 7 ANOS";
    //plano.valor = "14";
    //plano.centavo = "98";
    //plano.desc = "Mensal";
    //plano.css = "colorSlick2";
    //
    //planos.push(plano);
    //
    //plano = getRepository("plano");
    //plano.cdPlano = 7;
    //plano.nome = "DENTE DE LEITE DE 0 A 7 ANOS";
    //plano.valor = "149";
    //plano.centavo = "80";
    //plano.desc = "Anual";
    //plano.css = "colorSlick2";
    //
    //planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 74;
    plano.nome = "DENTAL ESTÉTICA";
    plano.valor = "115";
    plano.centavo = "00";
    plano.valorFloat = 115.00;
    plano.desc = "Mensal";
    plano.css = "colorSlick3";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 73;
    plano.nome = "DENTAL ESTÉTICA";
    plano.valor = "1150";
    plano.centavo = "00";
    plano.valorFloat = 1150.00;
    plano.desc = "Anual";
    plano.css = "colorSlick3";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 75;
    plano.nome = "DENTAL ESTÉTICA";
    plano.valor = "1380";
    plano.centavo = "00";
    plano.valorFloat = 1380.00;
    plano.desc = "Anual";
    plano.css = "colorSlick3";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 77;
    plano.nome = "DENTAL ORTO";
    plano.valor = "147";
    plano.centavo = "00";
    plano.valorFloat = 147.00;
    plano.desc = "Mensal";
    plano.css = " colorSlick4";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 76;
    plano.nome = "DENTAL ORTO";
    plano.valor = "1470";
    plano.centavo = "00";
    plano.valorFloat = 1470.00;
    plano.desc = "Anual";
    plano.css = "colorSlick4";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 78;
    plano.nome = "DENTAL ORTO";
    plano.valor = "1764";
    plano.centavo = "00";
    plano.valorFloat = 1764.00;
    plano.desc = "Anual";
    plano.css = "colorSlick4";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 80;
    plano.nome = "DENTAL VIP";
    plano.valor = "220";
    plano.centavo = "35";
    plano.valorFloat = 220.35;
    plano.desc = "Mensal";
    plano.css = "colorSlick5";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 79;
    plano.nome = "DENTAL VIP";
    plano.valor = "2203";
    plano.centavo = "50";
    plano.valorFloat = 2203.50;
    plano.desc = "Anual";
    plano.css = "colorSlick5";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 81;
    plano.nome = "DENTAL VIP";
    plano.valor = "2644";
    plano.centavo = "20";
    plano.valorFloat = 2644.00;
    plano.desc = "Anual";
    plano.css = "colorSlick5";

    planos.push(plano);

    put("planos", JSON.stringify(planos));
    setPlanosProdCod()
}


function setPlanosProdCod() {

    planos = [];

    var plano = new Object();
    plano.cdPlano = 101;
    plano.nome = "INTEGRAL DOC LE";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 102;
    plano.nome = "MASTER LE";
    planos.push(plano);

    ////// CODIGO PLANOS DENTAL BEM - ESTAR ////////

    var plano = new Object();
    plano.cdPlano = 67;
    plano.nome = "DENTAL BEM-ESTAR MENSAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 66;
    plano.nome = "DENTAL BEM-ESTAR ANUAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 68;
    plano.nome = "DENTAL BEM-ESTAR ANUAL S/CARENCIA";
    planos.push(plano);

    
    //////// CODIGO PLANOS DENTAL ESTETICA /////////

    var plano = new Object();
    plano.cdPlano = 74;
    plano.nome = "DENTAL ESTETICA MENSAL";  
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 73;
    plano.nome = "DENTAL ESTETICA ANUAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 75;
    plano.nome = "DENTAL ESTETICA ANUAL S/CARENCIA";
    planos.push(plano);

    ///////// CODIGO PLANOS DENTAL ORTO //////////

    var plano = new Object();
    plano.cdPlano = 77;
    plano.nome = "DENTAL ORTO MENSAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 76;
    plano.nome = "DENTAL ORTO ANUAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 78;
    plano.nome = "DENTAL ORTO ANUAL S/CARENCIA";
    planos.push(plano);

    ///////// CODIGO PLANOS DENTAL VIP //////////

    var plano = new Object();
    plano.cdPlano = 80;
    plano.nome = "DENTAL VIP MENSAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 79;
    plano.nome = "DENTAL VIP ANUAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 81;
    plano.nome = "DENTAL VIP ANUAL S/CARENCIA";
    planos.push(plano);

    /////////////////////////////////////////////

    put("CodPlanos", JSON.stringify(planos));
}

function setPlanosHmlCod() {

    planos = [];

    var plano = new Object();
    plano.cdPlano = 61;
    plano.nome = "INTEGRAL DOC LE";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 62;
    plano.nome = "MASTER LE";
    planos.push(plano);

    ////// CODIGO PLANOS DENTAL BEM - ESTAR ////////

    var plano = new Object();
    plano.cdPlano = 7;
    plano.nome = "DENTAL BEM-ESTAR MENSAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 8;
    plano.nome = "DENTAL BEM-ESTAR ANUAL";
    planos.push(plano);

    //var plano = new Object();
    //plano.cdPlano = 68;
    //plano.nome = "DENTAL BEM-ESTAR ANUAL S/CARENCIA";
    //planos.push(plano);


    //////// CODIGO PLANOS DENTAL ESTETICA /////////

    var plano = new Object();
    plano.cdPlano = 1;
    plano.nome = "DENTAL ESTETICA MENSAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 2;
    plano.nome = "DENTAL ESTETICA ANUAL";
    planos.push(plano);

    //var plano = new Object();
    //plano.cdPlano = 75;
    //plano.nome = "DENTAL ESTETICA ANUAL S/CARENCIA";
    //planos.push(plano);

    ///////// CODIGO PLANOS DENTAL ORTO //////////

    var plano = new Object();
    plano.cdPlano = 5;
    plano.nome = "DENTAL ORTO MENSAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 6;
    plano.nome = "DENTAL ORTO ANUAL";
    planos.push(plano);

    //var plano = new Object();
    //plano.cdPlano = 78;
    //plano.nome = "DENTAL ORTO ANUAL S/CARENCIA";
    //planos.push(plano);

    ///////// CODIGO PLANOS DENTAL VIP //////////

    var plano = new Object();
    plano.cdPlano = 3;
    plano.nome = "DENTAL VIP MENSAL";
    planos.push(plano);

    var plano = new Object();
    plano.cdPlano = 4;
    plano.nome = "DENTAL VIP ANUAL";
    planos.push(plano);

    //var plano = new Object();
    //plano.cdPlano = 81;
    //plano.nome = "DENTAL VIP ANUAL S/CARENCIA";
    //planos.push(plano);

    /////////////////////////////////////////////

    put("CodPlanos", JSON.stringify(planos));
}

function setPlanosHml() {
    planos = [];

    plano = getRepository("plano");
    plano.cdPlano = 61;
    plano.nome = "Integral DOC LE";
    plano.valor = "24";
    plano.centavo = "93";
    plano.valorFloat = 24.93;
    plano.desc = "Modalidade Compulsório";
    plano.css = "colorSlick3";
    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 62;
    plano.nome = "Master LE";
    plano.valor = "101";
    plano.centavo = "10";
    plano.valorFloat = 101.10;
    plano.desc = "Modalidade Compulsório";
    plano.css = "colorSlick2";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 7;
    plano.nome = "DENTAL BEM-ESTAR";
    plano.valor = "45";
    plano.centavo = "60";
    plano.valorFloat = 45.60;
    plano.desc = "Mensal";
    plano.css = "colorSlick3";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 8;
    plano.nome = "DENTAL BEM-ESTAR";
    plano.valor = "456";
    plano.centavo = "00";
    plano.valorFloat = 456.00;
    plano.desc = "Anual";
    plano.css = "colorSlick3";

    planos.push(plano);

    //plano = getRepository("plano"); // Plano sem carencia, nao existe em homolog
    //plano.cdPlano = 68;
    //plano.nome = "DENTAL BEM-ESTAR";
    //plano.valor = "547";
    //plano.centavo = "20";
    //plano.desc = "Anual";
    //plano.css = "colorSlick3";
    //
    //planos.push(plano);

    //plano = getRepository("plano");
    //plano.cdPlano = 6;
    //plano.nome = "DENTE DE LEITE DE 0 A 7 ANOS";
    //plano.valor = "14";
    //plano.centavo = "98";
    //plano.desc = "Mensal";
    //plano.css = "colorSlick2";
    //
    //planos.push(plano);
    //
    //plano = getRepository("plano");
    //plano.cdPlano = 7;
    //plano.nome = "DENTE DE LEITE DE 0 A 7 ANOS";
    //plano.valor = "149";
    //plano.centavo = "80";
    //plano.desc = "Anual";
    //plano.css = "colorSlick2";
    //
    //planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 1;
    plano.nome = "DENTAL ESTÉTICA";
    plano.valor = "115";
    plano.centavo = "00";
    plano.valorFloat = 115.00;
    plano.desc = "Mensal";
    plano.css = "colorSlick3";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 2;
    plano.nome = "DENTAL ESTÉTICA";
    plano.valor = "1150";
    plano.centavo = "00";
    plano.valorFloat = 1150.00;
    plano.desc = "Anual";
    plano.css = "colorSlick3";

    planos.push(plano);

    //plano = getRepository("plano"); // Plano sem carencia, nao existe em homolog
    //plano.cdPlano = 75;
    //plano.nome = "DENTAL ESTÉTICA";
    //plano.valor = "1380";
    //plano.centavo = "00";
    //plano.desc = "Anual";
    //plano.css = "colorSlick3";
    //
    //planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 5;
    plano.nome = "DENTAL ORTO";
    plano.valor = "147";
    plano.centavo = "00";
    plano.valorFloat = 147.00;
    plano.desc = "Mensal";
    plano.css = " colorSlick4";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 6;
    plano.nome = "DENTAL ORTO";
    plano.valor = "1470";
    plano.centavo = "00";
    plano.valorFloat = 1470.00;
    plano.desc = "Anual";
    plano.css = "colorSlick4";

    planos.push(plano);

    //plano = getRepository("plano"); // Plano sem carencia, nao existe em homolog
    //plano.cdPlano = 78;
    //plano.nome = "DENTAL ORTO";
    //plano.valor = "1764";
    //plano.centavo = "00";
    //plano.desc = "Anual";
    //plano.css = "colorSlick4";
    //
    //planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 3;
    plano.nome = "DENTAL VIP";
    plano.valor = "220";
    plano.centavo = "35";
    plano.valorFloat = 220.35;
    plano.desc = "Mensal";
    plano.css = "colorSlick5";

    planos.push(plano);

    plano = getRepository("plano");
    plano.cdPlano = 4;
    plano.nome = "DENTAL VIP";
    plano.valor = "2203";
    plano.centavo = "50";
    plano.valorFloat = 2203.50;
    plano.desc = "Anual";
    plano.css = "colorSlick5";

    planos.push(plano);

    //plano = getRepository("plano"); // Plano sem carencia, nao existe em homolog
    //plano.cdPlano = 81;
    //plano.nome = "DENTAL VIP";
    //plano.valor = "2644";
    //plano.centavo = "20";
    //plano.desc = "Anual";
    //plano.css = "colorSlick5";
    //
    //planos.push(plano);

    put("planos", JSON.stringify(planos));

    setPlanosHmlCod();
}

function getComponent(pCompName) {
    compName = pCompName;

    $.ajax({
        url: "componente/" + compName + ".html",
        type: "get",
        async: false,
        success: function (result) {
            pdata = result;
        },
        error: function () {

        }
    });

    return pdata;
}

function getRepository(pJsonName) {

    jsonName = pJsonName;

    $.ajax({
        url: "repositorio/" + jsonName + ".json",
        type: "get",
        async: false,
        success: function (result) {
            pdata = result;
        },
        error: function () {

        }
    });

    return eval(pdata);
}

function put(localName, obj) {
    localStorage.setItem(localName, obj);
}

function get(localName, obj) {
    var o = localStorage.getItem(localName);

    if (o == null)
        return null;

    return JSON.parse(o);
}

function carregarDadosUsuario() {
    var carregarDados = get("dadosUsuario");

    if (carregarDados == null)
        return;

    $("#nomeCorretorMenu").html(carregarDados.nome == null? "" : carregarDados.nome.split(' ')[0]);
    $("#nomeCorretoraMenu").html(carregarDados.nomeEmpresa == null? "" : carregarDados.nomeEmpresa.split(' ')[0]);
    $("#nomeCorretor").html(carregarDados.nome);
    $("#nomeCorretora").html(carregarDados.nomeEmpresa);
    $("#emailCorretor").val(carregarDados.email);
    $("#numeroCorretor").val(carregarDados.telefone);
}

function toDate(dateStr) {
    var parts = dateStr.split("/");
    return new Date(parts[2], parts[1] - 1, parts[0]);
}

function toDateSplitHifenSerasa(dateStr) {
    var parts = dateStr.split("-");
    return new Date(parts[0], parts[1] - 1, parts[2].substring(0, 2));
}

function isMaiorDeIdade(date) {

    var eightYearsAgo = moment().subtract(18, "years");
    var birthday = moment(date);

    if (!birthday.isValid()) {
        // INVALID DATE
    } else if (eightYearsAgo.isAfter(birthday)) return true;

    return false;
}

function getUrlParameter(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

function atualizarPessoas(proposta) {
    var pessoas = get("pessoas");
    var propostas = pessoas.filter(function (x) { return x.cpf != proposta.cpf });
    pessoas = []; //limpar

    $.each(propostas, function (i, item) {
        pessoas.push(item);
    });

    pessoas.push(proposta);
    put("pessoas", JSON.stringify(pessoas));
    put("propostaPf", JSON.stringify(proposta));
}

function atualizarEmpresas(proposta) {
    var empresas = get("empresas");
    var propostas = empresas.filter(function (x) { return x.cnpj != proposta.cnpj });
    empresas = []; //limpar

    $.each(propostas, function (i, item) {
        empresas.push(item);
    });

    empresas.push(proposta);
    put("empresas", JSON.stringify(empresas));
}

function atualizarDashBoard() {

    var pessoas = get("pessoas");
    var empresas = get("empresas");

    var digitandoPessoas = [];
    var criticadaPessoas = [];
    var prontaPessoas = [];
    var syncPessoas = [];

    var digitandoEmpresas = [];
    var criticadaEmpresas = [];
    var prontaEmpresas = [];

    if (pessoas != null) {
        digitandoPessoas = pessoas.filter(function (x) { return x.status == "DIGITANDO" });
        criticadaPessoas = pessoas.filter(function (x) { return x.status == "CRITICADA" });
        prontaPessoas = pessoas.filter(function (x) { return x.status == "PRONTA" });
        syncPessoas = pessoas.filter(function (x) { return x.status == "SYNC" });
    }

    if (empresas != null) {
        digitandoEmpresas = empresas.filter(function (x) { return x.status == "DIGITANDO" });
        criticadaEmpresas = empresas.filter(function (x) { return x.status == "CRITICADA" });
        prontaEmpresas = empresas.filter(function (x) { return x.status == "PRONTA" });
    }

    var qtdFinalizada = get("QtdFinalizada");

    if (qtdFinalizada == null) {
        put("QtdFinalizada", 0);
        qtdFinalizada = 0;
    }

    $("#digitando").html(digitandoPessoas.length + digitandoEmpresas.length);
    $("#criticada").html(criticadaPessoas.length + criticadaEmpresas.length);
    $("#pronta").html(prontaPessoas.length + prontaEmpresas.length + syncPessoas.length);
    $("#finalizada").html(qtdFinalizada);
}




function sincronizar() {

    if (navigator.onLine) {

        var empresas = get("empresas");
        var pessoas = get("pessoas");
        var beneficiarios = get("beneficiarios");

        if (empresas != null) {

            swal({
                title: "Aguarde",
                text: 'Estamos enviando as suas propostas (PME)',
                content: "input",
                imageUrl: "img/load.gif",
                showCancelButton: false,
                showConfirmButton: false,
                icon: "info",
                button: {
                    text: "...",
                    closeModal: false,
                },
            });

            $.each(empresas, function (i, item) {
                if (item.status == "PRONTA") {

                    var o = empresas.filter(function (x) { return x.cnpj == item.cnpj });
                    var b = beneficiarios.filter(function (x) { return x.cnpj == item.cnpj });

                    var todosExcetoExclusao = empresas.filter(function (x) { return x.cnpj != item.cnpj });

                    o[0].status = "SYNC";
                    o[0].horaSync = new Date();

                    todosExcetoExclusao.push(o[0]);

                    put("empresas", JSON.stringify(todosExcetoExclusao));

                    sincronizarEmpresa(o, b);
                    atualizarDashBoard();
                }
            });

            swal.close();
        }

        if (pessoas != null) {

            swal({
                title: "Aguarde",
                text: 'Estamos enviando as suas propostas (PF)',
                content: "input",
                imageUrl: "img/load.gif",
                showCancelButton: false,
                showConfirmButton: false,
                icon: "info",
                button: {
                    text: "...",
                    closeModal: false,
                },
            });

            $.each(pessoas, function (i, item) {
                if (item.status == "PRONTA") {

                    var o = pessoas.filter(function (x) { return x.cpf == item.cpf });
                    var propostas = pessoas.filter(function (x) { return x.cpf != item.cpf });

                    pessoas = []; //limpar

                    $.each(propostas, function (i, item) {
                        pessoas.push(item);
                    });

                    o[0].status = "SYNC";

                    o[0].horaSync = new Date();

                    pessoas.push(o[0]);

                    put("pessoas", JSON.stringify(pessoas));
                   
                    sincronizarPessoa(function (dataProposta) {
                        console.log(dataProposta);
                    }, o, false);

                }
            });
        }
    }
    else {
        swal("Você está sem Internet", "Não se preocupe, você pode acessar a tela inicial e enviar esta proposta depois.", "info");0
    }
}

function removerAcentos(newStringComAcento) {
    var string = newStringComAcento;
    var mapaAcentosHex = {
        a: /[\xE0-\xE6]/g,
        e: /[\xE8-\xEB]/g,
        i: /[\xEC-\xEF]/g,
        o: /[\xF2-\xF6]/g,
        u: /[\xF9-\xFC]/g,
        c: /\xE7/g,
        n: /\xF1/g
    };

    for (var letra in mapaAcentosHex) {
        var expressaoRegular = mapaAcentosHex[letra];
        string = string.replace(expressaoRegular, letra);
    }

    return string;
}

function sincronizarPessoa(callback, pessoa, reSync) { // caso a proposta esteja sendo ressincronizada reSync recebe true

    //var pessoa = eval(pessoaString);
    var forcaVenda = get("dadosUsuario");

    console.log(pessoa[0].planos[0].cdPlano);

    var cdPlano = pessoa[0].planos[0].cdPlano;

    var pdata = [];

    //var json = "{ \"cdForcaVenda\": \"" + forcaVenda.codigo + "\", \"cdPlano\": \"" + cdPlano + "\", \"titulares\": " + JSON.stringify(pessoa) + "}";

    var date = toDate(pessoa[0].dataNascimento);

    if (isMaiorDeIdade(date)) {
        var json = {
            "cdForcaVenda": forcaVenda.codigo,
            "cdPlano": cdPlano,
            "titulares": [
                {
                    "nome": removerAcentos(pessoa[0].nome),
                    "cpf": pessoa[0].cpf,
                    "dataNascimento": pessoa[0].dataNascimento,
                    "nomeMae": removerAcentos(pessoa[0].nomeMae),
                    "sexo": pessoa[0].sexo,
                    "status": pessoa[0].status,
                    "titular": pessoa[0].titular,
                    "celular": pessoa[0].celular,
                    "contatoEmpresa": pessoa[0].contatoEmpresa,
                    "dadosBancarios": {
                        "agencia": pessoa[0].dadosBancarios.agencia,
                        "codigoBanco": pessoa[0].dadosBancarios.codigoBanco,
                        "conta": pessoa[0].dadosBancarios.conta,
                        "tipoConta": pessoa[0].dadosBancarios.tipoConta
                    },
                    "dependentes": pessoa[0].dependentes,
                    "email": pessoa[0].email,
                    "endereco": {
                        "bairro": removerAcentos(pessoa[0].endereco.bairro),
                        "cep": pessoa[0].endereco.cep,
                        "cidade": removerAcentos(pessoa[0].endereco.cidade),
                        "complemento": pessoa[0].endereco.complemento,
                        "logradouro": removerAcentos(pessoa[0].endereco.logradouro),
                        "estado": pessoa[0].endereco.estado,
                        "numero": pessoa[0].endereco.numero
                    }
                }
            ],
            "responsavelContratual": {
                "nome": pessoa[0].responsavelContratual.nome,
                "cpf": pessoa[0].responsavelContratual.cpf,
                "dataNascimento": pessoa[0].responsavelContratual.dataNascimento,
                "email": pessoa[0].responsavelContratual.email,
                "celular": pessoa[0].responsavelContratual.celular,
                "sexo": pessoa[0].responsavelContratual.sexo,
                "endereco": {
                    "bairro": removerAcentos(pessoa[0].endereco.bairro),
                    "cep": pessoa[0].endereco.cep,
                    "cidade": removerAcentos(pessoa[0].endereco.cidade),
                    "complemento": pessoa[0].endereco.complemento,
                    "logradouro": removerAcentos(pessoa[0].endereco.logradouro),
                    "estado": pessoa[0].endereco.estado,
                    "numero": pessoa[0].endereco.numero
                }
            }
        };

    } else {
        var json = {
            "cdForcaVenda": forcaVenda.codigo,
            "cdPlano": cdPlano,
            "titulares": [
                {
                    "nome": removerAcentos(pessoa[0].nome),
                    "cpf": pessoa[0].cpf,
                    "dataNascimento": pessoa[0].dataNascimento,
                    "nomeMae": removerAcentos(pessoa[0].nomeMae),
                    "sexo": pessoa[0].sexo,
                    "status": pessoa[0].status,
                    "titular": pessoa[0].titular,
                    "celular": pessoa[0].celular,
                    "contatoEmpresa": pessoa[0].contatoEmpresa,
                    "dadosBancarios": {
                        "agencia": pessoa[0].dadosBancarios.agencia,
                        "codigoBanco": pessoa[0].dadosBancarios.codigoBanco,
                        "conta": pessoa[0].dadosBancarios.conta,
                        "tipoConta": pessoa[0].dadosBancarios.tipoConta
                    },
                    "dependentes": pessoa[0].dependentes,
                    "email": pessoa[0].email,
                    "endereco": {
                        "bairro": removerAcentos(pessoa[0].endereco.bairro),
                        "cep": pessoa[0].endereco.cep,
                        "cidade": removerAcentos(pessoa[0].endereco.cidade),
                        "complemento": pessoa[0].endereco.complemento,
                        "logradouro": removerAcentos(pessoa[0].endereco.logradouro),
                        "estado": pessoa[0].endereco.estado,
                        "numero": pessoa[0].endereco.numero
                    }
                }
            ],
            "responsavelContratual": {
                "nome": "",
                "cpf": "",
                "dataNascimento": "",
                "email": "",
                "celular": "",
                "sexo": "",
                "endereco": {
                    "bairro": "",
                    "cep": "",
                    "cidade": "",
                    "complemento": "",
                    "logradouro": "",
                    "estado": "",
                    "numero": ""
                }
            }
        };
    }

    

    json = JSON.stringify(json);

    console.log(json);

    if (reSync){

        swal({
            title: "Aguarde",
            text: 'Estamos enviando a sua proposta',
            content: "input",
            imageUrl: "img/load.gif",
            showCancelButton: false,
            showConfirmButton: false,
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

    }




    callTokenProd(function (dataToken) {

        $.ajax({
            async: true,
            //url: URLBase + "vendapf",
            url: URLBase + "/corretorservicos/1.0/vendapf",
            method: "POST",
            data: json,
            headers: {
                "Content-Type": "application/json",               
                "Authorization": "Bearer " + dataToken.access_token
            },
            //data: "{ \r\n   \"cdForcaVenda\":\"" + forcaVenda.codigo + "\",\r\n   \"cdPlano\":\"" + 4 + "\",\r\n   \"titulares\":[ \r\n      { \r\n         \"celular\":\"" + pessoa[0].celular + "\",\r\n         \"contatoEmpresa\":" + pessoa[0].contatoEmpresa + ",\r\n         \"cpf\":\"" + pessoa[0].cpf + "\",\r\n         \"dadosBancarios\":{ \r\n            \"agencia\":\"" + pessoa[0].dadosBancarios.agencia + "\",\r\n            \"codigoBanco\":\"" + pessoa[0].dadosBancarios.codigoBanco + "\",\r\n            \"conta\":\"" + pessoa[0].dadosBancarios.conta + "\",\r\n            \"tipoConta\":\"" + pessoa[0].dadosBancarios.tipoConta + "\"\r\n         },\r\n         \"dependentes\":[ \r\n \r\n         ],\r\n         \"email\":\"" + pessoa[0].email + "\",\r\n         \"endereco\":{ \r\n            \"bairro\":\"" + pessoa[0].endereco.bairro + "\",\r\n            \"cep\":\"" + pessoa[0].endereco.cep + "\",\r\n            \"cidade\":\"" + pessoa[0].endereco.cidade + "\",\r\n            \"complemento\":\"" + pessoa[0].endereco.complemento + "\",\r\n            \"logradouro\":\"" + pessoa[0].endereco.logradouro + "\",\r\n            \"estado\":\"" + pessoa[0].endereco.estado + "\",\r\n            \"numero\":\"" + pessoa[0].endereco.numero + "\"\r\n         },\r\n         \"dataNascimento\":\"" + pessoa[0].dataNascimento + "\",\r\n         \"nomeMae\":\"" + pessoa[0].nomeMae + "\",\r\n         \"nome\":\"" + pessoa[0].nome + "\",\r\n         \"sexo\":\"" + pessoa[0].sexo +"\",\r\n         \"status\":\"PRONTA\",\r\n         \"titular\":true\r\n      }\r\n   ]\r\n}\r\n",

            processData: false,
            success: function (result) {

                swal.close();

                callback(result);

                if (result.id == 0) {

                    pessoa[0].status = "CRITICADA";
                    atualizarPessoas(pessoa[0]);
                    console.log("Erro");
                }
                else {

                    var pessoas = get("pessoas");
                    pessoa[0].status = "ENVIADA";
                    pessoa[0].dataAtualizacao = new Date();

                    var todosExcetoExclusao = pessoas.filter(function (x) { return x.cpf != pessoa[0].cpf });
                    todosExcetoExclusao.push(pessoa[0]);

                    console.log(todosExcetoExclusao);
                    put("pessoas", JSON.stringify(todosExcetoExclusao));

                }

                atualizarDashBoard();
            },
            error: function (resp) {
                swal.close();
                console.log(resp);
            }
        });
    });
}

function sincronizarEmpresa(proposta, beneficiarios) {

    console.log(proposta);

    var dadosUsuario = get("dadosUsuario");
    var pdata = [];
    var json = "{ \"cdForcaVenda\":" + dadosUsuario.codigo + ", \"empresas\": " + JSON.stringify(proposta) + ", \"titulares\":" + JSON.stringify(beneficiarios) + "}";

    console.log(json);

    callTokenProd(function (dataToken) {
        $.ajax({
            url: URLBase + "/corretorservicos/1.0/vendapme",
            //url: "http://www.corretorvendaodonto.com.br:7001/portal-corretor-servico-0.0.1-SNAPSHOT/vendapme",
            type: "POST",
            data: json,
            dataType: "json",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + dataToken.access_token
            },
            success: function (result) {
                if (result.id == 0) {
                    proposta[0].status = "CRITICADA";
                    atualizarEmpresas(proposta[0]);
                }
                else {
                    var empresas = get("empresas");
                    var todosExcetoExclusao = empresas.filter(function (x) { return x.cnpj != proposta[0].cnpj });
                
                    proposta[0].status = "ENVIADA";

                    todosExcetoExclusao.push(proposta[0]);

                    put("empresas", JSON.stringify(todosExcetoExclusao));

                }

                atualizarDashBoard();
            },
            error: function (xhr) {
                console.log(xhr);
                swal.close();
            }
        });
    });
}

function isValidDate(date) {
    var matches = /^(\d{2})[-\/](\d{2})[-\/](\d{4})$/.exec(date);
    if (matches == null) return false;
    var d = matches[2];
    var m = matches[1] - 1;
    var y = matches[3];
    var composedDate = new Date(y, m, d);
    return composedDate.getDate() == d &&
        composedDate.getMonth() == m &&
        composedDate.getFullYear() == y;
}

function checkNetConnection() {
    var xhr = new XMLHttpRequest();
    var file = "http://www.odontoprev.com.br/home/portugues/_img/logo-odontoprev.png";
    var r = Math.round(Math.random() * 10000);
    xhr.open('HEAD', file + "?subins =" + r, false);
    try {
        xhr.send();
        if (xhr.status >= 200 && xhr.status < 304) {
            return true;
        } else {
            return false;
        }
    } catch (e) {
        return false;
    }
}

function getInputsByValue(value) {
    var allInputs = document.getElementsByTagName("input");
    var results = [];
    for (var x = 0; x < allInputs.length; x++)
        if (allInputs[x].value == value)
            results.push(allInputs[x]);
    return results;
}

function validateEmail(email) {
    var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    if (reg.test(email)) {
        return true;
    }

    return false;
}

function resizeIframe(iframeID)
{
    var iframe = window.parent.document.getElementById(iframeID);
    var container = document.body;
    var h =  parseInt(container.offsetHeight)+200;
    iframe.style.height = h.toString() + 'px';
}