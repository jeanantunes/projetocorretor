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

    });

    $("#inputEmail").blur(function () {

        if( validateEmail( $(this).val() ) ) {

            $(this).removeClass("input-email-red");
            $(this).removeClass("input-email-gray");
            $(this).addClass("input-email-blue");
            $("#btnConfirmarCinza").hide();
            $("#btnConfirmarVerde").show();

        } else {

            $(this).removeClass("input-email-gray");
            $(this).removeClass("input-email-blue");
            $(this).addClass("input-email-red");
            $("#btnConfirmarVerde").hide();
            $("#btnConfirmarCinza").show();

        }

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

    });

    $("#btnConfirmarVerde").click(function () {

        swal({
            title: "Aguarde",
            text: 'Estamos atualizando seu email',
            content: "input",
            showCancelButton: false,
            showConfirmButton: false,
            imageUrl: "img/icon-aguarde.gif",
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

        putEmailCorretora(function (resp) {
            console.log(resp)
            if(resp!=undefined){
                if(resp.status==undefined){
                    swal("Parabéns!", "Email alterado com sucesso.", "success");
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

    //$("input").attr('disabled','disabled');
    //To enable again, the proper method is to use .removeAttr()
//
    //$("input").removeAttr('disabled');
});

//odontoprev.web.salvaremail



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