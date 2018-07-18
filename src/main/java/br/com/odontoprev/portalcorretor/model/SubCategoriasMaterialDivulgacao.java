package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class SubCategoriasMaterialDivulgacao {

    Integer codigoSubCategoria;
    String nome;
    String descricao;
    String ativo;
    String app;
    String web;
    List<MateriaisDivulgacao> materiaisDivulgacao;

    public Integer getCodigoSubCategoria() {
        return codigoSubCategoria;
    }

    public void setCodigoSubCategoria(Integer codigoSubCategoria) {
        this.codigoSubCategoria = codigoSubCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public List<MateriaisDivulgacao> getMateriaisDivulgacao() {
        return materiaisDivulgacao;
    }

    public void setMateriaisDivulgacao(List<MateriaisDivulgacao> materiaisDivulgacao) {
        this.materiaisDivulgacao = materiaisDivulgacao;
    }

    @Override
    public String toString() {
        return "subCategoriasMaterialDivulgacao{" +
                "codigoSubCategoria=" + codigoSubCategoria +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", ativo='" + ativo + '\'' +
                ", app='" + app + '\'' +
                ", web='" + web + '\'' +
                ", materiaisDivulgacao=" + materiaisDivulgacao +
                '}';
    }
}
