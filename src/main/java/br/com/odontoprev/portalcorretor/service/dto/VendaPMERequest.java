package br.com.odontoprev.portalcorretor.service.dto;

import java.util.List;

public class VendaPMERequest {
    public int getCdForcaVenda() {
        return cdForcaVenda;
    }

    public void setCdForcaVenda(int cdForcaVenda) {
        this.cdForcaVenda = cdForcaVenda;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Titulare> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Titulare> titulares) {
        this.titulares = titulares;
    }

    public List<Plano> getPlanos() {
		return planos;
	}

	public void setPlanos(List<Plano> planos) {
		this.planos = planos;
	}



	private int cdForcaVenda;
    private List<Empresa> empresas;
    private List<Plano> planos;
    private List<Titulare> titulares;
}
