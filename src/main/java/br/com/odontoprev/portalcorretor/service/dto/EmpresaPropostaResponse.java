package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;
import java.util.List;

public class EmpresaPropostaResponse implements Serializable  {

	private static final long serialVersionUID = -6170025278181580898L;

	private String cnpj;
	private String razaoSocial;
	private String incEstadual;
	private String ramoAtividade;
	private String nomeFantasia;
	private String representanteLegal;
	private boolean contatoEmpresa;
	private String telefone;
	private String celular;
	private String email;
	private long vencimentoFatura;
	private String cnae;
	private String dataVencimentoFatura;
	private Endereco enderecoEmpresa;
	private List<Plano> planos;
	private String cnpjCorretora;
	private String nomeCorretora;
	private ContatoEmpresaPropostaResponse contactEmpresa;
	private String dataVigencia;
	private String dataMovimentacao;
	private Long cdEmpresa;
	private String empDcms;
	private String cpfRepresentante;

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

	public String getIncEstadual() {
		return incEstadual;
	}

	public void setIncEstadual(String incEstadual) {
		this.incEstadual = incEstadual;
	}

	public String getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(String ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(String representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public boolean isContatoEmpresa() {
		return contatoEmpresa;
	}

	public void setContatoEmpresa(boolean contatoEmpresa) {
		this.contatoEmpresa = contatoEmpresa;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCnae() {
		return cnae;
	}

	public void setCnae(String cnae) {
		this.cnae = cnae;
	}

	public String getDataVencimentoFatura() {
		return dataVencimentoFatura;
	}

	public void setDataVencimentoFatura(String dataVencimentoFatura) {
		this.dataVencimentoFatura = dataVencimentoFatura;
	}

	public Endereco getEnderecoEmpresa() {
		return enderecoEmpresa;
	}

	public void setEnderecoEmpresa(Endereco enderecoEmpresa) {
		this.enderecoEmpresa = enderecoEmpresa;
	}

	public long getVencimentoFatura() {
		return vencimentoFatura;
	}

	public void setVencimentoFatura(long vencimentoFatura) {
		this.vencimentoFatura = vencimentoFatura;
	}

	public List<Plano> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Plano> planos) {
		this.planos = planos;
	}
	
	public String getCnpjCorretora() {
		return cnpjCorretora;
	}

	public void setCnpjCorretora(String cnpjCorretora) {
		this.cnpjCorretora = cnpjCorretora;
	}

	public String getNomeCorretora() {
		return nomeCorretora;
	}

	public void setNomeCorretora(String nomeCorretora) {
		this.nomeCorretora = nomeCorretora;
	}
	
	public ContatoEmpresaPropostaResponse getContactEmpresa() {
		return contactEmpresa;
	}

	public void setContactEmpresa(ContatoEmpresaPropostaResponse contactEmpresa) {
		this.contactEmpresa = contactEmpresa;
	}

	public String getDataVigencia() {
		return dataVigencia;
	}

	public void setDataVigencia(String dataVigencia) {
		this.dataVigencia = dataVigencia;
	}

	public String getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(String dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public String getEmpDcms() {
		return empDcms;
	}

	public void setEmpDcms(String empDcms) {
		this.empDcms = empDcms;
	}

	public String getCpfRepresentante() {
		return cpfRepresentante;
	}

	public void setCpfRepresentante(String cpfRepresentante) {
		this.cpfRepresentante = cpfRepresentante;
	}

	@Override
	public String toString() {
		return "Empresa ["
				+ "cnpj=" + cnpj
				+ ", cdEmpresa=" + cdEmpresa
				+ ", razaoSocial=" + razaoSocial
				+ ", incEstadual=" + incEstadual
				+ ", ramoAtividade=" + ramoAtividade
				+ ", nomeFantasia=" + nomeFantasia
				+ ", representanteLegal=" + representanteLegal
				+ ", contatoEmpresa=" + contatoEmpresa
				+ ", telefone=" + telefone
				+ ", celular=" + celular
				+ ", email=" + email
				+ ", vencimentoFatura=" + vencimentoFatura
				+ ", cnae=" + cnae
				+ ", dataVencimentoFatura=" + dataVencimentoFatura
				+ ", enderecoEmpresa=" + enderecoEmpresa
				+ ", planos=" + planos
				+ ", cnpjCorretora=" + cnpjCorretora
				+ ", nomeCorretora=" + nomeCorretora
				+ ", contactEmpresa=" + contactEmpresa
				+ ", dataVigencia=" + dataVigencia
				+ ", dataMovimentacao=" + dataMovimentacao
				+ ", empDcms=" + empDcms
				+ ", cpfRepresentante=" + cpfRepresentante
				+ "]";
	}


}
