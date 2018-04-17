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
        name = "requisicaoWim",
        propOrder = {"id", "timeout", "tempoVida", "status", "percentual", "prioridade", "tempoConclusao", "dataHora", "pesquisas", "login", "senha", "tipo"}
)
public class RequisicaoWim {
    protected long id;
    protected long timeout;
    protected int tempoVida;
    protected int status;
    protected int percentual;
    protected int prioridade;
    protected long tempoConclusao;
    protected String dataHora;
    @XmlElement(
            nillable = true
    )
    protected List<PesquisaWim> pesquisas;
    protected String login;
    protected String senha;
    protected int tipo;

    public RequisicaoWim() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long var1) {
        this.id = var1;
    }

    public long getTimeout() {
        return this.timeout;
    }

    public void setTimeout(long var1) {
        this.timeout = var1;
    }

    public int getTempoVida() {
        return this.tempoVida;
    }

    public void setTempoVida(int var1) {
        this.tempoVida = var1;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int var1) {
        this.status = var1;
    }

    public int getPercentual() {
        return this.percentual;
    }

    public void setPercentual(int var1) {
        this.percentual = var1;
    }

    public int getPrioridade() {
        return this.prioridade;
    }

    public void setPrioridade(int var1) {
        this.prioridade = var1;
    }

    public long getTempoConclusao() {
        return this.tempoConclusao;
    }

    public void setTempoConclusao(long var1) {
        this.tempoConclusao = var1;
    }

    public String getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(String var1) {
        this.dataHora = var1;
    }

    public List<PesquisaWim> getPesquisas() {
        if (this.pesquisas == null) {
            this.pesquisas = new ArrayList();
        }

        return this.pesquisas;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String var1) {
        this.login = var1;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String var1) {
        this.senha = var1;
    }

    public int getTipo() {
        return this.tipo;
    }

    public void setTipo(int var1) {
        this.tipo = var1;
    }
}
