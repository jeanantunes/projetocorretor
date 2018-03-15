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
        name = "getReqs",
        propOrder = {"reqids"}
)
public class GetReqs {
    @XmlElement(
            nillable = true
    )
    protected List<ReqInfo> reqids;

    public GetReqs() {
    }

    public List<ReqInfo> getReqids() {
        if (this.reqids == null) {
            this.reqids = new ArrayList();
        }

        return this.reqids;
    }
}
