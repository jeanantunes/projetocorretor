
package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "ArrayOfCnae",
        propOrder = {"tnsCnae"}
)
public class ArrayOfCnae {
    @XmlElement(
            nillable = true
    )
    protected List<Cnae> tnsCnae;

    public ArrayOfCnae() {
    }

    public List<Cnae> getTnsCnae() {
        if (this.tnsCnae == null) {
            this.tnsCnae = new ArrayList();
        }

        return this.tnsCnae;
    }
}
