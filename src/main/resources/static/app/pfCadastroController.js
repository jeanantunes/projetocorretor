var preenchidos = false;
emRequisicao = false;

$(document).ready(function () {

    buscarPlanosSelecionados();
    carregarProposta();
    localStorage.removeItem("dependentePfEmEdicao");
    resizeIframe('frame_pf');

    //Aqui comeca os steps de salvamento de cada campo
    $("#cpf").blur(function () {

        var propostasPfs = get("pessoas");
        var propostaEmEdicao = get("propostaPf");

        var propostaExistente = propostasPfs.filter(function (x) { return x.cpf == $('#cpf').val() });

        if (propostasPfs.length > 0 && propostaExistente.length > 0) {

            if (propostaExistente[0].cpf != propostaEmEdicao.cpf) {

                verificarSePropostaPfExiste();

            }

        } else {

            if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

                var propostaPf = get("propostaPf");
                propostaPf.status = "DIGITANDO";
                propostaPf.cpf = $("#cpf").val();
                atualizarPessoas(propostaPf);

            }
        }

    });
    
    $(".nome").blur(function () {

        $(".nome").val($(".nome").val().trim());

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get("propostaPf");
            propostaPf.nome = $(".nome").val().trim();
            atualizarPessoas(propostaPf);

        }

    });
    
    $(".email").blur(function () {

        $(".email").val($(".email").val().trim());

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get("propostaPf");
            propostaPf.email = $(".email").val().trim();
            atualizarPessoas(propostaPf);

        }

    });

    $(".celular").blur(function () {

        $(".celular").val($(".celular").val().trim());

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get("propostaPf");
            propostaPf.celular = $(".celular").val().trim();
            atualizarPessoas(propostaPf);

        }

    });

    $("#dataNascimentoTitular").blur(function () {

        if ($("#dataNascimentoTitular").val() == "") return false;

        var date = toDate($("#dataNascimentoTitular").val());

        if (isMaiorDeIdade(date)) {

            $(".representanteContratual").addClass('hide');
            if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

                var propostaPf = get("propostaPf");
                propostaPf.responsavelContratual.nome = "";
                propostaPf.responsavelContratual.cpf = "";
                propostaPf.responsavelContratual.nomeMae = "";
                propostaPf.responsavelContratual.celular = "";
                propostaPf.responsavelContratual.sexo = "";
                propostaPf.responsavelContratual.email = "";
                propostaPf.dataNascimento = $("#dataNascimentoTitular").val().trim();
                limparCamposResponsavelLegal();
                atualizarPessoas(propostaPf);

            }

            return false;
        }

        $(".representanteContratual").removeClass('hide');

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get("propostaPf");
            propostaPf.dataNascimento = $("#dataNascimentoTitular").val().trim();
            atualizarPessoas(propostaPf);

        }

    });


    $("#radio-1").click(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.sexo = $("#radio-1").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#radio-2").click(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.sexo = $("#radio-2").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#nomeMae").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.nomeMae = $('#nomeMae').val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#nomeResponsavel").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.responsavelContratual.nome = $('#nomeResponsavel').val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#emailRepresentanteLegal").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.responsavelContratual.email = $('#emailRepresentanteLegal').val();
            atualizarPessoas(propostaPf);

        }

    });

    $(".celular-representante-legal").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.responsavelContratual.celular = $('.celular-representante-legal').val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#cpf-representante").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.responsavelContratual.cpf = $('#cpf-representante').val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#dataNascimentoResponsavel").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.responsavelContratual.dataNascimento = $('#dataNascimentoResponsavel').val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#radio-3").click(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.responsavelContratual.sexo = $("#radio-3").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#radio-4").click(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.responsavelContratual.sexo = $("#radio-4").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#cep-proposta-pf").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.endereco.cep = $("#cep-proposta-pf").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#rua").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.endereco.logradouro = $("#rua").val();
            atualizarPessoas(propostaPf);

        }

    });

    $(".numero").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.endereco.numero = $(".numero").val();
            atualizarPessoas(propostaPf);

        }

    });

    $(".complemento").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.endereco.complemento = $(".complemento").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#bairro").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.endereco.bairro = $("#bairro").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#uf").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.endereco.estado = $("#uf").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#cidade").blur(function () {

        if ($("#cpf").val() != "" && TestaCPF($("#cpf").val())) {

            var propostaPf = get('propostaPf');
            propostaPf.endereco.cidade = $("#cidade").val();
            atualizarPessoas(propostaPf);

        }

    });

    $("#adicionarPlano").click(function () {

        salvarRascunhoMemoria();
        window.location.href = "venda_index_pf.html";

    });

    $("#cep-proposta-pf").keyup(function () {

        if (!navigator.onLine) {
            return;
        }

        //Nova variável "cep" somente com dígitos.
        var cep = $('#cep-proposta-pf').val().replace(/\D/g, '');
        //Verifica se campo cep possui valor informado.
        if (cep != "" && cep.length == 8) {
            //Expressão regular para validar o CEP.
            var validacep = /^[0-9]{8}$/;
            //Valida o formato do CEP.
            if (validacep.test(cep)) {
                //Preenche os campos com "..." enquanto consulta webservice.
                $("#rua").val("");
                $("#bairro").val("");
                $("#cidade").val("");
                $("#uf").val("");

                swal({
                    title: "Aguarde",
                    text: 'Estamos procurando o endereço',
                    content: "input",
                    imageUrl: "img/icon-aguarde.gif",
                    showCancelButton: false,
                    showConfirmButton: false,
                    icon: "info",
                    button: {
                        text: "...",
                        closeModal: false,
                    },
                })

                try {

                    callTokenVendas(function (dataToken) {

                        if (dataToken.status != undefined) {

                            swal.close();
                            return;

                        }

                        callApiCep(function (dataCep) {

                            if (dataCep.status != undefined) {

                                swal.close();
                                return;

                            }

                            $("#bairro").val(dataCep[0].bairro);
                            $("#cidade").val(dataCep[0].cidade);
                            $("#rua").val(dataCep[0].logradouro);
                            $("#uf").val(dataCep[0].estado);

                            var propostaPf = get('propostaPf');
                            propostaPf.endereco.logradouro = $("#rua").val();
                            propostaPf.endereco.estado = $("#uf").val();
                            propostaPf.endereco.bairro = $("#bairro").val();
                            propostaPf.endereco.cidade = $("#cidade").val();
                            propostaPf.endereco.complemento = $(".complemento").val();
                            atualizarPessoas(propostaPf);

                            swal.close();

                        }, dataToken.access_token, cep);

                    });

                } catch (Erro) { }
            } else {

                limparCamposDoEndereco();

                var propostaPf = get('propostaPf');
                propostaPf.endereco.logradouro = $("#rua").val();
                propostaPf.endereco.estado = $("#uf").val();
                propostaPf.endereco.bairro = $("#bairro").val();
                propostaPf.endereco.cidade = $("#cidade").val();
                propostaPf.endereco.complemento = $(".complemento").val();
                atualizarPessoas(propostaPf);

            }

        } else {

            limparCamposDoEndereco();

            var propostaPf = get('propostaPf');
            propostaPf.endereco.logradouro = $("#rua").val();
            propostaPf.endereco.estado = $("#uf").val();
            propostaPf.endereco.bairro = $("#bairro").val();
            propostaPf.endereco.cidade = $("#cidade").val();
            propostaPf.endereco.complemento = $(".complemento").val();
            atualizarPessoas(propostaPf);

        }
    });

    $("#email").blur(function () {

        var emailDigitado = $(this).val();

        if (!validateEmail(emailDigitado)){

            return;

        }

        if (navigator.onLine) {

            var arrayEmails = [];
            arrayEmails.push(emailDigitado);

            validarEmailForcaCorretora(arrayEmails,
                function () {

                    swal.close();
                    $(".label-email").css("color", "#1974CE");
                    $(".email").css("color", "#1974CE");
                    $(".email").css("border-color", "#1974CE");

                },
                function (error) {

                    if (error != 500) {
                        $(".label-email").css("color", "#FF4141");
                        $(".email").css("color", "#FF4141");
                        $(".email").css("border-color", "#FF4141");
                    }else {
                        swal.close();
                    }
                }
            )

        }

    })

    $("#emailRepresentanteLegal").blur(function () {

        var emailDigitado = $(this).val();

        if (!validateEmail(emailDigitado)){

            return;

        }

        if (navigator.onLine) {

            var arrayEmails = [];
            arrayEmails.push(emailDigitado);

            validarEmailForcaCorretora(arrayEmails,
                function () {

                    swal.close();
                    $(".label-email-representante").css("color", "#1974CE");
                    $(".input-email-representante-legal").css("color", "#1974CE");
                    $(".input-email-representante-legal").css("border-color", "#1974CE");

                },
                function (error) {

                    if (error != 500) {
                        $(".label-email-representante").css("color", "#FF4141");
                        $(".input-email-representante-legal").css("color", "#FF4141");
                        $(".input-email-representante-legal").css("border-color", "#FF4141");
                    }else {
                        swal.close();
                    }
                }
            )

        }

    })

});



