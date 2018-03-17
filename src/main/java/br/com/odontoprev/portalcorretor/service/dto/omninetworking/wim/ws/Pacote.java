
package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(
        name = "Pacote"
)
@XmlEnum
public enum Pacote {
    BASICO("BASICO"),
    @XmlEnumValue("PF1")
    PF_1("PF1"),
    @XmlEnumValue("PF2")
    PF_2("PF2"),
    @XmlEnumValue("PF3")
    PF_3("PF3"),
    @XmlEnumValue("PJ1")
    PJ_1("PJ1"),
    @XmlEnumValue("PJ2")
    PJ_2("PJ2");

    private final String value;

    private Pacote(String var3) {
        this.value = var3;
    }

    public String value() {
        return this.value;
    }

    public static Pacote fromValue(String var0) {
        Pacote[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Pacote var4 = var1[var3];
            if (var4.value.equals(var0)) {
                return var4;
            }
        }

        throw new IllegalArgumentException(var0);
    }
}
