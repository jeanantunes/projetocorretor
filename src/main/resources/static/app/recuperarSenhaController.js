$(document).ready(function () {

    defineConexao();
});

var URLBase = "";

function defineConexao() {

    $.ajax({
        url: "config/connection.json",
        type: "get",
        async: false,
        success: function (result) {
            conexao = eval(result);
        },
        error: function () {

        }
    });

    if (conexao.producaoLigado) {
        URLBase = conexao.producaoURL;
    }
    else {
        URLBase = conexao.homologacaoURL;
        console.log(URLBase);
    }
}

$("#continuarRecuperacaoDeSenha").click(function () {

    if (!TestaCPF($("#cpf").val())) {

        swal("Ops!", "CPF inválido", "error");
        return;
    }

    callTokenProd(function (dataToken) {
        callRecuperarSenha(function (dataRecuperar) {

            swal.close();
            $("#loadingRecuperacaoSenha").addClass('hide');
            $("#senhaEnviada").removeClass('hide');

            var position = dataRecuperar.mensagem.indexOf(":");
            var email = dataRecuperar.mensagem.substring(position + 2, dataRecuperar.mensagem.length);


            var doisPrimeirosCharEmail = email.substring(0, 2);
            var emailSemDominio = email.substring(0, email.indexOf("@"));
            var emailComAsterisco = doisPrimeirosCharEmail;

            for (i = 0; i < emailSemDominio.length - 2; i++) {
                emailComAsterisco += "*";
            }

            $("#emailRecuperacao").html(email.replace(emailSemDominio, emailComAsterisco));
        }, dataToken.token, $("#cpf").val().replace(/\D/g, ''));
    });

});



function callRecuperarSenha(callback, token, cpf) {

    var json = { "cpf": cpf };

    swal({
        title: "Aguarde",
        text: 'Estamos buscando seu CPF',
        content: "input",
        imageUrl: "img/load.gif",
        showCancelButton: false,
        showConfirmButton: false,
        icon: "info",
        button: {
            text: "...",
            closeModal: false,
        },
    });

    $.ajax({

        async: true,
        url:   URLBase + "/corretorservicos/1.0/esqueciMinhaSenha",//"https://api.odontoprev.com.br:8243/corretorservicos/1.0/esqueciMinhaSenha",
        method: "POST",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        processData: false,
        data: JSON.stringify({ "cpf": cpf }),
        success: function (resp) {
           callback(resp);
        },
        error: function (xhr) {

            if (xhr.status == 500) {
                swal("Ops!", "Não encontramos seu CPF", "error");
            } else swal.close();
            
        }
    });
}

function callTokenProd(callback) {

    $.ajax({
        async: true,
        url: "/get_token",
        method: "GET",

        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {
            swal("Ops!", "Erro na conexão, tente mais tarde", "error");
        }
    });
};

// Um link para redefinir sua senha foi enviado para o e-mail ***@hotmail.com.
// Acesse e finalize o processo