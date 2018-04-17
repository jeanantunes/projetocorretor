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
        name = "RetornoPF",
        propOrder = {"cpf", "nome", "endereco", "telefone", "dataNascimento", "sexo", "nomeMae", "situacaoCadastral", "renda", "triagemRisco", "atividadeConsumo", "socioEmpresa", "profissao", "estadoCivil", "escolaridade", "bolsaFamilia", "classeSocial", "mosaic", "afinidadeCartaoCredito", "afinidadeCreditoPessoal", "afinidadeArtigosLuxo", "afinidadePacotesTurismo", "afinidadeCelularPosPago", "afinidadeImobiliario", "afinidadeTvAssinatura", "afinidadeBandaLarga", "afinidadeEcommerce", "afinidadeSmartphone", "ccf", "codigoIbge", "facesClasseMedia", "latitudeLongitude", "nis", "rg", "servidoresPublicosFederal", "houseHoldID", "houseHoldRenda", "houseHoldQtdPessoa", "representanteLegal", "pacote", "flagObito"}
)
public class RetornoPF {
    protected Boolean cpf;
    protected Boolean nome;
    protected Boolean endereco;
    protected Boolean telefone;
    protected Boolean dataNascimento;
    protected Boolean sexo;
    protected Boolean nomeMae;
    @XmlSchemaType(
            name = "string"
    )
    protected TipoConsulta situacaoCadastral;
    protected Boolean renda;
    protected Boolean triagemRisco;
    protected Boolean atividadeConsumo;
    protected Boolean socioEmpresa;
    protected Boolean profissao;
    protected Boolean estadoCivil;
    protected Boolean escolaridade;
    protected Boolean bolsaFamilia;
    protected Boolean classeSocial;
    protected Boolean mosaic;
    protected Boolean afinidadeCartaoCredito;
    protected Boolean afinidadeCreditoPessoal;
    protected Boolean afinidadeArtigosLuxo;
    protected Boolean afinidadePacotesTurismo;
    protected Boolean afinidadeCelularPosPago;
    protected Boolean afinidadeImobiliario;
    protected Boolean afinidadeTvAssinatura;
    protected Boolean afinidadeBandaLarga;
    protected Boolean afinidadeEcommerce;
    @XmlElement(
            name = "AfinidadeSmartphone"
    )
    protected Boolean afinidadeSmartphone;
    protected Boolean ccf;
    protected Boolean codigoIbge;
    protected Boolean facesClasseMedia;
    protected Boolean latitudeLongitude;
    protected Boolean nis;
    protected Boolean rg;
    protected Boolean servidoresPublicosFederal;
    protected Boolean houseHoldID;
    protected Boolean houseHoldRenda;
    protected Boolean houseHoldQtdPessoa;
    protected Boolean representanteLegal;
    @XmlElement(
            name = "Pacote"
    )
    @XmlSchemaType(
            name = "string"
    )
    protected Pacote pacote;
    protected Boolean flagObito;

    public RetornoPF() {
    }

    public Boolean isCpf() {
        return this.cpf;
    }

    public void setCpf(Boolean var1) {
        this.cpf = var1;
    }

    public Boolean isNome() {
        return this.nome;
    }

