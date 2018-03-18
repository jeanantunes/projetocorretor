$(document).ready(function () {
    var forca = new Object();
    forca.codigo = 6;

    put("dadosUsuario", JSON.stringify(forca)) ;

});

function iniciarProposta(cdPlano) {

    var proposta = getRepository("proposta");

    plano = getRepository("plano");
    plano.cdPlano = cdPlano;

    proposta.planos = [];
    proposta.planos.push(plano);

    put("proposta", JSON.stringify(proposta));

    window.location.href = "venda_pme_dados_proposta.html";
}
