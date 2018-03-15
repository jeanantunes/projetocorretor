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
        name = "",
        propOrder = {"nomeCampo", "valorCampo"}
)
public class CamposResposta {
    protected String nomeCampo;
    protected String valorCampo;

    public CamposResposta() {
    }

    public String getNomeCampo() {
        return this.nomeCampo;
    }

    public void setNomeCampo(String var1) {
        this.nomeCampo = var1;
    }

    public String getValorCampo() {
        return this.valorCampo;
    }

    public void setValorCampo(String var1) {
        this.valorCampo = var1;
    }
}
