function callLogin(callback, token, login, password) {

    $.ajax({
        async: true,
        url: URLBase + "/corretorservicos/1.0/login",
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Cache-Control": "no-cache",
            "Authorization": "Bearer " + token
        },
        processData: false,
        data: "{\r\n\"usuario\": \"" + login + "\",\r\n\"senha\": \"" + password + "\"\r\n}\r\n\r\n ",
        success: function (resp) {
            callback(resp)
        },
        error: function (xhr) {


            //console.log(JSON.stringify(resp.statusText));
            //ob.imprimirAlgo(JSON.stringify(resp.statusText));
            //console.log(xhr.status);
            //$("#loadingLogin").addClass('hide');

            if (xhr.status == 403) {
                swal("Ops!", "Login ou senha inválida.", "error");
                $("#erroLogin").removeClass('hide');
                $("#erroLogin").html("CPF ou senha inválida.");

                return;
            } else if (xhr.status == 0) {
                swal("Ops!", "Erro na conexão, tente novamente.", "error");
                //swal.close();
                return;
            }

        }, timeout: 15000
    });
}

$("#continuarLogin").click(function () {

    if (!TestaCPF($("#cpf").val().replace().replace(/\D/g, ''))) {
        swal("Ops", "CPF inválido", "error");

        return;
    }

    if ($("#password").val().length < 8) {
        swal("Ops", "A senha deve conter no mínimo 8 caracteres", "error");

        return;
    }

    logarETrazerDadosUsuario();

    swal({
        title: "Aguarde",
        text: 'Estamos procurando seus dados',
        content: "input",
        showCancelButton: false,
        showConfirmButton: false,
        imageUrl: "img/icon-aguarde.gif",
        icon: "info",
        button: {
            text: "...",
            closeModal: false,
        },
    });
});

function callDadosForcaVenda(callback, token, cpf) {

    $.ajax({
        async: true,
        url: URLBase + "/corretorservicos/1.0/forcavenda/" + cpf,
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Cache-Control": "no-cache",
            "Authorization": "Bearer " + token
        },
        success: function (resp) {
            //$("#loadingLogin").addClass('hide');
            callback(resp);
            swal.close();
        },
        error: function (xhr) {
            //$("#loadingLogin").addClass('hide');
            console.log(xhr);
            if (xhr.status == 0) {

                swal.close();
                return;
            }
            //console.log(JSON.stringify(resp.statusText));
            //ob.imprimirAlgo(JSON.stringify(resp.statusText));
            swal.close();
        }
    });
}

// Mantêm os inputs em cache:
var inputs = $('input');

/*
// Chama a função de verificação quando as entradas forem modificadas
// Usei o 'keyup', mas 'change' ou 'keydown' são tambêm eventos úteis aqui
inputs.on('keyup', verificarInputs);

function verificarInputs() {
    var preenchidos = true;  // assumir que estão preenchidos

    inputs.each(function () {

        // verificar um a um e passar a false se algum falhar
        // no lugar do if pode-se usar alguma função de validação, regex ou outros
        var id = this.id;
        if (!this.value || !TestaCPF($("#cpf").val()) || $("#password").val().length < 8) {//|| !TestaCPF($("#cpf").val()) || $("#password").val().length < 8){// || $("#password").val().length < 8){//|| !TestaCPF($("#cpf").val()) || $("#password").val().length < 8 ) {
            preenchidos = false;
            $('button').prop('disabled', preenchidos);
            // parar o loop, evitando que mais inputs sejam verificados sem necessidade
            return false;
        }
    });
    // Habilite, ou não, o <button>, dependendo da vari�vel:
    $('button').prop('disabled', !preenchidos); //,
    return true;
}
*/

//$("#continuarLogin").click(function () {
//
//    logarETrazerDadosUsuario();
//
//    //window.location = "logado.html";
//
//});

function logarETrazerDadosUsuario() {

    //$("#loadingLogin").removeClass('hide');

    var online = navigator.onLine;
    if (!online) {
        //$("#loadingLogin").addClass('hide');
        $("#erroLogin").removeClass('hide');
        $("#erroLogin").html("Erro na conexão, tente novamente.");
        return;
    }
    var cpfTratado = $("#cpf").val().replace(/\D/g, '');

    $("divLoading").removeClass('hide');

    callTokenProd(function (dataToken) {

        callLogin(function (dataLogin) {

            //ob.imprimirSucess();
            //console.log(dataUsuarios);

            //var teste = dataUsuarios.status;

            callDadosForcaVenda(function (dataDadosUsuario) { // Essa request salva os dados dos corretores no localstorage

                swal.close();
                //console.log(dataDadosUsuario);
                //console.log(JSON.stringify(dataDadosUsuario));
                //ob.imprimirSucess();
                //console.log(dataDadosUsuario);
                var forca = getRepository("dadosUsuario");

                forca.nome = dataDadosUsuario.nome;
                forca.cargo = dataDadosUsuario.cargo;
                forca.cpf = dataDadosUsuario.cpf;
                forca.email = dataDadosUsuario.email;
                forca.login = dataDadosUsuario.cpf;
                forca.nomeEmpresa = dataDadosUsuario.corretora.razaoSocial;
                forca.nomeGerente = dataDadosUsuario.nomeGerente;
                forca.responsavel = dataDadosUsuario.responsavel;
                forca.rg = dataDadosUsuario.rg;
                forca.senha = dataDadosUsuario.senha;
                forca.statusUsuario = dataDadosUsuario.statusForcaVenda;
                forca.telefone = dataDadosUsuario.celular;
                forca.cnpjCorretora = dataDadosUsuario.corretora.cnpj;
                forca.codigo = dataLogin.codigoUsuario;

                put("dadosUsuario", JSON.stringify(forca));
                //console.log(JSON.stringify(dataDadosUsuario));
                //ob.imprimirAlgo(JSON.stringify(dataDadosUsuario));
                //Marcelo
                //ob.salvarDadosUsuario(JSON.stringify(forca));
                //ob.salvarDadosUsuario();

                window.location = "logado.html";

            }, dataToken.access_token, cpfTratado);

        }, dataToken.access_token, cpfTratado, $("#password").val());

    });
}

