package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portalcorretor.service.dto.Plano;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carrinho {

    private VendaPme vendaPme;

    private List<Plano> planos = new ArrayList<>();

    private Double total;
    private List<Beneficiario> beneficiarios = new ArrayList<>();
    private String acao;

    public List<Beneficiario> getBeneficiarios() {
        return beneficiarios;
    }

    public void setBeneficiarios(List<Beneficiario> beneficiarios) {
        this.beneficiarios = beneficiarios;
    }

    public String getInteiro() {
        return Double.toString(planos.stream().mapToDouble(Plano::getValor).sum()).split("\\.")[0];
    }

    public String getCentavos() {
        return Double.toString(planos.stream().mapToDouble(Plano::getValor).sum()).split("\\.")[1];
    }


    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public VendaPme getVendaPme() {
        return vendaPme;
    }

    public void setVendaPme(VendaPme vendaPme) {
        this.vendaPme = vendaPme;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }
}
