package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;

import br.com.odontoprev.portalcorretor.Service.dto.LoginResponse;


public class UsuarioSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;
	private String documento;
	private long codigoDcss;
	private long codigoUsuario;
	private int codigoCorretora;
	private String nomeCorretora;
	private String perfil;

	public UsuarioSession setDados(LoginResponse response) {
		this.setNomeUsuario(response.getNomeUsuario());
		this.setDocumento(response.getDocumento());
		this.setCodigoDcss(response.getCodigoDcss());
		this.setCodigoUsuario(response.getCodigoUsuario());
		this.setCodigoCorretora(response.getCodigoCorretora());
		this.setNomeCorretora(response.getNomeCorretora());
		this.setPerfil(response.getPerfil());

		return this;
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public long getCodigoDcss() {
		return codigoDcss;
	}

	public void setCodigoDcss(long codigoDcss) {
		this.codigoDcss = codigoDcss;
	}

	public long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
}
