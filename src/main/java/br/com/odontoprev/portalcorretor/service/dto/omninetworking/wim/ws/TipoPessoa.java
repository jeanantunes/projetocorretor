//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
        name = "TipoPessoa"
)
@XmlEnum
public enum TipoPessoa {
    PESSOA_FISICA,
    PESSOA_JURIDICA;

    private TipoPessoa() {
    }

    public String value() {
        return this.name();
    }

    public static TipoPessoa fromValue(String var0) {
        return valueOf(var0);
    }
}
