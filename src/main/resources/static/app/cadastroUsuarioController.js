$(document).ready(function () {

    var termo = getComponent("termoCadastro");

    localStorage.removeItem("reCadastro");

    $(".componenteTermo").append(termo);

    $("#nomeNaoCadastrado").blur(function () {

        $("#nomeNaoCadastrado").val($("#nomeNaoCadastrado").val().trim());

    });

    $(".seta-home").click(function () {
        history.back();
    });

    $("#nomeNaoCadastrado").keyup(function () {

        var capturandoEspaco = $("#nomeNaoCadastrado").val().substring($("#nomeNaoCadastrado").val().length - 2, $("#nomeNaoCadastrado").val().length);

        if (capturandoEspaco == "  ") {

            $("#nomeNaoCadastrado").val($("#nomeNaoCadastrado").val().substring(0, $("#nomeNaoCadastrado").val().length - 1))

        }
    });

    $("#termoNCadastrado").scroll(function () {

        if ($(this).scrollTop() + $(this).innerHeight() >= this.scrollHeight) {

             $("#squaredOne").prop('disabled', false);
             $("#squaredOneLabel").prop('disabled', false);
             
         }
     
    });

    $("#termoCadastrado").scroll(function () {

        if ($(this).scrollTop() + $(this).innerHeight() >= this.scrollHeight) {

            $("#squaredOneTermoCadastrado").prop('disabled', false);
            $("#squaredOneTermoCadastradoLabel").prop('disabled', false);

        }

    });

    $("#btnCpfOdont").click(function () {

        var cpfValidado = $("#cpf").val().replace(/\D/g, '');

        if (TestaCPF(cpfValidado)) {

            callTokenProd(function (dataToken) {
                callForcaVenda(function (dataDadosUsuario) {

                    if (dataDadosUsuario.statusForcaVenda == "Aguardando Aprovação") {

                        swal("Ops!", "Seu cadastro está aguardando aprovação!", "error");
                        return;
                    }

                    if (dataDadosUsuario.statusForcaVenda == "Ativo") {

                        swal({
                            title: "Você já está cadastrado",
                            text: "Volte para a página inicial, insira seu CPF e senha e clique em entrar.",
                            type: "error",
                            showCancelButton: false,
                            confirmButtonText: 'OK',
                            closeOnConfirm: false,
                            closeOnCancel: false
                        });

                        return;
                    }

                    if (dataDadosUsuario.cdForcaVenda != null && dataDadosUsuario.statusForcaVenda.toUpperCase() == "PRÉ-CADASTRO") {

                        swal.close();
                        $("#celOdont").removeClass("hide");
                        $("#cpfOdont").addClass("hide");
                        $("#nomePreCadastrado").val(dataDadosUsuario.nome);
                        $("#celularPreCadastrado").val(aplicarMascaraTelefone(dataDadosUsuario.celular));
                        $("#emailPreCadastrado").val(dataDadosUsuario.email);

                        localStorage.setItem("dadosUsuario", JSON.stringify(dataDadosUsuario));

                    }

                    else if (dataDadosUsuario.cdForcaVenda != null && (dataDadosUsuario.statusForcaVenda.toUpperCase() == "INATIVO" || dataDadosUsuario.statusForcaVenda.toUpperCase() == "REPROVADO")) {
                    
                        swal.close();
                        put("reCadastro", true);
                        $("#divCadastroInativo").removeClass("hide");
                        $("#cpfOdont").addClass("hide");
                        $("#nomeInativoCadastrado").val(dataDadosUsuario.nome);
                        $("#celularInativoCadastrado").val(aplicarMascaraTelefone(dataDadosUsuario.celular));
                        $("#emailInativoCadastrado").val(dataDadosUsuario.email);
                    
                        localStorage.setItem("dadosUsuario", JSON.stringify(dataDadosUsuario));
                    
                    }
                    else if (dataDadosUsuario.cdForcaVenda == null) {
                        swal.close();
                        put("reCadastro", false);
                        $("#celOdontCorretora").removeClass("hide");
                        $("#cpfOdont").addClass("hide");
                    }

                }, dataToken.token, cpfValidado);
            });
        }
    });

    $("#btnInfoCorretoraNCpf").click(function () {

        var cnpjValidado = $("#cnpjNaoCadastrado").val().replace(/\D/g, '');

        callTokenProd(function (dataToken) {
            callCorretora(function (dataCorretora) {

                if (dataCorretora.cdCorretora == 0) {
         
                    $("#myModal").modal();
                    return;
                }

                var reCadastro = get("reCadastro");

                if (!reCadastro) {

                    var codCorretora = dataCorretora.cdCorretora;
                    var nome = $("#nomeNaoCadastrado").val();
                    var cpfTratado = $("#cpf").val().replace(/\D/g, '');
                    var celularTratado = $("#celularNaoCadastrado").val().replace(/\D/g, '');
                    var email = $("#emailNaoCadastrado").val();
                    var senha = $("#confirmar-senhaCpfFalse").val();
                    var dataNascimento = "12/09/2002";

                    callInputForcaVenda(function (dataForcaVenda) {

                        $("#infoCorretora").addClass('hide');
                        $("#cadastroSucessoCorretora").removeClass("hide");

                    }, dataToken.token, cpfTratado, celularTratado, email, codCorretora, nome, senha, dataNascimento);

                } else {

                    var dadosUsuario = get("dadosUsuario");
                    var codCorretora = dataCorretora.cdCorretora;
                    var nome = $("#nomeInativoCadastrado").val();
                    
                    var cpfTratado = $("#cpf").val().replace(/\D/g, '');
                    var celularTratado = $("#celularInativoCadastrado").val().replace(/\D/g, '');
                    var email = $("#emailInativoCadastrado").val();
                    var senha = $("#confirmar-senhaCpfFalse").val();
                    var dataNascimento = "12/09/2002";

                    callPutSenhaForcaVenda(function () {

                        $("#infoCorretora").addClass('hide');
                        $("#cadastroSucessoCorretora").removeClass("hide");

                    }, dataToken.token, dadosUsuario.cdForcaVenda, nome, celularTratado, email, senha, codCorretora);

                }

            }, dataToken.token, cnpjValidado);
        });

    });

    $("#btnTermoNCadastrado").click(function () {

        $("#termoOdontNCadastrado").addClass("hide");
        $("#infoCorretora").removeClass("hide")

    });

    $("#nomeNaoCadastrado").keyup(function () {

        if ($(this).val() != "" && $("#emailNaoCadastrado").val() != "" && $("#celularNaoCadastrado").val().length > 14 && validateEmail($("#emailNaoCadastrado").val()) && ValidaNome($("#nomeNaoCadastrado").val())) {

            $("#btnCelOdontNCpf").removeClass('background-color-cinza');
            $("#btnCelOdontNCpf").addClass('background-color-azul');
            $("#btnCelOdontNCpf").removeClass('disabled');
            return;
        }

        $("#btnCelOdontNCpf").removeClass('background-color-azul');
        $("#btnCelOdontNCpf").addClass('background-color-cinza');
        $("#btnCelOdontNCpf").addClass('disabled');
    });

    $("#squaredOne").click(function (e) {
        if ($("#squaredOne").is(":checked")) {

            $("#btnTermoNCadastrado").removeClass('background-color-cinza');
            $("#btnTermoNCadastrado").addClass('background-color-azul');
            $("#btnTermoNCadastrado").removeClass('disabled');

        } else {

            $("#btnTermoNCadastrado").removeClass('background-color-azul');
            $("#btnTermoNCadastrado").addClass('background-color-cinza');
            $("#btnTermoNCadastrado").addClass('disabled');
        }
    });

    $("#squaredOneTermoCadastrado").click(function (e) {
        if ($("#squaredOneTermoCadastrado").is(":checked")) {

            $("#btnTermo").removeClass('background-color-cinza');
            $("#btnTermo").addClass('background-color-azul');
            $("#btnTermo").removeClass("disabled");

        } else {
            $("#btnTermo").removeClass('background-color-azul');
            $("#btnTermo").addClass('background-color-cinza');
            $("#btnTermo").addClass("disabled");

        }
    });

    $("#celularNaoCadastrado").keyup(function () {

        if ($(this).val().length > 14 && $("#emailNaoCadastrado").val() != "" && $("#nomeNaoCadastrado").val() != "" && validateEmail($("#emailNaoCadastrado").val()) && ValidaNome($("#nomeNaoCadastrado").val())) {
            $("#btnCelOdontNCpf").removeClass('background-color-cinza');
            $("#btnCelOdontNCpf").addClass('background-color-azul');
            $("#btnCelOdontNCpf").removeClass('disabled');
            return;
        }

        $("#btnCelOdontNCpf").removeClass('background-color-azul');
        $("#btnCelOdontNCpf").addClass('background-color-cinza');
        $("#btnCelOdontNCpf").addClass('disabled');
    });

    $("#emailNaoCadastrado").blur(function () {

        if (validateEmail($(this).val())) {
            $(".img-erro-email").hide(250);
            $(".divErroEmail").hide(250);
        } else {
            $(".img-erro-email").show(250);
            $(".divErroEmail").show(250);
        }

        if ($(this).val() != "" && $("#celularNaoCadastrado").val().length > 14 && $("#nomeNaoCadastrado").val() != "" && validateEmail($(this).val()) && ValidaNome($("#nomeNaoCadastrado").val())) {
            $("#btnCelOdontNCpf").removeClass('background-color-cinza');
            $("#btnCelOdontNCpf").addClass('background-color-azul');
            $("#btnCelOdontNCpf").removeClass('disabled');
            return;
        }

        $("#btnCelOdontNCpf").removeClass('background-color-azul');
        $("#btnCelOdontNCpf").addClass('background-color-cinza');
        $("#btnCelOdontNCpf").addClass('disabled');
    });

    $("#emailNaoCadastrado").keyup(function () {

        $(".img-erro-email").hide(250);

        if ($(this).val() != "" && $("#celularNaoCadastrado").val().length > 14 && $("#nomeNaoCadastrado").val() != "" && validateEmail($(this).val()) && ValidaNome($("#nomeNaoCadastrado").val())) {
            $("#btnCelOdontNCpf").removeClass('background-color-cinza');
            $("#btnCelOdontNCpf").addClass('background-color-azul');
            $("#btnCelOdontNCpf").removeClass('disabled');
            return;
        }

        $("#btnCelOdontNCpf").removeClass('background-color-azul');
        $("#btnCelOdontNCpf").addClass('background-color-cinza');
        $("#btnCelOdontNCpf").addClass('disabled');

    });

    $("#cnpjNaoCadastrado").keyup(function () {

        if ($("#cnpjNaoCadastrado").val().length == 18 && validaCnpj($("#cnpjNaoCadastrado").val().replace(/\D/g, ''))) {

            $("#btnInfoCorretoraNCpf").removeClass('background-color-cinza');
            $("#btnInfoCorretoraNCpf").addClass('background-color-azul');
            $("#btnInfoCorretoraNCpf").removeClass('disabled');
            return;
        }

        $("#btnInfoCorretoraNCpf").removeClass('background-color-azul');
        $("#btnInfoCorretoraNCpf").addClass('background-color-cinza');
        $("#btnInfoCorretoraNCpf").addClass('disabled');
    });

    $("#cpf").keyup(function () {

        if ($("#cpf").val().length == 14 && TestaCPF($("#cpf").val().replace(/\D/g, ''))) {

            $("#btnCpfOdont").removeClass('background-color-cinza');
            $("#btnCpfOdont").addClass('background-color-azul');
            $("#btnCpfOdont").removeClass('disabled');
            return;
        }

        $("#btnCpfOdont").removeClass('background-color-azul');
        $("#btnCpfOdont").addClass('background-color-cinza');
        $("#btnCpfOdont").addClass('disabled');

    });

    $(".validacaoDivErro").blur(function () {

        if ($("#cpf").val().length == 14 && TestaCPF($("#cpf").val().replace(/\D/g, ''))) {
            $("#btnCpfOdont").removeClass('background-color-cinza');
            $("#btnCpfOdont").addClass('background-color-azul');
            $("#btnCpfOdont").removeClass('disabled');
            $(".img-erro-cpf").hide(250);
            $("#divErroCpf").hide(250);
            return;
        }

        if ($("#cpf").val() != "") {

            $("#btnCpfOdont").addClass('background-color-cinza');
            $("#btnCpfOdont").removeClass('background-color-azul');
            $(".img-erro-cpf").show(250);
            $("#divErroCpf").show(250);
            $("#btnCpfOdont").addClass('disabled');
            return;
        }

        $("#btnCpfOdont").addClass('background-color-cinza');
        $("#btnCpfOdont").removeClass('background-color-azul');

    });

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

    $("#btnTermo").click(function () {

        var dados = get("dadosUsuario");

        var senha = $("#confirmar-senhaCpfTrue").val();
        var cpf = $("#cpf").val().replace(/\D/g, '');
        var nome = $("#nomePreCadastrado").val();
        var telefone = $("#celularPreCadastrado").val().replace(/\D/g, '');
        var email = $("#emailPreCadastrado").val();
        var senha = $("#confirmar-senhaCpfTrue").val();
        var codCorretora = dados.corretora.cdCorretora;
        var cnpj = dados.corretora.cnpj;
        var nomeEmpresa = dados.corretora.razaoSocial;
        var codigoForca = dados.cdForcaVenda;

        callTokenProd(function (dataToken) {

            callPutForcaVenda(function (dataForcaVenda) {

                dados.nome = nome;
                dados.cpf = cpf;
                dados.celular = telefone;
                dados.email = email;
                dados.cnpj = cnpj;

                put("dadosUsuario", JSON.stringify(dados));

                callForcaVenda(function (dataDadosUsuario) {

                    swal.close();

                    var forca = getRepository("dadosUsuario");

                    $("#corretoraCadastro").html(nomeEmpresa);

                    forca.nome = dataDadosUsuario.nome;
                    forca.cargo = dataDadosUsuario.cargo;
                    forca.cpf = dataDadosUsuario.cpf;
                    forca.email = dataDadosUsuario.email;
                    forca.login = dataDadosUsuario.login;
                    forca.nomeEmpresa = dataDadosUsuario.corretora.razaoSocial;
                    forca.nomeGerente = dataDadosUsuario.nomeGerente;
                    forca.responsavel = dataDadosUsuario.responsavel;
                    forca.rg = dataDadosUsuario.rg;
                    forca.senha = dataDadosUsuario.senha;
                    forca.statusUsuario = dataDadosUsuario.statusForcaVenda;
                    forca.telefone = dataDadosUsuario.celular;
                    forca.codigo = dataDadosUsuario.cdForcaVenda;

                    put("dadosUsuario", JSON.stringify(forca));

                    login.salvarDadosUsuario(JSON.stringify(forca));

                    $("#termoOdont").addClass("hide")
                    $("#cadastroSucesso").removeClass("hide")

                }, dataToken.token, cpf);

            }, dataToken.token, codigoForca, nome, telefone, email, senha);

        });

    });

    // senha
    $(".password").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "1974CE" });
            $(".password").css("color", "#1974CE");
            $(".label-password").css("color", "#1974CE");
        }
    });

    // sennha corfimar

    $(".password-confirm").focus(function () {
        if ($(this).val() == "") {
            $(this).css({ "border-color": "1974CE" });
            $(".password").css("color", "#1974CE"); // se clicar dentro fica azul
            $(".label-password-confirm").css("color", "#1974CE");
        }
    });

    // Validacao da pagina de cadastro associado a corretora

    $("#senhaCpfTrue").focus(function () {

        var senha = $(this).val();

        $(this).css({ "border-color": "#1974CE" });
        $(this).css("color", "#1974CE");
        $(".label-password").css("color", "#1974CE");
        $("#divErroSenhaCpfTrue").hide(250);
        $(".label-password-confirm-8").css("color", "#1974CE");
        $(".img-erro-senha-cpf-true").hide(250);
    });

    $("#senhaCpfTrue").blur(function () {

        $("#btnkeyOdont").addClass('disabled');

        var confirmarSenha = $("#confirmar-senhaCpfTrue").val();
        var senha = $("#senhaCpfTrue").val();

        if (senha.length < 8) {

            $(this).css({ "border-color": "#FF4141" });
            $(this).css("color", "#FF4141");
            $(".label-password").css("color", "#FF4141");
            $(".label-password-confirm-8").css("color", "#FF4141");
            $(".img-erro-senha-cpf-true").hide(250);
            $("#btnkeyOdont").removeClass("background-color-azul");
            $("#btnkeyOdont").addClass("background-color-cinza");

            return;
        }
        else if (senha.length > 7) {

            $(this).css({ "border-color": "#1974CE" });
            $(this).css("color", "#1974CE");
            $(".label-password").css("color", "#1974CE");
            $(".label-password-confirm-8").css("color", "#1974CE");
            $("#btnkeyOdont").addClass('disabled');

            if (confirmarSenha != "" && confirmarSenha != senha) {

                $("#confirmar-senhaCpfTrue").css({ "border-color": "#FF4141" });
                $(".password-confirm").css("color", "#FF4141");
                $(".label-password-confirm").css("color", "#FF4141");
                $("#btnkeyOdont").removeClass("background-color-azul");
                $("#btnkeyOdont").addClass("background-color-cinza");
                $("#btnkeyOdont").addClass('disabled');
                $("#divErroSenhaCpfTrue").show(250);
                $(".img-erro-senha-cpf-true").show(250);

            } else {

                $(".img-erro-senha-cpf-true").hide(250);

                if (senha == confirmarSenha) {

                    $("#confirmar-senhaCpfTrue").css({ "border-color": "#1974CE" });
                    $("#confirmar-senhaCpfTrue").css("color", "#1974CE");
                    $(".label-password-confirm").css("color", "#1974CE");
                    $("#btnkeyOdont").removeClass("background-color-cinza");
                    $("#btnkeyOdont").addClass("background-color-azul");
                    $("#divErroSenhaCpfTrue").hide(250);
                    $("#btnkeyOdont").removeClass('disabled');
                }
            }
        }

    });

    $("#confirmar-senhaCpfTrue").focus(function () {

        $(this).css({ "border-color": "#1974CE" });
        $(this).css("color", "#1974CE");
        $(".label-password-confirm").css("color", "#1974CE");
        $("#divErroSenhaCpfTrue").hide(250);
        $(".img-erro-senha-cpf-true").hide(250);
    });

    $("#confirmar-senhaCpfTrue").blur(function () {

        $("#btnkeyOdontCpf").addClass('disabled');

        var confirmarSenha = $("#confirmar-senhaCpfTrue").val();
        var senha = $("#senhaCpfTrue").val();

        if (confirmarSenha != senha) {

            $(this).css({ "border-color": "#FF4141" });
            $(".password-confirm").css("color", "#FF4141");
            $(".label-password-confirm").css("color", "#FF4141");
            $("#btnkeyOdont").removeClass("background-color-azul");
            $("#btnkeyOdont").addClass("background-color-cinza");
            $(".img-erro-senha-cpf-true").show(250);
            $("#divErroSenhaCpfTrue").show(250);

        } else if (confirmarSenha == senha && confirmarSenha.length > 7 && senha.length > 7) {

            $(this).css({ "border-color": "#1974CE" });
            $(this).css("color", "#1974CE");
            $(".label-password-confirm").css("color", "#1974CE");
            $("#btnkeyOdont").removeClass("background-color-cinza");
            $("#btnkeyOdont").addClass("background-color-azul");
            $("#btnkeyOdont").removeClass('disabled');
            $("#divErroSenhaCpfTrue").hide(250);
            $(".img-erro-senha-cpf-true").hide(250);
        }

    });

    // Validaçao da pagina de cadastro nao associado a corretora

    $("#senhaCpfFalse").focus(function () {

        var senha = $(this).val();

        $(this).css({ "border-color": "#1974CE" });
        $(this).css("color", "#1974CE");
        $(".label-password").css("color", "#1974CE");
        $("#divErroSenhaCpfFalse").hide(250);
        $(".label-password-confirm-8").css("color", "#1974CE");
        $(".img-erro-senha-cpf-false").hide(250);
    });



    $("#senhaCpfFalse").blur(function () {

        $("#btnkeyOdontNCpf").addClass('disabled');

        var confirmarSenha = $("#confirmar-senhaCpfFalse").val();
        var senha = $("#senhaCpfFalse").val();

        if (senha.length < 8) {

            $(this).css({ "border-color": "#FF4141" });
            $(this).css("color", "#FF4141");
            $(".label-password").css("color", "#FF4141");
            $(".label-password-confirm-8").css("color", "#FF4141");
            $(".img-erro-senha-cpf-false").hide(250);
            $("#btnkeyOdontNCpf").removeClass("background-color-azul");
            $("#btnkeyOdontNCpf").addClass("background-color-cinza");

            return;
        }
        else if (senha.length > 7) {

            $(this).css({ "border-color": "#1974CE" });
            $(this).css("color", "#1974CE");
            $(".label-password").css("color", "#1974CE");
            $(".label-password-confirm-8").css("color", "#1974CE");
            $("#btnkeyOdontNCpf").addClass('disabled');

            if (confirmarSenha != "" && confirmarSenha != senha) {

                $("#confirmar-senhaCpfFalse").css({ "border-color": "#FF4141" });
                $(".password-confirm").css("color", "#FF4141");
                $(".label-password-confirm").css("color", "#FF4141");
                $("#btnkeyOdontNCpf").removeClass("background-color-azul");
                $("#btnkeyOdontNCpf").addClass("background-color-cinza");
                $("#btnkeyOdontNCpf").addClass('disabled');
                $("#divErroSenhaCpfFalse").show(250);
                $(".img-erro-senha-cpf-false").show(250);

            } else {

                $(".img-erro-senha-cpf-false").hide(250);

                if (senha == confirmarSenha) {

                    $("#confirmar-senhaCpfFalse").css({ "border-color": "#1974CE" });
                    $("#confirmar-senhaCpfFalse").css("color", "#1974CE");
                    $(".label-password-confirm").css("color", "#1974CE");
                    $("#btnkeyOdontNCpf").removeClass("background-color-cinza");
                    $("#btnkeyOdontNCpf").addClass("background-color-azul");
                    $("#divErroSenhaCpfFalse").hide(250);
                    $("#btnkeyOdontNCpf").removeClass('disabled');
                }
            }
        }

    });

    $("#confirmar-senhaCpfFalse").focus(function () {

        $(this).css({ "border-color": "#1974CE" });
        $(this).css("color", "#1974CE");
        $(".label-password-confirm").css("color", "#1974CE");
        $("#divErroSenhaCpfFalse").hide(250);
        $(".img-erro-senha-cpf-false").hide(250);
    });

    $("#confirmar-senhaCpfFalse").blur(function () {

        $("#btnkeyOdontNCpf").addClass('disabled');

        var confirmarSenha = $("#confirmar-senhaCpfFalse").val();
        var senha = $("#senhaCpfFalse").val();

        if (confirmarSenha != senha) {

            $(this).css({ "border-color": "#FF4141" });
            $(".password-confirm").css("color", "#FF4141");
            $(".label-password-confirm").css("color", "#FF4141");
            $("#btnkeyOdontNCpf").removeClass("background-color-azul");
            $("#btnkeyOdontNCpf").addClass("background-color-cinza");
            $(".img-erro-senha-cpf-false").show(250);
            $("#divErroSenhaCpfFalse").show(250);

        } else if (confirmarSenha == senha && confirmarSenha.length > 7 && senha.length > 7) {

            $(this).css({ "border-color": "#1974CE" });
            $(this).css("color", "#1974CE");
            $(".label-password-confirm").css("color", "#1974CE");
            $("#btnkeyOdontNCpf").removeClass("background-color-cinza");
            $("#btnkeyOdontNCpf").addClass("background-color-azul");
            $("#btnkeyOdontNCpf").removeClass('disabled');
            $("#divErroSenhaCpfFalse").hide(250);
            $(".img-erro-senha-cpf-false").hide(250);
        }
    });
});

