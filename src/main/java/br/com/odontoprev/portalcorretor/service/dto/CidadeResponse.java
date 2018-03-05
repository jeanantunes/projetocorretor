package br.com.odontoprev.portalcorretor.service.dto;

public class CidadeResponse {

    private  long codigoCidade;
    private String nome;
    private long microRegiao;
    private double latitude;
    private double logitude;

    public long getCodigoCidade() {
        return codigoCidade;
    }

    public void setCodigoCidade(long codigoCidade) {
        this.codigoCidade = codigoCidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getMicroRegiao() {
        return microRegiao;
    }

    public void setMicroRegiao(long microRegiao) {
        this.microRegiao = microRegiao;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLogitude() {
        return logitude;
    }

    public void setLogitude(double logitude) {
        this.logitude = logitude;
    }
}
