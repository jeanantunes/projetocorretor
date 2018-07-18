package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class CategoriasMaterialDivulgacao {

    Integer codigoCategoria;
    String nome;
    String descricao;
    List<SubCategoriasMaterialDivulgacao> subCategoriasMaterialDivulgacao;

    public Integer getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
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

    public List<SubCategoriasMaterialDivulgacao> getSubCategoriasMaterialDivulgacao() {
        return subCategoriasMaterialDivulgacao;
    }

    public void setSubCategoriasMaterialDivulgacao(List<SubCategoriasMaterialDivulgacao> subCategoriasMaterialDivulgacao) {
        this.subCategoriasMaterialDivulgacao = subCategoriasMaterialDivulgacao;
    }

    @Override
    public String toString() {
        return "categoriasMaterialDivulgacao{" +
                "codigoCategoria=" + codigoCategoria +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", subCategoriasMaterialDivulgacao=" + subCategoriasMaterialDivulgacao +
                '}';
    }
}
