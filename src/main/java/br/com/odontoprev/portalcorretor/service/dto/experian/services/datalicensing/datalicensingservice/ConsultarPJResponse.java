package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.PessoaJuridica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"result"}
)
@XmlRootElement(
        name = "ConsultarPJResponse"
)
public class ConsultarPJResponse {
    @XmlElement(
            required = true
    )
    protected PessoaJuridica result;

    public ConsultarPJResponse() {
    }

    public PessoaJuridica getResult() {
        return this.result;
    }

    public void setResult(PessoaJuridica var1) {
        this.result = var1;
    }
}
