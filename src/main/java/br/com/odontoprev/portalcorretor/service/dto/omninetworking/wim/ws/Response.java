//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "pesquisarUnicaResponse",
        propOrder = {"_return"}
)
public class Response {
    @XmlElement(
            name = "return"
    )
    protected RequisicaoWim _return;

    public Response() {
    }

    public RequisicaoWim getReturn() {
        return this._return;
    }

    public void setReturn(RequisicaoWim var1) {
        this._return = var1;
    }
}
