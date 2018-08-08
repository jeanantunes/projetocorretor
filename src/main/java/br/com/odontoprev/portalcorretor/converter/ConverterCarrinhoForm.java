package br.com.odontoprev.portalcorretor.converter;

import br.com.odontoprev.portalcorretor.model.Carrinho;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.model.VendaPme;
import br.com.odontoprev.portalcorretor.service.dto.Empresa;
import br.com.odontoprev.portalcorretor.service.dto.Endereco;
import br.com.odontoprev.portalcorretor.service.dto.Titulare;
import br.com.odontoprev.portalcorretor.service.dto.VendaPMERequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class ConverterCarrinhoForm implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VendaPMERequest converter(Carrinho carrinho, UsuarioSession usuarioSession) {

        final VendaPMERequest request = new VendaPMERequest();
        request.setCdForcaVenda(new Long(usuarioSession.getCodigoDcss()).intValue());
        Empresa empresa = new Empresa();
        VendaPme vendaPme = carrinho.getVendaPme();
        empresa.setCelular(vendaPme.getCelular());
        //TODO: CNAE
        empresa.setCnae("1111");
        empresa.setCnpj(vendaPme.getCnpj());
        empresa.setCpfRepresentante(vendaPme.getCpf());
        empresa.setEmail(vendaPme.getEmail());
        empresa.setIncEstadual(vendaPme.getInscricaoEstadual());
        empresa.setNomeFantasia(vendaPme.getNomeFantasia());
        empresa.setRamoAtividade(vendaPme.getRamoDeAtividade());
        empresa.setRazaoSocial(vendaPme.getRazaoSocial());
        empresa.setRepresentanteLegal(vendaPme.getRepresentanteLegal());
        empresa.setStatus("PRONTA");
        empresa.setTelefone(vendaPme.getTelefone());
        empresa.setVencimentoFatura(vendaPme.getDataVencimento());

        Endereco endereco = new Endereco();
        endereco.setBairro(vendaPme.getBairro());
        endereco.setCep(vendaPme.getCep());
        endereco.setCidade(vendaPme.getCidade());
        endereco.setComplemento(vendaPme.getComplemento());
        endereco.setEstado(vendaPme.getEstado());
        endereco.setLogradouro(vendaPme.getEndereco());
        endereco.setNumero(vendaPme.getNumero());


        empresa.setEnderecoEmpresa(endereco);

        request.setEmpresas(Collections.singletonList(empresa));

        empresa.setPlanos(carrinho.getPlanos());


        ArrayList<Titulare> titulares = new ArrayList<Titulare>();
        carrinho.getBeneficiarios().forEach(beneficiario -> {

            Titulare titulare = new Titulare();
            titulare.setCnpj(vendaPme.getCnpj());
            titulare.setCpf(beneficiario.getCpf());
            titulare.setDataNascimento(beneficiario.getDataNascimento());
            titulare.setEmail(vendaPme.getEmail());
            titulare.setNome(beneficiario.getNome());
            titulare.setNomeMae(beneficiario.getNomeMae());
            titulare.setPfPj("f");
            titulare.setSexo(beneficiario.getSexo());
            titulare.setCdPlano(Math.toIntExact(beneficiario.getCdPlano()));
            titulare.setEndereco(endereco);


            if (!beneficiario.getDependentes().isEmpty()) {
                titulare.setDependentes(new ArrayList<>());


                beneficiario.getDependentes().forEach(dep -> {
                    Titulare dependente = new Titulare();
                    dependente.setCpf(dep.getCpf());
                    dependente.setDataNascimento(dep.getDataNascimento());
                    dependente.setNome(dep.getNome());
                    dependente.setNomeMae(dep.getNomeMae());
                    dependente.setSexo(dep.getSexo());
                    dependente.setCdPlano(Math.toIntExact(dep.getCdPlano()));

                    titulare.getDependentes().add(dependente);
                });

            }
            titulares.add(titulare);

        });

        request.setTitulares(titulares);

        return request;
    }

}
