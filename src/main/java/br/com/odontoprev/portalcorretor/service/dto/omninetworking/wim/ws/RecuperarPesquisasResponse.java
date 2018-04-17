//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "recuperarPesquisasResponse",
        propOrder = {"_return"}
)
public class RecuperarPesquisasResponse {
    @XmlElement(
            name = "return"
    )
    protected List<PesquisaWim> _return;

    public RecuperarPesquisasResponse() {
    }

    public List<PesquisaWim> getReturn() {
        if (this._return == null) {
            this._return = new ArrayList();
        }

        return this._return;
    }
}
