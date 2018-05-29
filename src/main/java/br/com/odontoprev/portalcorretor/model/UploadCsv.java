package br.com.odontoprev.portalcorretor.model;

import java.util.Arrays;

public class UploadCsv {

    private String nome;
    private String cpf;
    private String dataNascimento;
    private String celular;
    private String email;
    private String departamento;
    private String cargo;
    private String error;
    private String sucesso;
    private byte[] file;

    /*
    public UploadCsv(String nome, String cpf, String dataNascimento, String celular, String email,
                     String departamento, String cargo, String error, String sucesso, byte[] file) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.celular = celular;
        this.email = email;
        this.departamento = departamento;
        this.cargo = cargo;
        this.error = error;
        this.sucesso = sucesso;
        this.file = file;
    }
    */

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

    public String getError(){
        return cargo;
    }

    public void setError(String error){
        this.error = error;
    }

    public String getSucesso() {
        return sucesso;
    }

    public void setSucesso(String sucesso) {
        this.sucesso = sucesso;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UploadCsv{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", celular='" + celular + '\'' +
                ", email='" + email + '\'' +
                ", departamento='" + departamento + '\'' +
                ", cargo='" + cargo + '\'' +
                ", error='" + error + '\'' +
                ", sucesso='" + sucesso + '\'' +
                ", file=" + Arrays.toString(file) +
                '}';
    }

}
