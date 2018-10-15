var metodoGetEmailForcaCorretora = "";

$(document).ready(function () {

    var propertieForcaBloqueado = "odontoprev.forcavendacorretora.email";

    getPropertie(propertieForcaBloqueado,

        function (dataPropertie) {

            metodoGetEmailForcaCorretora = dataPropertie;

        }
    );
});

function getEmailForcaCorretora(cdForcaVenda, callbackSuccess, callbackError){

    $.ajax({
        async: true,
        url: metodoGetEmailForcaCorretora.replace("{cdForcaVenda}", cdForcaVenda),
        method: "GET",
        success: function (resp) {
            callbackSuccess(resp);
        },
        error: function (xhr) {
            callbackError(xhr);
        }
    });

}