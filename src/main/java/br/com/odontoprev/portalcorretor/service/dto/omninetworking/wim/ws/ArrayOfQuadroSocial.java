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
        name = "ArrayOfQuadroSocial",
        propOrder = {"quadroSocial"}
)
public class ArrayOfQuadroSocial {
    @XmlElement(
            nillable = true
    )
    protected List<QuadroSocial> quadroSocial;

    public ArrayOfQuadroSocial() {
    }

    public List<QuadroSocial> getQuadroSocial() {
        if (this.quadroSocial == null) {
            this.quadroSocial = new ArrayList();
        }

        return this.quadroSocial;
    }
}
