$(document).ready(function () {
    var forca = new Object();
	console.log("Chamada UsuarioSession");
	$.ajax({
        url: "/usuario_session",
        type: "get",
        async: false,
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
        	console.log(result);
            forca.codigo = eval(result).codigoUsuario;
        },
        error: function (result) {
        	console.log(result);
        }
    });    
    
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
