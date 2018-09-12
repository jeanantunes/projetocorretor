package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

public class ContratoCorretoraPreenchido implements Serializable { //201805161145 - esert - COR-170

	private static final long serialVersionUID = -55554554454541L;

	private Long cdCorretora;
	private Long cdContratoModelo;
	private String contratoPreenchido;
	private String nomeArquivo;
	private String tipoConteudo;

	public Long getCdCorretora() {
		return cdCorretora;
	}
	public void setCdCorretora(Long cdCorretora) {
		this.cdCorretora = cdCorretora;
	}
	public Long getCdContratoModelo() {
		return cdContratoModelo;
	}
	public void setCdContratoModelo(Long cdContratoModelo) {
		this.cdContratoModelo = cdContratoModelo;
	}
	public String getContratoPreenchido() {
		return contratoPreenchido;
	}
	public void setContratoPreenchido(String contratoPreenchido) {
		this.contratoPreenchido = contratoPreenchido;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getTipoConteudo() {
		return tipoConteudo;
	}
	public void setTipoConteudo(String tipoConteudo) {
		this.tipoConteudo = tipoConteudo;
	}

	@Override
	public String toString() {
		return "ContratoCorretoraPreenchido ["
				+ "cdCorretora=" + cdCorretora
				+ ", cdContratoModelo=" + cdContratoModelo
				+ ", contratoPreenchido=" + contratoPreenchido
				+ ", nomeArquivo=" + nomeArquivo
				+ ", tipoConteudo=" + tipoConteudo
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdContratoModelo == null) ? 0 : cdContratoModelo.hashCode());
		result = prime * result + ((cdCorretora == null) ? 0 : cdCorretora.hashCode());
		result = prime * result + ((contratoPreenchido == null) ? 0 : contratoPreenchido.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratoCorretoraPreenchido other = (ContratoCorretoraPreenchido) obj;
		if (cdContratoModelo == null) {
			if (other.cdContratoModelo != null)
				return false;
		} else if (!cdContratoModelo.equals(other.cdContratoModelo))
			return false;
		if (cdCorretora == null) {
			if (other.cdCorretora != null)
				return false;
		} else if (!cdCorretora.equals(other.cdCorretora))
			return false;
		if (contratoPreenchido == null) {
			if (other.contratoPreenchido != null)
				return false;
		} else if (!contratoPreenchido.equals(other.contratoPreenchido))
			return false;
		return true;
	}
}
