package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;
import java.util.Objects;

//201809051827 - esert - COR-695 - novo dto para retorno service para salvar email corretora 
public class CorretoraResponse implements Serializable {

	private static final long serialVersionUID = 2127819694346550728L;

	private long id;
	private String mensagem;

	public CorretoraResponse(long id, String mensagem) {
		this.id = id;
		this.mensagem = mensagem;
	}

	public CorretoraResponse() {
	}

	public long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CorretoraResponse)) return false;
		CorretoraResponse that = (CorretoraResponse) o;
		return getId() == that.getId() &&
				Objects.equals(getMensagem(), that.getMensagem());
	}

	@Override
	public int hashCode() {

		return Objects.hash(getId(), getMensagem());
	}
}
