var URLBase = "";

$(document).ready(function () {


    $("#confirmarLogin").click(function(){

        if($("#senha").val().length < 8){
            swal("Ops!", "A senha deve conter no minimo 8 caracteres", "error");

            return;
        }

        if($("#confirmarSenha").val() != $("#senha").val()){
            swal("Ops!", "As senhas devem ser iguais", "error");

            return;
        }

        callTokenProd(function (dataToken) {

            callEsqueciMinhaSenha(function (dataEsqueciMinhaSenha) {

                var cdCorretora = dataEsqueciMinhaSenha.cdCorretora;
                var cdForca = dataEsqueciMinhaSenha.cdForcaVenda;
                var nome = dataEsqueciMinhaSenha.nome;
                var celular = dataEsqueciMinhaSenha.celular;
                var email = dataEsqueciMinhaSenha.email;
                var senha = $("#senha").val();
                var cdCorretoraForcaVenda = dataEsqueciMinhaSenha.cdCorretoraForcaVenda;

                if(cdCorretora && cdForca) {

                    swal(
                        {
                            title: "Atencao!",
                            text: "Situacao ineXperada!",
                            type: "success"
                        },
                        function (isConfirm) {
                            window.location.href = "/login";
                        }
                    );

                } else if(cdForca){

                    callPutForcaVenda(
                        function (dataPutSenha) {
                            swal(
                                {
                                    title: "Feito!",
                                    text: "Senha alterada com sucesso!",
                                    type: "success"
                                },
                                function (isConfirm) {
                                    window.location.href = "/login";
                                }
                            );
                        },
                        dataToken.token,
                        cdForca,
                        nome,
                        celular,
                        email,
                        senha
                    );

                }else if(cdCorretora) {

                    callPutCorretora(function (dataPutSenha) {

                            swal(
                                {
                                    title: "Feito!",
                                    text: "Senha Corretora alterada com sucesso!",
                                    type: "success"
                                },
                                function (isConfirm) {
                                    window.location.href = "/login";
                                }
                            );
                        },
                        dataToken.token,
                        cdCorretora,
                        senha,
                        cdCorretoraForcaVenda
                    );

                }


            }, dataToken.token);
        });
    });

});

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

function callPutCorretora(callback, token, cdCorretora, senha) {

    var json = {"cdCorretora": cdCorretora, "senha": senha};

    $.ajax({
        async: true,
        url: "https://api.odontoprev.com.br:8243/corretorservicos/1.0/corretora/login",
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

function callEsqueciMinhaSenha(callback, token) {

    var chaveRedefinirSenha = location.href.substring(location.href.lastIndexOf('/') + 1);

    $.ajax({

        async: true,
        url:  "https://api.odontoprev.com.br:8243/corretorservicos/1.0/esqueciMinhaSenha/" + chaveRedefinirSenha,
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        processData: false,
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {

        }
    });
}

function callPutForcaVenda(callback, token, codForca, nome, celular, email, senha, cdCorretora) {


    var json = {
        "cdForcaVenda": codForca,
        "nome": nome,
        "celular": celular,
        "email": email,
        "senha": senha,
        "corretora": {
            "cdCorretora": cdCorretora
        }
    };


    $.ajax({
        async: true,
        url: "https://api.odontoprev.com.br:8243/corretorservicos/1.0/forcavenda",
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

$(document).ready(function () {

    $(".validacaoDivErro").blur(function () {

        if ($("#confirmarSenha").val() == $("#senha").val()) {
            $("#confirmarLogin").removeClass('disabled');
            $(".img-erro-cpf").hide(250);
            $("#divErroCpf").hide(250);
            $("#confirmarLogin").removeClass("btnOdontCinza");
            $("#confirmarLogin").addClass("btnOdont");
            $("#senha").removeClass("inputLabelBlur");
            $("#senha").removeClass("inputLabelErro");
            $("#senha").addClass("inputLabelFocus");
            $("#senhaTxt").removeClass("text-red-cpfcnpj");
            $("#senhaTxt").addClass("text-blue-cpfcnpj");
            $("#confirmaTxt").removeClass("text-red-cpfcnpj");
            $("#confirmaTxt").addClass("text-blue-cpfcnpj");
            $("#confirmarSenha").removeClass("inputLabelBlur");
            $("#confirmarSenha").removeClass("inputLabelErro");
            $("#confirmarSenha").addClass("inputLabelFocus");


            return;
        }

        $(".img-erro-cpf").show(250);
        $("#divErroCpf").show(250);
        $("#confirmarLogin").addClass('disabled');
        $("#confirmarLogin").removeClass("btnOdont");
        $("#confirmarLogin").addClass("btnOdontCinza");
        $("#senha").removeClass("inputLabelBlur");
        $("#senha").removeClass("inputLabelFocus");
        $("#senha").addClass("inputLabelErro");
        $("#senhaTxt").removeClass("text-blue-cpfcnpj");
        $("#senhaTxt").addClass("text-red-cpfcnpj");
        $("#confirmaTxt").removeClass("text-blue-cpfcnpj");
        $("#confirmaTxt").addClass("text-red-cpfcnpj");
        $("#confirmarSenha").removeClass("inputLabelBlur");
        $("#confirmarSenha").removeClass("inputLabelFocus");
        $("#confirmarSenha").addClass("inputLabelErro");
    });
});