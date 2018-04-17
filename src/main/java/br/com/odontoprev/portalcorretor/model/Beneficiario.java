package br.com.odontoprev.portalcorretor.model;

import java.util.ArrayList;
import java.util.List;

public class Beneficiario {
    private String cpf;
    private String sexo;
    private String dataNascimento;
    private String nome;
    private String plano;
    private String nomeDaMae;    

    private List<Beneficiario> dependentes = new ArrayList<>();

    public Beneficiario() {
    }

    public Beneficiario(String plano) {
        this.plano = plano;
    }

    public List<Beneficiario> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Beneficiario> dependentes) {
        this.dependentes = dependentes;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPlano() {
        return plano;
    }

    public void setPlano(String plano) {
        this.plano = plano;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }


    
    
}
