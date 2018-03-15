package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.PessoaFisica;

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
        name = "ConsultarPFResponse"
)
public class ConsultarPFResponse {
    @XmlElement(
            required = true
    )
    protected PessoaFisica result;

    public ConsultarPFResponse() {
    }

    public PessoaFisica getResult() {
        return this.result;
    }

    public void setResult(PessoaFisica var1) {
        this.result = var1;
    }
}