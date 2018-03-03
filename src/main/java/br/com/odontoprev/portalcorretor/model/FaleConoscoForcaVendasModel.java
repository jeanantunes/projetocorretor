package br.com.odontoprev.portalcorretor.model;

public class FaleConoscoForcaVendasModel {

	private String nomeCorretor;
	private String nomeCorretora;
	private String tipoMensagem;
	private String mensagem;
	
	public String getNomeCorretor() {
		return nomeCorretor;
	}
	public void setNomeCorretor(String nomeCorretor) {
		this.nomeCorretor = nomeCorretor;
	}
	public String getNomeCorretora() {
		return nomeCorretora;
	}
	public void setNomeCorretora(String nomeCorretora) {
		this.nomeCorretora = nomeCorretora;
	}
	public String getTipoMensagem() {
		return tipoMensagem;
	}
	public void setTipoMensagem(String tipoMensagem) {
		this.tipoMensagem = tipoMensagem;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		return "FaleConoscoForcaVendasModel [nomeCorretor=" + nomeCorretor + ", nomeCorretora=" + nomeCorretora
				+ ", tipoMensagem=" + tipoMensagem + ", mensagem=" + mensagem + "]";
	}
}
