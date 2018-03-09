package br.com.odontoprev.portalcorretor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bairro {
    @JsonProperty("codigo")
   private String codigo;
    @JsonProperty("nome")
   private String nome;
    @JsonProperty("cidade")
   private String cidade;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
