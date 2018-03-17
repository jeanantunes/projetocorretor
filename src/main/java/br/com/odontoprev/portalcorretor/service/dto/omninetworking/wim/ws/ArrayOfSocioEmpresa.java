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
        name = "ArrayOfSocioEmpresa",
        propOrder = {"socioEmpresa"}
)
public class ArrayOfSocioEmpresa {
    @XmlElement(
            nillable = true
    )
    protected List<SocioEmpresa> socioEmpresa;

    public ArrayOfSocioEmpresa() {
    }

    public List<SocioEmpresa> getSocioEmpresa() {
        if (this.socioEmpresa == null) {
            this.socioEmpresa = new ArrayList();
        }

        return this.socioEmpresa;
    }
}