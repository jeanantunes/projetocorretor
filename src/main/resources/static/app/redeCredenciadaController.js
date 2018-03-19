﻿$(document).ready(function () {

    especialidades();
    estados();

});

function initMap(redeCredenciada) {
    var latlng = { lat: -23.5432147, lng: -46.7356894 };
    mapa(redeCredenciada);
}

function mapa(abc) {



    var latlng = { lat: -23.5432147, lng: -46.7356894 };
    //initMap(latlng);

    if (abc == null) {
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: latlng,
            disableDefaultUI: true
        });
    }
    else if (abc != null) {
        map = new google.maps.Map(document.getElementById('map'), {
            zoom: 13,
            center: { lat: parseFloat(abc.dentistas[0].endereco.cidade.latitude), lng: parseFloat(abc.dentistas[0].endereco.cidade.longitude) },
            disableDefaultUI: true
        });

        for (var i = 0; i < abc.dentistas.length; i++) {
            var latlng2 = new google.maps.LatLng((abc.dentistas[i].endereco.cidade.latitude), (abc.dentistas[i].endereco.cidade.longitude));

            var marker = new google.maps.Marker({
                position: latlng2,
                map: map,
                center: latlng2
            });


            var infowindow = new google.maps.InfoWindow();


            google.maps.event.addListener(marker, 'click', (function (marker, i) {
                return function () {
                    //console.log("Dentro da funcão Click:  " + contentString[i]);
                    //infowindow.setContent('<div><strong>' + abc.dentistas[i].nomeDentista + '</strong><br>');
                    //infowindow.open(map, marker);
                                        
                    document.getElementById('nomeDentista').innerHTML = abc.dentistas[i].nomeDentista;
                    document.getElementById('croDentista').innerHTML = abc.dentistas[i].numeroCRO;
                    document.getElementById('especialidadeDentista1').innerHTML = abc.dentistas[i].especialidade.descricaoEspecialidade;
                    document.getElementById('especialidadeDentista1').innerHTML = abc.dentistas[i].especialidade.descricaoEspecialidade;
                    document.getElementById('enderecoDentista').innerHTML = abc.dentistas[i].endereco.enderecoCompleto;
                    document.getElementById('cepDentista').innerHTML = abc.dentistas[i].endereco.cep;
                    document.getElementById('telefoneDentista').innerHTML = abc.dentistas[i].numeroFone;
                    document.getElementById('tipoPessoaDentista').innerHTML = abc.dentistas[i].tipoPrestador;

                    $("#btnModal").click();
                }
            })(marker, i));
        }
    }




}

var t;

function callEspecialidades(callback, token) {

    $.ajax({
        async: true,
        url: URLBase + "/redecredenciada/1.0/especialidades",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Cache-Control": "no-cache",
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callback(resp)
        },
    });
}

function callEstados(callback, token) {

    $.ajax({
        async: true,
        url: URLBase + "/cep/1.1/estados",
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Cache-Control": "no-cache",
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callback(resp)
        },
    });
}

function callCidade(callback, token, uf) {

    $.ajax({
        async: true,
        url: URLBase + "/cep/1.1/cidades/uf?uf=" + uf,
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token,
            "Cache-Control": "no-cache",
            "Content-Type": "application/json"
        },
        success: function (resp) {
            callback(resp)
        },
    });
}

function callBairro(callback, token, uf, codigoCidade) {

    var codigoBeneficiario = "375796040";

    $.ajax({
        async: true,
        url: URLBase + "/cep/1.1/bairros?uf=" + uf + "&codigoCidade=" + codigoCidade + "&codigoBeneficiario=" + codigoBeneficiario,
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token,
            "Cache-Control": "no-cache"
        },
        success: function (resp) {
            callback(resp)
        },
        error: function (xhr) {
            callback(xhr)
        }
    });
}

function especialidades() {
    callTokenProd(function (dataToken) {
        callEspecialidades(function (dataEspecialidades) {
            //console.log(dataEspecialidades);

            var sel = document.getElementById('especs');



            for (var i = 0; i < dataEspecialidades.length; i++) {

                var opt = document.createElement('option');
                //console.log(dataEspecialidades[i].descricao);
                opt.setAttribute('value', dataEspecialidades[i].codigo);
                //console.log(dataEspecialidades[i].codigo);
                opt.appendChild(document.createTextNode(dataEspecialidades[i].descricao));
                sel.appendChild(opt);
            }

            var especialidade = 0;

            var opt = document.createElement('option');
            //console.log(dataEspecialidades[i].descricao);
            opt.setAttribute('value', 0);
            //console.log(dataEspecialidades[i].codigo);
            var selecione = "Todas";
            opt.appendChild(document.createTextNode(selecione));
            sel.appendChild(opt);

            document.getElementById('especs').value = especialidade;

        }, dataToken.access_token);
    });
}

