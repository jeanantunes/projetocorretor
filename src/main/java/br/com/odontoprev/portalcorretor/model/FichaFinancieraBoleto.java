package br.com.odontoprev.portalcorretor.model;

public class FichaFinancieraBoleto {

    String codigoDoAssociado;
    String dataVencimentoOriginal;
    String numeroParcela;
    String dataVencimento;
    String tipoBoleto;
    String codigoSistema;
    String realizarRenegociacao;

    public String getCodigoDoAssociado() {
        return codigoDoAssociado;
    }

    public void setCodigoDoAssociado(String codigoDoAssociado) {
        this.codigoDoAssociado = codigoDoAssociado;
    }

    public String getDataVencimentoOriginal() {
        return dataVencimentoOriginal;
    }

    public void setDataVencimentoOriginal(String dataVencimentoOriginal) {
        this.dataVencimentoOriginal = dataVencimentoOriginal;
    }

    public String getNumeroParcela() {
        return numeroParcela;
    }

    public void setNumeroParcela(String numeroParcela) {
        this.numeroParcela = numeroParcela;
    }

    public String getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(String dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getTipoBoleto() {
        return tipoBoleto;
    }

    public void setTipoBoleto(String tipoBoleto) {
        this.tipoBoleto = tipoBoleto;
    }

    public String getCodigoSistema() {
        return codigoSistema;
    }

    public void setCodigoSistema(String codigoSistema) {
        this.codigoSistema = codigoSistema;
    }

    public String getRealizarRenegociacao() {
        return realizarRenegociacao;
    }

    public void setRealizarRenegociacao(String realizarRenegociacao) {
        this.realizarRenegociacao = realizarRenegociacao;
    }

    @Override
    public String toString() {
        return "{" +
                "codigoDoAssociado='" + codigoDoAssociado + '\'' +
                ", dataVencimentoOriginal='" + dataVencimentoOriginal + '\'' +
                ", numeroParcela='" + numeroParcela + '\'' +
                ", dataVencimento='" + dataVencimento + '\'' +
                ", tipoBoleto='" + tipoBoleto + '\'' +
                ", codigoSistema='" + codigoSistema + '\'' +
                ", realizarRenegociacao='" + realizarRenegociacao + '\'' +
                '}';
    }
}
