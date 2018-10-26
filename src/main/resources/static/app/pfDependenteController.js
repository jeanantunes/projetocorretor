﻿var problema = true;

$(document).ready(function () {

    $('.nascimento').mask('00/00/0000');
    $('.cpf').mask('000.000.000-00');
    $('.cpf').off('blur').on();

});

function SalvarDependente() {

    var currentYear = (new Date).getFullYear();
    var idade = $(".nascimento").val().split("/");

    if ($("#nome").val() == "" || !ValidaNome($("#nome").val())) {
        swal("Ops!", "Preencha nome completo do dependente", "error");
        $("#nome").focus();
        return;
    }

    if ($("#email").val() == "" || !validateEmail($("#email").val())) {
        swal("Ops!", "Preencha um E-mail válido", "error");
        $("#email").focus();
        return;
    }

    if ($("#celular").val() == "") {
        swal("Ops!", "Preencha o Celular", "error");
        $("#celular").focus();
        return;
    }

    if ($("#nascimento").val() == "" || !validarData($("#nascimento").val())) {
        swal("Ops!", "Preencha uma data de nascimento correta", "error");
        $("#nascimento").focus();
        return;
    }

    var benef = get("propostaPf");
    if (benef.cpf == $("#cpf").val() && $("#cpf").val() != "") {
        swal("Conflito!", "Você informou o mesmo CPF do titular para este dependente, por favor verifique.", "error");
        $("#cpf").focus();
        return;
    }

    if ($("#radio-1").is(":checked") != true && $("#radio-2").is(":checked") != true) {
        swal("Ops!", "Selecione o sexo do dependente", "error");
        $("#radio-1").focus();
        return false;
    }

    if ($("#nomeMae").val() == "" || !ValidaNome($("#nomeMae").val())) {
        swal("Ops!", "Nome da mãe inválido", "error");
        $("#nomeMae").focus();
        return false;
    }

    if (!validarNascimentoBeneficiario()) return false;

    var proposta = get("propostaPf");

    var listBeneficiarios = listCpfPropostaPf();
    var responsavelContratual = listBeneficiarios.filter(function (x) {
        return x.tipo == "responsavelContratual"
    });
    var dependentes = listBeneficiarios.filter(function (x) {
        return x.tipo == "dependente"
    });
    var checkDependentes = dependentes.filter(function (x) {
        return x.cpf == $(".cpf").val() && x.cpf != ""
    });
    var checkDependentesSemCpf = dependentes.filter(function (x) {
        return x.cpf == "" && x.nome == $(".nome").val() && x.dataNascimento == $(".nascimento").val()
    });

    if (checkDependentes.length > 0) {

        swal("Ops!", "Ops! Não será possível adicionar um dependente com dados duplicados em uma venda", "error")
        return;

    }

    if (checkDependentesSemCpf.length > 0) {

        swal("Ops!", "Ops! Não será possível adicionar um dependente com dados duplicados em uma venda", "error")
        return;

    }

    if (responsavelContratual.length > 0) {

        if (responsavelContratual[0].cpf == $(".cpf").val()) {

            if ($(".nome").val().trim() != responsavelContratual[0].nome) {

                swal("Ops!", "O nome digitado é diferente do nome do responsável contratual", "error")
                return;

            }

            if ($(".nascimento").val() != responsavelContratual[0].dataNascimento) {

                swal("Ops!", "A data de nascimento digitada é diferente do data de nascimento do responsável contratual", "error")
                return;
            }
        }
    }

    problema = false;

    var dependente = getRepository("dependente");
    dependente.nome = $(".nome").val();
    dependente.nome = dependente.nome.toUpperCase();
    dependente.dataNascimento = $(".nascimento").val();
    dependente.cpf = $(".cpf").val();
    dependente.email = $(".email").val();
    dependente.nomeMae = $(".nome-mae").val();
    dependente.nomeMae = dependente.nomeMae.toUpperCase();
    dependente.dataNascimento = $(".nascimento").val();

    if ($("#radio-1").is(":checked") == true) {
        dependente.sexo = $("#radio-1").val();
    }
    else {
        dependente.sexo = $("#radio-2").val();
    }

    dependente.celular = $("#celular").val();

    if (proposta.dependentes.length == 0) {
        proposta.dependentes = [];
    }

    proposta.dependentes.push(dependente);
    atualizarPropostasPfById(proposta);
}

