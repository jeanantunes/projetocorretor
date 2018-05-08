var preenchidos = false;

$(document).ready(function () {
    var proposta = get("proposta");

    //if (proposta.vencimentoFatura != "") {
    //    $(".selectListBlur").val(proposta.vencimentoFatura);
    //}
	
	$(".selectListBlur").change(function () {

		if ($(".selectListBlur").val() == "Selecione...") {

			$("#divProximoMes").addClass('hide');
			$("#corte").html("");
			$("#vencimento").html("");
			$("#vigencia").html("");

			return;
		}

    isEffectiveDate($(".selectListBlur").val());
    
	});

    
});



function atualizarFatura() {

    if ($(".selectListBlur").val() == null || $(".selectListBlur").val() == "Selecione...") {
        swal("Ops!", "Selecione a Data de vencimento da Fatura", "error");
        return;
    }

    var proposta = get("proposta");
    proposta.vencimentoFatura = $(".selectListBlur").val(); 

    atualizarEmpresas(proposta);
    put("proposta", JSON.stringify(proposta));

    window.location.href = "resumo_pme_proposta.html";

}



function isEffectiveDate(dayDueDate) {

var currentTime = moment();
currentTime.set({ hour: 0, minute: 0, second: 0, millisecond: 0 });
currentTime.toISOString();
currentTime.format();

var month = currentTime.format('MM');
var day = currentTime.format('DD');
var year = currentTime.format('YYYY');

$("#divProximoMes").addClass('hide');

switch (dayDueDate) {

case "05":

var vencimento;
var dataVencimento = moment("05-" + month.toString() + "-" + year, "DD-MM-YYYY");
var dataVencimento = dataVencimento.add(1, 'M');

var olderDate = moment(dataVencimento).add(-11, "days");

if (currentTime.isAfter(olderDate)) vencimento = dataVencimento.add(1, 'M');
else vencimento = dataVencimento;

var dataDeCorteDeMovimentacao = moment(dataVencimento).add(-11, "days");

$("#corte").html('Data de corte de movimentação:<br>' + dataDeCorteDeMovimentacao.format("DD/MM/YYYY"));
$("#vencimento").html('Data de vencimento:<br>' + vencimento.format("DD/MM/YYYY"));
$("#vigencia").html('Data de vigência:<br>' + vencimento.format("DD/MM/YYYY"));

break;

case "15": 

var vencimento;
var dataVencimento = moment("15-" + month.toString() + "-" + year, "DD-MM-YYYY");
var olderDate = moment(dataVencimento).add(-11, "days");

if (currentTime.isAfter(olderDate)) vencimento = dataVencimento.add(1, 'M');
else vencimento = dataVencimento;

var dataDeCorteDeMovimentacao = moment(dataVencimento).add(-11, "days");

$("#corte").html('Data de corte de movimentação:<br>' + dataDeCorteDeMovimentacao.format("DD/MM/YYYY"));
$("#vencimento").html('Data de vencimento:<br>' + vencimento.format("DD/MM/YYYY"));
$("#vigencia").html('Data de vigência:<br>' + vencimento.format("DD/MM/YYYY"));

break;

case "25":

var vencimento;
var dataVencimento = moment("25-" + month.toString() + "-" + year, "DD-MM-YYYY");
var olderDate = moment(dataVencimento).add(-11, "days");

if (currentTime.isAfter(olderDate)) vencimento = dataVencimento.add(1, 'M');
else vencimento = dataVencimento;

var dataDeCorteDeMovimentacao = moment(dataVencimento).add(-11, "days");

$("#corte").html('Data de corte de movimentação:<br>' + dataDeCorteDeMovimentacao.format("DD/MM/YYYY"));
$("#vencimento").html('Data de vencimento:<br>' + vencimento.format("DD/MM/YYYY"));
$("#vigencia").html('Data de vigência:<br>' + vencimento.format("DD/MM/YYYY"));

break;

}

}