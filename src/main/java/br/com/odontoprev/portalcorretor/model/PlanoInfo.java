package br.com.odontoprev.portalcorretor.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class PlanoInfo implements Serializable {

	private static final long serialVersionUID = 1336338726489381946L;
	
	Long codigoPlanoInfo;
    String nomePlanoInfo;
    List<String> descricaoList;
    String descricao;
    Long codigoArquivoGeral;
    Long codigoArquivoCarencia;
    Long codigoArquivoIcone;
    String tipoSegmento;
    String ativo;
    Arquivo arquivoIcone;


    public Long getCodigoPlanoInfo() {
        return codigoPlanoInfo;
    }

    public void setCodigoPlanoInfo(Long codigoPlanoInfo) {
        this.codigoPlanoInfo = codigoPlanoInfo;
    }

    public String getNomePlanoInfo() {
        return nomePlanoInfo;
    }

    public void setNomePlanoInfo(String nomePlanoInfo) {
        this.nomePlanoInfo = nomePlanoInfo;
    }

    public List<String> getDescricaoList() {
        return descricaoList;
    }

    public void setDescricaoList(List<String> descricaoList) {
        this.descricaoList = descricaoList;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getCodigoArquivoGeral() {
        return codigoArquivoGeral;
    }

    public void setCodigoArquivoGeral(Long codigoArquivoGeral) {
        this.codigoArquivoGeral = codigoArquivoGeral;
    }

    public Long getCodigoArquivoCarencia() {
        return codigoArquivoCarencia;
    }

    public void setCodigoArquivoCarencia(Long codigoArquivoCarencia) {
        this.codigoArquivoCarencia = codigoArquivoCarencia;
    }

    public String getTipoSegmento() {
        return tipoSegmento;
    }

    public void setTipoSegmento(String tipoSegmento) {
        this.tipoSegmento = tipoSegmento;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public Long getCodigoArquivoIcone() {
        return codigoArquivoIcone;
    }

    public void setCodigoArquivoIcone(Long codigoArquivoIcone) {
        this.codigoArquivoIcone = codigoArquivoIcone;
    }

    public Arquivo getArquivoIcone() {
        return arquivoIcone;
    }

    public void setArquivoIcone(Arquivo arquivoIcone) {
        this.arquivoIcone = arquivoIcone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanoInfo planoInfo = (PlanoInfo) o;
        return Objects.equals(codigoPlanoInfo, planoInfo.codigoPlanoInfo) &&
                Objects.equals(nomePlanoInfo, planoInfo.nomePlanoInfo) &&
                Objects.equals(descricaoList, planoInfo.descricaoList) &&
                Objects.equals(descricao, planoInfo.descricao) &&
                Objects.equals(codigoArquivoGeral, planoInfo.codigoArquivoGeral) &&
                Objects.equals(codigoArquivoCarencia, planoInfo.codigoArquivoCarencia) &&
                Objects.equals(codigoArquivoIcone, planoInfo.codigoArquivoIcone) &&
                Objects.equals(tipoSegmento, planoInfo.tipoSegmento) &&
                Objects.equals(ativo, planoInfo.ativo) &&
                Objects.equals(arquivoIcone, planoInfo.arquivoIcone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoPlanoInfo, nomePlanoInfo, descricaoList, descricao, codigoArquivoGeral, codigoArquivoCarencia, codigoArquivoIcone, tipoSegmento, ativo, arquivoIcone);
    }

    @Override
    public String toString() {
        return "PlanoInfo{" +
                "codigoPlanoInfo=" + codigoPlanoInfo +
                ", nomePlanoInfo='" + nomePlanoInfo + '\'' +
                ", descricaoList=" + descricaoList +
                ", descricao='" + descricao + '\'' +
                ", codigoArquivoGeral=" + codigoArquivoGeral +
                ", codigoArquivoCarencia=" + codigoArquivoCarencia +
                ", codigoArquivoIcone=" + codigoArquivoIcone +
                ", tipoSegmento='" + tipoSegmento + '\'' +
                ", ativo='" + ativo + '\'' +
                ", arquivoIcone=" + arquivoIcone +
                '}';
    }
}