$(document).ready(function () {

    $("#cpfcnpj").keyup(function () {

        var caracteres = $(this).val().replace(/'\s'/g, '');
        var isNumeric = function (value) {
            return /^\d+(?:\.\d+)?$/.test(value);
        };

        console.log(isNumeric(caracteres));

        if (isNumeric(caracteres)) {
            if (caracteres.length == 11) {
                $("#cpfcnpj").mask("000.000.000-000");
            } else if (caracteres.length == 14) {
                $("#cpfcnpj").mask("00.000.000/0000-00");
            }
        } else {
            $("#cpfcnpj").unmask();
        }

        // ajustando foco
        var elem = this;
        setTimeout(function () {
            // mudo a posição do seletor
            elem.selectionStart = elem.selectionEnd = 10000;
        }, 0);
        // reaplico o valor para mudar o foco
        var currentValue = $(this).val();
        $(this).val('');
        $(this).val(currentValue);
    });

    $(".validacaoDivErro").blur(function () {

        if ($("#cpfcnpj").val().length == 14 && TestaCPF($("#cpfcnpj").val().replace(/\D/g, ''))) {
            $("#btnOdont").removeClass('disabled');
            $(".img-erro-cpf").hide(250);
            $("#divErroCpf").hide(250);
            $("#jsCpfCnpj").removeClass("text-red-cpfcnpj");
            $("#jsCpfCnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("text-red-cpfcnpj");
            $("#cpfcnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("form-control-login-red");
            $("#cpfcnpj").addClass("form-control-login-blue");

            /*
            $("#cpfcnpj").removeClass("inputLabelErro");
            $("#cpfcnpj").addClass("inputLabelFocus");
            */
            return;
        }

        if ($("#cpfcnpj").val().length == 18 && validaCnpj($("#cpfcnpj").val().replace(/\D/g, ''))) {
            $("#btnOdont").removeClass('disabled');
            $(".img-erro-cpf").hide(250);
            $("#divErroCpf").hide(250);
            $("#jsCpfCnpj").removeClass("text-red-cpfcnpj");
            $("#jsCpfCnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("text-red-cpfcnpj");
            $("#cpfcnpj").addClass("text-blue-cpfcnpj");
            $("#cpfcnpj").removeClass("form-control-login-red");
            $("#cpfcnpj").addClass("form-control-login-blue");

            /*
            $("#cpfcnpj").removeClass("inputLabelErro");
            $("#cpfcnpj").addClass("inputLabelFocus");
            */
            return;
        }

        $("#btnOdont").addClass('disabled');
        $("#jsCpfCnpj").addClass("text-red-cpfcnpj");
        $("#cpfcnpj").addClass("text-red-cpfcnpj");
        $("#cpfcnpj").addClass("form-control-login-red");
        $("#cpfcnpj").removeClass("form-control-login-blue");
        /*
        $("#cpfcnpj").removeClass("inputLabelBlur");
        $("#cpfcnpj").removeClass("inputLabelFocus");
        $("#cpfcnpj").addClass("inputLabelErro");
        */

    });

    var urlAtual = window.location.href.toString();

    if(urlAtual.indexOf('login?error') != -1){
        $(".img-erro-cpf").show(250);
        $("#divErroCpf").show(250);
    }

    $(".validacaoDivErro").keyup(function () {
        $(".img-erro-cpf").hide(250);
        $("#divErroCpf").hide(250);
    });

    $(".validacaoDivErro").focus(function () {
        if($(this).val() == ""){
            $(".img-erro-cpf").hide(250);
            $("#divErroCpf").hide(250);
        }
    });

    $(".img-erro-cpf").click(function () {
        $(".img-erro-cpf").hide(250);
        $("#divErroCpf").hide(250);

    });

    $(window).on('load',function(){
        $('#modalAtualizaContrato').modal('show');
    });

});

