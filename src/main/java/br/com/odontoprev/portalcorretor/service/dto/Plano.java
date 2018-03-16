package br.com.odontoprev.portalcorretor.service.dto;

import java.util.Objects;

public class Plano {


    public static Plano Master_LALE = new Plano("Master-LALE", "Master LE", "ORTO+PRÓTESE", 119.48, "Modalidade Compulsoria", "font-pink");

    public static Plano Integral_DOC_LE = new Plano("Integral-DOC-LALE", "Integral DOC LE", "BEM-ESTAR+DOC", 37.82, "Modalidade Compulsoria", "font-gold");

    private final String cdPlano;
    private final String nome;
    private final String desc;
    private final Double valor;
    private final String tipo;
    private final String css;

    public Plano(String cdPlano, String nome, String desc, Double valor, String tipo, String css) {
        this.cdPlano = cdPlano;
        this.nome = nome;
        this.desc = desc;
        this.valor = valor;
        this.tipo = tipo;
        this.css = css;
    }

    public String getCentavos() {
        return valor.toString().split("\\.")[1];
    }

    public String getInteiro() {
        return valor.toString().split("\\.")[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plano plano = (Plano) o;
        return Objects.equals(cdPlano, plano.cdPlano) &&
                Objects.equals(nome, plano.nome) &&
                Objects.equals(desc, plano.desc) &&
                Objects.equals(valor, plano.valor) &&
                Objects.equals(tipo, plano.tipo) &&
                Objects.equals(css, plano.css);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cdPlano, nome, desc, valor, tipo, css);
    }

    public String getCdPlano() {
        return cdPlano;
    }

    public String getNome() {
        return nome;
    }

    public String getDesc() {
        return desc;
    }

    public Double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCss() {
        return css;
    }
}
