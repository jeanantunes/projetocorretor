package br.com.odontoprev.portalcorretor.model;

import java.util.ArrayList;
import java.util.List;

public class Beneficiarios {

    private Integer cdVida;
    private Integer cdTitular;
    private String celular;
    private String cpf;
    private String cnpj;
    private String dataNascimento;
    private String email;
    private String nome;
    private String nomeMae;
    private String pfPj;
    private String sexo;
    private String cdPlano;
    private String cdVenda;
    private String dadosBancarios;
    private Endereco endereco;
    private String plano;

    private List<Beneficiarios> dependentes = new ArrayList<>();

    public Beneficiarios() {
    }

    public Integer getCdVida() {
        return cdVida;
    }

    public void setCdVida(Integer cdVida) {
        this.cdVida = cdVida;
    }

    public Integer getCdTitular() {
        return cdTitular;
    }

    public void setCdTitular(Integer cdTitular) {
        this.cdTitular = cdTitular;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getPfPj() {
        return pfPj;
    }

    public void setPfPj(String pfPj) {
        this.pfPj = pfPj;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCdPlano() {
        return cdPlano;
    }

    public void setCdPlano(String cdPlano) {
        this.cdPlano = cdPlano;
    }

    public String getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(String cdVenda) {
        this.cdVenda = cdVenda;
    }

    public String getDadosBancarios() {
        return dadosBancarios;
    }

    public void setDadosBancarios(String dadosBancarios) {
        this.dadosBancarios = dadosBancarios;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public List<Beneficiarios> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Beneficiarios> dependentes) {
        this.dependentes = dependentes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
