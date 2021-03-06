package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;

import java.util.List;

public class ListaForca {
    private List<ForcaVenda> aguardandoAprovacao;
    private List<ForcaVenda> lista;
    private Integer totalForca;
    private String message;
    private Boolean erro;


    public List<ForcaVenda> getAguardandoAprovacao() {
        return aguardandoAprovacao;
    }

    public void setAguardandoAprovacao(List<ForcaVenda> aguardandoAprovacao) {
        this.aguardandoAprovacao = aguardandoAprovacao;
    }

    public List<ForcaVenda> getLista() {
        return lista;
    }

    public void setLista(List<ForcaVenda> lista) {
        this.lista = lista;
    }

    public Integer getTotalForca() {
        return totalForca;
    }

    public void setTotalForca(Integer totalForca) {
        this.totalForca = totalForca;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setErro(Boolean erro) {
        this.erro = erro;
    }

    public Boolean isErro() {
        return erro;
    }
}
