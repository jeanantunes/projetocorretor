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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "RetornoPJ",
        propOrder = {"cnpj", "razaoSocial", "nomeFantasia", "capitalSocial", "dataAbertura", "naturezaJuridica", "cnae", "endereco", "telefone", "situacaoCadastral", "porte", "quadroSocial", "representanteLegal", "sintegra", "faturamento", "funcionarios", "indicadorOperacionalidade", "ccf", "codigoIbge", "simplesNacional", "triagemRisco", "matrizFilial", "latitudeLongitude", "suframa", "pacote", "mosaic"}
)
public class RetornoPJ {
    protected Boolean cnpj;
    protected Boolean razaoSocial;
    protected Boolean nomeFantasia;
    protected Boolean capitalSocial;
    protected Boolean dataAbertura;
    protected Boolean naturezaJuridica;
    protected Boolean cnae;
    protected Boolean endereco;
    protected Boolean telefone;
    @XmlSchemaType(
            name = "string"
    )
    protected TipoConsulta situacaoCadastral;
    protected Boolean porte;
    protected Boolean quadroSocial;
    protected Boolean representanteLegal;
    @XmlSchemaType(
            name = "string"
    )
    protected TipoConsulta sintegra;
    protected Boolean faturamento;
    protected Boolean funcionarios;
    protected Boolean indicadorOperacionalidade;
    protected Boolean ccf;
    protected Boolean codigoIbge;
    @XmlSchemaType(
            name = "string"
    )
    protected TipoConsulta simplesNacional;
    protected Boolean triagemRisco;
    protected Boolean matrizFilial;
    protected Boolean latitudeLongitude;
    protected Boolean suframa;
    @XmlElement(
            name = "Pacote"
    )
    @XmlSchemaType(
            name = "string"
    )
    protected Pacote pacote;
    protected Boolean mosaic;

    public RetornoPJ() {
    }

    public Boolean isCnpj() {
        return this.cnpj;
    }

    public void setCnpj(Boolean var1) {
        this.cnpj = var1;
    }

    public Boolean isRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(Boolean var1) {
        this.razaoSocial = var1;
    }

    public Boolean isNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(Boolean var1) {
        this.nomeFantasia = var1;
    }

    public Boolean isCapitalSocial() {
        return this.capitalSocial;
    }

    public void setCapitalSocial(Boolean var1) {
        this.capitalSocial = var1;
    }

    public Boolean isDataAbertura() {
        return this.dataAbertura;
    }

    public void setDataAbertura(Boolean var1) {
        this.dataAbertura = var1;
    }

    public Boolean isNaturezaJuridica() {
        return this.naturezaJuridica;
    }

    public void setNaturezaJuridica(Boolean var1) {
        this.naturezaJuridica = var1;
    }

    public Boolean isCnae() {
        return this.cnae;
    }

    public void setCnae(Boolean var1) {
        this.cnae = var1;
    }

    public Boolean isEndereco() {
        return this.endereco;
    }

    public void setEndereco(Boolean var1) {
        this.endereco = var1;
    }

    public Boolean isTelefone() {
        return this.telefone;
    }

    public void setTelefone(Boolean var1) {
        this.telefone = var1;
    }

    public TipoConsulta getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

    public void setSituacaoCadastral(TipoConsulta var1) {
        this.situacaoCadastral = var1;
    }

    public Boolean isPorte() {
        return this.porte;
    }

    public void setPorte(Boolean var1) {
        this.porte = var1;
    }

    public Boolean isQuadroSocial() {
        return this.quadroSocial;
    }

    public void setQuadroSocial(Boolean var1) {
        this.quadroSocial = var1;
    }

    public Boolean isRepresentanteLegal() {
        return this.representanteLegal;
    }

    public void setRepresentanteLegal(Boolean var1) {
        this.representanteLegal = var1;
    }

    public TipoConsulta getSintegra() {
        return this.sintegra;
    }

    public void setSintegra(TipoConsulta var1) {
        this.sintegra = var1;
    }

    public Boolean isFaturamento() {
        return this.faturamento;
    }

    public void setFaturamento(Boolean var1) {
        this.faturamento = var1;
    }

    public Boolean isFuncionarios() {
        return this.funcionarios;
    }

    public void setFuncionarios(Boolean var1) {
        this.funcionarios = var1;
    }

    public Boolean isIndicadorOperacionalidade() {
        return this.indicadorOperacionalidade;
    }

    public void setIndicadorOperacionalidade(Boolean var1) {
        this.indicadorOperacionalidade = var1;
    }

    public Boolean isCcf() {
        return this.ccf;
    }

    public void setCcf(Boolean var1) {
        this.ccf = var1;
    }

    public Boolean isCodigoIbge() {
        return this.codigoIbge;
    }

    public void setCodigoIbge(Boolean var1) {
        this.codigoIbge = var1;
    }

    public TipoConsulta getSimplesNacional() {
        return this.simplesNacional;
    }

    public void setSimplesNacional(TipoConsulta var1) {
        this.simplesNacional = var1;
    }

    public Boolean isTriagemRisco() {
        return this.triagemRisco;
    }

    public void setTriagemRisco(Boolean var1) {
        this.triagemRisco = var1;
    }

    public Boolean isMatrizFilial() {
        return this.matrizFilial;
    }

    public void setMatrizFilial(Boolean var1) {
        this.matrizFilial = var1;
    }

    public Boolean isLatitudeLongitude() {
        return this.latitudeLongitude;
    }

    public void setLatitudeLongitude(Boolean var1) {
        this.latitudeLongitude = var1;
    }

    public Boolean isSuframa() {
        return this.suframa;
    }

    public void setSuframa(Boolean var1) {
        this.suframa = var1;
    }

    public Pacote getPacote() {
        return this.pacote;
    }

    public void setPacote(Pacote var1) {
        this.pacote = var1;
    }

    public Boolean isMosaic() {
        return this.mosaic;
    }

    public void setMosaic(Boolean var1) {
        this.mosaic = var1;
    }
}
