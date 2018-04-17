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
        name = "ArrayOfSintegra",
        propOrder = {"ocorrencia"}
)
public class ArrayOfSintegra {
    @XmlElement(
            nillable = true
    )
    protected List<Sintegra> ocorrencia;

    public ArrayOfSintegra() {
    }

    public List<Sintegra> getOcorrencia() {
        if (this.ocorrencia == null) {
            this.ocorrencia = new ArrayList();
        }

        return this.ocorrencia;
    }
}
