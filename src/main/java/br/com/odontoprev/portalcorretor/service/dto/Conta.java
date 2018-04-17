package br.com.odontoprev.portalcorretor.service.dto;

public class Conta {
	
	private Long cdBancoConta;	
	private String codigoAgencia;	
	private String codigoBanco;	
	private String numeroConta;
		
	public Conta() {
		
	}
	
	public Long getCdBancoConta() {
		return cdBancoConta;
	}
	
	public void setCdBancoConta(Long cdBancoConta) {
		this.cdBancoConta = cdBancoConta;
	}
	
	public String getCodigoAgencia() {
		return codigoAgencia;
	}
	
	public void setCodigoAgencia(String codigoAgencia) {
		this.codigoAgencia = codigoAgencia;
	}
	
	public String getCodigoBanco() {
		return codigoBanco;
	}
	
	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
}
