package br.com.odontoprev.portalcorretor.model;

import java.util.Date;

public class Proposta {

    private String vendedor;
    private String tipoPlano;
    private Date dataVenda;
    private Double valor;

    public Proposta() {
    }

    public Proposta(String vendedor, String tipoPlano, Date dataVenda, Double valor) {
        this.vendedor = vendedor;
        this.tipoPlano = tipoPlano;
        this.dataVenda = dataVenda;
        this.valor = valor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getTipoPlano() {
        return tipoPlano;
    }

    public void setTipoPlano(String tipoPlano) {
        this.tipoPlano = tipoPlano;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}
