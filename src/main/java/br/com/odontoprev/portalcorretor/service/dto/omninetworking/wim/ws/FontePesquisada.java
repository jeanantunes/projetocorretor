
package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(
        name = "FontePesquisada"
)
@XmlEnum
public enum FontePesquisada {
    HISTORICO,
    ONLINE;

    private FontePesquisada() {
    }

    public String value() {
        return this.name();
    }

    public static FontePesquisada fromValue(String var0) {
        return valueOf(var0);
    }
}
