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
        name = "Suframa",
        propOrder = {"inscricaoSuframa", "situacaoCadastral", "dataInscricao", "dataValidade", "tipoIncentivo", "isentaTSA"}
)
public class Suframa {
    @XmlElement(
            required = true
    )
    protected String inscricaoSuframa;
    @XmlElement(
            required = true
    )
    protected String situacaoCadastral;
    @XmlElement(
            required = true
    )
    protected String dataInscricao;
    @XmlElement(
            required = true
    )
    protected String dataValidade;
    @XmlElement(
            required = true
    )
    protected String tipoIncentivo;
    @XmlElement(
            required = true
    )
    protected String isentaTSA;

    public Suframa() {
    }

    public String getInscricaoSuframa() {
        return this.inscricaoSuframa;
    }

    public void setInscricaoSuframa(String var1) {
        this.inscricaoSuframa = var1;
    }

    public String getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

    public void setSituacaoCadastral(String var1) {
        this.situacaoCadastral = var1;
    }

    public String getDataInscricao() {
        return this.dataInscricao;
    }

    public void setDataInscricao(String var1) {
        this.dataInscricao = var1;
    }

    public String getDataValidade() {
        return this.dataValidade;
    }

    public void setDataValidade(String var1) {
        this.dataValidade = var1;
    }

    public String getTipoIncentivo() {
        return this.tipoIncentivo;
    }

    public void setTipoIncentivo(String var1) {
        this.tipoIncentivo = var1;
    }

    public String getIsentaTSA() {
        return this.isentaTSA;
    }

    public void setIsentaTSA(String var1) {
        this.isentaTSA = var1;
    }
}
