//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "enviarRequisicao",
        propOrder = {"requisicao"}
)
public class EnviarRequisicao {
    protected RequisicaoWim requisicao;

    public EnviarRequisicao() {
    }

    public RequisicaoWim getRequisicao() {
        return this.requisicao;
    }

    public void setRequisicao(RequisicaoWim var1) {
        this.requisicao = var1;
    }
}
