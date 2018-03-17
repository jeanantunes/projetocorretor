package br.com.odontoprev.portalcorretor.service.dto;

import java.util.List;

import br.com.odontoprev.portalcorretor.model.Dependente;

public class Titulare {
    private int cdVida;
    private int cdTitular;
    private String celular;
    private String cpf ;
    private String cnpj;
    private String dataNascimento ;
    private String email ;
    private String nome ;
    private String nomeMae ;
    private String pfPj ;
    private String sexo ;
    private int cdPlano ;
    private int cdVenda ;
    private int cdTeste ;
    private Endereco endereco ;
    private List<Titulare> dependentes ;

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

    public int getCdPlano() {
        return cdPlano;
    }

    public void setCdPlano(int cdPlano) {
        this.cdPlano = cdPlano;
    }

    public int getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(int cdVenda) {
        this.cdVenda = cdVenda;
    }

    public int getCdTeste() {
        return cdTeste;
    }

    public void setCdTeste(int cdTeste) {
        this.cdTeste = cdTeste;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Titulare> getDependentes() {
        return dependentes;
    }

    public void setDependentes(List<Titulare> dependentes) {
        this.dependentes = dependentes;
    }

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
    
    
}
