

$(document).ready(function () {

    function validaNome(campo) {
        var regex = /^[a-zA-ZéúíóáÉÚÍÓÁèùìòàçÇÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄ\-\ \s]+$/;
        if (campo.match(regex)) {
            return false;
        } else { return true; }
    }

    function validarDependentes() {
        var myJSON = JSON.stringify($("#meu_formulario").serializeArray());
        window.localStorage.setItem('Beneficiario', myJSON);
        window.location = "venda_pme_dependentes.html";
    }


    function limpa_formulario_cep() {
        // Limpa valores do formulário de cep.
        $("#rua").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#uf").val("");
    }
    var typingTimer; //timer identifier
    var doneTypingInterval = 500; //time in ms, 5 second for example
    //on keyup, start the countdown
    $('#cep').keyup(function () {

        if (!navigator.onLine)
        {
            return;
        }

        clearTimeout(typingTimer);
        if ($('#cep').val) {
            typingTimer = setTimeout(doneTyping, doneTypingInterval);
        }
    });
    function doneTyping() {
        validar();
    }
    function validar() {
        //Nova variável "cep" somente com dígitos.
        var cep = $('#cep').val().replace(/\D/g, '');
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
                    text: 'Estamos procurando seus dados',
                    content: "input",
                    imageUrl: "img/load.gif",
                    showCancelButton: false,
                    showConfirmButton: false,
                    icon: "info",
                    button: {
                        text: "...",
                        closeModal: false,
                    },
                })

                try {
                    callTokenProd(function (dataToken) {
                        callCep(function (dataCep) {
                            $("#bairro").val(dataCep[0].bairro);
                            $("#cidade").val(dataCep[0].cidade);
                            $("#rua").val(dataCep[0].logradouro);
                            $("#uf").val(dataCep[0].estado);
                        }, dataToken.access_token, cep);
                    });
                } catch (e) {
                    swal.close();
                }
                //Consulta o webservice viacep.com.br/
                //$.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados)
                //{
                //    if (!("erro" in dados)) {
                //    //Atualiza os campos com os valores da consulta.
                //        $("#rua").val(dados.logradouro);
                //        $("#bairro").val(dados.bairro);
                //        $("#cidade").val(dados.localidade);
                //        $("#uf").val(dados.uf);
                //    } //end if.
                //    else {
                //        //CEP pesquisado não foi encontrado.
                //        limpa_formulário_cep();
                //        alert("CEP não encontrado.");
                //    }
                //});
            } //end if.
            else {
                //cep é inválido.
                limpa_formulario_cep();
                alert("Formato de CEP inválido.");
            }
        } //end if.
        else {
            //cep sem valor, limpa formulário.
            limpa_formulario_cep();
        }
    }
    //Quando o campo cep perde o foco.
    
    //Verica se o checkbox esta ativo e retira o "disabled"//

    $(document).ready(function () {
        $("#squaredOne").click(function (e) {
            if ($("#squaredOne").is(":checked")) {


                $("#btnTermoNCadastrado").removeClass('disabled');
            } else {

                $("#btnTermoNCadastrado").addClass('disabled');
            }
        });

        $("#squaredOneTermoCadastrado").click(function (e) {
            if ($("#squaredOneTermoCadastrado").is(":checked")) {

                $("#btnTermo").removeClass("disabled");

            } else {
                $("#btnTermo").addClass("disabled");
       
            }
        });


    });
});

function callCep(callback, token, cep) {
    $.ajax({
        async: true,
        url: "https://api.odontoprev.com.br:8243/cep/1.1/por/cep/" + cep,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callback(resp)
            swal.close();
        },
        error: function () {
            swal.close();
        }
    });
}


// <!-- Função Valida CPF -->



function TestaCPF(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;

    strCPF = strCPF.replace(/\D/g, '');
    if (strCPF == "00000000000" || strCPF == "11111111111" || strCPF == "22222222222" || strCPF == "33333333333" || strCPF == "44444444444" || strCPF == "55555555555" || strCPF == "66666666666" || strCPF == "77777777777" || strCPF == "88888888888" || strCPF == "99999999999") return false;

    for (i = 1; i <= 9; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10))) return false;

    Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11))) return false;
    return true;
}


