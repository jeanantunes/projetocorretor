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
        name = "getStatusReqs",
        propOrder = {"ids"}
)
public class GetStatusReqs {
    @XmlElement(
            nillable = true
    )
    protected List<String> ids;

    public GetStatusReqs() {
    }

    public List<String> getIds() {
        if (this.ids == null) {
            this.ids = new ArrayList();
        }

        return this.ids;
    }
}
