//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "getStatusList",
        propOrder = {"dataInicial", "dataFinal"}
)
public class GetStatusList {
    protected String dataInicial;
    protected String dataFinal;

    public GetStatusList() {
    }

    public String getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(String var1) {
        this.dataInicial = var1;
    }

    public String getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(String var1) {
        this.dataFinal = var1;
    }
}
