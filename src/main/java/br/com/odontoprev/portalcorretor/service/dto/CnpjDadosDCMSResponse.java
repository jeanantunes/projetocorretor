package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

public class CnpjDadosDCMSResponse implements Serializable { //201805161145 - esert - COR-170

	private static final long serialVersionUID = -4332984703592420514L;
	
	private Long cdEmpresa;
	private String cnpj;
	private String razaoSocial;
	private String observacao;
	private String empDcms; //201805102036 - esert - COR-169 
	
	public Long getCdEmpresa() {
		return cdEmpresa;
	}
	public void setCdEmpresa(Long cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getEmpDcms() {
		return empDcms;
	}
	public void setEmpDcms(String empDcms) {
		this.empDcms = empDcms;
	}
	
	@Override
	public String toString() {
		return "CnpjDadosDCMSResponse [cdEmpresa=" + cdEmpresa + ", cnpj=" + cnpj + ", razaoSocial=" + razaoSocial
				+ ", observacao=" + observacao + ", empDcms=" + empDcms + "]";
	}

}
