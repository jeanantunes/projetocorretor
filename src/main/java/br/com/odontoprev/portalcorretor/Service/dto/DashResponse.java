package br.com.odontoprev.portalcorretor.Service.dto;

public class DashResponse {

    int codigoEmpresa;

    //esse codigo sera filtro para soma dos finalizados / para a barra na tela utilizar só os finalizados
    int codigoDcms;

    int countTotalVidas;

    Double valueTotalVidas;

    int codigoCorretor;

    String nomeCorretor;

    String cpfCorretor;

    int codigoCorretora;

    String nomeCorretora;

    String cnpjCorretora;

    String tipoVenda;

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public int getCodigoDcms() {
        return codigoDcms;
    }

    public void setCodigoDcms(int codigoDcms) {
        this.codigoDcms = codigoDcms;
    }

    public int getCountTotalVidas() {
        return countTotalVidas;
    }

    public void setCountTotalVidas(int countTotalVidas) {
        this.countTotalVidas = countTotalVidas;
    }

    public Double getValueTotalVidas() {
        return valueTotalVidas;
    }

    public void setValueTotalVidas(Double valueTotalVidas) {
        this.valueTotalVidas = valueTotalVidas;
    }

    public int getCodigoCorretor() {
        return codigoCorretor;
    }

    public void setCodigoCorretor(int codigoCorretor) {
        this.codigoCorretor = codigoCorretor;
    }

    public String getNomeCorretor() {
        return nomeCorretor;
    }

    public void setNomeCorretor(String nomeCorretor) {
        this.nomeCorretor = nomeCorretor;
    }

    public String getCpfCorretor() {
        return cpfCorretor;
    }

    public void setCpfCorretor(String cpfCorretor) {
        this.cpfCorretor = cpfCorretor;
    }

    public int getCodigoCorretora() {
        return codigoCorretora;
    }

    public void setCodigoCorretora(int codigoCorretora) {
        this.codigoCorretora = codigoCorretora;
    }

    public String getNomeCorretora() {
        return nomeCorretora;
    }

    public void setNomeCorretora(String nomeCorretora) {
        this.nomeCorretora = nomeCorretora;
    }

    public String getCnpjCorretora() {
        return cnpjCorretora;
    }

    public void setCnpjCorretora(String cnpjCorretora) {
        this.cnpjCorretora = cnpjCorretora;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }
}
