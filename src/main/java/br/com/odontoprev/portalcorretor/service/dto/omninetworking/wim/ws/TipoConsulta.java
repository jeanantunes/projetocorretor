//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
        name = "TipoConsulta"
)
@XmlEnum
public enum TipoConsulta {
    ONLINE,
    HISTORICO,
    ONLINE_HISTORICO,
    HISTORICO_ONLINE;

    private TipoConsulta() {
    }

    public String value() {
        return this.name();
    }

    public static TipoConsulta fromValue(String var0) {
        return valueOf(var0);
    }
}