function limparCamposResponsavelLegal() {

    $("#nomeResponsavel").val("");
    $("#cpf-representante").val("");
    $("#dataNascimentoResponsavel").val("");
    $("#emailRepresentanteLegal").val("");
    $(".celular-representante-legal").val("");

}

function verificarSePropostaPfExiste() {

    var propostasPf = get("pessoas");

    if (propostasPf == null) {
        return;
    }

    var propostaExistente = propostasPf.filter(function (x) { return x.cpf == $('#cpf').val() });

    if (propostaExistente.length == 0) {
        return;
    }

    if (propostaExistente.length == 1 && propostaExistente[0].status == "SYNC") {
        swal("Ops!", "Você possui uma proposta com esse CPF em sincronismo", "error");
        return;
    }

    var propostaPfEmEdicao = get("propostaPf");

    if (propostaPfEmEdicao.cpf == propostaExistente[0].cpf) { // Caso a proposta existe mas esta em edicao;
        return;
    }

    if (propostaExistente.length == 1 && propostaExistente[0].status != "ENVIADA") {

        swal({
            title: "Ops!",
            text: "Você já tem uma proposta com esse CPF, selecione uma opção:",
            type: "warning",
            confirmButtonClass: "btn-danger",
            confirmButtonColor: "#1974CE",
            confirmButtonText: "Editar proposta existente",
            cancelButtonText: "Excluir proposta",
            showCancelButton: true,
            closeOnConfirm: false,
            closeOnCancel: false
        },
            function (isConfirm) {
                if (isConfirm) {

                    put("propostaPf", JSON.stringify(propostaExistente[0]));
                    window.location.href = "venda_pf_dados_proposta.html";

                } else {

                    swal({
                        title: "Ops!",
                        text: "Tem certeza que deseja excluir a proposta?",
                        type: "warning",
                        confirmButtonClass: "btn-danger",
                        confirmButtonColor: "#1974CE",
                        confirmButtonText: "Sim",
                        cancelButtonText: "Não",
                        showCancelButton: true,
                        closeOnConfirm: false,
                        closeOnCancel: false
                    },
                        function (isConfirm) {
                            if (isConfirm) {
                                var propostasPfExcetoExcluidas = propostasPf.filter(function (x) { return x.cpf != $('#cpf').val() });

                                put("pessoas", JSON.stringify(propostasPfExcetoExcluidas));
                                window.location.href = "venda_pf_dados_proposta.html";
                                //buscarEmpresa();
                            } else {
                                verificarSePropostaPfExiste();
                            }
                        });
                }
            });

    }


}

