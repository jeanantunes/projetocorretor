package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

public class Login implements Serializable {

	private static final long serialVersionUID = -5493233987085523214L;

	private Long cdLogin;
	private Long cdTipoLogin;
	private String fotoPerfilB64;
	private String senha;
	private String usuario;
	private boolean temBloqueio;
	private Long codigoTipoBloqueio;
	private String descricaoTipoBloqueio;

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getCdLogin() {
		return cdLogin;
	}

	public void setCdLogin(Long cdLogin) {
		this.cdLogin = cdLogin;
	}

	public Long getCdTipoLogin() {
		return cdTipoLogin;
	}

	public void setCdTipoLogin(Long cdTipoLogin) {
		this.cdTipoLogin = cdTipoLogin;
	}

	public String getFotoPerfilB64() {
		return fotoPerfilB64;
	}

	public void setFotoPerfilB64(String fotoPerfilB64) {
		this.fotoPerfilB64 = fotoPerfilB64;
	}

	public boolean getTemBloqueio() {
		return temBloqueio;
	}

	public void setTemBloqueio(boolean temBloqueio) {
		this.temBloqueio = temBloqueio;
	}

	public Long getCodigoTipoBloqueio() {
		return codigoTipoBloqueio;
	}

	public void setCodigoTipoBloqueio(Long codigoTipoBloqueio) {
		this.codigoTipoBloqueio = codigoTipoBloqueio;
	}

	public String getDescricaoTipoBloqueio() {
		return descricaoTipoBloqueio;
	}

	public void setDescricaoTipoBloqueio(String descricaoTipoBloqueio) {
		this.descricaoTipoBloqueio = descricaoTipoBloqueio;
	}

	@Override
	public String toString() {
		return "Login{" +
				"cdLogin=" + cdLogin +
				", cdTipoLogin=" + cdTipoLogin +
				", fotoPerfilB64='" + fotoPerfilB64 + '\'' +
				", senha='" + senha + '\'' +
				", usuario='" + usuario + '\'' +
				", temBloqueio=" + temBloqueio +
				", codigoTipoBloqueio=" + codigoTipoBloqueio +
				", descricaoTipoBloqueio='" + descricaoTipoBloqueio + '\'' +
				'}';
	}
}
