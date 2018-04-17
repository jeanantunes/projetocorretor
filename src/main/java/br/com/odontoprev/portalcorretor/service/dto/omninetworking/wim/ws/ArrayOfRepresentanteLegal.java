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
        name = "ArrayOfRepresentanteLegal",
        propOrder = {"representanteLegal"}
)
public class ArrayOfRepresentanteLegal {
    @XmlElement(
            name = "RepresentanteLegal"
    )
    protected List<RepresentanteLegal> representanteLegal;

    public ArrayOfRepresentanteLegal() {
    }

    public List<RepresentanteLegal> getRepresentanteLegal() {
        if (this.representanteLegal == null) {
            this.representanteLegal = new ArrayList();
        }

        return this.representanteLegal;
    }
}