// <!--Mascara -->

function validaCnpj(str) {
    str = str.replace('.', '');
    str = str.replace('.', '');
    str = str.replace('.', '');
    str = str.replace('-', '');
    str = str.replace('/', '');
    cnpj = str;
    var numeros, digitos, soma, i, resultado, pos, tamanho, digitos_iguais;
    digitos_iguais = 1;
    if (cnpj.length < 14 && cnpj.length < 15)
        return false;
    for (i = 0; i < cnpj.length - 1; i++)
        if (cnpj.charAt(i) != cnpj.charAt(i + 1)) {
            digitos_iguais = 0;
            break;
        }
    if (!digitos_iguais) {
        tamanho = cnpj.length - 2
        numeros = cnpj.substring(0, tamanho);
        digitos = cnpj.substring(tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(0))
            return false;
        tamanho = tamanho + 1;
        numeros = cnpj.substring(0, tamanho);
        soma = 0;
        pos = tamanho - 7;
        for (i = tamanho; i >= 1; i--) {
            soma += numeros.charAt(tamanho - i) * pos--;
            if (pos < 2)
                pos = 9;
        }
        resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
        if (resultado != digitos.charAt(1))
            return false;
        return true;
    }
    else
        return false;
        }

$(document).ready(function () {
    $('.data').mask('00/00/0000');
    $('.tempo').mask('00:00:00');
    $('.data_tempo').mask('00/00/0000 00:00:00');
    $('.data').mask('00/00/0000');
    $('.data-responsavel').mask('00/00/0000');
    $('.cep').mask('00000-000');
    $('.tel').mask('00000-0000');
    $('.telefone').mask('(00) 0000-0000');
    $('.celular').mask('(00) 00000-0000');
    $('.cpf').mask('000.000.000-00');
    $('.cnae').mask('0000000');
    $('.cpf-representante-legal').mask('000.000.000-00');
    $('.celular-representante-legal').mask('(00) 00000-0000');
    $('.cnpj').mask('00.000.000/0000-00');
    $('.dinheiro').mask('000.000.000.000.000,00', { reverse: true });
    $('.dinheiro2').mask("#.##0,00", { reverse: true });

    $('.cor_hex').mask('#xxxxxx', {

        translation: {
            'x': {
                pattern: /[a-fA-F0-9]/
            },
            '#': ''
        }
    });

    $('.placeholder').mask("00/00/0000", { placeholder: "__/__/____" });


    $('#unmask').click(function () {
        var unmask_value = $('.cpf').cleanVal();
        $('#clearcpf').html(unmask_value);
    });

    //CPF
    $(".cpf").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cpf").css("color", "#1974CE");
            $(".label-cpf").css("color", "#1974CE");
        }
    });

    $(".cpf").blur(function () {

        var stringteste = $(this).val().replace(".", "");
        stringteste = stringteste.replace("-", "");
        stringteste = stringteste.replace(".", "");

        console.log(stringteste);

        if ($(this).val() == "" || TestaCPF(stringteste) == false) {
            $(this).css({ "border-color": "#F00" });
            $(".label-cpf").css("color", "red");
            $(".cpf").css("color", "red");
        }
    });

    $(".cpf").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cpf").css("color", "#1974CE");
            $(".label-cpf").css("color", "#1974CE");
            $("input:-webkit-autofill").css("color", "#1974CE");
        }
    });
    
 // NOME SEGUNDO CONTATO

    $(".segundo-contato").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".segundo-contato").css("color", "#1974CE");
            $(".label-nome-segundo-contato").css("color", "#1974CE");
        }
    });

    $(".segundo-contato").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-nome-segundo-contato").css("color", "red");
        }
    });

    $(".segundo-contato").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".segundo-contato").css("color", "#1974CE");
            $(".label-nome-segundo-contato").css("color", "#1974CE");
        }
    });

