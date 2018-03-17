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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "Pessoa",
        propOrder = {"id", "enderecos", "telefones"}
)
@XmlSeeAlso({PessoaJuridica.class, PessoaFisica.class})
public abstract class Pessoa {
    @XmlElement(
            required = true
    )
    protected String id;
    @XmlElement(
            nillable = true
    )
    protected List<ArrayOfEndereco> enderecos;
    @XmlElement(
            nillable = true
    )
    protected List<ArrayOfTelefone> telefones;

    public Pessoa() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String var1) {
        this.id = var1;
    }

    public List<ArrayOfEndereco> getEnderecos() {
        if (this.enderecos == null) {
            this.enderecos = new ArrayList();
        }

        return this.enderecos;
    }

    public List<ArrayOfTelefone> getTelefones() {
        if (this.telefones == null) {
            this.telefones = new ArrayList();
        }

        return this.telefones;
    }
}
