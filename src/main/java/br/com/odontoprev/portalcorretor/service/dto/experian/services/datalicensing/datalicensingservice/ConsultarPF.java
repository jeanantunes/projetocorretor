package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.ParametersInPF;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"parameters"}
)
@XmlRootElement(
        name = "ConsultarPF"
)
public class ConsultarPF {
    @XmlElement(
            required = true
    )
    protected ParametersInPF parameters;

    public ConsultarPF() {
    }

    public ParametersInPF getParameters() {
        return this.parameters;
    }

    public void setParameters(ParametersInPF var1) {
        this.parameters = var1;
    }
}
