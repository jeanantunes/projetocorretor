package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;

import br.com.odontoprev.portalcorretor.service.dto.LoginResponse;


public class UsuarioSession implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nomeUsuario;
	private String documento;
	private long codigoDcss;
	private long codigoUsuario;
	private Integer codigoCorretora;
	private String nomeCorretora;
	private String perfil;
	private String dtAceiteContrato;
	private boolean temBloqueio;
	private String email;

	public UsuarioSession setDados(LoginResponse response) {
		this.setNomeUsuario(response.getNomeUsuario());
		this.setDocumento(response.getDocumento());
		this.setCodigoDcss(response.getCodigoDcss());
		this.setCodigoUsuario(response.getCodigoUsuario());
		this.setCodigoCorretora(response.getCodigoCorretora());
		this.setNomeCorretora(response.getNomeCorretora());
		this.setPerfil(response.getPerfil());
		this.setDtAceiteContrato(response.getDtAceiteContrato());
		this.setTemBloqueio(response.getTemBloqueio());
		this.setEmail(response.getEmail());

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

	public Integer getCodigoCorretora() {
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

	public String getDtAceiteContrato() {
		return dtAceiteContrato;
	}

	public void setDtAceiteContrato(String dtAceiteContrato) {
		this.dtAceiteContrato = dtAceiteContrato;
	}

	public boolean getTemBloqueio() {
		return temBloqueio;
	}

	public void setTemBloqueio(boolean temBloqueio) {
		this.temBloqueio = temBloqueio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
