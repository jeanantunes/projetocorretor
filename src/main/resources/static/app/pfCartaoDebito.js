emRequisicao = false;

$(document).ready(function () {
    $(".bancos").change(function () {
        if ($(this).val() == "341") {
            swal("Atenção!", "Lembre o seu cliente de que o Itaú exige liberação para o débito em conta.", "info");
        }
    });

    $("#contaDebito").keyup(function () {

        $("#continuarPfDebito").prop('disabled', true);
        if ($(this).val() == "" || $("#agenciaDebito").val() == "") {
            return;
        }

        $("#continuarPfDebito").prop('disabled', false);
    });

    $("#agenciaDebito").keyup(function () {

        $("#continuarPfDebito").prop('disabled', true);

        if ($(this).val() == "" || $("#contaDebito").val() == "") {

            return;
        }

        $("#continuarPfDebito").prop('disabled', false);
    });

    $("#continuarPfDebito").click(function () {

        if (emRequisicao) return;

        emRequisicao = true;

        $("#continuarPfDebito").prop('disabled', true);

        cadastrarConta();

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

});

function cadastrarConta() {

    if ($(".bancos").val() == " ") {
        swal("Ops!", "Selecione o banco", "error");
        $(".bancos").focus();
        emRequisicao = false;
        return;
    }

    if ($(".agencia").val() == "") {
        swal("Ops!", "Preencha a agencia", "error");
        $(".agencia").focus();
        emRequisicao = false;
        return;
    }

    if ($("#conta-corrente").val() == "") {
        swal("Ops!", "Preencha a conta corrente", "error");
        $("#conta-corrente").focus();
        emRequisicao = false;
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

    validarForcaVenda(function (retornoForcaVenda) {

        if (retornoForcaVenda != 403) {

            enviarPropostaPf();

        } else {

            var fraseCorretoraBloqueada = getRepository("fraseCorretoraBloqueada");
            swal(fraseCorretoraBloqueada.title, fraseCorretoraBloqueada.descricao, fraseCorretoraBloqueada.tipo);

            $("#continuarPfDebito").prop('disabled', false);
            emRequisicao = false;
            return;
        }


    });
}