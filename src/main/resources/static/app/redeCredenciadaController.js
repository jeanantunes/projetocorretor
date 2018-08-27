﻿var estadoSelecionado = "";
var cidadeSelecionada = "";
var bairroSelecionado = "";


$(document).ready(function () {
    especialidades();
    estados();


    $('#bairros').blur(function () {

        $('#bairros').val($('#bairros').val().trim());

    });

    $("#estados").click(function () {

        $(this).css("color", "#1974CE");
        $(this).css("border-bottom", "2px solid #1974CE");
        $(".label-estado").css("color", "#1974CE");

    });

    $("#especs").click(function () {

        $(this).css("color", "#1974CE");
        $(this).css("border-bottom", "2px solid #1974CE");
        $(".label-especialidade").css("color", "#1974CE");

    });

    $("#estados").change(function () {

        $(this).css("color", "#1974CE");
        $(this).css("border-bottom", "2px solid #1974CE");
        $(".label-estado").css("color", "#1974CE");

        $('#cidades').val("");
        $('#bairros').val("");

        dadosCidades = [];

        autoCompleteCidades = [];

        var uf = $("#estados").val().trim();

        if(uf == "selecione"){

            $("#cidades").val("");
            $("#bairros").val("");

            autoCompleteCidades = [];
            dadosCidades = [];
            autoCompleteBairros = [];
            dadosBairros = [];
            return;
        }

        if(uf == estadoSelecionado) return;

        estadoSelecionado = uf;

        callTokenProd(function (dataToken) {

            callCidade(function (dataCidade) {

                //var sel = document.getElementById('cidades');
                //
                //var opt = document.createElement('option');
                ////console.log(dataEspecialidades[i].descricao);
                //opt.setAttribute('value', "selecione");
                ////console.log(dataEspecialidades[i].codigo);
                //var selecione = "Selecione...";
                //opt.appendChild(document.createTextNode(selecione));
                //sel.appendChild(opt);

                autoCompleteCidades = [];
                dadosCidades = [];
                autoCompleteBairros = [];
                dadosBairros = [];

                for (var i = 0; i < dataCidade.length; i++) {

                    //if (dataCidade[i].codigoUf == 0) continue

                    //var opt = document.createElement('option');
                    ////console.log(dataEspecialidades[i].descricao);
                    //opt.setAttribute('value', dataCidade[i].codigoCidade);
                    //opt.setAttribute('data-id', dataCidade[i].codigoMicroregiao);
                    ////console.log(dataEspecialidades[i].codigo);
                    //opt.appendChild(document.createTextNode(dataCidade[i].nome));
                    //

                    var cidades = new Object();
                    cidades.nome = removerAcentos(dataCidade[i].nome.toUpperCase().trim());
                    cidades.codigoMicroregiao = dataCidade[i].codigoMicroregiao;
                    cidades.codigoCidade = dataCidade[i].codigoCidade;

                    dadosCidades.push(cidades);

                    autoCompleteCidades.push(removerAcentos(dataCidade[i].nome.toUpperCase()).trim());
                    //
                    //sel.appendChild(opt);
                }

                $("#cidades").autocomplete({
                    source: autoCompleteCidades,
                    minLength: 0
                }).focus(function () {

                    $(this).autocomplete("search");
                });

                //document.getElementById('cidades').value = "selecione";

                swal.close();
            }, dataToken.access_token, uf);
        });
    });

    $("#especs").change(function () {
        $(this).css("color", "#1974CE");
        $(this).css("border-bottom", "2px solid #1974CE");
        $(".label-especialidade").css("color", "#1974CE");
    });

    $("#cidades").focus(function () {

    });

    $("#cidades").blur(function () {

        var cidade = $("#cidades").val();

        if (cidade == "") return;

        if(cidade == cidadeSelecionada) return;

        cidadeSelecionada = cidade;

        var cidade = dadosCidades.filter(function (x) { if (x.nome == $("#cidades").val().trim()) { return x.nome; } });

        if (cidade.length == 0) return;

        $('#bairros').val("");

        var codigoCidade = cidade[0].codigoCidade;//$("#cidades").val();
        var uf = $("#estados").val().trim();

        callTokenProd(function (dataToken) {

            callBairro(function (dataBairro) {

                if (dataBairro.status == 422) {

                    $('#bairros').val("");
                    return;
                }

                //var opt = document.createElement('option');
                ////console.log(dataEspecialidades[i].descricao);
                //opt.setAttribute('value', "selecione");
                ////console.log(dataEspecialidades[i].codigo);
                //var selecione = "Selecione...";
                //opt.appendChild(document.createTextNode(selecione));
                //sel.appendChild(opt);

                autoCompleteBairros = [];
                dadosBairros = [];

                for (var i = 0; i < dataBairro.length; i++) {

                    var bairros = new Object();

                    bairros.nome = removerAcentos(dataBairro[i].nome.replace("JD", "JARDIM").toUpperCase()).trim();
                    bairros.codigo = dataBairro[i].codigo;

                    dadosBairros.push(bairros);

                    autoCompleteBairros.push(removerAcentos(dataBairro[i].nome.replace("JD", "JARDIM").toUpperCase()).trim());

                    //var opt = document.createElement('option');
                    //console.log(dataEspecialidades[i].descricao);
                    //opt.setAttribute('value', dataBairro[i].codigo);
                    //console.log(dataEspecialidades[i].codigo);
                    //opt.appendChild(document.createTextNode(dataBairro[i].nome));
                    //sel.appendChild(opt);
                }

                $("#bairros").blur();

                $("#bairros").autocomplete({
                    source: autoCompleteBairros,
                    minLength: 0
                }).focus(function () {

                    $(this).autocomplete("search");
                });

                //document.getElementById('bairros').value = "selecione";

                swal.close();

            }, dataToken.access_token, uf, codigoCidade);
        });
    });

    $("#btnBuscar").click(function () {

        if ($("#estados").val() == "selecione") {

            swal("Ops!", "Selecione um estado", "error");
            return;
        }

        if ($('#cidades').val() == "") {

            swal("Ops!", "Digite uma cidade", "error");
            $("#cidades").css("color", "#FF4141");
            $("#cidades").css("border-bottom", "2px solid #FF4141");
            $(".label-cidade").css("color", "#FF4141");
            return;
        }

        if ($('#bairros').val() == "" && $('#cidades').val() == "SAO PAULO") {

            swal("Ops!", "Digite uma cidade", "info");
            return;
        }

        var codigoEspecialidade = $('#especs').val();
        var privian = "FALSE";
        var codigoMarca = "1";
        var codBeneficiario = "375796040";
        var estado = $("#estados").val();

        var bairro = dadosBairros.filter(function (x) { if (x.nome == $("#bairros").val().trim()) { return x.nome; } });
        var cidade = dadosCidades.filter(function (x) { if (x.nome == $("#cidades").val().trim()) { return x.nome; } });

        var codBairro = bairro.length > 0 ? bairro[0].codigo : "selecione";   //$("#bairros").val();

        var codigoMicroregiao = cidade[0].codigoMicroregiao;//$("#cidades").find(':selected').data('id');
        var codCidade = cidade[0].codigoCidade; //$("#cidades").val();

        callTokenProd(function (dataToken) {

            if (codBairro == "selecione") {
                callRedeCredenciada(function (dataRedeCredenciada) {
                    console.log(dataRedeCredenciada);

                    initMap(dataRedeCredenciada);



                }, dataToken.access_token, codBeneficiario, estado, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, "0", codCidade);

            }
            else if (codBairro != undefined) {
                callRedeCredenciada(function (dataRedeCredenciada) {

                    console.log(dataRedeCredenciada);
                    initMap(dataRedeCredenciada);

                }, dataToken.access_token, codBeneficiario, estado, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, codBairro, codCidade);
            }
            else if (codBairro == 0) {
                callRedeCredenciada(function (dataRedeCredenciada) {

                    console.log(dataRedeCredenciada);
                    initMap(dataRedeCredenciada);

                }, dataToken.access_token, codBeneficiario, estado, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, "0", codCidade);
            }


        });
    });

    $("#closeModalRedeCredenciada").click(function () {

        $('#myModal').modal('toggle');

    });

});

