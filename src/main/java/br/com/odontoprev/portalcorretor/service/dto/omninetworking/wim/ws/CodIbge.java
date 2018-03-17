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
        name = "CodIbge",
        propOrder = {"codIbge", "codIbgeUf"}
)
public class CodIbge {
    @XmlElement(
            required = true
    )
    protected String codIbge;
    @XmlElement(
            required = true
    )
    protected String codIbgeUf;

    public CodIbge() {
    }

    public String getCodIbge() {
        return this.codIbge;
    }

    public void setCodIbge(String var1) {
        this.codIbge = var1;
    }

    public String getCodIbgeUf() {
        return this.codIbgeUf;
    }

    public void setCodIbgeUf(String var1) {
        this.codIbgeUf = var1;
    }
}
