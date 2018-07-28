package br.com.odontoprev.portalcorretor.service.dto;

import br.com.odontoprev.portalcorretor.model.DadosBancariosVenda;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiarioPropostaResponse implements Serializable {

	private static final long serialVersionUID = 3103017260669982091L;

	private long cdVida;
	private long cdTitular;
	private String celular;
	private String cpf;
	private String cnpj;
	private String dataNascimento;
	private String email;
	private String nome;
	private String nomeMae;
	private String pfPj;
	private String sexo;
	private long cdPlano;
	private long cdVenda;

	private DadosBancariosVenda dadosBancarios;

	private Endereco endereco;

	List<BeneficiarioPropostaResponse> dependentes;

	public long getCdVida() {
		return cdVida;
	}

	public void setCdVida(long cdVida) {
		this.cdVida = cdVida;
	}

	public long getCdTitular() {
		return cdTitular;
	}

	public void setCdTitular(long cdTitular) {
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

	public long getCdPlano() {
		return cdPlano;
	}

	public void setCdPlano(long cdPlano) {
		this.cdPlano = cdPlano;
	}

	public long getCdVenda() {
		return cdVenda;
	}

	public void setCdVenda(long cdVenda) {
		this.cdVenda = cdVenda;
	}

	public DadosBancariosVenda getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancariosVenda dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<BeneficiarioPropostaResponse> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<BeneficiarioPropostaResponse> dependentes) {
		this.dependentes = dependentes;
	}

	@Override
	public String toString() {
		return "Beneficiario [cdVida=" + cdVida + ", cdTitular=" + cdTitular + ", celular=" + celular + ", cpf=" + cpf
				+ ", cnpj=" + cnpj + ", dataNascimento=" + dataNascimento + ", email=" + email + ", nome=" + nome
				+ ", nomeMae=" + nomeMae + ", pfPj=" + pfPj + ", sexo=" + sexo + ", cdPlano=" + cdPlano + ", cdVenda="
				+ cdVenda + ", dadosBancarios=" + dadosBancarios + ", endereco=" + endereco + ", dependentes="
				+ dependentes + "]";
	}

}
