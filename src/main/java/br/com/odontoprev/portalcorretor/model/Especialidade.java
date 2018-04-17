package br.com.odontoprev.portalcorretor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Especialidade {
    @JsonProperty("codigo")
   private String codigo;
    @JsonProperty("descricao")
   private String descricao;


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
