package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

public class RelatorioGestaoVendaResponse implements Serializable {
	
	private static final long serialVersionUID = 3835121277515096998L;
	
	private long id;
    private String mensagem;    
    
	public RelatorioGestaoVendaResponse() {
		
	}	
	
	public RelatorioGestaoVendaResponse(long id) {	
		this.id = id;
	}

	public RelatorioGestaoVendaResponse(long id, String mensagem) {	
		this.id = id;
		this.mensagem = mensagem;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
