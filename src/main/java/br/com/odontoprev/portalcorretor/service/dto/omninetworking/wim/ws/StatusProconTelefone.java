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
        name = "StatusProconTelefone",
        propOrder = {"ddd", "telefone", "cnpjIndireto"}
)
public class StatusProconTelefone {
    protected String ddd;
    protected String telefone;
    @XmlElement(
            required = true
    )
    protected String cnpjIndireto;

    public StatusProconTelefone() {
    }

    public String getDdd() {
        return this.ddd;
    }

    public void setDdd(String var1) {
        this.ddd = var1;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String var1) {
        this.telefone = var1;
    }

    public String getCnpjIndireto() {
        return this.cnpjIndireto;
    }

    public void setCnpjIndireto(String var1) {
        this.cnpjIndireto = var1;
    }
}
