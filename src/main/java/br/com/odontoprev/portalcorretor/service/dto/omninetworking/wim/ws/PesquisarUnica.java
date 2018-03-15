//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "pesquisarUnica",
        propOrder = {"fonte", "parametros", "timeout", "validade"}
)
public class PesquisarUnica {
    protected String fonte;
    @XmlElement(
            nillable = true
    )
    protected List<Parametros> parametros;
    protected int timeout;
    protected int validade;

    public PesquisarUnica() {
    }

    public String getFonte() {
        return this.fonte;
    }

    public void setFonte(String var1) {
        this.fonte = var1;
    }

    public List<Parametros> getParametros() {
        if (this.parametros == null) {
            this.parametros = new ArrayList();
        }

        return this.parametros;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int var1) {
        this.timeout = var1;
    }

    public int getValidade() {
        return this.validade;
    }

    public void setValidade(int var1) {
        this.validade = var1;
    }
}
