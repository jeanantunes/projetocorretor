package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.ParametersInPJ;

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
        name = "ConsultarPJ"
)
public class ConsultarPJ {
    @XmlElement(
            required = true
    )
    protected ParametersInPJ parameters;

    public ConsultarPJ() {
    }

    public ParametersInPJ getParameters() {
        return this.parameters;
    }

    public void setParameters(ParametersInPJ var1) {
        this.parameters = var1;
    }
}
