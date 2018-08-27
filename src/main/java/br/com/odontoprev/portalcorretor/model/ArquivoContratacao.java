package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;
import java.util.Date;

public class ArquivoContratacao implements Serializable {

    private static final long serialVersionUID = 7605496331918144311L;

    private Long codigoEmpresa;
    private Date dataCriacao;
    private String nomeArquivo;
    private Long tamanhoArquivo;
    private String tipoConteudo;
    private String arquivoBase64;
    private String caminhoCarga;

    public ArquivoContratacao() {
    }

    public Long getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Long codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Long getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(Long tamanhoArquivo) {
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }

    public String getArquivoBase64() {
        return arquivoBase64;
    }

    public void setArquivoBase64(String arquivoBase64) {
        this.arquivoBase64 = arquivoBase64;
    }

    public String getCaminhoCarga() {
        return caminhoCarga;
    }

    public void setCaminhoCarga(String caminhoCarga) {
        this.caminhoCarga = caminhoCarga;
    }

    @Override
    public String toString() {
        String stringArquivo = arquivoBase64 != null ? String.valueOf(arquivoBase64.length()) : "NuLL";
        return "TbodMaterialDivulgacao ["
                + "codigoEmpresa=" + codigoEmpresa
                + ", dataCriacao=" + dataCriacao
                + ", nomeArquivo=" + nomeArquivo
                + ", tamanhoArquivo=" + tamanhoArquivo
                + ", tipoConteudo=" + tipoConteudo
                + ", arquivo=" + stringArquivo
                + "]";
    }

}