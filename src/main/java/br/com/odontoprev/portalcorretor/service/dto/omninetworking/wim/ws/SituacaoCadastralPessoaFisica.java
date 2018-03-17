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
        name = "SituacaoCadastralPessoaFisica",
        propOrder = {"nome", "situacao", "codigoControle", "dataConsulta", "fontePesquisada"}
)
public class SituacaoCadastralPessoaFisica {
    @XmlElement(
            required = true
    )
    protected String nome;
    @XmlElement(
            required = true
    )
    protected String situacao;
    @XmlElement(
            required = true
    )
    protected String codigoControle;
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

    public SituacaoCadastralPessoaFisica() {
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String var1) {
        this.nome = var1;
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String var1) {
        this.situacao = var1;
    }

    public String getCodigoControle() {
        return this.codigoControle;
    }

    public void setCodigoControle(String var1) {
        this.codigoControle = var1;
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
