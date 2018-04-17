package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import javax.xml.ws.WebFault;

@WebFault(
        name = "DataLicensingFault",
        targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/"
)
public class DataLicensingFaultException extends Exception {
    private DataLicensingFault faultInfo;

    public DataLicensingFaultException(String var1, DataLicensingFault var2) {
        super(var1);
        this.faultInfo = var2;
    }

    public DataLicensingFaultException(String var1, DataLicensingFault var2, Throwable var3) {
        super(var1, var3);
        this.faultInfo = var2;
    }

    public DataLicensingFault getFaultInfo() {
        return this.faultInfo;
    }
}
