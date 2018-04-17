//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "QuadroSocial",
        propOrder = {"documento", "nome", "participacao", "tipo"}
)
public class QuadroSocial {
    @XmlElement(
            required = true
    )
    protected String documento;
    @XmlElement(
            required = true
    )
    protected String nome;
    @XmlElement(
            required = true
    )
    protected String participacao;
    @XmlElement(
            required = true
    )
    @XmlSchemaType(
            name = "string"
    )
    protected TipoPessoa tipo;

    public QuadroSocial() {
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

    public String getParticipacao() {
        return this.participacao;
    }

    public void setParticipacao(String var1) {
        this.participacao = var1;
    }

    public TipoPessoa getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoPessoa var1) {
        this.tipo = var1;
    }
}
