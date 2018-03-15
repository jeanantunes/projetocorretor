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
        name = "RepresentanteLegal",
        propOrder = {"documento", "nome"}
)
public class RepresentanteLegal {
    @XmlElement(
            required = true
    )
    protected String documento;
    @XmlElement(
            required = true
    )
    protected String nome;

    public RepresentanteLegal() {
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String var1) {
        this.documento = var1;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String var1) {
        this.nome = var1;
    }
}