function callRedeCredenciada(callback, token, CodBeneficiario, uf, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, codigoBairro, codigoCidade) {

    swal({
        title: "Aguarde",
        text: 'Estamos procurando seus dados',
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
            "Authorization": "Bearer " + token,
            "Cache-Control": "no-cache"
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

        }, dataToken.access_token);
    });
}

$("#estados").change(function () {

    $('#cidades').empty();
    $('#bairros').empty();

    var uf = $("#estados").val();

    callTokenProd(function (dataToken) {

        callCidade(function (dataCidade) {

            var sel = document.getElementById('cidades');

            var opt = document.createElement('option');
            //console.log(dataEspecialidades[i].descricao);
            opt.setAttribute('value', "selecione");
            //console.log(dataEspecialidades[i].codigo);
            var selecione = "Selecione...";
            opt.appendChild(document.createTextNode(selecione));
            sel.appendChild(opt);

            for (var i = 0; i < dataCidade.length; i++) {

                //if (dataCidade[i].codigoUf == 0) continue

                var opt = document.createElement('option');
                //console.log(dataEspecialidades[i].descricao);
                opt.setAttribute('value', dataCidade[i].codigoCidade);
                opt.setAttribute('data-id', dataCidade[i].codigoMicroregiao);
                //console.log(dataEspecialidades[i].codigo);
                opt.appendChild(document.createTextNode(dataCidade[i].nome));
                sel.appendChild(opt);
            }

            document.getElementById('cidades').value = "selecione";

        }, dataToken.access_token, uf);
    });
});

$("#cidades").change(function () {

    $('#bairros').empty();

    var codigoCidade = $("#cidades").val();
    var uf = $("#estados").val();

    callTokenProd(function (dataToken) {

        callBairro(function (dataBairro) {

            var sel = document.getElementById('bairros');

            if (dataBairro.status == 422) {

                var opt = document.createElement('option');
                //console.log(dataEspecialidades[i].descricao);
                opt.setAttribute('value', 0);
                //console.log(dataEspecialidades[i].codigo);
                var selecione = "TODOS";
                opt.appendChild(document.createTextNode(selecione));
                sel.appendChild(opt);

                return;
            }

            var opt = document.createElement('option');
            //console.log(dataEspecialidades[i].descricao);
            opt.setAttribute('value', "selecione");
            //console.log(dataEspecialidades[i].codigo);
            var selecione = "Selecione...";
            opt.appendChild(document.createTextNode(selecione));
            sel.appendChild(opt);

            for (var i = 0; i < dataBairro.length; i++) {

                //if (dataCidade[i].codigoUf == 0) continue

                var opt = document.createElement('option');
                //console.log(dataEspecialidades[i].descricao);
                opt.setAttribute('value', dataBairro[i].codigo);
                //console.log(dataEspecialidades[i].codigo);
                opt.appendChild(document.createTextNode(dataBairro[i].nome));
                sel.appendChild(opt);
            }

            document.getElementById('bairros').value = "selecione";

        }, dataToken.access_token, uf, codigoCidade);
    });
});

$("#btnBuscar").click(function () {

    if ($("#estados").val() == "selecione") {

        swal("Ops!", "Selecione um estado", "info");
        return;
    }

    if ($('#cidades').val() == "selecione") {

        swal("Ops!", "Selecione uma cidade", "info");
        return;
    }

    var codigoEspecialidade = $('#especs').val();
    var privian = "FALSE";
    var codigoMarca = "1";
    var codBeneficiario = "375796040";
    var estado = $("#estados").val();
    var codBairro = $("#bairros").val();
    var codigoMicroregiao = $("#cidades").find(':selected').data('id');
    var codCidade = $("#cidades").val();

    callTokenProd(function (dataToken) {

        if (codBairro == "selecione") {
            callRedeCredenciada(function (dataRedeCredenciada) {
                console.log(dataRedeCredenciada);

                initMap(dataRedeCredenciada);

            }, dataToken.access_token, codBeneficiario, estado, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, "0", codCidade);

        }
        else if (codBairro != undefined) {
            callRedeCredenciada(function (dataRedeCredenciada) {

                initMap(dataRedeCredenciada);
            }, dataToken.access_token, codBeneficiario, estado, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, codBairro, codCidade);
        } 
        else if (codBairro == 0) {
            callRedeCredenciada(function (dataRedeCredenciada) {

                initMap(dataRedeCredenciada);
            }, dataToken.access_token, codBeneficiario, estado, codigoEspecialidade, codigoMicroregiao, privian, codigoMarca, "0", codCidade);
        } 


    });
});


