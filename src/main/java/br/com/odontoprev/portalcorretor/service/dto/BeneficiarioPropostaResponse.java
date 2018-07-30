package br.com.odontoprev.portalcorretor.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeneficiarioPropostaResponse extends BeneficiarioProposta implements Serializable {

    private static final long serialVersionUID = 3103017260669982091L;


    private Long cdPlano;
    private String descPlano;
    private Long qtdDependentes;

    public Long getCdPlano() {
        return cdPlano;
    }

    public void setCdPlano(Long cdPlano) {
        this.cdPlano = cdPlano;
    }

    public String getDescPlano() {
        return descPlano;
    }

    public void setDescPlano(String descPlano) {
        this.descPlano = descPlano;
    }

    public Long getQtdDependentes() {
        return (this.getDependentes() != null ? this.getDependentes().size() : 0L);
    }

    public void setQtdDependentes(Long qtdDependentes) {
        this.qtdDependentes = qtdDependentes;
    }

    @Override
    public String toString() {
        return "BeneficiarioPaginacao ["
                + "cdPlano=" + cdPlano
                + ", descPlano=" + descPlano
                + ", qtdDependentes=" + qtdDependentes
                + "]";
    }
}
