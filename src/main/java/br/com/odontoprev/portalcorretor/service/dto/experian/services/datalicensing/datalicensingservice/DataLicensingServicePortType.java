package br.com.odontoprev.portalcorretor.service.dto.experian.services.datalicensing.datalicensingservice;

import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

@WebService(
        name = "DataLicensingServicePortType",
        targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/"
)
@XmlSeeAlso({ObjectFactory.class, ObjectFactory.class})
public interface DataLicensingServicePortType {
    @WebMethod(
            operationName = "ConsultarPF",
            action = "http://services.experian.com.br/DataLicensing/DataLicensingService/ConsultarPF"
    )
    @WebResult(
            name = "result",
            targetNamespace = ""
    )
    @RequestWrapper(
            localName = "ConsultarPF",
            targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/",
            className = "br.com.experian.services.datalicensing.datalicensingservice.ConsultarPF"
    )
    @ResponseWrapper(
            localName = "ConsultarPFResponse",
            targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/",
            className = "br.com.experian.services.datalicensing.datalicensingservice.ConsultarPFResponse"
    )
    @Action(
            input = "http://services.experian.com.br/DataLicensing/DataLicensingService/ConsultarPF",
            output = "http://services.experian.com.br/DataLicensing/DataLicensingService/DataLicensingServicePortType/ConsultarPFResponse",
            fault = {@FaultAction(
                    className = DataLicensingFaultException.class,
                    value = "http://services.experian.com.br/DataLicensing/DataLicensingService/DataLicensingServicePortType/ConsultarPF/Fault/DataLicensingFault_Exception"
            )}
    )
    PessoaFisica consultarPF(@WebParam(name = "parameters",targetNamespace = "") ParametersInPF var1) throws DataLicensingFaultException;

    @WebMethod(
            operationName = "ConsultarPJ",
            action = "http://services.experian.com.br/DataLicensing/DataLicensingService/ConsultarPJ"
    )
    @WebResult(
            name = "result",
            targetNamespace = ""
    )
    @RequestWrapper(
            localName = "ConsultarPJ",
            targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/",
            className = "br.com.experian.services.datalicensing.datalicensingservice.ConsultarPJ"
    )
    @ResponseWrapper(
            localName = "ConsultarPJResponse",
            targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/",
            className = "br.com.experian.services.datalicensing.datalicensingservice.ConsultarPJResponse"
    )
    @Action(
            input = "http://services.experian.com.br/DataLicensing/DataLicensingService/ConsultarPJ",
            output = "http://services.experian.com.br/DataLicensing/DataLicensingService/DataLicensingServicePortType/ConsultarPJResponse",
            fault = {@FaultAction(
                    className = DataLicensingFaultException.class,
                    value = "http://services.experian.com.br/DataLicensing/DataLicensingService/DataLicensingServicePortType/ConsultarPJ/Fault/DataLicensingFault_Exception"
            )}
    )
    PessoaJuridica consultarPJ(@WebParam(name = "parameters",targetNamespace = "") ParametersInPJ var1) throws DataLicensingFaultException;

    @WebMethod(
            operationName = "RetornaStatusProconTelefone",
            action = "http://services.experian.com.br/DataLicensing/DataLicensingService/RetornaStatusProconTelefone"
    )
    @WebResult(
            name = "result",
            targetNamespace = ""
    )
    @RequestWrapper(
            localName = "RetornaStatusProconTelefone",
            targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/",
            className = "br.com.experian.services.datalicensing.datalicensingservice.RetornaStatusProconTelefone"
    )
    @ResponseWrapper(
            localName = "RetornaStatusProconTelefoneResponse",
            targetNamespace = "http://services.experian.com.br/DataLicensing/DataLicensingService/",
            className = "br.com.experian.services.datalicensing.datalicensingservice.RetornaStatusProconTelefoneResponse"
    )
    @Action(
            input = "http://services.experian.com.br/DataLicensing/DataLicensingService/RetornaStatusProconTelefone",
            output = "http://services.experian.com.br/DataLicensing/DataLicensingService/DataLicensingServicePortType/RetornaStatusProconTelefoneResponse",
            fault = {@FaultAction(
                    className = DataLicensingFaultException.class,
                    value = "http://services.experian.com.br/DataLicensing/DataLicensingService/DataLicensingServicePortType/RetornaStatusProconTelefone/Fault/DataLicensingFault_Exception"
            )}
    )
    StatusProconTelefoneResponse retornaStatusProconTelefone(@WebParam(name = "parameters",targetNamespace = "") StatusProconTelefone var1) throws DataLicensingFaultException;
}
