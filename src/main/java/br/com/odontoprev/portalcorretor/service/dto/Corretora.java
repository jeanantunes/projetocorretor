package br.com.odontoprev.portalcorretor.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Corretora implements Serializable {

    private static final long serialVersionUID = 2180567984276302023L;

    private long cdCorretora;
    private String cnpj;
    private String razaoSocial;
    private String cnae;
    private String telefone;
    private String celular;
    private String email;
    private String statusCnpj;
    private String simplesNacional;
    private String dataAbertura;
    private Endereco enderecoCorretora;
    private Conta conta;
    private Login login;
    private String temSusep;
    private String codigoSusep;

    private String senha; //201807181317 - esert - COR-319 - incluida senha para json do put de troca de senha

    private List<Representante> representantes;

    public long getCdCorretora() {
        return cdCorretora;
    }

    public void setCdCorretora(long cdCorretora) {
        this.cdCorretora = cdCorretora;
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

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
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

    public String getStatusCnpj() {
        return statusCnpj;
    }

    public void setStatusCnpj(String statusCnpj) {
        this.statusCnpj = statusCnpj;
    }

    public String getSimplesNacional() {
        return simplesNacional;
    }

    public void setSimplesNacional(String simplesNacional) {
        this.simplesNacional = simplesNacional;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Endereco getEnderecoCorretora() {
        return enderecoCorretora;
    }

    public void setEnderecoCorretora(Endereco enderecoCorretora) {
        this.enderecoCorretora = enderecoCorretora;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Representante> getRepresentantes() {
        return representantes;
    }

    public void setRepresentantes(List<Representante> representantes) {
        this.representantes = representantes;
    }

    public String getTemSusep() {
        return temSusep;
    }

    public void setTemSusep(String temSusep) {
        this.temSusep = temSusep;
    }

    public String getCodigoSusep() {
        return codigoSusep;
    }

    public void setCodigoSusep(String codigoSusep) {
        this.codigoSusep = codigoSusep;
    }

    @Override
    public String toString() {
        return "Corretora ["
                + "cdCorretora=" + cdCorretora
                + ", cnpj=" + cnpj
                + ", razaoSocial=" + razaoSocial
                + ", cnae=" + cnae
                + ", telefone=" + telefone
                + ", celular=" + celular
                + ", email=" + email
                + ", statusCnpj=" + statusCnpj
                + ", simplesNacional=" + simplesNacional
                + ", dataAbertura=" + dataAbertura
                + ", enderecoCorretora=" + (enderecoCorretora != null ? enderecoCorretora.toString() : "NuLL")
                + ", conta=" + (conta != null ? conta.toString() : "NuLL")
                + ", login=" + (login != null ? login.toString() : "NuLL")
                + ", senha=" + senha
                + ", representantes=" + (representantes != null ? String.valueOf(representantes.size()) : "NuLL")
                + "temSusep=" + temSusep
                + "codigoSusep=" + codigoSusep
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corretora)) return false;
        Corretora corretora = (Corretora) o;
        return getCdCorretora() == corretora.getCdCorretora() &&
                Objects.equals(getCnpj(), corretora.getCnpj()) &&
                Objects.equals(getRazaoSocial(), corretora.getRazaoSocial()) &&
                Objects.equals(getCnae(), corretora.getCnae()) &&
                Objects.equals(getTelefone(), corretora.getTelefone()) &&
                Objects.equals(getCelular(), corretora.getCelular()) &&
                Objects.equals(getEmail(), corretora.getEmail()) &&
                Objects.equals(getStatusCnpj(), corretora.getStatusCnpj()) &&
                Objects.equals(getSimplesNacional(), corretora.getSimplesNacional()) &&
                Objects.equals(getDataAbertura(), corretora.getDataAbertura()) &&
                Objects.equals(getEnderecoCorretora(), corretora.getEnderecoCorretora()) &&
                Objects.equals(getConta(), corretora.getConta()) &&
                Objects.equals(getLogin(), corretora.getLogin()) &&
                Objects.equals(getSenha(), corretora.getSenha()) &&
                Objects.equals(getRepresentantes(), corretora.getRepresentantes());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getCdCorretora(), getCnpj(), getRazaoSocial(), getCnae(), getTelefone(), getCelular(), getEmail(), getStatusCnpj(), getSimplesNacional(), getDataAbertura(), getEnderecoCorretora(), getConta(), getLogin(), getSenha(), getRepresentantes());
    }
}
