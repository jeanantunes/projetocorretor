
package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "ParametersInPJ",
        propOrder = {"cnpj", "id", "cnpjIndireto", "retornoPJ"}
)
public class ParametersInPJ {
    protected String cnpj;
    protected String id;
    protected String cnpjIndireto;
    @XmlElement(
            name = "RetornoPJ",
            required = true
    )
    protected RetornoPJ retornoPJ;

    public ParametersInPJ() {
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String var1) {
        this.cnpj = var1;
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

    public RetornoPJ getRetornoPJ() {
        return this.retornoPJ;
    }

    public void setRetornoPJ(RetornoPJ var1) {
        this.retornoPJ = var1;
    }
}