$("#cpf").focusout(function () {

    var idade = $(".nascimento").val().split("/");
    var date = toDate(idade);

    if (!isMaiorDeIdade(date)) {
        var stringteste = $(this).val().replace(".", "");
        stringteste = stringteste.replace("-", "");
        stringteste = stringteste.replace(".", "");

        console.log(stringteste);

        if ($(this).val() == "" || !TestaCPF(stringteste)) {
            $(this).css({ "border-color": "#F00" });
            $(".label-cpf").css("color", "red");
            $(".cpf").css("color", "red");
        }
    }
});

$("#nascimento").focusout(function () {

    var dataNascimento = toDate($(this).val());

    if (isMaiorDeIdade(dataNascimento)) {

        var stringCpf = $('.cpf').val();

        if ($('.cpf').val() == "" || !TestaCPF(stringCpf)) {

            $('.cpf').css({"border-color": "#FF4141"});
            $(".label-cpf").css("color", "#FF4141");
            $(".cpf").css("color", "#FF4141");

        } else {

            $('.cpf').css({"border-color": "#1974CE"});
            $(".label-cpf").css("color", "#1974CE");
            $(".cpf").css("color", "#1974CE");

        }

    } else if ($('#nascimento').val() != "") {

        if (isMaiorDeIdade(dataNascimento)) {
            $('.cpf').css({"border-color": "#FF4141"});
            $(".label-cpf").css("color", "#FF4141");
            $(".cpf").css("color", "#FF4141");
            swal("Ops!", "CPF Obrigatório para maiores de idade", "error");
            $("#cpf").focus();
            return;
        }else if (!isMaiorDeIdade(dataNascimento)) {
            $('.cpf').css({"border-color": "#1974CE"});
            $(".label-cpf").css("color", "#1974CE");
            $(".cpf").css("color", "#1974CE");
        }

    }
});

function salvarEVoltar() {
    SalvarDependente();

    if (problema == false)
        window.location.href = 'venda_pf_dados_proposta.html';
}

function salvarEContinuar() {
    SalvarDependente();

    if (problema == false)
        window.location.href = 'venda_pf_dados_dependentes.html';
}

