﻿$(document).ready(function () {
    $("#cpfcnpj").mask("000.000.000-00");
    defineConexao();
});

var URLBase = "";

function defineConexao() {

    $.ajax({
        url: "/config/connection.json",
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
    }
    else {
        URLBase = conexao.homologacaoURL;
        console.log(URLBase);
    }
}

$("#cpfcnpj").keyup(function() {

    var caracteres = $(this).val().replace(/\D/g, '');

    if(caracteres.length <= 11){
        $("#cpfcnpj").mask("000.000.000-000");
    } else{
        $("#cpfcnpj").mask("00.000.000/0000-00");
    }

});

$(".validacaoDivErro").blur(function () {

    if ($("#cpfcnpj").val().length == 14 && TestaCPF($("#cpfcnpj").val().replace(/\D/g, ''))) {
        $("#btnOdont").removeClass('disabled');
        $("#btnOdont").removeClass('btnOdontCinza');
        $("#btnOdont").addClass('btnOdont');
        $(".img-erro-cpf").hide(250);
        $("#divErroCpf").hide(250);
            $("#jsCpfCnpj").removeClass("text-red-cpfcnpj");
            $("#jsCpfCnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("text-red-cpfcnpj");
            $("#cpfcnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("inputLabelErro");
            $("#cpfcnpj").removeClass("inputLabelBlur");
            $("#cpfcnpj").addClass("inputLabelFocus");
        return;
    }

    if ($("#cpfcnpj").val().length == 18 && validaCnpj($("#cpfcnpj").val().replace(/\D/g, ''))) {
        $("#btnOdont").removeClass('disabled');
        $("#btnOdont").removeClass('btnOdontCinza');
        $("#btnOdont").addClass('btnOdont');
        $(".img-erro-cpf").hide(250);
        $("#divErroCpf").hide(250);
            $("#jsCpfCnpj").removeClass("text-red-cpfcnpj");
            $("#jsCpfCnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("text-red-cpfcnpj");
            $("#cpfcnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("inputLabelErro");
            $("#cpfcnpj").removeClass("inputLabelBlur");
            $("#cpfcnpj").addClass("inputLabelFocus");
        return;
    }

    $(".img-erro-cpf").show(250);
    $("#divErroCpf").show(250);
    $("#btnOdont").addClass('disabled');
    $("#btnOdont").addClass('btnOdontCinza');
        $("#jsCpfCnpj").addClass("text-red-cpfcnpj");
        $("#cpfcnpj").addClass("text-red-cpfcnpj");
        $("#cpfcnpj").removeClass("inputLabelBlur");
        $("#cpfcnpj").removeClass("inputLabelFocus");
        $("#cpfcnpj").addClass("inputLabelErro");

});



$("#btnOdont").click(function () {

    var cpfAndCnpj = $("#cpfcnpj").val().replace(/\D/g, '');

    if(cpfAndCnpj == ""){

        swal("Ops!", "Por favor digite um CPF/CNPJ", "error");
        return;

    }

   if(cpfAndCnpj.length <= 11) {
       if (!TestaCPF($("#cpfcnpj").val())) {

           swal("Ops!", "CPF inválido", "error");
           return;
       }
   } else {

       if (!validaCnpj(cpfAndCnpj)) {

           swal("Ops!", "CNPJ inválido", "error");
           return;
       }

   }

    callTokenProd(function (dataToken) {

        callRecuperarSenha(function (dataRecuperar) {

            var position = dataRecuperar.mensagem.indexOf(":");
            var email = dataRecuperar.mensagem.substring(position + 2, dataRecuperar.mensagem.length);


            var doisPrimeirosCharEmail = email.substring(0, 2);
            var emailSemDominio = email.substring(0, email.indexOf("@"));
            var emailComAsterisco = doisPrimeirosCharEmail;

            for (i = 0; i < emailSemDominio.length - 2; i++) {
                emailComAsterisco += "*";
            }

            $("#emailRecuperacao").html(email.replace(emailSemDominio, emailComAsterisco));

            swal.close();

            $("#loadingRecuperacaoSenha").addClass('hide');
            $("#senhaEnviada").removeClass('hide');

        }, dataToken.token, cpfAndCnpj);
    });

});



function callRecuperarSenha(callback, token, cpfCnpj) {

    var cpfAndCnpj = cpfCnpj.replace(/\D/g, '');

    var json;

    if(cpfAndCnpj.length <= 11) {

        swal({
            title: "Aguarde",
            text: 'Estamos buscando seu CPF',
            content: "input",
            imageUrl: "img/icon-aguarde.gif",
            showCancelButton: false,
            showConfirmButton: false,
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

        json = { "cpf": cpfCnpj };

    } else {

        swal({
            title: "Aguarde",
            text: 'Estamos buscando seu CNPJ',
            content: "input",
            imageUrl: "img/icon-aguarde.gif",
            showCancelButton: false,
            showConfirmButton: false,
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

        json = { "cnpj": cpfCnpj };
    }

    $.ajax({

        async: true,
        url:   URLBase + "/corretorservicos/1.0/esqueciMinhaSenha",
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        processData: false,
        data: JSON.stringify(json),
        success: function (resp) {
           callback(resp);
        },
        error: function (xhr) {

            if (xhr.status == 500) {

                if(cpfAndCnpj.length <= 11) swal("Ops!", "Não encontramos seu CPF", "error");
                    else swal("Ops!", "Não encontramos a corretora", "error");

            } else swal.close();

        }
    });
}

function callTokenProd(callback) {

    $.ajax({
        async: true,
        url: "/get_token",
        method: "GET",

        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {
            swal("Ops!", "Erro na conexão, tente mais tarde", "error");
        }
    });
};