function initMap(redeCredenciada) {
    var latlng = { lat: -23.5432147, lng: -46.7356894 };
    mapa(redeCredenciada);
}

var t;

var autoCompleteCidades = [];
var dadosCidades = [];
var autoCompleteBairros = [];
var dadosBairros = [];

function callEspecialidades(callback, token) {

    swal({
        title: "Aguarde",
        text: 'Estamos buscando as especialidades',
        content: "input",
        showCancelButton: false,
        showConfirmButton: false,
        imageUrl: "img/load.gif",
        icon: "info",
        button: {
            text: "...",
            closeModal: false,
        },
    })

    $.ajax({
        async: true,
        url: URLBase + "/redecredenciada/1.0/especialidades",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        success: function (resp) {

            callback(resp)
        },
    });
}

function callEstados(callback, token) {

    swal({
        title: "Aguarde",
        text: 'Estamos buscando os estados',
        content: "input",
        showCancelButton: false,
        showConfirmButton: false,
        imageUrl: "img/load.gif",
        icon: "info",
        button: {
            text: "...",
            closeModal: false,
        },
    })

    $.ajax({
        async: true,
        url: URLBase + "/cep/1.1/estados",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        success: function (resp) {

            callback(resp)
        },
    });
}

function callCidade(callback, token, uf) {

    swal({
        title: "Aguarde",
        text: 'Estamos buscando as cidades',
        content: "input",
        showCancelButton: false,
        showConfirmButton: false,
        imageUrl: "img/load.gif",
        icon: "info",
        button: {
            text: "...",
            closeModal: false,
        },
    })

    $.ajax({
        async: true,
        url: URLBase + "/cep/1.1/cidades/uf?uf=" + uf,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callback(resp)
        },
    });
}

