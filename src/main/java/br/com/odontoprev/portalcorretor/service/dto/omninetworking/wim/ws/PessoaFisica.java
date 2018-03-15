//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "PessoaFisica",
        propOrder = {"cpf", "nome", "dataNascimento", "idade", "signo", "sexo", "nomeMae", "situacaoCadastral", "renda", "triagemRisco", "atividadeConsumo", "sociosEmpresa", "profissao", "estadoCivil", "escolaridade", "bolsaFamilia", "mosaic", "mosaicGrupo", "mosaicDescricao", "classeSocial", "afinidadeCartaoCredito", "afinidadeCreditoPessoal", "afinidadeArtigosLuxo", "afinidadePacotesTurismo", "afinidadeCelularPosPago", "afinidadeImobiliario", "afinidadeTvAssinatura", "afinidadeBandaLarga", "afinidadeEcommerce", "afinidadeClientePremium", "afinidadeSmartphone", "ccf", "codIbge", "facesClasseMedia", "latitude", "longitude", "nis", "rg", "servidorPublicoFederal", "houseHoldID", "houseHoldRenda", "houseHoldQtdPessoa", "representanteLegal", "inibir", "mensagem", "flagObito"}
)
public class PessoaFisica extends Pessoa {
    protected String cpf;
    protected String nome;
    @XmlSchemaType(
            name = "dateTime"
    )
    protected XMLGregorianCalendar dataNascimento;
    protected Integer idade;
    protected String signo;
    protected String sexo;
    protected String nomeMae;
    protected SituacaoCadastralPessoaFisica situacaoCadastral;
    protected String renda;
    protected String triagemRisco;
    protected AtividadeConsumo atividadeConsumo;
    protected ArrayOfSocioEmpresa sociosEmpresa;
    protected String profissao;
    protected String estadoCivil;
    protected String escolaridade;
    protected Boolean bolsaFamilia;
    protected String mosaic;
    protected String mosaicGrupo;
    protected String mosaicDescricao;
    protected String classeSocial;
    protected Boolean afinidadeCartaoCredito;
    protected Boolean afinidadeCreditoPessoal;
    protected Boolean afinidadeArtigosLuxo;
    protected Boolean afinidadePacotesTurismo;
    protected Boolean afinidadeCelularPosPago;
    protected Boolean afinidadeImobiliario;
    protected Boolean afinidadeTvAssinatura;
    protected Boolean afinidadeBandaLarga;
    protected Boolean afinidadeEcommerce;
    protected Boolean afinidadeClientePremium;
    protected Boolean afinidadeSmartphone;
    protected Ccf ccf;
    protected CodIbge codIbge;
    protected String facesClasseMedia;
    protected String latitude;
    protected String longitude;
    protected String nis;
    protected String rg;
    protected Boolean servidorPublicoFederal;
    protected String houseHoldID;
    protected String houseHoldRenda;
    protected String houseHoldQtdPessoa;
    protected ArrayOfRepresentanteLegal representanteLegal;
    protected Integer inibir;
    protected String mensagem;
    protected String flagObito;

    public PessoaFisica() {
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String var1) {
        this.cpf = var1;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String var1) {
        this.nome = var1;
    }

