package br.com.odontoprev.portalcorretor.model;

import java.util.List;

public class VendaPf {

	private String nomeTitularPlano;
	private String cpfTitularPlano;
	private String sexo;
	private String dataNascimentoTitularPlano;
	private String nomeMaeTitularPlano;
	private String celularTitularPlano;
	private String emailTitularPlano;
	private String cepTitularPlano;
	private String enderecoTitularPlano;
	private String numeroTitularPlano;
	private String complementoTitularPlano;
	private String bairroTitularPlano;
	private String cidadeTitularPlano;
	private String pagamentoCartaoNome;
	private String pagamentoCartaoValidade;
	private List<String> estado;
	private List<Dependente> dependente;
	
	public String getPagamentoCartaoNome() {
		return pagamentoCartaoNome;
	}
	public void setPagamentoCartaoNome(String pagamentoCartaoNome) {
		this.pagamentoCartaoNome = pagamentoCartaoNome;
	}
	public String getPagamentoCartaoValidade() {
		return pagamentoCartaoValidade;
	}
	public void setPagamentoCartaoValidade(String pagamentoCartaoValidade) {
		this.pagamentoCartaoValidade = pagamentoCartaoValidade;
	}
	public List<Dependente> getDependente() {
		return dependente;
	}
	public void setDependente(List<Dependente> dependente) {
		this.dependente = dependente;
	}
	public List<String> getEstado() {
		return estado;
	}
	public void setEstado(List<String> estado) {
		this.estado = estado;
	}
	public String getComplementoTitularPlano() {
		return complementoTitularPlano;
	}
	public void setComplementoTitularPlano(String complementoTitularPlano) {
		this.complementoTitularPlano = complementoTitularPlano;
	}
	public String getBairroTitularPlano() {
		return bairroTitularPlano;
	}
	public void setBairroTitularPlano(String bairroTitularPlano) {
		this.bairroTitularPlano = bairroTitularPlano;
	}
	public String getCidadeTitularPlano() {
		return cidadeTitularPlano;
	}
	public void setCidadeTitularPlano(String cidadeTitularPlano) {
		this.cidadeTitularPlano = cidadeTitularPlano;
	}
	public String getNomeTitularPlano() {
		return nomeTitularPlano;
	}
	public void setNomeTitularPlano(String nomeTitularPlano) {
		this.nomeTitularPlano = nomeTitularPlano;
	}
	public String getCpfTitularPlano() {
		return cpfTitularPlano;
	}
	public void setCpfTitularPlano(String cpfTitularPlano) {
		this.cpfTitularPlano = cpfTitularPlano;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDataNascimentoTitularPlano() {
		return dataNascimentoTitularPlano;
	}
	public void setDataNascimentoTitularPlano(String dataNascimentoTitularPlano) {
		this.dataNascimentoTitularPlano = dataNascimentoTitularPlano;
	}
	public String getNomeMaeTitularPlano() {
		return nomeMaeTitularPlano;
	}
	public void setNomeMaeTitularPlano(String nomeMaeTitularPlano) {
		this.nomeMaeTitularPlano = nomeMaeTitularPlano;
	}
	public String getCelularTitularPlano() {
		return celularTitularPlano;
	}
	public void setCelularTitularPlano(String celularTitularPlano) {
		this.celularTitularPlano = celularTitularPlano;
	}
	public String getEmailTitularPlano() {
		return emailTitularPlano;
	}
	public void setEmailTitularPlano(String emailTitularPlano) {
		this.emailTitularPlano = emailTitularPlano;
	}
	public String getCepTitularPlano() {
		return cepTitularPlano;
	}
	public void setCepTitularPlano(String cepTitularPlano) {
		this.cepTitularPlano = cepTitularPlano;
	}
	public String getEnderecoTitularPlano() {
		return enderecoTitularPlano;
	}
	public void setEnderecoTitularPlano(String enderecoTitularPlano) {
		this.enderecoTitularPlano = enderecoTitularPlano;
	}
	public String getNumeroTitularPlano() {
		return numeroTitularPlano;
	}
	public void setNumeroTitularPlano(String numeroTitularPlano) {
		this.numeroTitularPlano = numeroTitularPlano;
	}

}
