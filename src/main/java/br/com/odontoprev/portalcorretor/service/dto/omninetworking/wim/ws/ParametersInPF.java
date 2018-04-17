
package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "ParametersInPF",
        propOrder = {"cpf", "id", "cnpjIndireto", "datadeNascimento", "retornoPF"}
)
public class ParametersInPF {
    protected String cpf;
    protected String id;
    protected String cnpjIndireto;
    protected String datadeNascimento;
    @XmlElement(
            name = "RetornoPF",
            required = true
    )
    protected RetornoPF retornoPF;

    public ParametersInPF() {
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String var1) {
        this.cpf = var1;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String var1) {
        this.id = var1;
    }

    public String getCnpjIndireto() {
        return this.cnpjIndireto;
    }

    public void setCnpjIndireto(String var1) {
        this.cnpjIndireto = var1;
    }

    public String getDatadeNascimento() {
        return this.datadeNascimento;
    }

    public void setDatadeNascimento(String var1) {
        this.datadeNascimento = var1;
    }

    public RetornoPF getRetornoPF() {
        return this.retornoPF;
    }

    public void setRetornoPF(RetornoPF var1) {
        this.retornoPF = var1;
    }
}
