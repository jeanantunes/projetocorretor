
package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.StatusProconTelefone;

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
        name = "RetornaStatusProconTelefone"
)
public class RetornaStatusProconTelefone {
    @XmlElement(
            required = true
    )
    protected StatusProconTelefone parameters;

    public RetornaStatusProconTelefone() {
    }

    public StatusProconTelefone getParameters() {
        return this.parameters;
    }

    public void setParameters(StatusProconTelefone var1) {
        this.parameters = var1;
    }
}