function callForcaVenda(callback, token, cpf) {

    swal({
        title: "Aguarde",
        text: 'Estamos procurando seus dados',
        content: "input",
        imageUrl: "img/icon-aguarde.gif",
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
        url: URLBase + "/corretorservicos/1.0/forcavenda/" + cpf,
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {

        }
    });
}

function callDadosUsuarios(callback, token, cpf) {

    $.ajax({
        async: true,
        url: URLBase + "dcss/usuario/1.0/cpf/" + cpf,
        method: "GET",
        headers: {
			
            "Authorization": "Bearer " + token
        },
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {
            if (xhr.status == 0) {
                $("#erroLogin").removeClass('hide');
                $("#erroLogin").html("Sem conexão, tente novamente.");
            }
        }
    });
}

function callInputForcaVenda(callback, token, cpf, celular, email, corretora, nome, senha, dataNascimento) {
    var ativo = "false";
    var departamento = "Corretor";
    var cargo = "Corretor";

    $.ajax({
        async: true,
        url: URLBase + "/corretorservicos/1.0/forcavenda/",
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        processData: false,
        data: "{  \r\n\t\"nome\":\"" + nome + "\",\r\n\t\"celular\":\"" + celular + "\",\r\n\t\"email\":\"" + email + "\",\r\n\t\"corretora\":{  \r\n\t\t\"cdCorretora\":\"" + corretora + "\"\r\n\t},\r\n\t\"cpf\":\"" + cpf + "\",\r\n\t\"ativo\":" + ativo + ",\r\n\t\"departamento\":\"" + departamento + "\",\r\n\t\"cargo\":\"" + cargo + "\",\r\n\t\"dataNascimento\":\"" + dataNascimento + "\",\r\n\t\"senha\": \"" + senha + "\"\r\n}\r\n",
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {

        }
    });
}

