package br.com.odontoprev.portalcorretor.model;

public class ReenvioEmailAceitePMEModel {

    private String cnpj;
    private String razaoSocial;
    private String dataAceite;
    private String email;
    private String observacao;
    private String error;

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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ReenvioEmailAceitePMEModel{" +
                "cnpj='" + cnpj + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", dataAceite='" + dataAceite + '\'' +
                ", email='" + email + '\'' +
                ", observacao='" + observacao + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
