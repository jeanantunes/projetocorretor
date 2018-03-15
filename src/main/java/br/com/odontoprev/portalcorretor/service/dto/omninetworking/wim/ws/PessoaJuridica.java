//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "PessoaJuridica",
        propOrder = {"cnpj", "razaoSocial", "nomeFantasia", "capitalSocial", "dataAbertura", "naturezaJuridica", "cnae", "situacaoCadastral", "porte", "quadroSocial", "representanteLegal", "sintegra", "faturamento", "funcionarios", "indicadorOperacionalidade", "ccf", "codIbge", "simplesNacional", "triagemDeRisco", "matrizFilial", "latitude", "longitude", "listaSuframa", "mosaic"}
)
public class PessoaJuridica extends Pessoa {
    protected String cnpj;
    protected String razaoSocial;
    protected String nomeFantasia;
    protected String capitalSocial;
    @XmlSchemaType(
            name = "dateTime"
    )
    protected XMLGregorianCalendar dataAbertura;
    protected NaturezaJuridica naturezaJuridica;
    protected ArrayOfCnae cnae;
    protected SituacaoCadastralPessoaJuridica situacaoCadastral;
    protected String porte;
    protected ArrayOfQuadroSocial quadroSocial;
    protected ArrayOfRepresentanteLegal representanteLegal;
    protected ArrayOfSintegra sintegra;
    protected String faturamento;
    protected String funcionarios;
    protected String indicadorOperacionalidade;
    protected Ccf ccf;
    protected CodIbge codIbge;
    protected SimplesNacional simplesNacional;
    protected String triagemDeRisco;
    protected String matrizFilial;
    protected String latitude;
    protected String longitude;
    @XmlElement(
            name = "ListaSuframa"
    )
    protected ArrayOfSuframa listaSuframa;
    protected Mosaic mosaic;

    public PessoaJuridica() {
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String var1) {
        this.cnpj = var1;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String var1) {
        this.razaoSocial = var1;
    }

    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(String var1) {
        this.nomeFantasia = var1;
    }

    public String getCapitalSocial() {
        return this.capitalSocial;
    }

    public void setCapitalSocial(String var1) {
        this.capitalSocial = var1;
    }

    public XMLGregorianCalendar getDataAbertura() {
        return this.dataAbertura;
    }

    public void setDataAbertura(XMLGregorianCalendar var1) {
        this.dataAbertura = var1;
    }

    public NaturezaJuridica getNaturezaJuridica() {
        return this.naturezaJuridica;
    }

    public void setNaturezaJuridica(NaturezaJuridica var1) {
        this.naturezaJuridica = var1;
    }

    public ArrayOfCnae getCnae() {
        return this.cnae;
    }

    public void setCnae(ArrayOfCnae var1) {
        this.cnae = var1;
    }

    public SituacaoCadastralPessoaJuridica getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

    public void setSituacaoCadastral(SituacaoCadastralPessoaJuridica var1) {
        this.situacaoCadastral = var1;
    }

    public String getPorte() {
        return this.porte;
    }

    public void setPorte(String var1) {
        this.porte = var1;
    }

    public ArrayOfQuadroSocial getQuadroSocial() {
        return this.quadroSocial;
    }

    public void setQuadroSocial(ArrayOfQuadroSocial var1) {
        this.quadroSocial = var1;
    }

    public ArrayOfRepresentanteLegal getRepresentanteLegal() {
        return this.representanteLegal;
    }

    public void setRepresentanteLegal(ArrayOfRepresentanteLegal var1) {
        this.representanteLegal = var1;
    }

    public ArrayOfSintegra getSintegra() {
        return this.sintegra;
    }

    public void setSintegra(ArrayOfSintegra var1) {
        this.sintegra = var1;
    }

    public String getFaturamento() {
        return this.faturamento;
    }

    public void setFaturamento(String var1) {
        this.faturamento = var1;
    }

    public String getFuncionarios() {
        return this.funcionarios;
    }

    public void setFuncionarios(String var1) {
        this.funcionarios = var1;
    }

    public String getIndicadorOperacionalidade() {
        return this.indicadorOperacionalidade;
    }

    public void setIndicadorOperacionalidade(String var1) {
        this.indicadorOperacionalidade = var1;
    }

    public Ccf getCcf() {
        return this.ccf;
    }

    public void setCcf(Ccf var1) {
        this.ccf = var1;
    }

    public CodIbge getCodIbge() {
        return this.codIbge;
    }

    public void setCodIbge(CodIbge var1) {
        this.codIbge = var1;
    }

    public SimplesNacional getSimplesNacional() {
        return this.simplesNacional;
    }

    public void setSimplesNacional(SimplesNacional var1) {
        this.simplesNacional = var1;
    }

    public String getTriagemDeRisco() {
        return this.triagemDeRisco;
    }

    public void setTriagemDeRisco(String var1) {
        this.triagemDeRisco = var1;
    }

    public String getMatrizFilial() {
        return this.matrizFilial;
    }

    public void setMatrizFilial(String var1) {
        this.matrizFilial = var1;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String var1) {
        this.latitude = var1;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String var1) {
        this.longitude = var1;
    }

    public ArrayOfSuframa getListaSuframa() {
        return this.listaSuframa;
    }

    public void setListaSuframa(ArrayOfSuframa var1) {
        this.listaSuframa = var1;
    }

    public Mosaic getMosaic() {
        return this.mosaic;
    }

    public void setMosaic(Mosaic var1) {
        this.mosaic = var1;
    }
}
