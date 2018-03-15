//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "Sintegra",
        propOrder = {"inscricaoEstadual", "situacaoCadastral", "dataSituacao", "dataConsulta", "uf", "logradouro", "numero", "cep", "municipio", "complemento", "bairro", "fontePesquisada"}
)
public class Sintegra {
    @XmlElement(
            required = true
    )
    protected String inscricaoEstadual;
    @XmlElement(
            required = true
    )
    protected String situacaoCadastral;
    @XmlElement(
            required = true
    )
    protected String dataSituacao;
    @XmlElement(
            required = true
    )
    protected String dataConsulta;
    @XmlElement(
            required = true
    )
    protected String uf;
    @XmlElement(
            required = true
    )
    protected String logradouro;
    @XmlElement(
            required = true
    )
    protected String numero;
    @XmlElement(
            required = true
    )
    protected String cep;
    @XmlElement(
            required = true
    )
    protected String municipio;
    @XmlElement(
            required = true
    )
    protected String complemento;
    @XmlElement(
            required = true
    )
    protected String bairro;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "string"
    )
    protected FontePesquisada fontePesquisada;

    public Sintegra() {
    }

    public String getInscricaoEstadual() {
        return this.inscricaoEstadual;
    }

    public void setInscricaoEstadual(String var1) {
        this.inscricaoEstadual = var1;
    }

    public String getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

    public void setSituacaoCadastral(String var1) {
        this.situacaoCadastral = var1;
    }

    public String getDataSituacao() {
        return this.dataSituacao;
    }

    public void setDataSituacao(String var1) {
        this.dataSituacao = var1;
    }

    public String getDataConsulta() {
        return this.dataConsulta;
    }

    public void setDataConsulta(String var1) {
        this.dataConsulta = var1;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String var1) {
        this.uf = var1;
    }

    public String getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(String var1) {
        this.logradouro = var1;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String var1) {
        this.numero = var1;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String var1) {
        this.cep = var1;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(String var1) {
        this.municipio = var1;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public void setComplemento(String var1) {
        this.complemento = var1;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String var1) {
        this.bairro = var1;
    }

    public FontePesquisada getFontePesquisada() {
        return this.fontePesquisada;
    }

    public void setFontePesquisada(FontePesquisada var1) {
        this.fontePesquisada = var1;
    }
}
