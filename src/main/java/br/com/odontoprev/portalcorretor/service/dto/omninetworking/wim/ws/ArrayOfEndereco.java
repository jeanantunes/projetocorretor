package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "ArrayOfEndereco",
        propOrder = {"endereco"}
)
public class ArrayOfEndereco {
    @XmlElement(
            nillable = true
    )
    protected List<Endereco> endereco;

    public ArrayOfEndereco() {
    }

    public List<Endereco> getEndereco() {
        if (this.endereco == null) {
            this.endereco = new ArrayList();
        }

        return this.endereco;
    }
}
