
package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.StatusProconTelefoneResponse;
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
        name = "RetornaStatusProconTelefoneResponse"
)
public class RetornaStatusProconTelefoneResponse {
    @XmlElement(
            required = true
    )
    protected StatusProconTelefoneResponse result;

    public RetornaStatusProconTelefoneResponse() {
    }

    public StatusProconTelefoneResponse getResult() {
        return this.result;
    }

    public void setResult(StatusProconTelefoneResponse var1) {
        this.result = var1;
    }
}
