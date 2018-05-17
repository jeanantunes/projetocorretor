package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;

//201805171759 - esert - COR-170
public class EmpresaDcms implements Serializable {

	private static final long serialVersionUID = -7393237693663557316L;

	private Long cdEmpresa;
	private String cnpj;
	private String empDcms;

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

	public String getEmpDcms() {
		return empDcms;
	}

	public void setEmpDcms(String empDcms) {
		this.empDcms = empDcms;
	}

	@Override
	public String toString() {
		return "EmpresaDcms [cdEmpresa=" + cdEmpresa + ", cnpj=" + cnpj + ", empDcms=" + empDcms + "]";
	}

}
