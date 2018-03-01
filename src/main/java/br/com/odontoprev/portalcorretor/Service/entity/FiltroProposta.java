package br.com.odontoprev.portalcorretor.Service.entity;

import groovy.transform.ToString;

public enum FiltroProposta {
    TODOS(0),
    PME(1),
    FORCAVENDAS(2);

    private final int value;
    private FiltroProposta(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}


