package br.com.odontoprev.portalcorretor.service.dto;

import java.util.List;

public class Empresa {

    private String status;
    private String cnpj;
    private String cnae;
    private String razaoSocial;
    private String incEstadual;
    private String ramoAtividade;
    private String nomeFantasia;
    private String representanteLegal;
    private Boolean contatoEmpresa;
    private String telefone;
    private String celular;
    private String email;
    private String vencimentoFatura;
    private Endereco enderecoEmpresa;
    private List<Plano> planos;
    private String cpfRepresentante;
    private List<Dependente> dependentes;
}
