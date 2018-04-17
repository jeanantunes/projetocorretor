package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public ConsultarPJResponse createConsultarPJResponse() {
        return new ConsultarPJResponse();
    }

    public ConsultarPJ createConsultarPJ() {
        return new ConsultarPJ();
    }

    public ConsultarPFResponse createConsultarPFResponse() {
        return new ConsultarPFResponse();
    }

    public DataLicensingFault createDataLicensingFault() {
        return new DataLicensingFault();
    }

    public ConsultarPF createConsultarPF() {
        return new ConsultarPF();
    }

    public RetornaStatusProconTelefone createRetornaStatusProconTelefone() {
        return new RetornaStatusProconTelefone();
    }

    public RetornaStatusProconTelefoneResponse createRetornaStatusProconTelefoneResponse() {
        return new RetornaStatusProconTelefoneResponse();
    }
}
