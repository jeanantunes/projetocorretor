package br.com.odontoprev.portalcorretor.service.entity;

public enum FiltroStatusProposta {
    TODOS(0),
    APROVADO(3), //Proposta concluida com sucesso
    CRITICADO(2), //Proposta criticada
    ENVIADO_UM(1), //Proposta enviada para a OdontoPrev
    ENVIADO_QUATRO(4); //Proposta enviada para a OdontoPrev

    private final int value;
    private FiltroStatusProposta(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}


