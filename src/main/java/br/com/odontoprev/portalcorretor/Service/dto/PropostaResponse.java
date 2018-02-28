package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.Date;

public class PropostaResponse {

    private long codigoCorretora;
    private String nomeCorretora;
    private String statusVenda;
    private Date dataVenda;
    private String documentoCorretora;

    public long getCodigoCorretora() {
        return codigoCorretora;
    }

    public void setCodigoCorretora(long codigoCorretora) {
        this.codigoCorretora = codigoCorretora;
    }

    public String getNomeCorretora() {
        return nomeCorretora;
    }

    public void setNomeCorretora(String nomeCorretora) {
        this.nomeCorretora = nomeCorretora;
    }

    public String getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(String statusVenda) {
        this.statusVenda = statusVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public String getDocumentoCorretora() {
        return documentoCorretora;
    }

    public void setDocumentoCorretora(String documentoCorretora) {
        this.documentoCorretora = documentoCorretora;
    }
}
