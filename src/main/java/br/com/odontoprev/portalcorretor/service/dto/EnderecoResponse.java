package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

public class EnderecoResponse implements Serializable {
	
	private static final long serialVersionUID = -4410264629765649293L;
	
	private String estado;
	private long idCidade;
	private String cidade;
	private long idBairro;
	private String bairro;
	private String bairroAbreviado;
	private String logradouro;
	private String cep;
	private String complemento;	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public long getIdCidade() {
		return idCidade;
	}
	public void setIdCidade(long idCidade) {
		this.idCidade = idCidade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public long getIdBairro() {
		return idBairro;
	}
	public void setIdBairro(long idBairro) {
		this.idBairro = idBairro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getBairroAbreviado() {
		return bairroAbreviado;
	}
	public void setBairroAbreviado(String bairroAbreviado) {
		this.bairroAbreviado = bairroAbreviado;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
