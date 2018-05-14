package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Perfil  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer codigoPerfil;

    private String nomePerfil;

    private String descricao;

    @JsonFormat(pattern="dd-MM-yyyy")
	private Date dataInclusao;
    
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date dataAlteracao;

	public Integer getCodigoPerfil() {
		return codigoPerfil;
	}

	public void setCodigoPerfil(Integer codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getNomePerfil() {
		return nomePerfil;
	}

	public void setNomePerfil(String nomePerfil) {
		this.nomePerfil = nomePerfil;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
    
    
}
