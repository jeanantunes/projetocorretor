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
        name = "pesquisaWim",
        propOrder = {"id", "nome", "fonte", "validade", "mensagemStatus", "status", "dataHora", "foiUtilizadoCache", "permiteWIMDB", "tempoPesquisa", "camposEntrada", "camposResposta", "binarios"}
)
public class PesquisaWim {
    protected long id;
    protected String nome;
    protected String fonte;
    protected int validade;
    protected String mensagemStatus;
    protected int status;
    protected String dataHora;
    protected boolean foiUtilizadoCache;
    protected boolean permiteWIMDB;
    protected long tempoPesquisa;
    @XmlElement(
            nillable = true
    )
    protected List<CamposEntrada> camposEntrada;
    @XmlElement(
            nillable = true
    )
    protected List<CamposResposta> camposResposta;
    @XmlElement(
            nillable = true
    )
    protected List<BinarioWim> binarios;

    public PesquisaWim() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long var1) {
        this.id = var1;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String var1) {
        this.nome = var1;
    }

    public String getFonte() {
        return this.fonte;
    }

    public void setFonte(String var1) {
        this.fonte = var1;
    }

    public int getValidade() {
        return this.validade;
    }

    public void setValidade(int var1) {
        this.validade = var1;
    }

    public String getMensagemStatus() {
        return this.mensagemStatus;
    }

    public void setMensagemStatus(String var1) {
        this.mensagemStatus = var1;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int var1) {
        this.status = var1;
    }

    public String getDataHora() {
        return this.dataHora;
    }

    public void setDataHora(String var1) {
        this.dataHora = var1;
    }

    public boolean isFoiUtilizadoCache() {
        return this.foiUtilizadoCache;
    }

    public void setFoiUtilizadoCache(boolean var1) {
        this.foiUtilizadoCache = var1;
    }

    public boolean isPermiteWIMDB() {
        return this.permiteWIMDB;
    }

    public void setPermiteWIMDB(boolean var1) {
        this.permiteWIMDB = var1;
    }

    public long getTempoPesquisa() {
        return this.tempoPesquisa;
    }

    public void setTempoPesquisa(long var1) {
        this.tempoPesquisa = var1;
    }

    public List<CamposEntrada> getCamposEntrada() {
        if (this.camposEntrada == null) {
            this.camposEntrada = new ArrayList();
        }

        return this.camposEntrada;
    }

    public List<CamposResposta> getCamposResposta() {
        if (this.camposResposta == null) {
            this.camposResposta = new ArrayList();
        }

        return this.camposResposta;
    }

    public List<BinarioWim> getBinarios() {
        if (this.binarios == null) {
            this.binarios = new ArrayList();
        }

        return this.binarios;
    }
}
