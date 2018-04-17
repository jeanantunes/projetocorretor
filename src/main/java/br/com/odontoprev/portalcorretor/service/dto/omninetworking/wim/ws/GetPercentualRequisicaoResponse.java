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
        name = "getPercentualRequisicaoResponse",
        propOrder = {"_return"}
)
public class GetPercentualRequisicaoResponse {
    @XmlElement(
            name = "return"
    )
    protected int _return;

    public GetPercentualRequisicaoResponse() {
    }

    public int getReturn() {
        return this._return;
    }

    public void setReturn(int var1) {
        this._return = var1;
    }
}
