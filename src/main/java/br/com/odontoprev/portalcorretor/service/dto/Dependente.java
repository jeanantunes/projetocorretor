package br.com.odontoprev.portalcorretor.service.dto;

public class Dependente {
    public int cdVida;
    public int cdTitular;
    public String celular;
    public String cpf;
    public String dataNascimento;
    public String email;
    public String nome;
    public String nomeMae;
    public String pfPj;
    public String sexo;

    public int getCdVida() {
        return cdVida;
    }

    public void setCdVida(int cdVida) {
        this.cdVida = cdVida;
    }

    public int getCdTitular() {
        return cdTitular;
    }

    public void setCdTitular(int cdTitular) {
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
}
