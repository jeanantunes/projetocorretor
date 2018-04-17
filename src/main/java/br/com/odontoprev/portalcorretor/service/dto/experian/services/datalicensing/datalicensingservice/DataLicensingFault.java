package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"reason"}
)
@XmlRootElement(
        name = "DataLicensingFault"
)
public class DataLicensingFault {
    protected String reason;

    public DataLicensingFault() {
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String var1) {
        this.reason = var1;
    }
}
