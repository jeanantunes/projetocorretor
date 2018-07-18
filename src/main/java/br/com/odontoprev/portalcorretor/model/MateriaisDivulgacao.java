package br.com.odontoprev.portalcorretor.model;

import java.util.Arrays;

public class MateriaisDivulgacao {

    Integer codigoMaterialDivulgacao;
    Integer codigoCategoria;
    Integer codigoSubCategoria;
    String nome;
    String descricao;
    String tipoConteudo;
    byte[] thumbnail;
    Integer tamanhoThumbnail;
    String arquivo;
    Integer tamanhoArquivo;
    String ativo;
    String caminhoCarga;

    public Integer getCodigoMaterialDivulgacao() {
        return codigoMaterialDivulgacao;
    }

    public void setCodigoMaterialDivulgacao(Integer codigoMaterialDivulgacao) {
        this.codigoMaterialDivulgacao = codigoMaterialDivulgacao;
    }

    public Integer getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Integer codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

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

    public String getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getTamanhoThumbnail() {
        return tamanhoThumbnail;
    }

    public void setTamanhoThumbnail(Integer tamanhoThumbnail) {
        this.tamanhoThumbnail = tamanhoThumbnail;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Integer getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(Integer tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getCaminhoCarga() {
        return caminhoCarga;
    }

    public void setCaminhoCarga(String caminhoCarga) {
        this.caminhoCarga = caminhoCarga;
    }

    @Override
    public String toString() {
        return "materiaisDivulgacao{" +
                "codigoMaterialDivulgacao=" + codigoMaterialDivulgacao +
                ", codigoCategoria=" + codigoCategoria +
                ", codigoSubCategoria=" + codigoSubCategoria +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tipoConteudo='" + tipoConteudo + '\'' +
                ", thumbnail=" + Arrays.toString(thumbnail) +
                ", tamanhoThumbnail=" + tamanhoThumbnail +
                ", arquivo='" + arquivo + '\'' +
                ", tamanhoArquivo=" + tamanhoArquivo +
                ", ativo='" + ativo + '\'' +
                ", caminhoCarga='" + caminhoCarga + '\'' +
                '}';
    }
}
