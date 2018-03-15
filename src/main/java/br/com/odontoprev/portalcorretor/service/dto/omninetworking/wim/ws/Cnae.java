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
        name = "Cnae",
        propOrder = {"codigo", "descricao"}
)
public class Cnae {
    @XmlElement(
            required = true
    )
    protected String codigo;
    @XmlElement(
            required = true
    )
    protected String descricao;

    public Cnae() {
    }

    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String var1) {
        this.codigo = var1;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String var1) {
        this.descricao = var1;
    }
}
