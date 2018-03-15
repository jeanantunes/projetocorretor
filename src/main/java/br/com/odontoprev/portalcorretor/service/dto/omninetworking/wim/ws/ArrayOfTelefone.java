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
        name = "ArrayOfTelefone",
        propOrder = {"telefone"}
)
public class ArrayOfTelefone {
    @XmlElement(
            nillable = true
    )
    protected List<Telefone> telefone;

    public ArrayOfTelefone() {
    }

    public List<Telefone> getTelefone() {
        if (this.telefone == null) {
            this.telefone = new ArrayList();
        }

        return this.telefone;
    }
}
