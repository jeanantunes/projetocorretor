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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getIncEstadual() {
        return incEstadual;
    }

    public void setIncEstadual(String incEstadual) {
        this.incEstadual = incEstadual;
    }

    public String getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(String ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public Boolean getContatoEmpresa() {
        return contatoEmpresa;
    }

    public void setContatoEmpresa(Boolean contatoEmpresa) {
        this.contatoEmpresa = contatoEmpresa;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVencimentoFatura() {
        return vencimentoFatura;
    }

    public void setVencimentoFatura(String vencimentoFatura) {
        this.vencimentoFatura = vencimentoFatura;
    }

    public Endereco getEnderecoEmpresa() {
        return enderecoEmpresa;
    }

    public void setEnderecoEmpresa(Endereco enderecoEmpresa) {
        this.enderecoEmpresa = enderecoEmpresa;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public String getCpfRepresentante() {
        return cpfRepresentante;
    }

    public void setCpfRepresentante(String cpfRepresentante) {
        this.cpfRepresentante = cpfRepresentante;
    }

    public List<Dependente> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Dependente> dependentes) {
        this.dependentes = dependentes;
    }
}
