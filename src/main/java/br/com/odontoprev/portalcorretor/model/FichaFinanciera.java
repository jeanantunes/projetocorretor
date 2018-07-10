package br.com.odontoprev.portalcorretor.model;

public class FichaFinanciera {

    String competencia;
    String dataRenegociacao;
    Integer diasDeAtraso;
    Long fatura;
    Boolean isencaoMultaJuros;
    Integer multaRescisoria;
    String notificacao;
    String parcela;
    Integer percentualJuros;
    Integer percentualMulta;
    Boolean permiteIsencaoMultaJuros;
    Integer quantidadeDiasAtraso;
    String statusPagamento;
    Integer valorAtualJuros;
    Integer valorAtualMulta;
    Double valorAtualParcelaCalculado;
    Integer valorEstorno;
    Integer valorJurosPago;
    Integer valorMultaPago;
    Double valorNaoAplicado;
    Integer valorPago;
    Double valorParcela;
    String vencimentoOriginal;
    String dataHoje;

    public String getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia = competencia;
    }

    public String getDataRenegociacao() {
        return dataRenegociacao;
    }

    public void setDataRenegociacao(String dataRenegociacao) {
        this.dataRenegociacao = dataRenegociacao;
    }

    public Integer getDiasDeAtraso() {
        return diasDeAtraso;
    }

    public void setDiasDeAtraso(Integer diasDeAtraso) {
        this.diasDeAtraso = diasDeAtraso;
    }

    public Long getFatura() {
        return fatura;
    }

    public void setFatura(Long fatura) {
        this.fatura = fatura;
    }

    public Boolean getIsencaoMultaJuros() {
        return isencaoMultaJuros;
    }

    public void setIsencaoMultaJuros(Boolean isencaoMultaJuros) {
        this.isencaoMultaJuros = isencaoMultaJuros;
    }

    public Integer getMultaRescisoria() {
        return multaRescisoria;
    }

    public void setMultaRescisoria(Integer multaRescisoria) {
        this.multaRescisoria = multaRescisoria;
    }

    public String getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(String notificacao) {
        this.notificacao = notificacao;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public Integer getPercentualJuros() {
        return percentualJuros;
    }

    public void setPercentualJuros(Integer percentualJuros) {
        this.percentualJuros = percentualJuros;
    }

    public Integer getPercentualMulta() {
        return percentualMulta;
    }

    public void setPercentualMulta(Integer percentualMulta) {
        this.percentualMulta = percentualMulta;
    }

    public Boolean getPermiteIsencaoMultaJuros() {
        return permiteIsencaoMultaJuros;
    }

    public void setPermiteIsencaoMultaJuros(Boolean permiteIsencaoMultaJuros) {
        this.permiteIsencaoMultaJuros = permiteIsencaoMultaJuros;
    }

    public Integer getQuantidadeDiasAtraso() {
        return quantidadeDiasAtraso;
    }

    public void setQuantidadeDiasAtraso(Integer quantidadeDiasAtraso) {
        this.quantidadeDiasAtraso = quantidadeDiasAtraso;
    }

    public String getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(String statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public Integer getValorAtualJuros() {
        return valorAtualJuros;
    }

    public void setValorAtualJuros(Integer valorAtualJuros) {
        this.valorAtualJuros = valorAtualJuros;
    }

    public Integer getValorAtualMulta() {
        return valorAtualMulta;
    }

    public void setValorAtualMulta(Integer valorAtualMulta) {
        this.valorAtualMulta = valorAtualMulta;
    }

    public Double getValorAtualParcelaCalculado() {
        return valorAtualParcelaCalculado;
    }

    public void setValorAtualParcelaCalculado(Double valorAtualParcelaCalculado) {
        this.valorAtualParcelaCalculado = valorAtualParcelaCalculado;
    }

    public Integer getValorEstorno() {
        return valorEstorno;
    }

    public void setValorEstorno(Integer valorEstorno) {
        this.valorEstorno = valorEstorno;
    }

    public Integer getValorJurosPago() {
        return valorJurosPago;
    }

    public void setValorJurosPago(Integer valorJurosPago) {
        this.valorJurosPago = valorJurosPago;
    }

    public Integer getValorMultaPago() {
        return valorMultaPago;
    }

    public void setValorMultaPago(Integer valorMultaPago) {
        this.valorMultaPago = valorMultaPago;
    }

    public Double getValorNaoAplicado() {
        return valorNaoAplicado;
    }

    public void setValorNaoAplicado(Double valorNaoAplicado) {
        this.valorNaoAplicado = valorNaoAplicado;
    }

    public Integer getValorPago() {
        return valorPago;
    }

    public void setValorPago(Integer valorPago) {
        this.valorPago = valorPago;
    }

    public Double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(Double valorParcela) {
        this.valorParcela = valorParcela;
    }

    public String getVencimentoOriginal() {
        return vencimentoOriginal;
    }

    public void setVencimentoOriginal(String vencimentoOriginal) {
        this.vencimentoOriginal = vencimentoOriginal;
    }

    public String getDataHoje() {
        return dataHoje;
    }

    public void setDataHoje(String dataHoje) {
        this.dataHoje = dataHoje;
    }

    @Override
    public String toString() {
        return "fichaFinanciera{" +
                "competencia='" + competencia + '\'' +
                ", dataRenegociacao='" + dataRenegociacao + '\'' +
                ", diasDeAtraso=" + diasDeAtraso +
                ", fatura=" + fatura +
                ", isencaoMultaJuros=" + isencaoMultaJuros +
                ", multaRescisoria=" + multaRescisoria +
                ", notificacao='" + notificacao + '\'' +
                ", parcela='" + parcela + '\'' +
                ", percentualJuros=" + percentualJuros +
                ", percentualMulta=" + percentualMulta +
                ", permiteIsencaoMultaJuros=" + permiteIsencaoMultaJuros +
                ", quantidadeDiasAtraso=" + quantidadeDiasAtraso +
                ", statusPagamento='" + statusPagamento + '\'' +
                ", valorAtualJuros=" + valorAtualJuros +
                ", valorAtualMulta=" + valorAtualMulta +
                ", valorAtualParcelaCalculado=" + valorAtualParcelaCalculado +
                ", valorEstorno=" + valorEstorno +
                ", valorJurosPago=" + valorJurosPago +
                ", valorMultaPago=" + valorMultaPago +
                ", valorNaoAplicado=" + valorNaoAplicado +
                ", valorPago=" + valorPago +
                ", valorParcela=" + valorParcela +
                ", vencimentoOriginal='" + vencimentoOriginal + '\'' +
                '}';
    }
}
