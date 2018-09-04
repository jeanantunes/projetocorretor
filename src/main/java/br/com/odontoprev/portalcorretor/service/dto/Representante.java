package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

//201809041540 - esert - COR-690 criar pagina dados da corretora - faltava a lista de representantes no retorno do servico web
public class Representante implements Serializable {

	private static final long serialVersionUID = -4619514049007987187L;
	
	private String nome;
	private String cpf;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	//201809041543 - esert - COR-690
	@Override
	public String toString() {
		return "Representante [nome=" + nome + ", cpf=" + cpf + "]";
	}
}
