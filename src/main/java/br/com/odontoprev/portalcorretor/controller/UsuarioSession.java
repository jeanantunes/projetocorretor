package br.com.odontoprev.portalcorretor.controller;

import java.io.Serializable;

import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;


public class UsuarioSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  int codigo;
	private String nome;
	private int codigoCorretora;
	private String nomeCorretora;
	private String perfil;
	private String documento;
	
	
	public UsuarioSession setDados(LoginResponse response) {
		this.setCodigo(response.getCodigo());
		this.setCodigoCorretora(response.getCodigoCorretora());
		this.setDocumento(response.getDocumento());
		this.setNome(response.getNome());
		this.setNomeCorretora(response.getNomeCorretora());
		this.setPerfil(response.getPerfil());
		return this;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getCodigoCorretora() {
		return codigoCorretora;
	}


	public void setCodigoCorretora(int codigoCorretora) {
		this.codigoCorretora = codigoCorretora;
	}


	public String getNomeCorretora() {
		return nomeCorretora;
	}


	public void setNomeCorretora(String nomeCorretora) {
		this.nomeCorretora = nomeCorretora;
	}


	public String getPerfil() {
		return perfil;
	}


	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}


	public String getDocumento() {
		return documento;
	}


	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	

}
