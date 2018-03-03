package br.com.odontoprev.portalcorretor.service.dto;

public class DashResponse {

    public int getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(int codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public int getEmpresaDCMS() {
        return empresaDCMS;
    }

    public void setEmpresaDCMS(int empresaDCMS) {
        this.empresaDCMS = empresaDCMS;
    }

    public int getTotalVidas() {
        return totalVidas;
    }

    public void setTotalVidas(int totalVidas) {
        this.totalVidas = totalVidas;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getCodigoForcaVenda() {
        return codigoForcaVenda;
    }

    public void setCodigoForcaVenda(int codigoForcaVenda) {
        this.codigoForcaVenda = codigoForcaVenda;
    }

    public String getNomeForca() {
        return nomeForca;
    }

    public void setNomeForca(String nomeForca) {
        this.nomeForca = nomeForca;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTipoVenda() {
        return tipoVenda;
    }

    public void setTipoVenda(String tipoVenda) {
        this.tipoVenda = tipoVenda;
    }

    int codigoEmpresa;

    //esse codigo sera filtro para soma dos finalizados / para a barra na tela utilizar só os finalizados
    int empresaDCMS;

    int totalVidas;

    Double valorTotal;

    int codigoForcaVenda;

    String nomeForca;

    String cpf;

    int codigoCorretora;

    String nomeCorretora;

    String cnpj;

    String tipoVenda;


}
