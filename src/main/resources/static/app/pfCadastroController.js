var preenchidos = false;

$(document).ready(function () {
    buscarPlanosSelecionados();
    carregarProposta();
    localStorage.removeItem("dependentePfEmEdicao");
    resizeIframe('frame_pf');
});

function addDependente() {

    if ($("#cpf").val() == "") {
        swal("Ops!", "Preencha o CPF", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if (!ValidaNome($(".nome").val())) {
        swal("Ops!", "Nome inválido", "error");
        $("#nomeBeneficiario").focus();
        return false;
    }

    if (!TestaCPF($("#cpf").val())) {
        swal("Ops!", "CPF inválido", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".nome").val() == "") {
        swal("Ops!", "Preencha o Nome", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".email").val() == "") {
        swal("Ops!", "Preencha o E-mail", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if (!validateEmail($(".email").val())) {
        swal("Ops!", "Preencha um E-mail válido", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".celular").val() == "") {
        swal("Ops!", "Preencha o celular", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".cpf").val() == "" || !TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {

        $("#cpf").focus();
        swal("Ops!", "Preencha o CPF", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if (!validarData($(".nascimento").val())) {
        swal("Ops!", "Preencha uma data de nascimento válida", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".nascimento").val() == "") {
        swal("Ops!", "Preencha a Data de Nascimento", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    var date = toDate($("#dataNascimentoTitular").val());

    if (!isMaiorDeIdade(date)) {

        if ($("#nomeResponsavel").val() == "") {
            swal("Ops!", "Preencha o nome do representante legal", "error");
            return;
        }

        if (!ValidaNome($("#nomeResponsavel").val())) {
            swal("Ops!", "Nome do representante legal inválido", "error");
            return false;
        }

        if ($("#emailRepresentanteLegal").val() == "") {
            swal("Ops!", "Preencha o e-mail do representante legal", "error");
            return;
        }

        if (!validateEmail($("#emailRepresentanteLegal").val())) {
            swal("Ops!", "Email do representante legal inválido", "error");
            return;
        }

        if ($(".celular-representante-legal").val() == "") {
            swal("Ops!", "Preencha o celular do representante legal", "error");
            return;
        }

        if ($("#cpf-representante").val() == "") {

            swal("Ops!", "Preencha o CPF do representante legal", "error");
            return;
        }

        if (!TestaCPF($("#cpf-representante").val().replace().replace(/\D/g, ''))) {
            swal("Ops!", "CPF do representante legal está inválido", "error");
            return;
        }

        if ($("#cpf-representante").val() == $("#cpf").val()) {

            swal("Ops!", "O representante legal não pode ter o mesmo CPF do titular", "error");
            return;
        }

        if ($("#dataNascimentoResponsavel").val() == "") {
            swal("Ops!", "Preencha a data de nascimento do responsável", "error");
            return;
        }

        var dateResponsavelLegal = toDate($("#dataNascimentoResponsavel").val());

        if (!isMaiorDeIdade(dateResponsavelLegal)) {
            swal("Ops!", "O responsável legal não pode ser menor de idade", "error");
            return;
        }

        if ($("#radio-3").is(":checked") == false && $("#radio-4").is(":checked") == false) {
            swal("Ops!", "Selecione o sexo do responsável legal", "error");
            $(".dependentes").val(0);
            return;
        }
    }

    if ($("#radio-1").is(":checked") == false && $("#radio-2").is(":checked") == false) {
        swal("Ops!", "Selecione o Sexo", "error");
        $(".dependentes").val(0);
        return;
    }

    if ($(".nome-mae").val() == "") {
        swal("Ops!", "Preencha Nome da Mãe", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if (!ValidaNome($("#nomeMae").val())) {
        swal("Ops!", "Nome da mãe inválido", "error");
        $("#nomeBeneficiario").focus();
        return false;
    }

    if ($(".cep").val() == "") {
        swal("Ops!", "Preencha o cep", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".rua").val() == "") {
        swal("Ops!", "Preencha o endereço", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".numeroEndereco").val() == "") {
        swal("Ops!", "Preencha o número do endereço", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".bairro").val() == "") {
        swal("Ops!", "Preencha o bairro", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".cidade").val() == "") {
        swal("Ops!", "Preencha o cidade", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if ($(".estado").val() == "") {
        swal("Ops!", "Preencha o estado", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    //var idade = toDate($(".nascimento").val());
    //
    //if (!isMaiorDeIdade(idade)) {
    //    swal("Ops!", "O Titular não pode ser menor de idade", "error");
    //    return;
    //}

    salvarRascunhoMemoria();
    window.location = "venda_pf_dados_dependentes.html";
}

function buscarPlanosSelecionados() {

    var proposta = get("propostaPf");
    var planos = get("planos");

    if (proposta == null) {
        window.location.href = "venda_index_pf.html";
    }

    $.each(proposta.planos, function (i, item) {
        var o = planos.filter(function (x) { return x.cdPlano == item.cdPlano });
        var plano = getComponent("plano");

        plano = plano.replace("{CDPLANO}", o[0].cdPlano);
        plano = plano.replace("{CDPLANO-BT}", o[0].cdPlano);
        plano = plano.replace("{NOME}", o[0].nome);
        plano = plano.replace("{DESC}", o[0].desc);
        plano = plano.replace("{VALOR}", o[0].valor);
        plano = plano.replace("{CENTAVO}", o[0].centavo);
        plano = plano.replace("{CSS}", o[0].css);
        plano = plano.replace("{CSSVALOR}", o[0].css);

        $("#planos").append(plano);
    });

    $(".labelQuantidadeBeneficiarios").addClass('hide');
    resizeIframe('frame_pf');
}

//$("#cpf").blur(function () {
//
//    if (!TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {
//        swal("Ops", "CPF inválido", "error");
//    }
//});

$("#dataNascimentoTitular").blur(function () {

    if ($("#dataNascimentoTitular").val() == "") return;

    var date = toDate($("#dataNascimentoTitular").val());

    if (isMaiorDeIdade(date)) {

        $(".representanteContratual").addClass('hide');
        resizeIframe('frame_pf');
        return;
    }

    $(".representanteContratual").removeClass('hide');
    resizeIframe('frame_pf');

});

function excluirPlano(obj) {

    var container = $(".div-excluir[data-id=" + $(obj).attr("data-id") + "]");
    var proposta = get("propostaPf");

    if (proposta == null) {
        proposta = getRepository("proposta");
    }

    var planosExcetoExcluido = proposta.planos.filter(function (x) { return x.cdPlano != container.attr("data-id") });
    proposta.planos = [];
    $.each(planosExcetoExcluido, function (i, item) {
        proposta.planos.push(item);
    });

    put("propostaPf", JSON.stringify(proposta));
    container.remove();

    swal({
        title: "Exclusão de Plano",
        text: "Você precisa escolher outro plano.",
        type: "info",
        showCancelButton: false,
        confirmButtonText: 'OK',
        closeOnConfirm: false,
        closeOnCancel: false
    },
        function (isConfirm) {
            salvarRascunhoMemoria();
            window.location.href = "venda_index_pf.html";
        });
}

function salvarRascunho() {

    if ($(".nome").val() == "") {
        swal("Ops!", "Preencha o Nome", "error");
        $("#nomeBeneficiario").focus();
        return;
    }

    if (!ValidaNome($(".nome").val())) {
        swal("Ops!", "Nome inválido", "error");
        $("#nomeBeneficiario").focus();
        return false;
    }

    if ($(".email").val() == "") {
        swal("Ops!", "Preencha o E-mail", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if (!validateEmail($(".email").val())) {
        swal("Ops!", "Email do titular inválido", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".celular").val() == "") {
        swal("Ops!", "Preencha o celular", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".cpf").val() == "" || !TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {

        $("#cpf").focus();
        swal("Ops!", "Preencha o CPF", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if (!validarData($(".nascimento").val())) {
        swal("Ops!", "Preencha uma data de nascimento correta", "error");
		$("#nomeBeneficiario").focus();
        return;
    }


    if ($(".nascimento").val() == "") {
        swal("Ops!", "Preencha a Data de Nascimento", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($("#radio-1").is(":checked") == false && $("#radio-2").is(":checked") == false) {
        swal("Ops!", "Selecione o Sexo", "error");
        $(".dependentes").val(0);
        return;
    }

    if ($(".nome-mae").val() == "") {
        swal("Ops!", "Preencha Nome da Mãe", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    var date = toDate($("#dataNascimentoTitular").val());

    if (!isMaiorDeIdade(date)) {

        if ($("#nomeResponsavel").val() == "") {
            swal("Ops!", "Preencha o nome do representante legal", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if (!ValidaNome($("#nomeResponsavel").val())) {
            swal("Ops!", "Nome do representante legal inválido", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if ($("#emailRepresentanteLegal").val() == "") {
            swal("Ops!", "Preencha o e-mail do representante legal", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if (!validateEmail($("#emailRepresentanteLegal").val())) {
            swal("Ops!", "Email do representante legal inválido", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if ($(".celular-representante-legal").val() == "") {
            swal("Ops!", "Preencha o celular do representante legal", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if ($("#cpf-representante").val() == "") {

            swal("Ops!", "Preencha o CPF do representante legal", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if (!TestaCPF($("#cpf-representante").val().replace().replace(/\D/g, '')))
        {
            swal("Ops!", "CPF do representante legal está inválido", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if ($("#dataNascimentoResponsavel").val() == "") {
            swal("Ops!", "Preencha a data de nascimento do responsável", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        var dateResponsavelLegal = toDate($("#dataNascimentoResponsavel").val());

        if (!isMaiorDeIdade(dateResponsavelLegal)) {
            swal("Ops!", "O responsável legal não pode ser menor de idade", "error");
            $("#nomeResponsavel").focus();
			return;
        }

        if ($("#radio-3").is(":checked") == false && $("#radio-4").is(":checked") == false) {
            swal("Ops!", "Selecione o sexo do responsável legal", "error");
            $(".dependentes").val(0);
            return;
        }
    }

    if (!ValidaNome($("#nomeMae").val())) {
        swal("Ops!", "Nome da mãe inválido", "error");
		$("#nomeResponsavel").focus();
		return;
    }

    if ($(".cep").val() == "") {
        swal("Ops!", "Preencha o cep", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".rua").val() == "") {
        swal("Ops!", "Preencha o endereço", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".numeroEndereco").val() == "") {
        swal("Ops!", "Preencha o número do endereço", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".bairro").val() == "") {
        swal("Ops!", "Preencha o bairro", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".cidade").val() == "") {
        swal("Ops!", "Preencha o cidade", "error");
		$("#nomeBeneficiario").focus();
        return;
    }

    if ($(".estado").val() == "") {
        swal("Ops!", "Preencha o estado", "error");
		$("#nomeBeneficiario").focus();
        return;
    }


    //var currentYear = (new Date).getFullYear();
    //var idade = $(".nascimento").val().split("/");
    //var menor = currentYear - idade[2];
    //
    //if (menor < 18) {
    //    swal("Ops!", "O Titular não pode ser menor de idade", "error");
    //    return;
    //}

    salvarRascunhoMemoria();
    window.location.href = "resumo_pf_proposta.html";
}

function salvarRascunhoMemoria() {

    var proposta = get("propostaPf");
    proposta.status = "DIGITANDO";
    proposta.nome = $(".nome").val();
    proposta.cpf = $(".cpf").val();
    proposta.nomeMae = $("#nomeMae").val();
    proposta.dataNascimento = $(".nascimento").val();

    var date = toDate($("#dataNascimentoTitular").val());

    if (!isMaiorDeIdade(date)) {
        //$(".representanteContratual").addClass('hide');

        proposta.responsavelContratual.nome = $("#nomeResponsavel").val();
        proposta.responsavelContratual.cpf = $("#cpf-representante").val();
        proposta.responsavelContratual.dataNascimento = $("#dataNascimentoResponsavel").val();
        proposta.responsavelContratual.email = $("#emailRepresentanteLegal").val();
        proposta.responsavelContratual.celular = $(".celular-representante-legal").val();

        if ($("#radio-3").is(":checked") == true) {
            proposta.responsavelContratual.sexo = $("#radio-3").val();
        }
        else {
            proposta.responsavelContratual.sexo = $("#radio-4").val();
        }

        proposta.responsavelContratual.endereco.cep = $(".cep").val();
        proposta.responsavelContratual.endereco.logradouro = $(".endereco").val();
        proposta.responsavelContratual.endereco.numero = $(".numero").val();
        proposta.responsavelContratual.endereco.complemento = $(".complemento").val();
        proposta.responsavelContratual.endereco.bairro = $(".bairro").val();
        proposta.responsavelContratual.endereco.cidade = ($(".cidade").val());
        proposta.responsavelContratual.endereco.estado = $(".estado").val();
    }

    proposta.contatoEmpresa = $("#squaredOne").is(":checked");
    proposta.telefone = $(".telefone").val();
    proposta.celular = $(".celular").val();
    proposta.email = $(".email").val();
    proposta.endereco.cep = $(".cep").val();
    proposta.endereco.logradouro = $(".endereco").val();
    proposta.endereco.numero = $(".numero").val();
    proposta.endereco.complemento = $(".complemento").val();
    proposta.endereco.bairro = $(".bairro").val();
    proposta.endereco.cidade = ($(".cidade").val());
    proposta.endereco.estado = $(".estado").val();

    if ($("#radio-1").is(":checked") == true) {
        proposta.sexo = $("#radio-1").val();
    }
    else {
        proposta.sexo = $("#radio-2").val();
    }

    var pessoas = get("pessoas");

    if (pessoas == null) {
        pessoas = [];
        pessoas.push(proposta);
    }
    else {
        var propostas = pessoas.filter(function (x) { return x.cpf != proposta.cpf });
        pessoas = []; //limpar

        $.each(propostas, function (i, item) {
            pessoas.push(item);
        });

        pessoas.push(proposta);
    }

    put("pessoas", JSON.stringify(pessoas));
    put("propostaPf", JSON.stringify(proposta));
}

function carregarProposta() {
    var proposta = get("propostaPf");
    $("#nomeBeneficiario").val(proposta.nome);
    $(".cpf").val(proposta.cpf);

    //if (proposta.contatoEmpresa) {
    //    $("#squaredOne").attr("checked", true);
    //}
    //else {
    //    $("#squaredOne").attr("checked", false);
    //}

    if (proposta.sexo == "m") {
        $("#radio-2").attr("checked", true);
    }
    else {
        $("#radio-1").attr("checked", true);
    }

    $("#nomeMae").val(proposta.nomeMae);
    $(".telefone").val(proposta.telefone);
    $(".nascimento").val(proposta.dataNascimento);

    if (proposta.dataNascimento != "") {

        var date = toDate(proposta.dataNascimento);

        if (!isMaiorDeIdade(date)) {

            $(".representanteContratual").removeClass('hide');

            $("#nomeResponsavel").val(proposta.responsavelContratual.nome);
            $("#cpf-representante").val(proposta.responsavelContratual.cpf);
            $("#dataNascimentoResponsavel").val(proposta.responsavelContratual.dataNascimento);
            $("#emailRepresentanteLegal").val(proposta.responsavelContratual.email);
            $(".celular-representante-legal").val(proposta.responsavelContratual.celular);

            if (proposta.responsavelContratual.sexo == "m") {
                $("#radio-4").attr("checked", true);
            }
            else {
                $("#radio-3").attr("checked", true);
            }
        }
    }

    $(".celular").val(proposta.celular);
    $(".email").val(proposta.email);
    $(".cep").val(proposta.endereco.cep);
    $(".endereco").val(proposta.endereco.logradouro);
    $(".numero").val(proposta.endereco.numero);
    $(".complemento").val(proposta.endereco.complemento);
    $(".bairro").val(proposta.endereco.bairro);
    $(".cidade").val(proposta.endereco.cidade);
    $(".estado").val(proposta.endereco.estado);

    listarDependentes();
}

function listarDependentes() {
    var proposta = get("propostaPf");

    $.each(proposta.dependentes, function (i, item) {

        var dep = getComponent("dependente");
        dep = dep.replace("{CPF}", (item.cpf == "" ? item.nome : item.cpf));
        dep = dep.replace("{CPF-BT}", (item.cpf == "" ? item.nome : item.cpf));
        dep = dep.replace("{CPF-BTN-EDITAR}", (item.cpf == "" ? item.nome : item.cpf));
        dep = dep.replace("{CPF-DESC}", item.cpf);
        dep = dep.replace("{NOME}", item.nome);
        dep = dep.replace("{NOME-DEP}", item.nome);
        dep = dep.replace("{NASCIMENTO-EDITAR}", item.dataNascimento);
        dep = dep.replace("{NASCIMENTO-EXCLUIR}", item.dataNascimento);
        dep = dep.replace("{NASCIMENTOBOX}", item.dataNascimento);
        $("#listaDep").append(dep);
    });

    //$(".btnEditar").addClass('hide');
}

function validarCampos() {

    if ($(".nome").val() == "") {
        swal("Ops!", "Preencha o Nome", "error");
        return false;
    }

    if (!ValidaNome($(".nome").val())) {
        swal("Ops!", "Nome inválido", "error");
        return false;
    }

    if ($(".email").val() == "") {
        swal("Ops!", "Preencha o E-mail", "error");
        return false;
    }

    if (!validateEmail($(".email").val())) {
        swal("Ops!", "Email do titular inválido", "error");
        return false;
    }

    if ($(".celular").val() == "") {
        swal("Ops!", "Preencha o celular do titular", "error");
        return false;
    }

    if ($(".celular").val().length < 14) {
        swal("Ops!", "Preencha o celular do titular", "error");
        return false;
    }

    if ($(".cpf").val() == "" || !TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {

        $("#cpf").focus();
        swal("Ops!", "Preencha o CPF", "error");
        return false;
    }

    if ($(".nascimento").val() == "") {
        swal("Ops!", "Preencha a Data de Nascimento", "error");
        return false;
    }

    if (!validarData($(".nascimento").val())) {
        swal("Ops!", "Preencha uma data de nascimento correta", "error");
        return false;
    }

    if ($("#radio-1").is(":checked") == false && $("#radio-2").is(":checked") == false) {
        swal("Ops!", "Selecione o Sexo", "error");
        $(".dependentes").val(0);
        return false;
    }

    if ($(".nome-mae").val() == "") {
        swal("Ops!", "Preencha Nome da Mãe", "error");
        return false;
    }

    var date = toDate($("#dataNascimentoTitular").val());

    if (!isMaiorDeIdade(date)) {

        if ($("#nomeResponsavel").val() == "") {
            swal("Ops!", "Preencha o nome do representante legal", "error");
            return false;
        }

        if (!ValidaNome($("#nomeResponsavel").val())) {
            swal("Ops!", "Nome do representante legal inválido", "error");
            return false;
        }

        if ($("#emailRepresentanteLegal").val() == "") {
            swal("Ops!", "Preencha o e-mail do representante legal", "error");
            return false;
        }

        if (!validateEmail($("#emailRepresentanteLegal").val())) {
            swal("Ops!", "Email do representante legal inválido", "error");
            return false;
        }

        if ($(".celular-representante-legal").val() == "") {
            swal("Ops!", "Preencha o celular do representante legal", "error");
            return false;
        }

        if ($(".celular-representante-legal").val().length < 14) {
            swal("Ops!", "Preencha o celular do representante legal", "error");
            return false;
        }

        if ($("#cpf-representante").val() == "") {

            swal("Ops!", "Preencha o CPF do representante legal", "error");
            return false;
        }

        if (!TestaCPF($("#cpf-representante").val().replace().replace(/\D/g, ''))) {
            swal("Ops!", "CPF do representante legal está inválido", "error");
            return false;
        }

        if ($("#dataNascimentoResponsavel").val() == "") {
            swal("Ops!", "Preencha a data de nascimento do responsável", "error");
            return false;
        }

        var dateResponsavelLegal = toDate($("#dataNascimentoResponsavel").val());

        if (!isMaiorDeIdade(dateResponsavelLegal)) {
            swal("Ops!", "O responsável legal não pode ser menor de idade", "error");
            return false;
        }

        if ($("#radio-3").is(":checked") == false && $("#radio-4").is(":checked") == false) {
            swal("Ops!", "Selecione o sexo do responsável legal", "error");
            $(".dependentes").val(0);
            return false;
        }
    }

    var proposta = get("propostaPf");

    if (proposta.planos.length == 0) {

        swal("Ops!", "Por favor, escolha um plano!", "error");
        return false;
    }

    var planos = get("CodPlanos");
    var plano = planos.filter(function (x) { return x.cdPlano == proposta.planos[0].cdPlano });



    if (!menorQueSeteAnos(date) && plano[0].nome.indexOf("DENTE DE LEITE") !== -1) {

        swal("Ops!", "No plano dente de leite o titular deve ter menos que 7 anos", "error");
        return false;
    }

    if (!ValidaNome($("#nomeMae").val())) {
        swal("Ops!", "Nome da mãe inválido", "error");
        return false;
    }

    if ($(".cep").val() == "") {
        swal("Ops!", "Preencha o cep", "error");
        return false;
    }

    if ($("#rua").val() == "") {
        swal("Ops!", "Preencha o endereço", "error");
        return false;
    }

    if ($(".numero").val() == "") {
        swal("Ops!", "Preencha o número do endereço", "error");
        return false;
    }

    if ($("#bairro").val() == "") {
        swal("Ops!", "Preencha o bairro", "error");
        return false;
    }

    if ($(".cidade").val() == "") {
        swal("Ops!", "Preencha o cidade", "error");
        return false;
    }

    if ($(".estado").val() == "") {
        swal("Ops!", "Preencha o estado", "error");
        return false;
    }

    return true;

}

function editarDependente(obj) {

    if (!validarCampos()) return;
    salvarRascunhoMemoria();

    var container = $(".div-excluir[data-id='" + $(obj).attr("data-id") + "'][data-nascimento='" + $(obj).attr("data-nascimento") + "']");
    var beneficiario = get("propostaPf");

    var beneficiarioEmEdicao = beneficiario.dependentes.filter(function (x) {

        if (container.attr("data-id") == container.attr("data-nome")) return x.nome == container.attr("data-nome") && x.dataNascimento == container.attr("data-nascimento")

        return x.cpf == container.attr("data-id")
    });

    put("dependentePfEmEdicao", JSON.stringify(beneficiarioEmEdicao[0]));

    window.location.href = "venda_pf_dependente_edicao.html";
}


function excluirDep(obj) {

    var container = $(".div-excluir[data-id='" + $(obj).attr("data-id") + "'][data-nascimento='" + $(obj).attr("data-nascimento") + "']");

    //if (container.length == 0) {
    //
    //    var container = $(".div-excluir[data-id='" + $(obj).attr("data-nome") + "']");
    //}
    var proposta = get("propostaPf");

    var propostaExcetoExcluido = proposta.dependentes.filter(function (x) {

        if (container.attr("data-id") == container.attr("data-nome")) return x.nome != container.attr("data-nome") || x.dataNascimento != container.attr("data-nascimento")

        return x.cpf != container.attr("data-id")
    });

    proposta.dependentes = [];

    $.each(propostaExcetoExcluido, function (i, item) {
        proposta.dependentes.push(item);
    });

    put("propostaPf", JSON.stringify(proposta));
    container.remove();
    resizeIframe('frame_pf');
    atualizarPessoas(proposta);
}


// Mantém os inputs em cache:
var inputs = $('input');

// Chama a função de verificação quando as entradas forem modificadas
// Usei o 'keyup', mas 'change' ou 'keydown' são também eventos úteis aqui
inputs.on('keyup', verificarInputs);

function verificarInputs() {
    inputs.each(function () {
        // verificar um a um e passar a false se algum falhar
        // no lugar do if pode-se usar alguma função de validação, regex ou outros
        var id = this.id;
        //console.log(id);

        if (!this.value) {
            preenchidos = false;
            //swal("Ops!", "Por Favor preencha todos os Dados");

            // parar o loop, evitando que mais inputs sejam verificados sem necessidade
            return false;
        }

    });

    preenchidos = true;  // assumir que estão preenchidos

    // Habilite, ou não, o <button>, dependendo da variável:
    $("#continuarVendaPf").removeClass('disabled'); //,
    resizeIframe('frame_pf');
    return true;
}