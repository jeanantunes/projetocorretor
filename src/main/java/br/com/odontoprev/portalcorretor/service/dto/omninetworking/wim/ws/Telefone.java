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
        name = "Telefone",
        propOrder = {"ddd", "numero", "tipo"}
)
public class Telefone {
    @XmlElement(
            required = true
    )
    protected String ddd;
    @XmlElement(
            required = true
    )
    protected String numero;
    protected String tipo;

    public Telefone() {
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

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String var1) {
        this.tipo = var1;
    }
}
