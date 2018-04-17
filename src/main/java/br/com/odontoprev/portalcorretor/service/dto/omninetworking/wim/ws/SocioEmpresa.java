//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "SocioEmpresa",
        propOrder = {"cnpj", "razaoSocial", "participacaoSocio"}
)
public class SocioEmpresa {
    @XmlElement(
            required = true
    )
    protected String cnpj;
    @XmlElement(
            required = true
    )
    protected String razaoSocial;
    @XmlElement(
            required = true
    )
    protected BigDecimal participacaoSocio;

    public SocioEmpresa() {
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String var1) {
        this.cnpj = var1;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String var1) {
        this.razaoSocial = var1;
    }

    public BigDecimal getParticipacaoSocio() {
        return this.participacaoSocio;
    }

    public void setParticipacaoSocio(BigDecimal var1) {
        this.participacaoSocio = var1;
    }
}
