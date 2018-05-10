package br.com.odontoprev.portalcorretor.service.dto;

import java.util.List;

public class FileUploadRequest {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String celular;
    private String email;
    private String departamento;
    private String cargo;
    private List<ForcaVenda> forcaVendaList;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<ForcaVenda> getForcaVendaList() {
        return forcaVendaList;
    }

    public void setForcaVendaList(List<ForcaVenda> forcaVendaList) {
        this.forcaVendaList = forcaVendaList;
    }
}
