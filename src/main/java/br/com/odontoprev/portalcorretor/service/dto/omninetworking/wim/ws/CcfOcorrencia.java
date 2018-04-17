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
        name = "CcfOcorrencia",
        propOrder = {"quantidade", "discriminacao", "periodo"}
)
public class CcfOcorrencia {
    @XmlElement(
            required = true
    )
    protected String quantidade;
    @XmlElement(
            required = true
    )
    protected String discriminacao;
    @XmlElement(
            required = true
    )
    protected String periodo;

    public CcfOcorrencia() {
    }

    public String getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(String var1) {
        this.quantidade = var1;
    }

    public String getDiscriminacao() {
        return this.discriminacao;
    }

    public void setDiscriminacao(String var1) {
        this.discriminacao = var1;
    }

    public String getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(String var1) {
        this.periodo = var1;
    }
}