    public XMLGregorianCalendar getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(XMLGregorianCalendar var1) {
        this.dataNascimento = var1;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public void setIdade(Integer var1) {
        this.idade = var1;
    }

    public String getSigno() {
        return this.signo;
    }

    public void setSigno(String var1) {
        this.signo = var1;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String var1) {
        this.sexo = var1;
    }

    public String getNomeMae() {
        return this.nomeMae;
    }

    public void setNomeMae(String var1) {
        this.nomeMae = var1;
    }

    public SituacaoCadastralPessoaFisica getSituacaoCadastral() {
        return this.situacaoCadastral;
    }

    public void setSituacaoCadastral(SituacaoCadastralPessoaFisica var1) {
        this.situacaoCadastral = var1;
    }

    public String getRenda() {
        return this.renda;
    }

    public void setRenda(String var1) {
        this.renda = var1;
    }

    public String getTriagemRisco() {
        return this.triagemRisco;
    }

    public void setTriagemRisco(String var1) {
        this.triagemRisco = var1;
    }

    public AtividadeConsumo getAtividadeConsumo() {
        return this.atividadeConsumo;
    }

    public void setAtividadeConsumo(AtividadeConsumo var1) {
        this.atividadeConsumo = var1;
    }

    public ArrayOfSocioEmpresa getSociosEmpresa() {
        return this.sociosEmpresa;
    }

    public void setSociosEmpresa(ArrayOfSocioEmpresa var1) {
        this.sociosEmpresa = var1;
    }

    public String getProfissao() {
        return this.profissao;
    }

    public void setProfissao(String var1) {
        this.profissao = var1;
    }

    public String getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(String var1) {
        this.estadoCivil = var1;
    }

    public String getEscolaridade() {
        return this.escolaridade;
    }

    public void setEscolaridade(String var1) {
        this.escolaridade = var1;
    }

    public Boolean isBolsaFamilia() {
        return this.bolsaFamilia;
    }

    public void setBolsaFamilia(Boolean var1) {
        this.bolsaFamilia = var1;
    }

    public String getMosaic() {
        return this.mosaic;
    }

    public void setMosaic(String var1) {
        this.mosaic = var1;
    }

    public String getMosaicGrupo() {
        return this.mosaicGrupo;
    }

    public void setMosaicGrupo(String var1) {
        this.mosaicGrupo = var1;
    }

    public String getMosaicDescricao() {
        return this.mosaicDescricao;
    }

    public void setMosaicDescricao(String var1) {
        this.mosaicDescricao = var1;
    }

    public String getClasseSocial() {
        return this.classeSocial;
    }

    public void setClasseSocial(String var1) {
        this.classeSocial = var1;
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

    public Boolean isAfinidadeClientePremium() {
        return this.afinidadeClientePremium;
    }

    public void setAfinidadeClientePremium(Boolean var1) {
        this.afinidadeClientePremium = var1;
    }

    public Boolean isAfinidadeSmartphone() {
        return this.afinidadeSmartphone;
    }

    public void setAfinidadeSmartphone(Boolean var1) {
        this.afinidadeSmartphone = var1;
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

    public String getFacesClasseMedia() {
        return this.facesClasseMedia;
    }

    public void setFacesClasseMedia(String var1) {
        this.facesClasseMedia = var1;
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

    public String getNis() {
        return this.nis;
    }

    public void setNis(String var1) {
        this.nis = var1;
    }

    public String getRg() {
        return this.rg;
    }

    public void setRg(String var1) {
        this.rg = var1;
    }

    public Boolean isServidorPublicoFederal() {
        return this.servidorPublicoFederal;
    }

    public void setServidorPublicoFederal(Boolean var1) {
        this.servidorPublicoFederal = var1;
    }

    public String getHouseHoldID() {
        return this.houseHoldID;
    }

    public void setHouseHoldID(String var1) {
        this.houseHoldID = var1;
    }

    public String getHouseHoldRenda() {
        return this.houseHoldRenda;
    }

    public void setHouseHoldRenda(String var1) {
        this.houseHoldRenda = var1;
    }

    public String getHouseHoldQtdPessoa() {
        return this.houseHoldQtdPessoa;
    }

    public void setHouseHoldQtdPessoa(String var1) {
        this.houseHoldQtdPessoa = var1;
    }

    public ArrayOfRepresentanteLegal getRepresentanteLegal() {
        return this.representanteLegal;
    }

    public void setRepresentanteLegal(ArrayOfRepresentanteLegal var1) {
        this.representanteLegal = var1;
    }

    public Integer getInibir() {
        return this.inibir;
    }

    public void setInibir(Integer var1) {
        this.inibir = var1;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public void setMensagem(String var1) {
        this.mensagem = var1;
    }

    public String getFlagObito() {
        return this.flagObito;
    }

    public void setFlagObito(String var1) {
        this.flagObito = var1;
    }
}
