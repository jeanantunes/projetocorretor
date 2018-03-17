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
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "SituacaoCadastralPessoaJuridica",
        propOrder = {"situacao", "dataSituacao", "situacaoEspecial", "dataEspecial", "motivo", "dataConsulta", "fontePesquisada"}
)
public class SituacaoCadastralPessoaJuridica {
    @XmlElement(
            required = true
    )
    protected String situacao;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "dateTime"
    )
    protected XMLGregorianCalendar dataSituacao;
    @XmlElement(
            required = true
    )
    protected String situacaoEspecial;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "dateTime"
    )
    protected XMLGregorianCalendar dataEspecial;
    @XmlElement(
            required = true
    )
    protected String motivo;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "dateTime"
    )
    protected XMLGregorianCalendar dataConsulta;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "string"
    )
    protected FontePesquisada fontePesquisada;

    public SituacaoCadastralPessoaJuridica() {
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String var1) {
        this.situacao = var1;
    }

    public XMLGregorianCalendar getDataSituacao() {
        return this.dataSituacao;
    }

    public void setDataSituacao(XMLGregorianCalendar var1) {
        this.dataSituacao = var1;
    }

    public String getSituacaoEspecial() {
        return this.situacaoEspecial;
    }

    public void setSituacaoEspecial(String var1) {
        this.situacaoEspecial = var1;
    }

    public XMLGregorianCalendar getDataEspecial() {
        return this.dataEspecial;
    }

    public void setDataEspecial(XMLGregorianCalendar var1) {
        this.dataEspecial = var1;
    }

    public String getMotivo() {
        return this.motivo;
    }

    public void setMotivo(String var1) {
        this.motivo = var1;
    }

    public XMLGregorianCalendar getDataConsulta() {
        return this.dataConsulta;
    }

    public void setDataConsulta(XMLGregorianCalendar var1) {
        this.dataConsulta = var1;
    }

    public FontePesquisada getFontePesquisada() {
        return this.fontePesquisada;
    }

    public void setFontePesquisada(FontePesquisada var1) {
        this.fontePesquisada = var1;
    }
}
