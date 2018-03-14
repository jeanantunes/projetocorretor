package br.com.odontoprev.portalcorretor.service.dto;

public class Plano {
    private int cdPlano;
    private String nome;
    private String desc ;
    private String valor;
    private String centavo;
    private String tipo;
    private String css;

    public int getCdPlano() {
        return cdPlano;
    }

    public void setCdPlano(int cdPlano) {
        this.cdPlano = cdPlano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCentavo() {
        return centavo;
    }

    public void setCentavo(String centavo) {
        this.centavo = centavo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }
}
