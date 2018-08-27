var preenchidos = false;

$(document).ready(function () {

    localStorage.removeItem("proposta");

});

/** TODO: Corrigir chamada AJAX, download Contrato PDF **/
function downloadContrato() {

    var cdEmpresa = getUrlParameter("cdEmpresa");

    $.ajax({
        async: true,
        url: "/downloadContratacao?cdEmpresa=" + cdEmpresa,
        //url: URLBase + "/corretorservicos/1.0/devicetoken/forcavenda/" + cdForcaVenda + "?token=" + tokenDeviceFirebase,
        method: "GET",
        success: function (resp) {
        },
        error: function (xhr) {
        }
    });

}
