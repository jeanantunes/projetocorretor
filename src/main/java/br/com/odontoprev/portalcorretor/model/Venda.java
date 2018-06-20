package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Venda implements Serializable {

	private static final long serialVersionUID = 3281440316438484993L;

	private Long cdVenda;
	private Long cdEmpresa;
	
	//private List<Plano> planos;
	private Long cdPlano;
	
	private Long cdForcaVenda;
	private Date dataVenda;
	private Long cdStatusVenda;
	private Long faturaVencimento;
	
	private String tipoPagamento;

	private List<Beneficiarios> titulares;

	private Long cdDCSSUsuario;
	
	private ResponsavelContratual responsavelContratual;

	public Long getCdVenda() {
		return cdVenda;
	}

	public void setCdVenda(long cdVenda) {
		this.cdVenda = cdVenda;
	}

	public Long getCdEmpresa() {
		return cdEmpresa;
	}

	public void setCdEmpresa(long cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}

	public Long getCdForcaVenda() {
		return cdForcaVenda;
	}

	public void setCdForcaVenda(long cdForcaVenda) {
		this.cdForcaVenda = cdForcaVenda;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Long getCdStatusVenda() {
		return cdStatusVenda;
	}

	public void setCdStatusVenda(Long cdStatusVenda) {
		this.cdStatusVenda = cdStatusVenda;
	}

	public Long getFaturaVencimento() {
		return faturaVencimento;
	}

	public void setFaturaVencimento(long faturaVencimento) {
		this.faturaVencimento = faturaVencimento;
	}

	public String getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(String tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public List<Beneficiarios> getTitulares() {
		return titulares;
	}

	public void setTitulares(List<Beneficiarios> titulares) {
		this.titulares = titulares;
	}

	public Long getCdPlano() {
		return cdPlano;
	}
	
	public Long getCdDCSSUsuario() {		
		return cdDCSSUsuario;
	}

	public void setCdDCSSUsuario(Long cdDCSSUsuario) {
		this.cdDCSSUsuario = cdDCSSUsuario;
	}

	public void setCdPlano(Long cdPlano) {
		this.cdPlano = cdPlano;
	}
	
	public ResponsavelContratual getResponsavelContratual() {
		return responsavelContratual;
	}

	public void setResponsavelContratual(ResponsavelContratual responsavelContratual) {
		this.responsavelContratual = responsavelContratual;
	}

	@Override
	public String toString() {
		return "Venda [cdVenda=" + cdVenda + ", cdEmpresa=" + cdEmpresa + ", cdPlano=" + cdPlano + ", cdForcaVenda="
				+ cdForcaVenda + ", dataVenda=" + dataVenda + ", cdStatusVenda=" + cdStatusVenda + ", faturaVencimento="
				+ faturaVencimento + ", tipoPagamento=" + tipoPagamento + ", titulares=" + titulares
				+ ", cdDCSSUsuario=" + cdDCSSUsuario + ", responsavelContratual=" + responsavelContratual + "]";
	}

}
