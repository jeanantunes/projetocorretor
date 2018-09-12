$(document).ready(function () {

    $("#btnAvancar").click(function () {

        swal({
            title: "",
            text: "",
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
            url: "/corretora/aceitarcontrato",
            method: "POST",
            success: function (resp) {
                window.location.href = "/corretora/contrato-aceito"
            },
            error: function (xhr) {
                callback(xhr);
            }
        });

    });

    $("#termoCadastrado").scroll(function () {

        if ($(this).scrollTop() + $(this).innerHeight() >= this.scrollHeight) {

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