function limparCamposDoEndereco() {
    // Limpa valores do formulário de cep.
    $("#rua").val("");
    $("#bairro").val("");
    $("#cidade").val("");
    $("#uf").val("");
}

function callApiCep(callback, token, cep) {
    $.ajax({
        async: true,
        url: URLBase + "/cep/1.1/por/cep/" + cep,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callback(resp)
        },
        error: function (resp) {
            callback(resp)
        }
    });
}

function addDependente() {

    if ($(".cpf").val() == "" || !TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {

        $("#cpf").focus();
        swal("Ops!", "Preencha o Cpf", "error");
        $("#cpf").focus();
        return;
    }

    if (!ValidaNome($("#nome").val())) {
        swal("Ops!", "Preencha nome completo", "error");
        $("#nome").focus();
        return false;
    }


    if (!validateEmail($(".email").val())) {
        swal("Ops!", "Preencha um E-mail válido", "error");
        $("#email").focus();
        return;
    }

    if ($(".celular").val() == "") {
        swal("Ops!", "Preencha o celular", "error");
        $(".celular").focus();
        return;
    }

    if (!validarData($("#dataNascimentoTitular").val())) {
        swal("Ops!", "Preencha uma data de nascimento válida", "error");
		$("#dataNascimentoTitular").focus();
        return;
    }

    if ($("#dataNascimentoTitular").val() == "") {
        swal("Ops!", "Preencha a Data de Nascimento", "error");
        $("#dataNascimentoTitular").focus();
        return;
    }

    var date = toDate($("#dataNascimentoTitular").val());

    if ($("#radio-1").is(":checked") == false && $("#radio-2").is(":checked") == false) {
        swal("Ops!", "Selecione o Sexo", "error");
        $(".dependentes").val(0);
        return;
    }

    if ($("#nomeMae").val() == "") {
        swal("Ops!", "Preencha Nome da Mãe", "error");
        $("#nomeMae").focus();
        return;
    }

    if (!ValidaNome($("#nomeMae").val())) {
        swal("Ops!", "Nome da mãe inválido", "error");
        $("#nomeMae").focus();
        return false;
    }

    if (!isMaiorDeIdade(date)) {

        if ($("#emailResponsavel").val() == "" || !ValidaNome($("#nomeResponsavel").val())) {
            swal("Ops!", "Preencha o nome completo do representante legal", "error");
            $("#emailResponsavel").focus();
            return;
        }

        if ($("#emailRepresentanteLegal").val() == "") {
            swal("Ops!", "Preencha o e-mail do representante legal", "error");
            $("#emailRepresentanteLegal").focus();
            return;
        }

        if ($("#emailRepresentanteLegal").val() == "" || !validateEmail($("#emailRepresentanteLegal").val())) {
            swal("Ops!", "Email do representante legal inválido", "error");
            $("#emailRepresentanteLegal").focus();
            return;
        }

        if ($(".celular-representante-legal").val() == "") {
            swal("Ops!", "Preencha o celular do representante legal", "error");
            $(".celular-representante-legal").focus();
            return;
        }

        if ($("#cpf-representante").val() == "" || !TestaCPF($("#cpf-representante").val().replace().replace(/\D/g, ''))) {
            swal("Ops!", "CPF do representante legal está inválido", "error");
            $("#cpf-representante").focus();
            return;
        }

        if ($("#cpf-representante").val() == $("#cpf").val()) {
            swal("Ops!", "O representante legal não pode ter o mesmo CPF do titular", "error");
            $("#cpf-representante").focus();
            return;
        }

        if ($("#dataNascimentoResponsavel").val() == "") {
            swal("Ops!", "Preencha a data de nascimento do responsável", "error");
            $("#dataNascimentoResponsavel").focus();
            return;
        }

        var dateResponsavelLegal = toDate($("#dataNascimentoResponsavel").val());

        if (!isMaiorDeIdade(dateResponsavelLegal)) {
            swal("Ops!", "O responsável legal não pode ser menor de idade", "error");
            $("#dataNascimentoResponsavel").focus();
            return;
        }

        if ($("#radio-3").is(":checked") == false && $("#radio-4").is(":checked") == false) {
            swal("Ops!", "Selecione o sexo do responsável legal", "error");
            $("#radio-3").focus();
            return;
        }
    }

    if ($("#cep-proposta-pf").val() == "") {
        swal("Ops!", "Preencha o cep", "error");
        $("#cep-proposta-pf").focus();
        return;
    }

    if ($("#rua").val() == "") {
        swal("Ops!", "Preencha o endereço", "error");
        $("#rua").focus();
        return;
    }

    if ($("#numeroEndereco").val() == "") {
        swal("Ops!", "Preencha o número do endereço", "error");
        $("#numeroEndereco").focus();
        return;
    }

    if ($("#bairro").val() == "") {
        swal("Ops!", "Preencha o bairro", "error");
        $("#bairro").focus();
        return;
    }

    if ($("#uf").val() == " ") {
        swal("Ops!", "Preencha o estado", "error");
        $("#uf").focus();
        return;
    }

    if ($("#cidade").val() == "") {
        swal("Ops!", "Preencha o cidade", "error");
        $("#cidade").focus();
        return;
    }

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

function validarEmailForcaCorretora(arrayEmail, callbackSuccess, callbackError) {

    swal({
        title: "Aguarde",
        text: '',
        content: "input",
        imageUrl: "img/icon-aguarde.gif",
        showCancelButton: false,
        showConfirmButton: false,
        allowEscapeKey: false,
        allowOutsideClick: false,
        icon: "info",
        button: {
            text: "...",
            closeModal: false
        }
    });

    var dadosUsuario = get("dadosUsuario");
    var codigoUsuario = dadosUsuario.codigo;

    getEmailForcaCorretora(codigoUsuario,
        function (dataEmailForcaCorretora) {

            if (dataEmailForcaCorretora != undefined){

                var emailCorretora = dataEmailForcaCorretora.emailCorretora;
                var emailForcaVenda = dataEmailForcaCorretora.emailForcaVenda;
                var possuiErros = false;

                $.each(arrayEmail, function( index, value ) {

                    if (value == emailCorretora || value == emailForcaVenda){

                        swal(
                            "E-mail inválido",
                            "Não é permitido colocar o e-mail do vendedor" +
                            " ou da corretora na venda. Por favor, informe o e-mail do cliente.",
                            "error"
                        );

                        possuiErros = true;
                    }

                });

                if (possuiErros){
                    callbackError(403);
                    return;
                }

                callbackSuccess();
                return;
            }
        },
        function (dataError) {
            callbackError(500);
            return;
        }
    )

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

    if (emRequisicao) return;

    if ($("#nome").val() == "") {
        swal("Ops!", "Preencha o Nome", "error");
        $("#nome").focus();
        return;
    }

    if (!ValidaNome($("#nome").val())) {
        swal("Ops!", "Nome inválido", "error");
        $("#nome").focus();
        return false;
    }

    if ($("#email").val() == "") {
        swal("Ops!", "Preencha o E-mail", "error");
		$("#email").focus();
        return;
    }

    if (!validateEmail($("#email").val())) {
        swal("Ops!", "Email do titular inválido", "error");
		$("#email").focus();
        return;
    }

    if ($(".celular").val() == "") {
        swal("Ops!", "Preencha o celular", "error");
		$("#celular").focus();
        return;
    }

    if ($(".cpf").val() == "" || !TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {

        $("#cpf").focus();
        swal("Ops!", "Preencha o CPF", "error");
		$("#cpf").focus();
        return;
    }

    if (!validarData($(".nascimento").val())) {
        swal("Ops!", "Preencha uma data de nascimento correta", "error");
		$("#nascimento").focus();
        return;
    }


    if ($(".nascimento").val() == "") {
        swal("Ops!", "Preencha a Data de Nascimento", "error");
		$("#nascimento").focus();
        return;
    }

    if ($("#radio-1").is(":checked") == false && $("#radio-2").is(":checked") == false) {
        swal("Ops!", "Selecione o Sexo", "error");
        $(".dependentes").val(0);
        return;
    }

    if ($(".nome-mae").val() == "") {
        swal("Ops!", "Preencha Nome da Mãe", "error");
		$("#nomeMae").focus();
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

        if (!TestaCPF($("#cpf-representante").val().replace(/\D/g, '')))
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
		$("#cep").focus();
        return;
    }

    if ($(".rua").val() == "") {
        swal("Ops!", "Preencha o endereço", "error");
		$("#rua").focus();
        return;
    }

    if ($(".numeroEndereco").val() == "") {
        swal("Ops!", "Preencha o número do endereço", "error");
		$("#numeroEndereco").focus();
        return;
    }

    if ($(".bairro").val() == "") {
        swal("Ops!", "Preencha o bairro", "error");
		$("#bairro").focus();
        return;
    }

    if ($(".cidade").val() == "") {
        swal("Ops!", "Preencha o cidade", "error");
		$("#cidade").focus();
        return;
    }

    if ($("#uf").val() == " ") {
        swal("Ops!", "Preencha o estado", "error");
		$(".estado").focus();
        return;
    }

    var emailPrincipal = $("#email").val();
    var emailSegundoContato = $("#emailRepresentanteLegal").val();
    var arrayEmails = [];
    arrayEmails.push(emailPrincipal);

    var dateNascimentoTitular = toDate($("#dataNascimentoTitular").val());

    if (!isMaiorDeIdade(dateNascimentoTitular)) {

        arrayEmails.push(emailSegundoContato);

    }

    if (navigator.onLine) {

        emRequisicao = true;
        $("#continuarPropostaPf").prop('disabled', true);

        validarEmailForcaCorretora(arrayEmails,
            function () {

                emRequisicao = false;
                $("#continuarPropostaPf").prop('disabled', emRequisicao);
                salvarRascunhoMemoria();
                window.location.href = "resumo_pf_proposta.html";

            },
            function (error) {

                if (error != 500) {

                    emRequisicao = false;
                    $("#continuarPropostaPf").prop('disabled', emRequisicao);

                } else {

                    emRequisicao = false;
                    $("#continuarPropostaPf").prop('disabled', emRequisicao);
                    salvarRascunhoMemoria();
                    window.location.href = "resumo_pf_proposta.html";
                }
            }
        )
    } else {

        emRequisicao = false;
        $("#continuarPropostaPf").prop('disabled', emRequisicao);
        salvarRascunhoMemoria();
        window.location.href = "resumo_pf_proposta.html";
    }

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
    $("#nome").val(proposta.nome);
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