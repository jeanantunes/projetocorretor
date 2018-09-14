var memoriaInputEmail = "";
var metodoSalvarEmailCorretora = "";
var cdCorretora;

$(document).ready(function () {

    cdCorretora = parseInt($("#hiddenCdCorretora").val());

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
        $("#divErroEmail").hide(250);
    });

    $("#inputEmail").blur(function () {

        if( validateEmail( $(this).val() ) ) {

            $(this).removeClass("input-email-red");
            $(this).removeClass("input-email-gray");
            $(this).addClass("input-email-blue");
            $("#btnConfirmarCinza").hide();
            $("#btnConfirmarVerde").show();
            $("#divErroEmail").hide(250);
        } else {

            $(this).removeClass("input-email-gray");
            $(this).removeClass("input-email-blue");
            $(this).addClass("input-email-red");
            $("#btnConfirmarVerde").hide();
            $("#btnConfirmarCinza").show();
            $("#divErroEmail").show(250);
        }

    });

    $("#inputEmail").keyup(function () {

        if( validateEmail( $(this).val() ) ) {

            $(this).removeClass("input-email-red");
            $(this).removeClass("input-email-gray");
            $(this).addClass("input-email-blue");
            $("#btnConfirmarCinza").hide();
            $("#btnConfirmarVerde").show();
            $("#divErroEmail").hide(250);
        } else {

            $("#btnConfirmarVerde").hide();
            $("#btnConfirmarCinza").show();

        }

    });

    $("#inputEmail").focus(function () {
        $(this).removeClass("input-email-gray");
        $(this).removeClass("input-email-red");
        $(this).addClass("input-email-blue");
        $("#divErroEmail").hide(250);
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
        $("#divErroEmail").hide(250);
    });

    $("#btnConfirmarVerde").click(function () {

        swal({
            title: "Aguarde",
            text: 'Estamos atualizando seu email',
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

        putEmailCorretora(function (resp) {

            if(resp!=undefined){
                if(resp.status==undefined){
                    memoriaInputEmail = $("#inputEmail").val();
                    swal("Parabéns!", "Email alterado com sucesso.", "success");
                    $("#inputEmail").removeClass("input-email-blue");
                    $("#inputEmail").removeClass("input-email-red");
                    $("#inputEmail").addClass("input-email-gray");
                    $("#btnConfirmarVerde").hide();
                    $("#btnConfirmarCinza").hide();
                    $("#btnCancelar").hide();
                    $("#btnEditarEmail").show();
                    $("#inputEmail").attr('disabled','disabled');

                } else {
                    //erro
                    swal("Erro!", "Erro ao salvar email.(error)", "error");
                }
            } else {
                //noresponse
                swal("Erro!", "Erro ao salvar email.(noresponse)", "error");
            }
        })


    });

    getPropertie("odontoprev.web.salvaremail", function (dataPropertie) {

        if(dataPropertie != undefined){

            if(dataPropertie.status == undefined){

                metodoSalvarEmailCorretora = dataPropertie;

            }
        }
    });

    var dtAceite = new Object();

        $.ajax({
            url: "/usuario_session",
            type: "get",
            async: false,
            xhrFields: {
                withCredentials: true
            },
            success: function (result) {
                dtAceite.data = eval(result).dtAceiteContrato;
            },
            error: function (result) {
                $(".errorTxt").removeClass("hidden");
            }
        });

});

function putEmailCorretora(callback) {
    var emailCorretora = $("#inputEmail").val();
    var jsonCorretora = { cdCorretora: cdCorretora, email: emailCorretora}
    $.ajax({
        async: true,
        url: metodoSalvarEmailCorretora,
        method: "PUT",
        headers:{
            "Content-Type": "application/json"
        },
        data: JSON.stringify(jsonCorretora),
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {
            callback(xhr);
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