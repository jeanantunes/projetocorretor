﻿$(document).ready(function () {
    localStorage.removeItem('propostaPf')
    setIdPlano();
    resizeIframe('frame_pf');
});

function setIdPlano() {

    var planos = get("CodPlanos");

    var forca = new Object();
    console.log("Chamada UsuarioSession");
    $.ajax({
        url: "/usuario_session",
        type: "get",
        async: false,
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            forca.codigo = eval(result).codigoUsuario;
        },
        error: function (result) {
        }
    });

    put("dadosUsuario", JSON.stringify(forca));

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

    ////////////////////////////// Seta id dos planos no data id do botao

    ///////////////////////DENTAL BEM ESTAR ////////

    var plano = planos.filter(function (x) {if (x.nome == "DENTAL BEM-ESTAR MENSAL"){return x.nome;}});
    $("#bemEstarMensal").attr("data-id", plano[0].cdPlano);

    var plano = planos.filter(function (x) { if (x.nome == "DENTAL BEM-ESTAR ANUAL") { return x.nome; } });
    $("#bemEstarAnual").attr("data-id", plano[0].cdPlano);

    /////////////////////// DENTAL VIP ///////////////////

    var plano = planos.filter(function (x) { if (x.nome == "DENTAL VIP MENSAL") { return x.nome; } });
    $("#vipMensal").attr("data-id", plano[0].cdPlano);

    var plano = planos.filter(function (x) { if (x.nome == "DENTAL VIP ANUAL") { return x.nome; } });
    $("#vipAnual").attr("data-id", plano[0].cdPlano);

    /////////////////////// DENTAL ESTETICA //////////////////

    var plano = planos.filter(function (x) { if (x.nome == "DENTAL ESTETICA MENSAL") { return x.nome; } });
    $("#esteticaMensal").attr("data-id", plano[0].cdPlano);

    var plano = planos.filter(function (x) { if (x.nome == "DENTAL ESTETICA ANUAL") { return x.nome; } });
    $("#esteticaAnual").attr("data-id", plano[0].cdPlano);

    /////////////////////// DENTE DE LEITE //////////////////

    var plano = planos.filter(function (x) { if (x.nome == "DENTE DE LEITE MENSAL") { return x.nome; } });
    $("#denteDeLeiteMensal").attr("data-id", plano[0].cdPlano);

    var plano = planos.filter(function (x) { if (x.nome == "DENTE DE LEITE ANUAL") { return x.nome; } });
    $("#denteDeLeiteAnual").attr("data-id", plano[0].cdPlano);

    /////////////////////// PLANO JUNIOR //////////////////

    var plano = planos.filter(function (x) { if (x.nome == "JUNIOR MENSAL") { return x.nome; } });
    $("#planoJuniorMensal").attr("data-id", plano[0].cdPlano);

    var plano = planos.filter(function (x) { if (x.nome == "JUNIOR ANUAL") { return x.nome; } });
    $("#planoJuniorAnual").attr("data-id", plano[0].cdPlano);

    ///////////////////////// DENTAL ORTO ////////////////////////

    var plano = planos.filter(function (x) { if (x.nome == "DENTAL ORTO MENSAL") { return x.nome; } });
    $("#ortoMensal").attr("data-id", plano[0].cdPlano);

    var plano = planos.filter(function (x) { if (x.nome == "DENTAL ORTO ANUAL") { return x.nome; } });
    $("#ortoAnual").attr("data-id", plano[0].cdPlano);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        var plano = planos.filter(function (x) { if (x.nome == "DENTAL ORTO ANUAL S/CARENCIA") { return x.nome; } });
        $("#ortoAnualSCarencia").attr("data-id", plano[0].cdPlano);
    
        var plano = planos.filter(function (x) { if (x.nome == "DENTAL ESTETICA ANUAL S/CARENCIA") { return x.nome; } });
        $("#esteticaAnualSCarencia").attr("data-id", plano[0].cdPlano);
    
        var plano = planos.filter(function (x) { if (x.nome == "DENTAL VIP ANUAL S/CARENCIA") { return x.nome; } });
        $("#vipAnualSCarencia").attr("data-id", plano[0].cdPlano);

        var plano = planos.filter(function (x) { if (x.nome == "DENTAL BEM-ESTAR ANUAL S/CARENCIA") { return x.nome; } });
        $("#bemEstarAnualSCarencia").attr("data-id", plano[0].cdPlano);
    
        return;

}

function iniciarProposta(cdPlano) {

    var proposta = get("propostaPf");
    plano = getRepository("plano");
    plano.cdPlano = cdPlano;

    if (proposta == null) { // Caso seja um proposta iniciada, sera gerado um id e salvo apenas no localstorage propostaPf

        proposta = getRepository("propostaPf");
        proposta.idProposta = generateUUID();
        proposta.planos = [];
        proposta.planos.push(plano);
        put("propostaPf", JSON.stringify(proposta));

    } else { // se nao, o id ira ser reaproveitado e a proposta sera atualizada

        proposta.planos = [];
        proposta.planos.push(plano);
        atualizarPropostasPfById(proposta);

    }

    window.location.href = "venda_pf_dados_proposta.html";

}
