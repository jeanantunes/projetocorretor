package br.com.odontoprev.portalcorretor.service.dto;

public class LoginADResponse {

	private String nome;
	private String statuscode;
	
	public String getNome() {
		return nome;
	}
	
	public String getStatuscode() {
		return statuscode;
	}


	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	@Override
	public String toString() {
		return "LoginADResponse [nome=" + nome + ", statuscode=" + statuscode + "]";
	}
	
	
}
