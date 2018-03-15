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
        name = "ArrayOfSuframa",
        propOrder = {"suframa"}
)
public class ArrayOfSuframa {
    @XmlElement(
            nillable = true
    )
    protected List<Suframa> suframa;

    public ArrayOfSuframa() {
    }

    public List<Suframa> getSuframa() {
        if (this.suframa == null) {
            this.suframa = new ArrayList();
        }

        return this.suframa;
    }
}
