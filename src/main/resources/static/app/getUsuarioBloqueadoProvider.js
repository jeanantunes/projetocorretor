var metodoForcaVendaBloqueado = "";

$(document).ready(function () {

    var propertieForcaBloqueado = "odontoprev.web.forcavenda.bloqueado";

    getPropertie(propertieForcaBloqueado,

        function (dataPropertie) {

            metodoForcaVendaBloqueado = dataPropertie;

            getPerfilUsuario(
                function (dataPerfilUsuario) {

                    var perfil = dataPerfilUsuario.perfil.toUpperCase();

                    if (perfil == "CORRETOR") {
                        validarForcaIndex();
                    } else {

                    }

                },
                function (errorPerfil) {

                })
        })
});

function getPerfilUsuario(callbackSucess, callbackError) {

    $.ajax({
        url: "/usuario_session",
        type: "get",
        async: false,
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            callbackSucess(result);
        },
        error: function (result) {
            callbackError(result)
        }
    });


}

function validarForcaVenda(callback) {

    getForcaVendaBloqueado(
        function (dataUsuarioSucess) {

            if (dataUsuarioSucess != undefined) {

                if (dataUsuarioSucess.temBloqueio) {

                    callback(403) // Usuario bloqueado forbiden / cancelar request de venda
                    return;
                }

                callback(200) // Usuario nao bloqueado OK(200) / enviar venda
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

function getRepository(pJsonName) {

    jsonName = pJsonName;

    $.ajax({
        url: "repositorio/" + jsonName + ".json",
        type: "get",
        async: false,
        success: function (result) {
            pdata = result;
        },
        error: function () {

        }
    });

    return eval(pdata);
}

function validarForcaIndex() {

    getForcaVendaBloqueado(function (dataUsuarioSucess) {

            if (dataUsuarioSucess != undefined) {

                if (dataUsuarioSucess.temBloqueio) {

                    //var fraseCorretoraBloqueada = getRepository("fraseCorretoraBloqueada");

                    swal("Corretora Bloqueada", "Sua corretora possui uma pendência de atualização contratual com a OdontoPrev, por favor tente refazer as vendas após resolução.", "info");
                    return;
                }

            }

        },
        function (dataUsuarioError) {

        }
    );
}