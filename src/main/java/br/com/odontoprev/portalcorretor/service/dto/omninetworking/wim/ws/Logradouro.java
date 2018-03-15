//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "Logradouro",
        propOrder = {"completo", "tipo", "titulo", "nome", "numero", "complemento", "cepNota"}
)
public class Logradouro {
    @XmlElement(
            name = "Completo",
            required = true
    )
    protected String completo;
    @XmlElement(
            name = "Tipo",
            required = true
    )
    protected String tipo;
    @XmlElement(
            name = "Titulo",
            required = true
    )
    protected String titulo;
    @XmlElement(
            name = "Nome",
            required = true
    )
    protected String nome;
    @XmlElement(
            name = "Numero",
            required = true
    )
    protected String numero;
    @XmlElement(
            name = "Complemento",
            required = true
    )
    protected String complemento;
    @XmlElement(
            name = "CepNota"
    )
    protected int cepNota;

    public Logradouro() {
    }

    public String getCompleto() {
        return this.completo;
    }

    public void setCompleto(String var1) {
        this.completo = var1;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String var1) {
        this.tipo = var1;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String var1) {
        this.titulo = var1;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String var1) {
        this.nome = var1;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String var1) {
        this.numero = var1;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String var1) {
        this.complemento = var1;
    }

    public int getCepNota() {
        return this.cepNota;
    }

    public void setCepNota(int var1) {
        this.cepNota = var1;
    }
}
