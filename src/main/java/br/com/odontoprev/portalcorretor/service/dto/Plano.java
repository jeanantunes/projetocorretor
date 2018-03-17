package br.com.odontoprev.portalcorretor.service.dto;

import java.util.Objects;

public class Plano {


    public static Plano Master_LALE = new Plano("Master-LALE", "Master LE", "ORTO+PRÓTESE", 119.48, "Modalidade Compulsoria", "font-pink");

    public static Plano Integral_DOC_LE = new Plano("Integral-DOC-LALE", "Integral DOC LE", "BEM-ESTAR+DOC", 37.82, "Modalidade Compulsoria", "font-gold");

    //Planos PF
    public static Plano Dental_Bem_Estar_Anual_CC = new Plano("DENTAL-BEM-ESTAR", "DENTAL BEM-ESTAR", "DENTAL BEM-ESTAR", 456.00, "", "font-purple");
    public static Plano Dental_Bem_Estar_Anual_SC = new Plano("DENTAL-BEM-ESTAR", "DENTAL BEM-ESTAR", "DENTAL BEM-ESTAR", 547.00, "", "font-purple");
    public static Plano Dental_Bem_Estar = new Plano("DENTAL-BEM-ESTAR", "DENTAL BEM-ESTAR", "DENTAL BEM-ESTAR", 45.60, "", "font-purple");

    public static Plano Dente_De_Leite = new Plano("DENTE-DE-LEITE", "DENTE DE LEITE DE 0 A 7 ANOS", "DENTE DE LEITE DE 0 A 7 ANOS", 14.98, "", "font-gold");
    public static Plano Dente_De_Leite_Anual = new Plano("DENTE-DE-LEITE", "DENTE DE LEITE DE 0 A 7 ANOS", "DENTE DE LEITE DE 0 A 7 ANOS", 149.80, "", "font-gold");

    public static Plano Dental_Estetica_Anual_CC = new Plano("DENTAL-ESTETICA", "DENTAL ESTÉTICA", "DENTAL ESTÉTICA", 1150.00, "", "font-pink");
    public static Plano Dental_Estetica_Anual_SC = new Plano("DENTAL-ESTETICA", "DENTAL ESTÉTICA", "DENTAL ESTÉTICA", 1380.00, "", "font-pink");
    public static Plano Dental_Estetica = new Plano("DENTAL-ESTETICA", "DENTAL ESTÉTICA", "DENTAL ESTÉTICA", 115.00, "", "font-pink");

    public static Plano Dental_Orto_Anual_CC = new Plano("DENTAL-ORTO", "DENTAL-ORTO", "DENTAL ORTO", 1470.00, "", "font-green");
    public static Plano Dental_Orto_Anual_SC = new Plano("DENTAL-ORTO", "DENTAL-ORTO", "DENTAL ORTO", 1764.00, "", "font-green");
    public static Plano Dental_Orto = new Plano("DENTAL-ORTO", "DENTAL-ORTO", "DENTAL ORTO", 147.00, "", "font-green");

    public static Plano Dental_Vip_Anual_CC = new Plano("DENTAL-VIP", "DENTAL-VIP", "DENTAL VIP", 2203.05, "", "font-blue");
    public static Plano Dental_Vip_Anual_SC = new Plano("DENTAL-VIP", "DENTAL-VIP", "DENTAL VIP", 2644.20, "", "font-blue");
    public static Plano Dental_Vip = new Plano("DENTAL-VIP", "DENTAL-VIP", "DENTAL VIP", 220.35, "", "font-blue");


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
