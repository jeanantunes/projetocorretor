var memoriaInputEmail = "";
var metodoSalvarEmailVenda = "";
var metodoEnviarEmailVenda = "";
var cdEmpresa;

$(document).ready(function () {

    cdEmpresa = parseInt($("#hiddenCdEmpresa").val());

    $("#btnEditarEmail").click(function () {

        memoriaInputEmail = $("#inputEmail").val();
        $(this).hide();
        $("#inputEmail").removeAttr('disabled');
        $("#inputEmail").removeClass("input-email-gray");
        $("#inputEmail").removeClass("input-email-red");
        $("#inputEmail").addClass("input-email-blue");
        $("#btnConfirmarVerde").hide();
        $("#btnConfirmarCinza").show();
        $("#btnCancelar").show();
        $("#divErroEmail").hide();
    });

    $("#inputEmail").blur(function () {
        
        if ($(this).val() == memoriaInputEmail){
            $("#inputEmail").removeAttr('disabled');
            $("#inputEmail").removeClass("input-email-gray");
            $("#inputEmail").removeClass("input-email-red");
            $("#inputEmail").addClass("input-email-blue");
            $("#btnConfirmarVerde").hide();
            $("#btnConfirmarCinza").show();
            $("#btnCancelar").show();
            $("#divErroEmail").hide();
            return;
        }

        if( validateEmail( $(this).val() )
        ) {
            $(this).removeClass("input-email-red");
            $(this).removeClass("input-email-gray");
            $(this).addClass("input-email-blue");
            $("#btnConfirmarCinza").hide();
            $("#btnConfirmarVerde").show();
            $("#divErroEmail").hide();
        } else {

            $(this).removeClass("input-email-gray");
            $(this).removeClass("input-email-blue");
            $(this).addClass("input-email-red");
            $("#btnConfirmarVerde").hide();
            $("#btnConfirmarCinza").show();
            $("#divErroEmail").show();
        }

    });

    $("#inputEmail").keyup(function () {

        if ($(this).val() == memoriaInputEmail){
            $("#inputEmail").removeAttr('disabled');
            $("#inputEmail").removeClass("input-email-gray");
            $("#inputEmail").removeClass("input-email-red");
            $("#inputEmail").addClass("input-email-blue");
            $("#btnConfirmarVerde").hide();
            $("#btnConfirmarCinza").show();
            $("#btnCancelar").show();
            $("#divErroEmail").hide();
            return;
        }

        if(validateEmail( $(this).val() )) {

            $(this).removeClass("input-email-red");
            $(this).removeClass("input-email-gray");
            $(this).addClass("input-email-blue");
            $("#btnConfirmarCinza").hide();
            $("#btnConfirmarVerde").show();
            $("#divErroEmail").hide();
        } else {

            $("#btnConfirmarVerde").hide();
            $("#btnConfirmarCinza").show();

        }

    });

    $("#inputEmail").focus(function () {
        $(this).removeClass("input-email-gray");
        $(this).removeClass("input-email-red");
        $(this).addClass("input-email-blue");
        $("#divErroEmail").hide();
    });

    $("#btnCancelar").click(function () {

        $("#inputEmail").val(memoriaInputEmail);
        $("#inputEmail").removeClass("input-email-blue");
        $("#inputEmail").removeClass("input-email-red");
        $("#inputEmail").addClass("input-email-gray");
        $("#btnConfirmarVerde").hide();
        $("#btnConfirmarCinza").hide();
        $(this).hide();
        $("#btnEditarEmail").show();
        $("#inputEmail").attr('disabled','disabled');
        $("#divErroEmail").hide();
    });

    $("#btnConfirmarVerde").click(function () {

        swal({
            title: "Aguarde",
            text: 'Estamos atualizando o email da venda',
            content: "input",
            showCancelButton: false,
            showConfirmButton: false,
            imageUrl: "../../img/icon-aguarde.gif",
            allowEscapeKey: false,
            allowOutsideClick: false,
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

        putEmailEmpresaVenda(

            function (dataEmailSuccess) {

                swal({
                        title: "Email alterado com sucesso",
                        text: "Deseja reenviar o email de aceite?",
                        type: "success",
                        confirmButtonClass: "btn-danger",
                        confirmButtonColor: "#1974CE",
                        confirmButtonText: "Sim",
                        cancelButtonText: "Não",
                        allowEscapeKey: false,
                        allowOutsideClick: false,
                        showCancelButton: true,
                        closeOnConfirm: false,
                        closeOnCancel: false
                    },
                    function (isConfirm) {

                        if (isConfirm){

                            swal({
                                title: "Aguarde",
                                text: 'Estamos enviando o email da venda',
                                content: "input",
                                showCancelButton: false,
                                showConfirmButton: false,
                                imageUrl: "../../img/icon-aguarde.gif",
                                allowEscapeKey: false,
                                allowOutsideClick: false,
                                icon: "info",
                                button: {
                                    text: "...",
                                    closeModal: false,
                                },
                            });

                            postEnviarEmailVenda(
                                function (dataEmailEnviadoSucess) {

                                    if (dataEmailEnviadoSucess == undefined) return;

                                    swal("Email enviado com sucesso", "", "success");

                                    memoriaInputEmail = $("#inputEmail").val();
                                    $("#inputEmail").val(memoriaInputEmail);
                                    $("#inputEmail").removeClass("input-email-blue");
                                    $("#inputEmail").removeClass("input-email-red");
                                    $("#inputEmail").addClass("input-email-gray");
                                    $("#btnConfirmarVerde").hide();
                                    $("#btnConfirmarCinza").hide();
                                    $("#btnCancelar").hide();
                                    $("#btnEditarEmail").show();
                                    $("#inputEmail").attr('disabled','disabled');
                                    $("#divErroEmail").hide();

                                },
                                function (dataError) {
                                    swal("Ops!", "Erro no envio do email", "error");
                                }
                            )

                        } else {

                            memoriaInputEmail = $("#inputEmail").val();
                            $("#inputEmail").val(memoriaInputEmail);
                            $("#inputEmail").removeClass("input-email-blue");
                            $("#inputEmail").removeClass("input-email-red");
                            $("#inputEmail").addClass("input-email-gray");
                            $("#btnConfirmarVerde").hide();
                            $("#btnConfirmarCinza").hide();
                            $("#btnCancelar").hide();
                            $("#btnEditarEmail").show();
                            $("#inputEmail").attr('disabled','disabled');
                            $("#divErroEmail").hide();
                            swal.close();
                        }

                    });

            },
            function (dataEmailError) {

                swal("Ops!", "Erro na alteração do email", "error");
            }
        );

    });

    getPropertie("odontoprev.web.empresa.alterar", function (dataPropertie) {

        if(dataPropertie != undefined){

            if(dataPropertie.status == undefined){

                metodoSalvarEmailVenda = dataPropertie;

            }
        }
    });

    getPropertie("odontoprev.web.empresa.enviaremailaceite", function (dataPropertie) {

        if(dataPropertie != undefined){

            if(dataPropertie.status == undefined){

                metodoEnviarEmailVenda = dataPropertie;

            }
        }
    });

    $("#btnEnviarEmail").click(function () {
        swal({
            title: "Aguarde",
            text: 'Estamos enviando o email da venda',
            content: "input",
            showCancelButton: false,
            showConfirmButton: false,
            imageUrl: "../../img/icon-aguarde.gif",
            allowEscapeKey: false,
            allowOutsideClick: false,
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

        postEnviarEmailVenda(function (dataSucess) {
            if (dataSucess == undefined){
                swal("Ops!", "Erro ao enviar e-mail, tente novamente.", "error");
            } else {
                swal("Sucesso!", "E-mail reenviado com sucesso.", "success");
            }
        }, function (dataError) {
            swal("Ops!", "Erro ao enviar e-mail, tente novamente.", "error");
        });
    });

});


function putEmailEmpresaVenda(callbackSuccess, callbackError) {
    var emailEmpresa = $("#inputEmail").val();
    var jsonEmpresa = { cdEmpresa: cdEmpresa, email: emailEmpresa};

    $.ajax({
        async: true,
        url: metodoSalvarEmailVenda,
        method: "PUT",
        headers:{
            "Content-Type": "application/json"
        },
        data: JSON.stringify(jsonEmpresa),
        success: function (resp) {
            callbackSuccess(resp);
        },
        error: function (xhr) {
            callbackError(xhr);
        }
    });

}

function postEnviarEmailVenda(callbackSuccess, callbackError) {

    var jsonEmpresa = { cdEmpresa: cdEmpresa};

    $.ajax({
        async: true,
        url: metodoEnviarEmailVenda,
        method: "POST",
        headers:{
            "Content-Type": "application/json"
        },
        data: JSON.stringify(jsonEmpresa),
        success: function (resp) {
            callbackSuccess(resp);
        },
        error: function (xhr) {
            callbackError(xhr);
        }
    });

}

function validateEmail(email) {
    var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    if (reg.test(email)) {
        return true;
    }

    return false;
}