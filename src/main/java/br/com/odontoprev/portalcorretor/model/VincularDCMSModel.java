package br.com.odontoprev.portalcorretor.model;

public class VincularDCMSModel {

    private String cnpj;
    private Long cdEmpresa;
    private String cdEmpDcms;
    private String observacao;
    
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public Long getCdEmpresa() {
		return cdEmpresa;
	}
	public void setCdEmpresa(Long cdEmpresa) {
		this.cdEmpresa = cdEmpresa;
	}
	public String getCdEmpDcms() {
		return cdEmpDcms;
	}
	public void setCdEmpDcms(String cdEmpDcms) {
		this.cdEmpDcms = cdEmpDcms;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	@Override
	public String toString() {
		return "VincularDCMSModel [cnpj=" + cnpj + ", cdEmpresa=" + cdEmpresa + ", cdEmpDcms=" + cdEmpDcms + ", observacao="
				+ observacao + "]";
	}
}
