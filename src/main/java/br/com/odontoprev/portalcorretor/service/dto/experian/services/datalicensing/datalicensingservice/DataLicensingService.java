package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(
        name = "DataLicensingService",
        targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/",
        wsdlLocation = "https://sitenethomologa.serasa.com.br:443/experian-data-licensing-ws/dataLicensingService?wsdl"
)
public class DataLicensingService extends Service {
    private static final URL DATALICENSINGSERVICE_WSDL_LOCATION;
    private static final WebServiceException DATALICENSINGSERVICE_EXCEPTION;
    private static final QName DATALICENSINGSERVICE_QNAME = new QName("http://services.experian.com.br/DataLicensing/DataLicensingService/", "DataLicensingService");

    public DataLicensingService() {
        super(__getWsdlLocation(), DATALICENSINGSERVICE_QNAME);
    }

    public DataLicensingService(WebServiceFeature... var1) {
        super(__getWsdlLocation(), DATALICENSINGSERVICE_QNAME, var1);
    }

    public DataLicensingService(URL var1) {
        super(var1, DATALICENSINGSERVICE_QNAME);
    }

    public DataLicensingService(URL var1, WebServiceFeature... var2) {
        super(var1, DATALICENSINGSERVICE_QNAME, var2);
    }

    public DataLicensingService(URL var1, QName var2) {
        super(var1, var2);
    }

    public DataLicensingService(URL var1, QName var2, WebServiceFeature... var3) {
        super(var1, var2, var3);
    }

    @WebEndpoint(
            name = "DataLicensingServiceSOAP"
    )
    public DataLicensingServicePortType getDataLicensingServiceSOAP() {
        return (DataLicensingServicePortType)super.getPort(new QName("http://services.experian.com.br/DataLicensing/DataLicensingService/", "DataLicensingServiceSOAP"), DataLicensingServicePortType.class);
    }

    @WebEndpoint(
            name = "DataLicensingServiceSOAP"
    )
    public DataLicensingServicePortType getDataLicensingServiceSOAP(WebServiceFeature... var1) {
        return (DataLicensingServicePortType)super.getPort(new QName("http://services.experian.com.br/DataLicensing/DataLicensingService/", "DataLicensingServiceSOAP"), DataLicensingServicePortType.class, var1);
    }

    private static URL __getWsdlLocation() {
        if (DATALICENSINGSERVICE_EXCEPTION != null) {
            throw DATALICENSINGSERVICE_EXCEPTION;
        } else {
            return DATALICENSINGSERVICE_WSDL_LOCATION;
        }
    }

    static {
        URL var0 = null;
        WebServiceException var1 = null;

        try {
            var0 = new URL("https://sitenethomologa.serasa.com.br:443/experian-data-licensing-ws/dataLicensingService?wsdl");
        } catch (MalformedURLException var3) {
            var1 = new WebServiceException(var3);
        }

        DATALICENSINGSERVICE_WSDL_LOCATION = var0;
        DATALICENSINGSERVICE_EXCEPTION = var1;
    }
}
