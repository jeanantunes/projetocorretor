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
        name = "getPercentualRequisicao",
        propOrder = {"idRequisicao"}
)
public class GetPercentualRequisicao {
    protected long idRequisicao;

    public GetPercentualRequisicao() {
    }

    public long getIdRequisicao() {
        return this.idRequisicao;
    }

    public void setIdRequisicao(long var1) {
        this.idRequisicao = var1;
    }
}