// Celular segundo contato

    $(".celular-segundo-contato").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".celular-segundo-contato").css("color", "#1974CE");
            $(".label-celular-segundo-contato").css("color", "#1974CE");
        }
    });


    $(".celular-segundo-contato").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-celular-segundo-contato").css("color", "red");
        }
    });


    $(".celular-segundo-contato").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".celular-segundo-contato").css("color", "#1974CE");
            $(".label-celular-segundo-contato").css("color", "#1974CE");
        }
    });

// telefone segundo contato

    $(".telefone-segundo-contato").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".telefone-segundo-contato").css("color", "#1974CE");
            $(".label-telefone-segundo-contato").css("color", "#1974CE");
        }
    });


    $(".telefone-segundo-contato").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-telefone-segundo-contato").css("color", "red");
        }
    });

    $(".telefone-segundo-contato").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".telefone-segundo-contato").css("color", "#1974CE");
            $(".label-telefone-segundo-contato").css("color", "#1974CE");
        }
    });

///// email segundo contato pme

    $(".email-segundo-contato").focus(function () {

        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".email-segundo-contato").css("color", "#1974CE");
            $(".label-email-segundo-contato").css("color", "#1974CE");
        }
    });

    $(".email-segundo-contato").blur(function () {

        var emailNaoCadastrado = $(this).val();

        if (validateEmail(emailNaoCadastrado)) {
            $(".label-email-segundo-contato").css("color", "#1974CE");
            $(".email-segundo-contato").css("color", "#1974CE");
            $(".email-segundo-contato").css("border-color", "#1974CE");

            return;
        }

        $(".label-email-segundo-contato").css("color", "red");
        $(".email-segundo-contato").css("color", "red");
        $(".email-segundo-contato").css("border-color", "red");

    });

    $(".email-segundo-contato").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".email-segundo-contato").css("color", "#1974CE");
            $(".label-email-segundo-contato").css("color", "#1974CE");
        }
    });

    ////////////////////////////////////////////////

    //CPF REPRESENTANTE LEGAL

    $(".cpf-representante-legal").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cpf-representante-legal").css("color", "#1974CE");
            $(".label-cpf-representante-legal").css("color", "#1974CE");
        }
    });

    $(".cpf-representante-legal").blur(function () {

        var stringteste = $(this).val().replace(".", "");
        stringteste = stringteste.replace("-", "");
        stringteste = stringteste.replace(".", "");

        console.log(stringteste);

        if ($(this).val() == "" || TestaCPF(stringteste) == false) {
            $(this).css({ "border-color": "#F00" });
            $(".label-cpf-representante-legal").css("color", "red");
            $(".cpf-representante-legal").css("color", "red");
        }
    });

    $(".cpf-representante-legal").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cpf-representante-legal").css("color", "#1974CE");
            $(".label-cpf-representante-legal").css("color", "#1974CE");
            $("input:-webkit-autofill").css("color", "#1974CE");
        }
    });

    // CNPJ

    $(".cnpj").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cnpj").css("color", "#1974CE");
            $(".label-cnpj").css("color", "#1974CE");
        }
    });

    $(".cnpj").blur(function () {
        if ($(this).val() == "" || validaCnpj($(".cnpj").val()) == false) {
            $(this).css({ "border-color": "#F00" });
            $(".label-cnpj").css("color", "red");
            $(".cnpj").css("color", "red");
        }
    });

    $(".cnpj").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cnpj").css("color", "#1974CE");
            $(".label-cnpj").css("color", "#1974CE");
        }
    });

    // CNAE

    $(".cnae").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cnae").css("color", "#1974CE");
            $(".label-cnae").css("color", "#1974CE");
        }
    });

    $(".cnae").blur(function () {
        if ($(this).val().length < 7) {
            $(this).css({ "border-color": "#F00" });
            $(".label-cnae").css("color", "red");
            $(".cnae").css("color", "red");
        }
    });

    $(".cnae").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cnae").css("color", "#1974CE");
            $(".label-cnae").css("color", "#1974CE");
        }
    });

    // RAZÃO SOCIAL

    $(".razao-social").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".razao-social").css("color", "#1974CE");
            $(".label-razao-social").css("color", "#1974CE");
        }
    });

    $(".razao-social").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-razao-social").css("color", "red");
        }
    });

    $(".razao-social").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".razao-social").css("color", "#1974CE");
            $(".label-razao-social").css("color", "#1974CE");
        }
    });

    // NOME-FANTASIA

    $(".nome-fantasia").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-fantasia").css("color", "#1974CE");
            $(".label-nome-fantasia").css("color", "#1974CE");
        }
    });

    $(".nome-fantasia").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-nome-fantasia").css("color", "red");
        }
    });

    $(".nome-fantasia").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-fantasia").css("color", "#1974CE");
            $(".label-nome-fantasia").css("color", "#1974CE");
        }
    });

    // RAMO ATIVIDADE

    $(".ramo-atividade").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".ramo-atividade").css("color", "#1974CE");
            $(".label-ramo-atividade").css("color", "#1974CE");
        }
    });

    $(".ramo-atividade").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-ramo-atividade").css("color", "red");
        }
    });

    $(".ramo-atividade").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".ramo-atividade").css("color", "#1974CE");
            $(".ramo-atividade").css("color", "#1974CE");
        }
    });

    // MENSAGEM 

    $(".mensagem").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".mensagem").css("color", "#1974CE");
            $(".label-mensagem").css("color", "#1974CE");
        }
    });

    $(".mensagem").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-mensagem").css("color", "red");
          
        }
    });

    $(".mensagem").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".mensagem").css("color", "#1974CE");
            $(".mensagem").css("color", "#1974CE");
        }
    });

    //Verica se o checkbox esta ativo e retira o "disabled"//

    $(document).ready(function () {
        $("#squaredOne").click(function (e) {
            if ($("#squaredOne").is(":checked")) {

                $("#btnTermo").removeClass("disabled");
                $("#btnTermoNCadastrado").removeClass('disabled');
            } else {
                $("#btnTermo").addClass("disabled");
                $("#btnTermoNCadastrado").addClass('disabled');
            }
        });
    });

    //Verica se o checkbox esta ativo e retira o "disabled"//

    //Verica se o select option esta selecionado e retira o "disabled"//
    $(document).ready(function () {
        $("#vencOdont").click(function (e) {
            if ($('#vencOdont :selected').text()) {

                $("#bntVenc").removeClass("disabled");
            } else {
                $("#bntVenc").addClass("disabled");
            }
        });
    });

    //Verica se o select option esta selecionado e retira o "disabled"//

    // INSCRIÇÃO ESTADUAL

    $(".inscricao-estadual").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".inscricao-estadual").css("color", "#1974CE");
            $(".label-inscricao-estadual").css("color", "#1974CE");
        }
    });

    $(".inscricao-estadual").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-inscricao-estadual").css("color", "red");
        }
    });

    $(".inscricao-estadual").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".inscricao-estadual").css("color", "#1974CE");
            $(".inscricao-estadual").css("color", "#1974CE");
        }
    });

    // REPRESENTANTE LEGAL 

    $(".representante-legal").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".representante-legal").css("color", "#1974CE");
            $(".label-representante-legal").css("color", "#1974CE");
        }
    });

    $(".representante-legal").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-representante-legal").css("color", "red");
        }
    });

    $(".representante-legal").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".representante-legal").css("color", "#1974CE");
            $(".representante-legal").css("color", "#1974CE");
        }
    });

    // NUMERO CARTAO

    $(".cartao").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cartao").css("color", "#1974CE");
            $(".label-cartao").css("color", "#1974CE");
        }
    });

    $(".cartao").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-cartao").css("color", "red");
        }
    });

    $(".cartao").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cartao").css("color", "#1974CE");
            $(".label-cartao").css("color", "#1974CE");
        }
    });

    // NOME CARTAO

    $(".nome-cartao").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-cartao").css("color", "#1974CE");
            $(".label-nome-cartao").css("color", "#1974CE");
        }
    });

    $(".nome-cartao").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-nome-cartao").css("color", "red");
        }
    });

    $(".nome-cartao").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-cartao").css("color", "#1974CE");
            $(".label-nome-cartao").css("color", "#1974CE");
        }
    });

    // VALIDADE

    $(".validade").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".validade").css("color", "#1974CE");
            $(".label-validade").css("color", "#1974CE");
        }
    });

    $(".validade").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-validade").css("color", "red");
        }
    });

    $(".validade").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".validade").css("color", "#1974CE");
            $(".label-validade").css("color", "#1974CE");
        }
    });

    // CARTA CVV

    $(".cvv").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cvv").css("color", "#1974CE");
            $(".label-cvv").css("color", "#1974CE");
        }
    });

    $(".cvv").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-cvv").css("color", "red");
        }
    });

    $(".cvv").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cvv").css("color", "#1974CE");
            $(".label-cvv").css("color", "#1974CE");
        }
    });

    // NOME CORRETORA

    $(".corretora").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".corretora").css("color", "#1974CE");
            $(".label-corretora").css("color", "#1974CE");
        }
    });

    $(".corretora").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-corretora").css("color", "red");
        }
    });

    $(".corretora").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".corretora").css("color", "#1974CE");
            $(".label-corretora").css("color", "#1974CE");
        }
    });

    // CIDADE

    $(".cidade").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cidade").css("color", "#1974CE");
            $(".label-cidade").css("color", "#1974CE");
        }
    });

    $(".cidade").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-cidade").css("color", "red");
        }
    });

    $(".cidade").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cidade").css("color", "#1974CE");
            $(".label-cidade").css("color", "#1974CE");
        }
    });

    // ESTADO

    $(".estado").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "blue" });
            $(".estado").css("color", "#1974CE");
            $(".label-estado").css("color", "#1974CE");
        }
    });

    $(".estado").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-estado").css("color", "red");
        }
    });

    $(".estado").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".estado").css("color", "#1974CE");
            $(".label-estado").css("color", "#1974CE");
        }
    });

    // NOME

    $(".nome").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome").css("color", "#1974CE");
            $(".label-nome").css("color", "#1974CE");
        }
    });

    $(".nome").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-nome").css("color", "red");
        }
    });

    $(".nome").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({"border-color": "#1974CE"});
            $(".nome").css("color", "#1974CE");
            $(".label-nome").css("color", "#1974CE");
        }
    });

    // NOME MAE

    $(".nome-mae").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-mae").css("color", "#1974CE");
            $(".label-nome-mae").css("color", "#1974CE");
        }
    });

    $(".nome-mae").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "red" });
            $(".label-nome-mae").css("color", "red");

        }
    });

    $(".nome-mae").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-mae").css("color", "#1974CE");
            $(".label-nome-mae").css("color", "#1974CE");
        }
    });

    // NOME RESPONSAVEL LEGAL

    $(".nome-responsavel").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-responsavel").css("color", "#1974CE");
            $(".label-nome-responsavel-legal").css("color", "#1974CE");
        }
    });

    $(".nome-responsavel").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-nome-responsavel-legal").css("color", "red");
        }
    });

    $(".nome-responsavel").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-responsavel").css("color", "#1974CE");
            $(".label-nome-responsavel-legal").css("color", "#1974CE");
        }
    });

    // ESPECIALIDADE

    $(".especialidade").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".especialidade").css("color", "#1974CE");
            $(".label-especialidade").css("color", "#1974CE");
        }
    });

    $(".especialidade").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-especialidade").css("color", "red");
        }
    });

    $(".especialidade").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".especialidade").css("color", "#1974CE");
            $(".label-especialidade").css("color", "#1974CE");
        }
    });


    // DEPENDENTES

    $(".dependentes").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".dependentes").css("color", "#1974CE");
            $(".label-dependentes").css("color", "#1974CE");
        }
    });

    $(".dependentes").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-dependentes").css("color", "red");
        }
    });

    $(".dependentes").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".dependentes").css("color", "#1974CE");
            $(".label-dependentes").css("color", "#1974CE");
        }
    });

    // NOME DEPENDENTE

    $(".nome-dependente").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-dependente").css("color", "#1974CE");
            $(".label-nome-dependente").css("color", "#1974CE");
        }
    });

    $(".nome-dependente").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-nome-dependente").css("color", "red");
        }
    });

    $(".nome-dependente").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".nome-dependente").css("color", "#1974CE");
            $(".label-nome-dependente").css("color", "#1974CE");
        }
    });

    // GRAU PARENTESCO

    $(".parentesco").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".parentesco").css("color", "#1974CE");
            $(".label-parentesco").css("color", "#1974CE");
        }
    });

    $(".parentesco").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-parentesco").css("color", "red");
        }
    });

    $(".parentesco").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".parentesco").css("color", "#1974CE");
            $(".label-parentesco").css("color", "#1974CE");
        }
    });

    // telefone

    $(".telefone").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".telefone").css("color", "#1974CE");
            $(".label-telefone").css("color", "#1974CE");
        }
    });


    $(".telefone").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-telefone").css("color", "red");
        }
    });

    $(".telefone").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".telefone").css("color", "#1974CE");
            $(".label-telefone").css("color", "#1974CE");
        }
    });

    // Celular

    $(".celular").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".celular").css("color", "#1974CE");
            $(".label-celular").css("color", "#1974CE");
        }
    });


    $(".celular").blur(function () {
        if ($(this).val() == "" || $(this).val().length < 15) {
            $(this).css({ "border-color": "red" });
            $(this).css({ "color": "red" });
            $(".label-celular").css("color", "red");
        }
    });

    $(".celular").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".celular").css("color", "#1974CE");
            $(".label-celular").css("color", "#1974CE");
        }
    });

    // Celular representante legal

    $(".celular-representante-legal").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".celular-representante-legal").css("color", "#1974CE");
            $(".label-celular-representante-legal").css("color", "#1974CE");
        }
    });


    $(".celular-representante-legal").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-celular-representante-legal").css("color", "red");
        }
    });


    $(".celular-representante-legal").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".celular-representante-legal").css("color", "#1974CE");
            $(".label-celular-representante-legal").css("color", "#1974CE");
        }
    });

    // CEP

    $(".cep").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cep").css("color", "#1974CE");
            $(".label-cep").css("color", "#1974CE");
        }
    });

    $(".cep").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-cep").css("color", "red");
        }
    });

    $(".cep").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cep").css("color", "#1974CE");
            $(".label-cep").css("color", "#1974CE");
        }
    });

    // RAZAO SOCIAL

    $(".razao-social").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".razao-social").css("color", "#1974CE");
            $(".label-razao-social").css("color", "#1974CE");
        }
    });

    $(".razao-social").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00", "padding": "2px" });
            $(".label-razao-social").css("color", "red");
        }
    });

    $(".razao-social").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".razao-social").css("color", "#1974CE");
            $(".label-razao-social").css("color", "#1974CE");
        }
    });

    // endereço
    $(".endereco").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".endereco").css("color", "#1974CE");
            $(".label-endereco").css("color", "#1974CE");
        }
    });

    $(".endereco").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-endereco").css("color", "red");
        }
    });

    $(".endereco").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".endereco").css("color", "#1974CE");
            $(".label-endereco").css("color", "#1974CE");
        }
    });

    // numero
    $(".numero").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".numero").css("color", "#1974CE");
            $(".label-numero").css("color", "#1974CE");
            $(".label-numero").css("font-weight", "bold");
        }
    });

    $(".numero").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-numero").css("color", "red");
        }
    });

    $(".numero").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".numero").css("color", "#1974CE");
            $(".label-numero").css("color", "#1974CE");
        }
    });

    function validateEmail(email) {
        var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
        if (reg.test(email)) {
            return true;
        }

        return false;
    }

    $(".email-representante-legal").focus(function () {

        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".email-representante-legal").css("color", "#1974CE");
            $(".label-email-representante-legal").css("color", "#1974CE");
        }
    });

    $(".email-representante-legal").blur(function () {

        var emailNaoCadastrado = $(this).val();

        if (validateEmail(emailNaoCadastrado)) {
            $(".label-email-representante-legal").css("color", "#1974CE");
            $(".email-representante-legal").css("color", "#1974CE");
            $(".email-representante-legal").css("border-color", "#1974CE");

            return;
        }

        $(".label-email-representante-legal").css("color", "red");
        $(".email-representante-legal").css("color", "red");
        $(".email-representante-legal").css("border-color", "red");

    });

    $(".email-representante-legal").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".email-representante-legal").css("color", "#1974CE");
            $(".label-email-representante-legal").css("color", "#1974CE");
        }
    });

    $("#emailNaoCadastrado").blur(function () {

        var teste = $("#emailNaoCadastrado").val();

        if (validateEmail(teste)) {

            $(".label-email").css("color", "#1974CE");
            $(".email").css("color", "#1974CE");
            $(".email").css("border-color", "#1974CE");

            return;
        }

        $(".label-email").css("color", "red");
        $(".email").css("color", "red");
        $(".email").css("border-color", "red");

    });

    $(".email").focus(function () {

        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".email").css("color", "#1974CE");
            $(".label-email").css("color", "#1974CE");
        }
    });

    $(".email").blur(function () {

        var emailNaoCadastrado = $(".email").val();

        if (emailNaoCadastrado == "") var emailNaoCadastrado = $("#emailNaoCadastrado").val();

        if (validateEmail(emailNaoCadastrado)) {
            console.log(emailNaoCadastrado);
            $(".label-email").css("color", "#1974CE");
            $(".email").css("color", "#1974CE");
            $(".email").css("border-color", "#1974CE");

            return;
        }

        $(".label-email").css("color", "red");
        $(".email").css("color", "red");
        $(".email").css("border-color", "red");
    });

    $(".email").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".email").css("color", "#1974CE");
            $(".label-email").css("color", "#1974CE");
        }
    });


    // COMPLEMENTO
    //$(".complemento").focus(function () {
    //    if ($(this).val() == "") {
    //        $(this).css({ "border-color": "blue" });
    //        $(".complemento").css("color", "#1974CE");
    //        $(".label-complemento").css("color", "#1974CE");
    //    }
    //});
    //
    //$(".complemento").blur(function () {
    //    if ($(this).val() == "") {
    //        $(this).css({ "border-color": "#F00" });
    //        $(".label-complemento").css("color", "red");
    //    }
    //});
    //
    //$(".complemento").keyup(function () {
    //    if ($(this).val() != "") {
    //        $(this).css({ "border-color": "#1974CE" });
    //        $(".complemento").css("color", "#1974CE");
    //        $(".label-complemento").css("color", "#1974CE");
    //    }
    //});

    // BAIRRO
    $(".bairro").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".bairro").css("color", "#1974CE");
            $(".label-bairro").css("color", "#1974CE");
        }
    });

    $(".bairro").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-bairro").css("color", "red");
        }
        $(this).css({ "border-color": "#1974CE" });
        $(".bairro").css("color", "#1974CE");
        $(".label-bairro").css("color", "#1974CE");
    });

    $(".bairro").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".bairro").css("color", "#1974CE");
            $(".label-bairro").css("color", "#1974CE");
        }
    });

    // CIDADE

    $(".cidade").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cidade").css("color", "#1974CE");
            $(".label-cidade").css("color", "#1974CE");
        }
    });

    $(".cidade").blur(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "#F00" });
            $(".label-cidade").css("color", "red");
        }
    });

    $(".cidade").keyup(function () {
        if ($(this).val() != "") {
            $(this).css({ "border-color": "#1974CE" });
            $(".cidade").css("color", "#1974CE");
            $(".label-cidade").css("color", "#1974CE");
        }
    });

});

