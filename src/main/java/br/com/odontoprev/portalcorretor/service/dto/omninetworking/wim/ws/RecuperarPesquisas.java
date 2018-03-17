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
        name = "recuperarPesquisas",
        propOrder = {"dataInicial", "dataFinal", "idPesquisaInicial", "fontePesquisa", "statusPesquisa"}
)
public class RecuperarPesquisas {
    protected String dataInicial;
    protected String dataFinal;
    protected int idPesquisaInicial;
    protected String fontePesquisa;
    protected int statusPesquisa;

    public RecuperarPesquisas() {
    }

    public String getDataInicial() {
        return this.dataInicial;
    }

    public void setDataInicial(String var1) {
        this.dataInicial = var1;
    }

    public String getDataFinal() {
        return this.dataFinal;
    }

    public void setDataFinal(String var1) {
        this.dataFinal = var1;
    }

    public int getIdPesquisaInicial() {
        return this.idPesquisaInicial;
    }

    public void setIdPesquisaInicial(int var1) {
        this.idPesquisaInicial = var1;
    }

    public String getFontePesquisa() {
        return this.fontePesquisa;
    }

    public void setFontePesquisa(String var1) {
        this.fontePesquisa = var1;
    }

    public int getStatusPesquisa() {
        return this.statusPesquisa;
    }

    public void setStatusPesquisa(int var1) {
        this.statusPesquisa = var1;
    }
}
