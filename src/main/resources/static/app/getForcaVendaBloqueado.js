var metodoForcaVendaBloqueado = "";

$(document).ready(function () {

    var propertieForcaBloqueado = "odontoprev.web.forcavenda.bloqueado";

    getPropertie(propertieForcaBloqueado,

        function (dataPropertie) {

            metodoForcaVendaBloqueado = dataPropertie;

        });
});

function validarForcaVenda(callback) {

    getForcaVendaBloqueado(
        function (dataUsuarioSucess) {

            if (dataUsuarioSucess != undefined) {

                if (dataUsuarioSucess.temBloqueio) {

                    callback(403); // Usuario bloqueado forbiden / cancelar request de venda
                    return;
                }

                callback(200);// Usuario nao bloqueado OK(200) / enviar venda
                return;

            }

            callback(204); // Usuario nao encontrado

        },
        function (dataUsuarioError) {

            callback(500) // Erro no request (500) / enviar venda
            return;

        }
    )
}

function getForcaVendaBloqueado(callbackSucess, callbackError) {

    $.ajax({
        async: true,
        //url: URLBase + "/corretorservicos/1.0/forcavenda/bloqueio?cdForcaVenda=" + accessToken,
        url: metodoForcaVendaBloqueado,
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callbackSucess(resp);
        },
        error: function (xhr) {
            callbackError(xhr);
        }
    });

}
