package br.com.odontoprev.portalcorretor.model;

public class ContratoCorretora {

    Long cdCorretora;
    Long susep;

    public Long getCdCorretora() {
        return cdCorretora;
    }

    public void setCdCorretora(Long cdCorretora) {
        this.cdCorretora = cdCorretora;
    }

    public Long getSusep() {
        return susep;
    }

    public void setSusep(Long susep) {
        this.susep = susep;
    }

    @Override
    public String toString() {
        return "ContratoCorretora{" +
                "cdCorretora=" + cdCorretora +
                ", susep=" + susep +
                '}';
    }
}
