var metodoForcaVendaBloqueado = "";
var metodoCorretoraBloqueada = "";

$(document).ready(function () {

    var propertieForcaBloqueado = "odontoprev.web.forcavenda.bloqueado";
    var propertieCorretoraBloqueado = "odontoprev.web.corretora.bloqueada";

    getPropertie(propertieForcaBloqueado,

        function (dataPropertie) {

            metodoForcaVendaBloqueado = dataPropertie;

            getPerfilUsuario(
                function (dataPerfilUsuario) {

                    var perfil = dataPerfilUsuario.perfil.toUpperCase();

                    if (perfil == "CORRETOR") {
                        validarForcaIndex();
                    }

                },
                function (errorPerfil) {

                })
        });

    getPropertie(propertieCorretoraBloqueado,

        function (dataPropertie) {

            metodoCorretoraBloqueada = dataPropertie;

            getPerfilUsuario(
                function (dataPerfilUsuario) {

                    var perfil = dataPerfilUsuario.perfil.toUpperCase();

                    if (perfil == "CORRETORA") {
                        validarCorretora();
                    }
                },
                function (errorPerfil) {}
            );
        },
        function (errorCorretora) {
            
        }
    );
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

function validarCorretora() {

    getCorretoraBloqueada(
        function (dataCorretoraSucess) {

            if (dataCorretoraSucess != undefined) {

                if (dataCorretoraSucess.login.temBloqueio) {

                    swal("Corretora Bloqueada", "A corretora possui uma pendência de atualização contratual com a OdontoPrev, vendas e alterações de equipe serão normalizadas após aceite do contrato.", "info");
                    return;
                }

            }

        },
        function (errorGetCorrertora) {


        })
    
}

function validarForcaVenda(callback) {

    getForcaVendaBloqueado(
        function (dataUsuarioSucess) {

            if (dataUsuarioSucess != undefined) {

                if (dataUsuarioSucess.temBloqueio) {

                    callback(403); // Usuario bloqueado forbiden / cancelar request de venda
                    return;
                }

                callback(200); // Usuario nao bloqueado OK(200) / enviar venda
                return;

            }

            callback(204); // Usuario nao encontrado

        },
        function (dataUsuarioError) {

            callback(500); // Erro no request (500) / enviar venda
            return;

        }
    )
}

function getForcaVendaBloqueado(callbackSucess, callbackError) {

    $.ajax({
        async: true,
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

function getCorretoraBloqueada(callbackSucess, callbackError) {

    $.ajax({
        async: true,
        url: metodoCorretoraBloqueada,
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

                    swal("Corretora Bloqueada", "Sua corretora possui uma pendência de atualização contratual com a OdontoPrev, por favor tente refazer as vendas após resolução.", "info");
                    return;
                }

            }

        },
        function (dataUsuarioError) {

        }
    );
}