// BANCOS 

$(".bancos").focus(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".bancos").css("color", "#1974CE");
        $(".label-bancos").css("color", "#1974CE");
    }
});

$(".bancos").blur(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#F00" });
        $(".label-bancos").css("color", "red");
    }
});

$(".bancos").keyup(function () {
    if ($(this).val() != "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".bancos").css("color", "#1974CE");
        $(".bancos").css("color", "#1974CE");
    }
});

// AGENCIA 

$(".agencia").focus(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".agencia").css("color", "#1974CE");
        $(".label-agencia").css("color", "#1974CE");
    }
});

$(".agencia").blur(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#F00" });
        $(".label-agencia").css("color", "red");
    }
});

$(".agencia").keyup(function () {
    if ($(this).val() != "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".agencia").css("color", "#1974CE");
        $(".agencia").css("color", "#1974CE");
    }
});

// CONTA-CORRENTE 

$(".conta-corrente").focus(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".conta-corrente").css("color", "#1974CE");
        $(".label-conta-corrente").css("color", "#1974CE");
    }
});

$(".conta-corrente").blur(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#F00" });
        $(".label-conta-corrente").css("color", "red");
    }
});

$(".conta-corrente").keyup(function () {
    if ($(this).val() != "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".conta-corrente").css("color", "#1974CE");
        $(".conta-corrente").css("color", "#1974CE");
    }
});

