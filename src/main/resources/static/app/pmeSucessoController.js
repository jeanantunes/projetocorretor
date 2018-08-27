var URLBase = "";
var contexto = "";
var metodo = "";

$(document).ready(function () {

    localStorage.removeItem("proposta");

    getPropertie(function (dataUrlBase) {

        URLBase = dataUrlBase;

        getPropertie(function (dataContexto) {

            contexto = dataContexto;

            getPropertie(function (dataMetodo) {

                metodo = dataMetodo;

            },"odontoprev.arquivocontratacao");

        }, "odontoprev.corretor.api.contexto.api.url");

    }, "odontoprev.service.base");

    $("#baixarContrato").click(function () {

        swal({
            title: "Aguarde",
            text: 'Estamos baixando o contrato',
            content: "input",
            imageUrl: "img/load.gif",
            showCancelButton: false,
            showConfirmButton: false,
            icon: "info",
            button: {
                text: "...",
                closeModal: false,
            },
        });

        callTokenVendas(function (dataToken) {

            if (dataToken.status != undefined) {
                swal("Ops!", "Algo deu errado no download, por favor tente novamente.", "error");
                return;
            }

            var cdEmpresa = getUrlParameter("cdEmpresa");

            downloadContratoPdf(function (dataArquivo) {

                if (dataArquivo == undefined) {

                    swal("Ops!", "Algo deu errado no download, por favor tente novamente.", "error");
                    return;

                }

                if (dataArquivo.status != undefined) {

                    swal("Ops!", "Algo deu errado no download, por favor tente novamente.", "error");
                    return;

                }

                var link = document.createElement('a');
                link.href = 'data:application/pdf;base64,' + dataArquivo.arquivoBase64;
                link.download = dataArquivo.nomeArquivo;
                link.dispatchEvent(new MouseEvent('click'));

                swal.close();

            }, dataToken.access_token, cdEmpresa);

        });

    });





});

function getPropertie(callback, key){

    $.ajax({
        async: true,
        url: "/propertie?key=" + key,
        method: "GET",
        success: function (resp) {
            console.log(resp[key]);
            callback(resp[key]);
        },
        error: function (xhr) {
            callback(xhr);
        }
    });

}

function downloadContratoPdf(callback, token, cdEmpresa) {

    $.ajax({
        async: true,
        url: URLBase + contexto + metodo.replace("{CDEMPRESA}", cdEmpresa), //  "/arquivocontratacao/empresa/" + cdEmpresa + "/json",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {
            callback(xhr);
        }
    });

}
