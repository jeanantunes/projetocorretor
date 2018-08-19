$(document).ready(function () {
    $(".bancos").change(function () {
        if ($(this).val() == "341") {
            swal("Atenção!", "Lembre o seu cliente de que o Itaú exige liberação para o débito em conta.", "info");
        }
    });
});

$("#contaDebito").keyup(function () {

    $("#continuarPfDebito").addClass('disabled');
    if ($(this).val() == "" || $("#agenciaDebito").val() == "") {
        return;
    }

    $("#continuarPfDebito").removeClass('disabled');
});

$(function () {
    var regex = new RegExp('[^0-9]', 'g');
    // repare a flag "g" de global, para substituir todas as ocorr�ncias
    $('.agencia').bind('input', function () {
        $(this).val($(this).val().replace(regex, ''));
    });
    $('.conta').bind('input', function () {
        $(this).val($(this).val().replace(regex, ''));
    });
});

$(function () {
    var regex = new RegExp('[^a-zA-Z0-9]', 'g');
    // repare a flag "g" de global, para substituir todas as ocorr�ncias
    $('.conta-corrente').bind('input', function () {
        $(this).val($(this).val().replace(regex, ''));
    });
});

$("#agenciaDebito").keyup(function () {

    $("#continuarPfDebito").addClass('disabled');
    if ($(this).val() == "" || $("#contaDebito").val() == "") {

        return;
    }

    $("#continuarPfDebito").removeClass('disabled');
});

function cadastrarConta() {

    if ($(".bancos").val() == " ") {
        swal("Ops!", "Selecione o banco", "error");
        $(".bancos").focus();
        return;
    }

    if ($(".agencia").val() == "") {
        swal("Ops!", "Preencha a agencia", "error");
        $(".agencia").focus();
        return;
    }

    if ($("#conta-corrente").val() == "") {
        swal("Ops!", "Preencha a conta corrente", "error");
        $("#conta-corrente").focus();
        return;
    }

    var proposta = get("propostaPf");
    var cdBanco = $(".bancos").val();
    var agencia = $("#agenciaDebito").val();
    var conta = $("#contaDebito").val();

    while (agencia.length < 4) {
        agencia = "0" + agencia;
    }

    proposta.dadosBancarios.codigoBanco = cdBanco;
    proposta.dadosBancarios.agencia = agencia;
    proposta.dadosBancarios.conta = conta;
    proposta.dadosBancarios.tipoConta = "CC";

    proposta.status = "PRONTA";

    atualizarPessoas(proposta);
    put("propostaPf", JSON.stringify(proposta));
    enviarPropostaPf();
}