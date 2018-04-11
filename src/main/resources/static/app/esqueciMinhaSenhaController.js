$(document).ready(function () {

    $("#confirmarLogin").click(function(){

        if($("#confirmarSenha").val() != $("#senha").val()){
            swal("Ops!", "As senhas devem ser identicas", "error");

            return;
        }

        callTokenProd(function (dataToken) {

            callEsqueciMinhaSenha(function (dataEsqueciMinhaSenha) {

                var cdForca = dataEsqueciMinhaSenha.cdForcaVenda;
                var nome = dataEsqueciMinhaSenha.nome;
                var celular = dataEsqueciMinhaSenha.celular;
                var email = dataEsqueciMinhaSenha.email;
                var senha = $("#senha").val();

                callPutForcaVenda(function (dataPutSenha) {
                    swal({
                            title: "Feito!",
                            text: "Senha alterada com sucesso!",
                            type: "success"
                        },
                        function (isConfirm) {
                            window.location.href = "/login";
                        });
                }, dataToken.token, cdForca, nome, celular, email, senha);
            });
        });
    });

});

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

function callEsqueciMinhaSenha(callback) {

    var chaveRedefinirSenha = location.href.substring(location.href.lastIndexOf('/') + 1);

    $.ajax({

        async: true,
        url: "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/esqueciMinhaSenha/" + chaveRedefinirSenha,
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        },
        processData: false,
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {

        }
    });
}

function callPutForcaVenda(callback, token, codForca, nome, celular, email, senha) {

    var json = {"cdForcaVenda": codForca, "nome": nome, "celular": celular, "email": email, "senha": senha};

    $.ajax({
        async: true,
        url: "https://api-it1.odontoprev.com.br:8243/corretorservicos/1.0/forcavenda/login",
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        processData: false,
        data: JSON.stringify(json),
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {

        }
    });
}