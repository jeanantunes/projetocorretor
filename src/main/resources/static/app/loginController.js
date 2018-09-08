$(document).ready(function () {

    $('#modalAtualizaContrato').modal('show');

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

});