// DATA NASCIMENTO

$(".nascimento").focus(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".nascimento").css("color", "#1974CE");
        $(".label-nascimento").css("color", "#1974CE");
    }
});

$(".nascimento").blur(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#F00" });
        $(".label-nascimento").css("color", "red");
    }
});

$(".nascimento").keyup(function () {
    if ($(this).val() != "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".nascimento").css("color", "#1974CE");
        $(".label-nascimento").css("color", "#1974CE");
    }

});

// DATA NASCIMENTO REPRESENTANTE LEGAL

$(".nascimento-representante-legal").focus(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".nascimento-representante-legal").css("color", "#1974CE");
        $(".label-nascimento-representante-legal").css("color", "#1974CE");
    }
});

$(".nascimento-representante-legal").blur(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#F00" });
        $(".label-nascimento-representante-legal").css("color", "red");
    }
});

$(".nascimento-representante-legal").keyup(function () {
    if ($(this).val() != "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".nascimento-representante-legal").css("color", "#1974CE");
        $(".label-nascimento-representante-legal").css("color", "#1974CE");
    }

});

$(".nascimento-representante-legal").blur(function () {

    if (validarData($(".nascimento-representante-legal").val())) {

        $(this).css({ "border-color": "#1974CE" });
        $(".nascimento-representante-legal").css("color", "#1974CE");
        $(".label-nascimento-representante-legal").css("color", "#1974CE");
        return;
    }

    $(".nascimento-representante-legal").css("border-color", "red" );
    $(".nascimento-representante-legal").css("color", "red");
    $(".label-nascimento-representante-legal").css("color", "red");

});

// SEXO

$(".sexo").focus(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".sexo").css("color", "#1974CE");
        $(".label-sexo").css("color", "#1974CE");
    }
});

$(".sexo").blur(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#F00" });
        $(".label-sexo").css("color", "red");
    }
});

$(".sexo").keyup(function () {
    if ($(this).val() != "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".sexo").css("color", "#1974CE");
        $(".label-sexo").css("color", "#1974CE");
    }

});

// CHECKBOX

$(".checkbox").focus(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".checkbox").css("color", "#1974CE");
        $(".label-checkbox").css("color", "#1974CE");
    }
});

$(".checkbox").blur(function () {
    if ($(this).val() == "") {
        $(this).css({ "border-color": "#F00" });
        $(".label-checkbox").css("color", "red");
    }
});

$(".checkbox").keyup(function () {
    if ($(this).val() != "") {
        $(this).css({ "border-color": "#1974CE" });
        $(".checkbox").css("color", "#1974CE");
        $(".label-checkbox").css("color", "#1974CE");
    }

});