function callBairro(callback, token, uf, codigoCidade) {

    swal({
        title: "Aguarde",
        text: 'Estamos buscando os bairros',
        content: "input",
        showCancelButton: false,
        showConfirmButton: false,
        imageUrl: "img/load.gif",
        icon: "info",
        button: {
            text: "...",
            closeModal: false,
        },
    })

    var codigoBeneficiario = "375796040";

    $.ajax({
        async: true,
        url: URLBase + "/cep/1.1/bairros?uf=" + uf + "&codigoCidade=" + codigoCidade + "&codigoBeneficiario=" + codigoBeneficiario,
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        success: function (resp) {
            callback(resp)
        },
        error: function (xhr) {
            swal.close();
            callback(xhr)

        }
    });
}

function especialidades() {
    callTokenProd(function (dataToken) {

        callEspecialidades(function (dataEspecialidades) {
            //console.log(dataEspecialidades);

            var sel = document.getElementById('especs');

            var opt = document.createElement('option');
            //console.log(dataEspecialidades[i].descricao);
            opt.setAttribute('value', 0);
            //console.log(dataEspecialidades[i].codigo);
            var selecione = "Todas";
            opt.appendChild(document.createTextNode(selecione));
            sel.appendChild(opt);

            for (var i = 0; i < dataEspecialidades.length; i++) {

                var opt = document.createElement('option');
                //console.log(dataEspecialidades[i].descricao);
                opt.setAttribute('value', dataEspecialidades[i].codigo);
                //console.log(dataEspecialidades[i].codigo);
                opt.appendChild(document.createTextNode(dataEspecialidades[i].descricao));
                sel.appendChild(opt);
            }

            var especialidade = 0;

            document.getElementById('especs').value = especialidade;

            swal.close();

        }, dataToken.access_token);
    });
}

