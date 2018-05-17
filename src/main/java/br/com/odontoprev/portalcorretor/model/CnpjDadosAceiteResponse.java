package br.com.odontoprev.portalcorretor.model;

import br.com.odontoprev.portalcorretor.service.dto.TokenAceite;

import java.io.Serializable;
import java.util.Date;

public class CnpjDadosAceiteResponse implements Serializable { //2018051102036 - esert - COR-172

    private static final long serialVersionUID = -4530821493004106650L;

    private String cnpj;
    private String razaoSocial;
    private String dataAceite;
    private String email;
    private String observacao;
    private String error;
    private Long cdEmpresa;
    private Long cdVenda;

    private Date dtVenda; //2018051111438 - esert - COR-172
    private String empDcms; //2018051102036 - esert - COR-169
    private TokenAceite tokenAceite;

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

    public Long getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(Long cdVenda) {
        this.cdVenda = cdVenda;
    }

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
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

    public TokenAceite getTokenAceite() {
        return tokenAceite;
    }

    public void setTokenAceite(TokenAceite tokenAceite) {
        this.tokenAceite = tokenAceite;
    }

    public String getDataAceite() {
        return dataAceite;
    }

    public void setDataAceite(String dataAceite) {
        this.dataAceite = dataAceite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CnpjDadosAceiteResponse{" +
                "cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", dataAceite='" + dataAceite + '\'' +
                ", email='" + email + '\'' +
                ", observacao='" + observacao + '\'' +
                ", error='" + error + '\'' +
                ", cdEmpresa=" + cdEmpresa +
                ", cdVenda=" + cdVenda +
                ", dtVenda=" + dtVenda +
                ", empDcms='" + empDcms + '\'' +
                ", tokenAceite=" + tokenAceite +
                '}';
    }

}
