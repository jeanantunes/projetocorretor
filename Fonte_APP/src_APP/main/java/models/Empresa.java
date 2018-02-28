package models;

import android.webkit.JavascriptInterface;

/**
 * Created by sleke on 24/02/2018.
 */

public class Empresa {

    private String idEmpresa;
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;
    private String ramoAtividade;
    private String inscricaoEstadual;
    private String representanteLegal;
    private String cpf;
    private String telefone;
    private String celular;
    private String email;
    private String representanteLegalContatoEmpresa;
    private EnderecoEmpresa enderecoEmpresa;
    private String enderecoCorrespondenteMesmo;
    private String vencimentoFatura;

    @JavascriptInterface
    public String getIdEmpresa() {
        return idEmpresa;
    }

    @JavascriptInterface
    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @JavascriptInterface
    public String getCnpj() {
        return cnpj;
    }

    @JavascriptInterface
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @JavascriptInterface
    public String getRazaoSocial() {
        return razaoSocial;
    }

    @JavascriptInterface
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @JavascriptInterface
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    @JavascriptInterface
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    @JavascriptInterface
    public String getRamoAtividade() {
        return ramoAtividade;
    }

    @JavascriptInterface
    public void setRamoAtividade(String ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    @JavascriptInterface
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    @JavascriptInterface
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    @JavascriptInterface
    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    @JavascriptInterface
    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    @JavascriptInterface
    public String getCpf() {
        return cpf;
    }

    @JavascriptInterface
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @JavascriptInterface
    public String getTelefone() {
        return telefone;
    }

    @JavascriptInterface
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @JavascriptInterface
    public String getCelular() {
        return celular;
    }

    @JavascriptInterface
    public void setCelular(String celular) {
        this.celular = celular;
    }

    @JavascriptInterface
    public String getEmail() {
        return email;
    }

    @JavascriptInterface
    public void setEmail(String email) {
        this.email = email;
    }

    @JavascriptInterface
    public String getRepresentanteLegalContatoEmpresa() {
        return representanteLegalContatoEmpresa;
    }

    @JavascriptInterface
    public void setRepresentanteLegalContatoEmpresa(String representanteLegalContatoEmpresa) {
        this.representanteLegalContatoEmpresa = representanteLegalContatoEmpresa;
    }

    @JavascriptInterface
    public EnderecoEmpresa getEnderecoEmpresa() {
        return enderecoEmpresa;
    }

    @JavascriptInterface
    public void setEnderecoEmpresa(EnderecoEmpresa enderecoEmpresa) {
        this.enderecoEmpresa = enderecoEmpresa;
    }

    @JavascriptInterface
    public String getEnderecoCorrespondenteMesmo() {
        return enderecoCorrespondenteMesmo;
    }

    @JavascriptInterface
    public void setEnderecoCorrespondenteMesmo(String enderecoCorrespondenteMesmo) {
        this.enderecoCorrespondenteMesmo = enderecoCorrespondenteMesmo;
    }

    @JavascriptInterface
    public String getVencimentoFatura() {
        return vencimentoFatura;
    }

    @JavascriptInterface
    public void setVencimentoFatura(String vencimentoFatura) {
        this.vencimentoFatura = vencimentoFatura;
    }

}