function callPutForcaVenda(callback, token, codForca, nome, celular, email, senha) {
    var canalVenda = 57;
    var nomeEmpresa = "ODONTO CORRETOR";
    var nomeGerente = "ODONTO CORRETOR";
    var responsavel = "ODONTO CORRETOR";

    $.ajax({
        async: true,
        url: URLBase + "/corretorservicos/1.0/forcavenda/login",
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        processData: false,
        "data": "{\r\n\r\n    \"cdForcaVenda\": " + codForca + ",\r\n\r\n    \"nome\": \"" + nome + "\",\r\n\r\n    \"celular\": \"" + celular + "\",\r\n\r\n    \"email\": \"" + email + "\",\r\n\r\n    \"senha\": \"" + senha + "\",\r\n\r\n    \"nomeEmpresa\": \"" + nomeEmpresa + "\",\r\n\r\n    \"nomeGerente\": \"" + nomeGerente + "\",\r\n\r\n    \"responsavel\": \"" + responsavel + "\",\r\n\r\n    \"rg\": 0,\r\n\r\n    \"canalVenda\": " + canalVenda + "\r\n\r\n}",
        success: function (resp) {
            callback(resp);
        },
        error: function (xhr) {

        }
    });
}

function callPutSenhaForcaVenda(callback, token, codForca, nome, celular, email, senha, cdCorretora) {

    var setarPreCadastro = 1;

    var json = {
        "cdForcaVenda": codForca,
        "nome": nome,
        "celular": celular,
        "email": email,
        "senha": senha,
        "corretora": {
            "cdCorretora": parseInt(cdCorretora)
        },
        "statusForcaVenda": setarPreCadastro
    };

    $.ajax({
        async: true,
        url: URLBase + "/corretorservicos/1.0/forcavenda",
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

function callCorretora(callback, token, cnpj) {

    swal({
        title: "Aguarde",
        text: 'Estamos buscando a corretora',
        content: "input",
        imageUrl: "img/icon-aguarde.gif",
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
        url: URLBase + "/corretorservicos/1.0/corretora/" + cnpj,
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        success: function (resp) {
            callback(resp);
            swal.close();

        },
        error: function (xhr) {

        }
    });
}

// Aqui começa validacao de campos

function validateEmail(email) {
    var reg = /^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
    if (reg.test(email)) {
        return true;
    }

    return false;
}