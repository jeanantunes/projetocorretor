var metodoForcaVendaBloqueado  = "";

$(document).ready(function () {
    getPropertie("odontoprev.web.forcavenda.bloqueado",
        function (dataPropertie) {
            metodoForcaVendaBloqueado = dataPropertie;
            validarForcaIndex();
        })
});


function validarForcaVenda(callback) {

    callTokenVendas(function (dataToken) {

        if (dataToken.status == undefined) {

            var dadosUsuario = get("dadosUsuario");
            var cpfForcaVenda = undefined;

            getForcaVendaBloqueado(dadosUsuario.codigo, cpfForcaVenda, dataToken.access_token,

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

    });

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

                            swal("Corretora Bloqueada", "Sua corretora possui uma pendência de atualização contratual com a OdontoPrev.\n\n As vendas ficarão salvas na Lista de Propostas, por favor tente reenviar após resolução.", "info");
                            return;
                        }

                    }

                },
                function (dataUsuarioError) {

                }

            );
}