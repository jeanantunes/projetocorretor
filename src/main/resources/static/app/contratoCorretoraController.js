var metodoAceitar = "";
var redirecionarAceite = "";
var contratoLido = false;

$(document).ready(function () {

    getPropertie("odontoprev.contratocorretora.aceitarcontrato", function (key) {

        metodoAceitar = key;

        getPropertie("odontoprev.contratocorretora.contratoaceito", function (key) {

            redirecionarAceite = key;

        });

    });

    $("#btnAvancar").click(function () {

        swal({
            title: "Aguarde",
            text: "\n",
            content: "input",
            showCancelButton: false,
            showConfirmButton: false,
            imageUrl: "../img/icon-aguarde.gif",
            allowEscapeKey: false,
            allowOutsideClick: false,
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

        $.ajax({
            async: true,
            url: metodoAceitar,
            method: "POST",
            success: function (resp) {
                window.location.href = redirecionarAceite;
            },
            error: function (xhr) {
                callback(xhr);
            }
        });

    });

    $("#termoCadastrado").scroll(function () {

        if (($(this).scrollTop() + $(this).innerHeight()) + 100 >= this.scrollHeight) {

            if(contratoLido) return;

            contratoLido = true;
            $("#checkAceitoOsTermos").prop('checked', true);
            $("#checkAceitoOsTermos").prop('disabled', false);
            $("#checkSouRepresentante").prop('disabled', false);

        }

    });

    $("#labelAceitoTermos").click(function () {

        if($("#checkAceitoOsTermos").is(":checked") && $("#checkSouRepresentante").is(":checked")){
            $("#btnAvancar").removeClass("background-color-cinza");
            $("#btnAvancar").addClass("background-color-azul");
            $("#btnAvancar").removeClass("disabled");
        }else {
            $("#btnAvancar").removeClass("background-color-azul");
            $("#btnAvancar").addClass("background-color-cinza");
            $("#btnAvancar").addClass("disabled");

        }

    });

    $("#labelRepresentante").click(function () {

        if($("#checkAceitoOsTermos").is(":checked") && $("#checkSouRepresentante").is(":checked")){
            $("#btnAvancar").removeClass("background-color-cinza");
            $("#btnAvancar").addClass("background-color-azul");
            $("#btnAvancar").removeClass("disabled");
        }else {
            $("#btnAvancar").removeClass("background-color-azul");
            $("#btnAvancar").addClass("background-color-cinza");
            $("#btnAvancar").addClass("disabled");
        }

    });

});