package br.com.odontoprev.portalcorretor.model;

import java.util.Date;

public class Proposta {

    private String cliente;
    private String vendedor;
    private String tipoPlano;
    private Date dataVenda;
    private Double valor;
    private Integer vidas;
    private PropostaStatus status;

    public Proposta() {
    }

    public Proposta(String vendedor, String tipoPlano, Date dataVenda, Double valor, Integer vidas, PropostaStatus status) {
        this.vendedor = vendedor;
        this.tipoPlano = tipoPlano;
        this.dataVenda = dataVenda;
        this.valor = valor;
        this.vidas = vidas;
        this.status = status;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public PropostaStatus getStatus() {
        return status;
    }

    public void setStatus(PropostaStatus status) {
        this.status = status;
    }

    public Integer getVidas() {
        return vidas;
    }

    public void setVidas(Integer vidas) {
        this.vidas = vidas;
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
