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
        name = "StatusProconTelefoneResponse",
        propOrder = {"ddd", "numero", "flagCadastroProcon", "dataLimite"}
)
public class StatusProconTelefoneResponse {
    @XmlElement(
            required = true
    )
    protected String ddd;
    @XmlElement(
            required = true
    )
    protected String numero;
    @XmlElement(
            required = true
    )
    protected String flagCadastroProcon;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "dateTime"
    )
    protected XMLGregorianCalendar dataLimite;

    public StatusProconTelefoneResponse() {
    }

    public String getDdd() {
        return this.ddd;
    }

    public void setDdd(String var1) {
        this.ddd = var1;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String var1) {
        this.numero = var1;
    }

    public String getFlagCadastroProcon() {
        return this.flagCadastroProcon;
    }

    public void setFlagCadastroProcon(String var1) {
        this.flagCadastroProcon = var1;
    }

    public XMLGregorianCalendar getDataLimite() {
        return this.dataLimite;
    }

    public void setDataLimite(XMLGregorianCalendar var1) {
        this.dataLimite = var1;
    }
}