function validarNascimentoBeneficiario() {

    if ($(".nascimento").val() == "") return false;

    var date = toDate($(".nascimento").val());

    var planosInfantisJson = getRepository("planosInfantis"); // variaveis utilizada para verificar se nascimento do beneficiario
    var planosInfantis = [];                                  // estah de acordo com as regras do plano

    $.each(planosInfantisJson, function (indicePlano, itemPlano) {

        planosInfantis.push(itemPlano);

    });

    var possuiErros = false;
    var propostaPf = get("propostaPf");

    if (isMaiorDeIdade(date)) {

        $.each(propostaPf.planos, function (i, item) {

            var planoInfantil = planosInfantis.filter(function (x) { return x == item.cdPlano });

            if (planoInfantil.length > 0) {

                if (planoInfantil[0] == planosInfantisJson.dentalJuniorMensal || planoInfantil[0] == planosInfantisJson.dentalJuniorAnual) {

                    if ($(".cpf").val() != "" && !TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                        $("#cpf").focus();
                        exibirSwalCpfInvalidoInfantil();
                        possuiErros = true;
                        return;

                    }

                    var fraseSwal = getRepository("fraseMaiorDeIdadePlanoJunior");
                    swal(fraseSwal.title, fraseSwal.descricao, fraseSwal.tipo);
                    possuiErros = true;
                    return;

                } else if (planoInfantil[0] == planosInfantisJson.dentalDenteDeLeiteMensal || planoInfantil[0] == planosInfantisJson.dentalDenteDeLeiteAnual) {

                    if ($(".cpf").val() != "" && !TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                        $("#cpf").focus();
                        exibirSwalCpfInvalidoInfantil();
                        possuiErros = true;
                        return;

                    }

                    var fraseSwal = getRepository("fraseMaiorDeIdadePlanoDenteLeite");
                    swal(fraseSwal.title, fraseSwal.descricao, fraseSwal.tipo);
                    possuiErros = true;
                    return;

                }

            } else if ($(".cpf").val() == "" || !TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {

                $("#cpf").focus();
                swal("Ops!", "Preencha o CPF", "error");
                possuiErros = true;
                return;
            }

        })

        if (possuiErros) return false;

    } else if (isMaiorQueDezessete(date)) {

        $.each(propostaPf.planos, function (i, item) {

            var planoInfantil = planosInfantis.filter(function (x) { return x == item.cdPlano });

            if (planoInfantil.length > 0) {

                if (planoInfantil[0] == planosInfantisJson.dentalJuniorMensal || planoInfantil[0] == planosInfantisJson.dentalJuniorAnual) {

                    if ($(".cpf").val() != "" && !TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                        $("#cpf").focus();
                        exibirSwalCpfInvalidoInfantil();
                        possuiErros = true;
                        return;

                    }

                    var fraseSwal = getRepository("fraseMaiorDeIdadePlanoJunior");
                    swal(fraseSwal.title, fraseSwal.descricao, fraseSwal.tipo);
                    possuiErros = true;
                    return;

                } else if (planoInfantil[0] == planosInfantisJson.dentalDenteDeLeiteMensal || planoInfantil[0] == planosInfantisJson.dentalDenteDeLeiteAnual) {

                    if ($(".cpf").val() != "" && !TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                        $("#cpf").focus();
                        exibirSwalCpfInvalidoInfantil();
                        possuiErros = true;
                        return;

                    }

                    var fraseSwal = getRepository("fraseMaiorDeIdadePlanoDenteLeite");
                    swal(fraseSwal.title, fraseSwal.descricao, fraseSwal.tipo);
                    possuiErros = true;
                    return;

                }

            } else if ($(".cpf").val() != "") {

                if (!TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                    $("#cpf").focus();
                    swal("Ops!", "Preencha um CPF válido", "error");
                    possuiErros = true;
                    return;

                }

            }

        })

        if (possuiErros) return false;

    } else {

        $.each(propostaPf.planos, function (i, item) {

            if (item.cdPlano == planosInfantisJson.dentalDenteDeLeiteMensal || item.cdPlano == planosInfantisJson.dentalDenteDeLeiteAnual) {

                if ($(".cpf").val() != "" && !TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                    $("#cpf").focus();
                    exibirSwalCpfInvalidoInfantil();
                    possuiErros = true;
                    return;
                }

                if (!menorQueOitoAnos(date)) {

                    var fraseSwal = getRepository("fraseMaiorDeIdadePlanoDenteLeite");
                    swal(fraseSwal.title, fraseSwal.descricao, fraseSwal.tipo);
                    possuiErros = true;
                    return;

                }

            } else if (item.cdPlano == planosInfantisJson.dentalJuniorMensal || item.cdPlano == planosInfantisJson.dentalJuniorAnual) {

                if ($(".cpf").val() != "" && !TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                    $("#cpf").focus();
                    exibirSwalCpfInvalidoInfantil();
                    possuiErros = true;
                    return;
                }

                if (menorQueOitoAnos(date) || isMaiorQueDezessete(date)) {

                    var fraseSwal = getRepository("fraseMaiorDeIdadePlanoJunior");
                    swal(fraseSwal.title, fraseSwal.descricao, fraseSwal.tipo);
                    possuiErros = true;
                    return;

                }

            } else if ($(".cpf").val() != "") {

                if (!TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

                    $("#cpf").focus();
                    swal("Ops!", "Preencha um CPF válido", "error");
                    possuiErros = true;
                    return;

                }

            }

        })

        if (possuiErros) return false;

    }

    return true;

}

function exibirSwalCpfInvalidoInfantil() {

    var fraseSwal = getRepository("fraseCpfInvalidoPlanoInfantil");
    swal(fraseSwal.title, fraseSwal.descricao, fraseSwal.tipo);

}