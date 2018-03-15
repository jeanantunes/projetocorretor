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
        name = "SimplesNacional",
        propOrder = {"situacao", "dataConsulta", "fontePesquisada"}
)
public class SimplesNacional {
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
    protected XMLGregorianCalendar dataConsulta;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "string"
    )
    protected FontePesquisada fontePesquisada;

    public SimplesNacional() {
    }

    public String getSituacao() {
        return this.situacao;
    }

    public void setSituacao(String var1) {
        this.situacao = var1;
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
