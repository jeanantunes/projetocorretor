package br.com.odontoprev.portalcorretor.model;

public class DadosContratoCorretora {

    public String codSusep;
    public Boolean contratoAceito;
    public Long cdCorretora;
    public String textoContrato;

    public String getCodSusep() {
        return codSusep;
    }

    public void setCodSusep(String codSusep) {
        this.codSusep = codSusep;
    }

    public Boolean getContratoAceito() {
        return contratoAceito;
    }

    public void setContratoAceito(Boolean contratoAceito) {
        this.contratoAceito = contratoAceito;
    }

    public Long getCdCorretora() {
        return cdCorretora;
    }

    public void setCdCorretora(Long cdCorretora) {
        this.cdCorretora = cdCorretora;
    }

    public String getTextoContrato() {
        return textoContrato;
    }

    public void setTextoContrato(String textoContrato) {
        this.textoContrato = textoContrato;
    }
}
