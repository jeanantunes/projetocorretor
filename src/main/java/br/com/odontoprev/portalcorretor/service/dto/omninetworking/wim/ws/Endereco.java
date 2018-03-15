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
        name = "Endereco",
        propOrder = {"logradouro", "bairro", "cidade", "uf", "cep"}
)
public class Endereco {
    @XmlElement(
            required = true
    )
    protected Logradouro logradouro;
    @XmlElement(
            required = true
    )
    protected String bairro;
    @XmlElement(
            required = true
    )
    protected String cidade;
    @XmlElement(
            required = true
    )
    protected String uf;
    @XmlElement(
            required = true
    )
    protected String cep;

    public Endereco() {
    }

    public Logradouro getLogradouro() {
        return this.logradouro;
    }

    public void setLogradouro(Logradouro var1) {
        this.logradouro = var1;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setBairro(String var1) {
        this.bairro = var1;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String var1) {
        this.cidade = var1;
    }

    public String getUf() {
        return this.uf;
    }

    public void setUf(String var1) {
        this.uf = var1;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String var1) {
        this.cep = var1;
    }
}
