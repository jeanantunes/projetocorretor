package br.com.odontoprev.portalcorretor.service.entity;

public enum FiltroStatusProposta {
    TODOS(0),
    APROVADO(1),
    CRITICADO(2);

    private final int value;
    private FiltroStatusProposta(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}


