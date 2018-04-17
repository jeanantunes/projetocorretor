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
        name = "reqInfo",
        propOrder = {"concluidas", "id", "nomePesquisaPrincipal", "pesquisas", "sucessos"}
)
public class ReqInfo {
    protected int concluidas;
    protected long id;
    protected String nomePesquisaPrincipal;
    protected int pesquisas;
    protected int sucessos;

    public ReqInfo() {
    }

    public int getConcluidas() {
        return this.concluidas;
    }

    public void setConcluidas(int var1) {
        this.concluidas = var1;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long var1) {
        this.id = var1;
    }

    public String getNomePesquisaPrincipal() {
        return this.nomePesquisaPrincipal;
    }

    public void setNomePesquisaPrincipal(String var1) {
        this.nomePesquisaPrincipal = var1;
    }

    public int getPesquisas() {
        return this.pesquisas;
    }

    public void setPesquisas(int var1) {
        this.pesquisas = var1;
    }

    public int getSucessos() {
        return this.sucessos;
    }

    public void setSucessos(int var1) {
        this.sucessos = var1;
    }
}
