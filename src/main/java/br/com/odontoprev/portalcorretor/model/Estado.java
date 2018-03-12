package br.com.odontoprev.portalcorretor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Estado {
    @JsonProperty("uf")
   private String uf;
    @JsonProperty("codigoUf")
   private String codigoUf;
    @JsonProperty("nome")
   private String nome;


    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCodigoUf() {
        return codigoUf;
    }

    public void setCodigoUf(String codigoUf) {
        this.codigoUf = codigoUf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "uf='" + uf + '\'' +
                ", codigoUf='" + codigoUf + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