function callRedeCredenciada(callback, token, CodBeneficiario, uf, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, codigoBairro, codigoCidade) {

    swal({
        title: "Aguarde",
        text: 'Estamos procurando dentistas',
        content: "input",
        showCancelButton: false,
        showConfirmButton: false,
        imageUrl: "img/load.gif",
        icon: "info",
        button: {
            text: "...",
            closeModal: false,
        },
    });

    if (codigoCidade == undefined) {

        var urlTratada = "dentistas?codigoBeneficiario=" + CodBeneficiario + "&siglaUf=" + uf + "&codigoEspecialidade=" + codigoEspecialidade + "&codigoRegiao=" + codigoMicroregiao + "&privian=" + privian + "&codigoMarca=" + codigoMarca + "&codigoBairro=" + codigoBairro;
    }
    else if (codigoBairro == undefined) {

        var urlTratada = "dentistas?codigoBeneficiario=" + CodBeneficiario + "&siglaUf=" + uf + "&codigoEspecialidade=" + codigoEspecialidade + "&codigoRegiao=" + codigoMicroregiao + "&privian=" + privian + "&codigoMarca=" + codigoMarca + "&codigoCidade=" + codigoCidade;

    }
    else{
        var urlTratada = "dentistas?codigoBeneficiario=" + CodBeneficiario + "&siglaUf=" + uf + "&codigoEspecialidade=" + codigoEspecialidade + "&codigoRegiao=" + codigoMicroregiao + "&privian=" + privian + "&codigoMarca=" + codigoMarca + "&codigoBairro=" + codigoBairro + "&codigoCidade=" + codigoCidade;
    }

    $.ajax({
        async: true,
        url: URLBase + "/dcms/redecredenciada/1.0/" + urlTratada,
        //url: "https://api.odontoprev.com.br:8243/cep/1.1/bairros?uf=" + uf + "&codigoCidade=" + codigoCidade + "&codigoBeneficiario=" + codigoBeneficiario,
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },

        success: function (resp) {
            swal.close();
            callback(resp)

        },
        error: function (xhr) {
            swal(":(", "Nenhum dentista encontrado", "info");
        }
    });
}

function estados() {
    callTokenProd(function (dataToken) {

        callEstados(function (dataEstados) {
            //console.log(dataEspecialidades);

            var sel = document.getElementById('estados');

            var opt = document.createElement('option');
            //console.log(dataEspecialidades[i].descricao);
            opt.setAttribute('value', "selecione");
            //console.log(dataEspecialidades[i].codigo);
            var selecione = "Selecione...";
            opt.appendChild(document.createTextNode(selecione));
            sel.appendChild(opt);

            for (var i = 0; i < dataEstados.length; i++) {

                if (dataEstados[i].codigoUf == 0) continue

                var opt = document.createElement('option');
                //console.log(dataEspecialidades[i].descricao);
                opt.setAttribute('value', dataEstados[i].uf);
                //console.log(dataEspecialidades[i].codigo);
                opt.appendChild(document.createTextNode(dataEstados[i].nome));
                sel.appendChild(opt);
            }

            document.getElementById('estados').value = "selecione";


            swal.close();
        }, dataToken.access_token);
    });
}

function removerAcentos(newStringComAcento) {
    var string = newStringComAcento;
    var mapaAcentosHex = {
        A: /[\xE0-\xE6]/gi,
        E: /[\xE8-\xEB]/gi,
        I: /[\xEC-\xEF]/gi,
        O: /[\xF2-\xF6]/gi,
        U: /[\xF9-\xFC]/gi,
        C: /\xE7/gi,
        N: /\xF1/gi
    };

    for (var letra in mapaAcentosHex) {
        var expressaoRegular = mapaAcentosHex[letra];
        string = string.replace(expressaoRegular, letra);
    }

    return string;
}



