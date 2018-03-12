package br.com.odontoprev.portalcorretor.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cidade {
    @JsonProperty("codigoCidade")
    private String codigoCidade;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("codigoMicroregiao")
    private String codigoMicroregiao;
    @JsonProperty("latitude")
    private String latitude;
    @JsonProperty("longitude")
    private String longitude;


    public String getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(String codigoCidade) {
        this.codigoCidade = codigoCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoMicroregiao() {
        return codigoMicroregiao;
    }

    public void setCodigoMicroregiao(String codigoMicroregiao) {
        this.codigoMicroregiao = codigoMicroregiao;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
