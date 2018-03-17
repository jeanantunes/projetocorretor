package br.com.odontoprev.portalcorretor.service.dto;

import br.com.odontoprev.portalcorretor.model.VendaPme;

import java.util.List;

public class ConverterModelVendaPmeRequest {

    public VendaPMERequest converter(VendaPme vendaPme) {
        Empresa empresa = new Empresa();
        VendaPMERequest vendaPMERequest = new VendaPMERequest();
        empresa.setStatus("");
        empresa.setCnpj(vendaPme.getCnpj());
        empresa.setCnae("");
        empresa.setRazaoSocial(vendaPme.getRazaoSocial());
        empresa.setIncEstadual(vendaPme.getInscricaoEstadual());
        empresa.setRamoAtividade(vendaPme.getRamoDeAtividade());
        empresa.setNomeFantasia(vendaPme.getNomeFantasia());
        empresa.setRepresentanteLegal(vendaPme.getRepresentanteLegal());
        empresa.setContatoEmpresa(vendaPme.isRepresentanteEhContatoEmpresa());
        empresa.setTelefone(vendaPme.getTelefone());
        empresa.setCelular(vendaPme.getCelular());
        empresa.setEmail(vendaPme.getEmail());
        empresa.setVencimentoFatura(vendaPme.getDataVencimento());
        empresa.setEnderecoEmpresa(new Endereco());
        //empresa.setPlanos(new Plano());
        empresa.setCpfRepresentante(vendaPme.getCpf());
       // empresa.setDependentes(new Dependente());

        vendaPMERequest.setEmpresas((List<Empresa>) empresa);
        return vendaPMERequest;
    }
}
