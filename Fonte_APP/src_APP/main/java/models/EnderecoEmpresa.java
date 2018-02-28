package models;

import android.webkit.JavascriptInterface;

/**
 * Created by sleke on 24/02/2018.
 */

public class EnderecoEmpresa {

    private String idEnderecoEmpresa;
    private String cep;
    private String endereco;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;

    @JavascriptInterface
    public String getIdEnderecoEmpresa() {
        return idEnderecoEmpresa;
    }

    @JavascriptInterface
    public void setIdEnderecoEmpresa(String idEnderecoEmpresa) {
        this.idEnderecoEmpresa = idEnderecoEmpresa;
    }

    @JavascriptInterface
    public String getCep() {
        return cep;
    }

    @JavascriptInterface
    public void setCep(String cep) {
        this.cep = cep;
    }

    @JavascriptInterface
    public String getEndereco() {
        return endereco;
    }

    @JavascriptInterface
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @JavascriptInterface
    public String getNumero() {
        return numero;
    }

    @JavascriptInterface
    public void setNumero(String numero) {
        this.numero = numero;
    }

    @JavascriptInterface
    public String getComplemento() {
        return complemento;
    }

    @JavascriptInterface
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @JavascriptInterface
    public String getBairro() {
        return bairro;
    }

    @JavascriptInterface
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JavascriptInterface
    public String getCidade() {
        return cidade;
    }

    @JavascriptInterface
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @JavascriptInterface
    public String getEstado() {
        return estado;
    }

    @JavascriptInterface
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
