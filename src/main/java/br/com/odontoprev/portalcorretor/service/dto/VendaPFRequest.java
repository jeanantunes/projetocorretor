package br.com.odontoprev.portalcorretor.service.dto;

import org.joda.time.DateTime;
import java.util.List;

public class VendaPFRequest {

    private int cdPlano;
    private int cdEmpresa;
    private int cdForcaVenda;
    private DateTime dataVenda;
    private int cdStatusVenda;
    private int faturaVencimento;
    private String tipoConta;
    private String banco;
    private String agencia;
    private String conta;
    private String tipoPagamento;
    private List<Titulare> titulares;

    public int getCdPlano() {
        return cdPlano;
    }

    public void setCdPlano(int cdPlano) {
        this.cdPlano = cdPlano;
    }

    public int getCdEmpresa() {
        return cdEmpresa;
    }

    public void setCdEmpresa(int cdEmpresa) {
        this.cdEmpresa = cdEmpresa;
    }

    public int getCdForcaVenda() {
        return cdForcaVenda;
    }

    public void setCdForcaVenda(int cdForcaVenda) {
        this.cdForcaVenda = cdForcaVenda;
    }

    public DateTime getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(DateTime dataVenda) {
        this.dataVenda = dataVenda;
    }

    public int getCdStatusVenda() {
        return cdStatusVenda;
    }

    public void setCdStatusVenda(int cdStatusVenda) {
        this.cdStatusVenda = cdStatusVenda;
    }

    public int getFaturaVencimento() {
        return faturaVencimento;
    }

    public void setFaturaVencimento(int faturaVencimento) {
        this.faturaVencimento = faturaVencimento;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public List<Titulare> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Titulare> titulares) {
        this.titulares = titulares;
    }
}