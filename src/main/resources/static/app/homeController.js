$(document).ready(function () {

    var corretora = new Object();

    $.ajax({
        url: "/usuario_session",
        type: "get",
        async: false,
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            console.log(result);
            corretora.codigoCorretora = eval(result).codigoCorretora;
            corretora.dtAceiteContrato = eval(result).dtAceiteContrato;
        },
        error: function (result) {
            console.log(result);
        }
    });

    if (corretora.dtAceiteContrato != null){
        return false;
    }

    var olhouModal = localStorage.getItem("olhouModal");

    if (olhouModal == "false") {
        $('#btnModal').click();
        localStorage.setItem("olhouModal", true);
    }


    if ($("#radio-comSusep").click(function () {
        comSusep = $("#radio-comSusep").val();
        $(this).css({"border-color": "#1974CE"});
        $("#radio-comSusep").css("color", "#1974CE");

        $("#label-codSusep").removeClass("text-silver");
        $("#label-codSusep").addClass("text-blue-susep");

        $("#codSusep").removeClass("inputLabelSusepCinza");
        $("#codSusep").addClass("inputLabelSusepAzul");
        $("#codSusep").prop("disabled", false);
        $("#codSusep").css("color", "#1974CE");

        $("#btnSusep").removeClass("background-color-azul");
        $("#btnSusep").addClass("background-color-cinza");
        $("#btnSusep").addClass('disabled');

        $("#codSusep").click(function () {
            $("#codSusep").mask("00.00000000000-0");
        });

        $("#codSusep").blur(function () {
            if ($("#codSusep").val().length == 16) {
                $("#btnSusep").addClass("background-color-azul");
                $("#btnSusep").removeClass("background-color-cinza");
                $("#btnSusep").removeClass('disabled');
            }else {
                $("#btnSusep").removeClass("background-color-azul");
                $("#btnSusep").addClass("background-color-cinza");
                $("#btnSusep").addClass('disabled');
            }
        });


    }));

    if ($("#radio-semSusep").click(function () {
        semSusep = $("#radio-semSusep").val();
        $(this).css({"border-color": "#1974CE"});
        $("#radio-semSusep").css("color", "#1974CE");
        $("#label-codSusep").addClass("text-silver");
        $("#label-codSusep").removeClass("text-blue-susep");
        $("#codSusep").addClass("inputLabelSusepCinza");
        $("#codSusep").removeClass("inputLabelSusepAzul");
        $("#codSusep").prop("disabled", true);
        $("#btnSusep").addClass("background-color-azul");
        $("#btnSusep").removeClass("background-color-cinza");
        $("#btnSusep").removeClass('disabled');
        $("#codSusep").val("");
    }));

    $("#btnSusep").removeClass("background-color-azul");
    $("#btnSusep").addClass("background-color-cinza");
    $("#btnSusep").addClass('disabled');
    $("#label-codSusep").css("color", "#6A6A6A");
    $("#codSusep").css("color", "#6A6A6A");
    $("#codSusep").prop("disabled", true);



    $("#btnSusep").click(function () {
        if ($("#codSusep").val() != null) {
            window.location.href = "/corretora/contrato?codSusep=" + $("#codSusep").val();
        }else{
            window.location.href = "/corretora/contrato";
        }
    });

});