    public void setNome(Boolean var1) {
        this.nome = var1;
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

    public Boolean isDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(Boolean var1) {
        this.dataNascimento = var1;
    }

    public Boolean isSexo() {
        return this.sexo;
    }

    public void setSexo(Boolean var1) {
        this.sexo = var1;
    }

    public Boolean isNomeMae() {
        return this.nomeMae;
    }

    public void setNomeMae(Boolean var1) {
        this.nomeMae = var1;
    }

    public TipoConsulta getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

    public void setSituacaoCadastral(TipoConsulta var1) {
        this.situacaoCadastral = var1;
    }

    public Boolean isRenda() {
        return this.renda;
    }

    public void setRenda(Boolean var1) {
        this.renda = var1;
    }

    public Boolean isTriagemRisco() {
        return this.triagemRisco;
    }

    public void setTriagemRisco(Boolean var1) {
        this.triagemRisco = var1;
    }

    public Boolean isAtividadeConsumo() {
        return this.atividadeConsumo;
    }

    public void setAtividadeConsumo(Boolean var1) {
        this.atividadeConsumo = var1;
    }

    public Boolean isSocioEmpresa() {
        return this.socioEmpresa;
    }

    public void setSocioEmpresa(Boolean var1) {
        this.socioEmpresa = var1;
    }

    public Boolean isProfissao() {
        return this.profissao;
    }

    public void setProfissao(Boolean var1) {
        this.profissao = var1;
    }

    public Boolean isEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(Boolean var1) {
        this.estadoCivil = var1;
    }

    public Boolean isEscolaridade() {
        return this.escolaridade;
    }

    public void setEscolaridade(Boolean var1) {
        this.escolaridade = var1;
    }

    public Boolean isBolsaFamilia() {
        return this.bolsaFamilia;
    }

    public void setBolsaFamilia(Boolean var1) {
        this.bolsaFamilia = var1;
    }

    public Boolean isClasseSocial() {
        return this.classeSocial;
    }

    public void setClasseSocial(Boolean var1) {
        this.classeSocial = var1;
    }

    public Boolean isMosaic() {
        return this.mosaic;
    }

    public void setMosaic(Boolean var1) {
        this.mosaic = var1;
    }

    public Boolean isAfinidadeCartaoCredito() {
        return this.afinidadeCartaoCredito;
    }

    public void setAfinidadeCartaoCredito(Boolean var1) {
        this.afinidadeCartaoCredito = var1;
    }

    public Boolean isAfinidadeCreditoPessoal() {
        return this.afinidadeCreditoPessoal;
    }

    public void setAfinidadeCreditoPessoal(Boolean var1) {
        this.afinidadeCreditoPessoal = var1;
    }

    public Boolean isAfinidadeArtigosLuxo() {
        return this.afinidadeArtigosLuxo;
    }

    public void setAfinidadeArtigosLuxo(Boolean var1) {
        this.afinidadeArtigosLuxo = var1;
    }

    public Boolean isAfinidadePacotesTurismo() {
        return this.afinidadePacotesTurismo;
    }

    public void setAfinidadePacotesTurismo(Boolean var1) {
        this.afinidadePacotesTurismo = var1;
    }

    public Boolean isAfinidadeCelularPosPago() {
        return this.afinidadeCelularPosPago;
    }

    public void setAfinidadeCelularPosPago(Boolean var1) {
        this.afinidadeCelularPosPago = var1;
    }

    public Boolean isAfinidadeImobiliario() {
        return this.afinidadeImobiliario;
    }

    public void setAfinidadeImobiliario(Boolean var1) {
        this.afinidadeImobiliario = var1;
    }

    public Boolean isAfinidadeTvAssinatura() {
        return this.afinidadeTvAssinatura;
    }

    public void setAfinidadeTvAssinatura(Boolean var1) {
        this.afinidadeTvAssinatura = var1;
    }

    public Boolean isAfinidadeBandaLarga() {
        return this.afinidadeBandaLarga;
    }

    public void setAfinidadeBandaLarga(Boolean var1) {
        this.afinidadeBandaLarga = var1;
    }

    public Boolean isAfinidadeEcommerce() {
        return this.afinidadeEcommerce;
    }

    public void setAfinidadeEcommerce(Boolean var1) {
        this.afinidadeEcommerce = var1;
    }

    public Boolean isAfinidadeSmartphone() {
        return this.afinidadeSmartphone;
    }

    public void setAfinidadeSmartphone(Boolean var1) {
        this.afinidadeSmartphone = var1;
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

    public Boolean isFacesClasseMedia() {
        return this.facesClasseMedia;
    }

    public void setFacesClasseMedia(Boolean var1) {
        this.facesClasseMedia = var1;
    }

    public Boolean isLatitudeLongitude() {
        return this.latitudeLongitude;
    }

    public void setLatitudeLongitude(Boolean var1) {
        this.latitudeLongitude = var1;
    }

    public Boolean isNis() {
        return this.nis;
    }

    public void setNis(Boolean var1) {
        this.nis = var1;
    }

    public Boolean isRg() {
        return this.rg;
    }

    public void setRg(Boolean var1) {
        this.rg = var1;
    }

    public Boolean isServidoresPublicosFederal() {
        return this.servidoresPublicosFederal;
    }

    public void setServidoresPublicosFederal(Boolean var1) {
        this.servidoresPublicosFederal = var1;
    }

    public Boolean isHouseHoldID() {
        return this.houseHoldID;
    }

    public void setHouseHoldID(Boolean var1) {
        this.houseHoldID = var1;
    }

    public Boolean isHouseHoldRenda() {
        return this.houseHoldRenda;
    }

    public void setHouseHoldRenda(Boolean var1) {
        this.houseHoldRenda = var1;
    }

    public Boolean isHouseHoldQtdPessoa() {
        return this.houseHoldQtdPessoa;
    }

    public void setHouseHoldQtdPessoa(Boolean var1) {
        this.houseHoldQtdPessoa = var1;
    }

    public Boolean isRepresentanteLegal() {
        return this.representanteLegal;
    }

    public void setRepresentanteLegal(Boolean var1) {
        this.representanteLegal = var1;
    }

    public Pacote getPacote() {
        return this.pacote;
    }

    public void setPacote(Pacote var1) {
        this.pacote = var1;
    }

    public Boolean isFlagObito() {
        return this.flagObito;
    }

    public void setFlagObito(Boolean var1) {
        this.flagObito = var1;
    }
}
