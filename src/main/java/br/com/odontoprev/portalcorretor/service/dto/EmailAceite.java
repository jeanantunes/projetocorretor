package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class EmailAceite implements Serializable {

	private static final long serialVersionUID = 6304277230436674026L;
	
	private Long cdTokenAceite;
	
	@NotBlank
    @NotEmpty
	private Long cdVenda;
	
	private String ip;
	private Date dataAceite;
	
	@NotBlank
    @NotEmpty
	private String email;
	
	private String dataEnvio;
	private String dataExpiracao;
	
	@NotBlank
    @NotEmpty
	private String token;

	public Long getCdTokenAceite() {
		return cdTokenAceite;
	}

	public void setCdTokenAceite(Long cdTokenAceite) {
		this.cdTokenAceite = cdTokenAceite;
	}

	public Long getCdVenda() {
		return cdVenda;
	}

	public void setCdVenda(Long cdVenda) {
		this.cdVenda = cdVenda;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getDataAceite() {
		return dataAceite;
	}

	public void setDataAceite(Date dataAceite) {
		this.dataAceite = dataAceite;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(String dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getDataExpiracao() {
		return dataExpiracao;
	}

	public void setDataExpiracao(String dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "EmailAceite [cdTokenAceite=" + cdTokenAceite + ", cdVenda=" + cdVenda + ", ip=" + ip + ", dataAceite="
				+ dataAceite + ", email=" + email + ", dataEnvio=" + dataEnvio + ", dataExpiracao=" + dataExpiracao
				+ ", token=" + token + "]";
	}	
}
