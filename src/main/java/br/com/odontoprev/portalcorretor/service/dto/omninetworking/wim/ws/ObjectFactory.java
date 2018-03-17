package br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private static final QName _GetSiteErrorList_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getSiteErrorList");
    private static final QName _GetReqs_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getReqs");
    private static final QName _RecuperarRequisicaoResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "recuperarRequisicaoResponse");
    private static final QName _EnviarRequisicaoResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "enviarRequisicaoResponse");
    private static final QName _GetPercentualRequisicao_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getPercentualRequisicao");
    private static final QName _GetPercentualRequisicaoResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getPercentualRequisicaoResponse");
    private static final QName _RequisicaoWim_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "requisicaoWim");
    private static final QName _CancelarRequisicao_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "cancelarRequisicao");
    private static final QName _PesquisarUnicaResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "pesquisarUnicaResponse");
    private static final QName _GetStatusReqsResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getStatusReqsResponse");
    private static final QName _PesquisarUnica_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "pesquisarUnica");
    private static final QName _WimFault_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "WimFault");
    private static final QName _GetStatusReqs_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getStatusReqs");
    private static final QName _PesquisarResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "pesquisarResponse");
    private static final QName _RecuperarPesquisas_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "recuperarPesquisas");
    private static final QName _RecuperarRequisicao_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "recuperarRequisicao");
    private static final QName _GetReqsResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getReqsResponse");
    private static final QName _ReprocessarRequisicaoResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "reprocessarRequisicaoResponse");
    private static final QName _GetStatusListResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getStatusListResponse");
    private static final QName _GetStatusList_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getStatusList");
    private static final QName _ReprocessarRequisicao_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "reprocessarRequisicao");
    private static final QName _GetSiteErrorListResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "getSiteErrorListResponse");
    private static final QName _EnviarRequisicao_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "enviarRequisicao");
    private static final QName _CancelarRequisicaoResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "cancelarRequisicaoResponse");
    private static final QName _RecuperarPesquisasResponse_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "recuperarPesquisasResponse");
    private static final QName _Pesquisar_QNAME = new QName("http://ws.wim.omninetworking.com.br/", "pesquisar");

    public ObjectFactory() {
    }

    public PesquisaWim createPesquisaWim() {
        return new PesquisaWim();
    }

    public PesquisarUnica createPesquisarUnica() {
        return new PesquisarUnica();
    }

    public CancelarRequisicao createCancelarRequisicao() {
        return new CancelarRequisicao();
    }

    public Response createPesquisarUnicaResponse() {
        return new Response();
    }

    public RequisicaoWim createRequisicaoWim() {
        return new RequisicaoWim();
    }

    public GetPercentualRequisicaoResponse createGetPercentualRequisicaoResponse() {
        return new GetPercentualRequisicaoResponse();
    }

    public GetPercentualRequisicao createGetPercentualRequisicao() {
        return new GetPercentualRequisicao();
    }

    public EnviarRequisicaoResponse createEnviarRequisicaoResponse() {
        return new EnviarRequisicaoResponse();
    }

    public GetSiteErrorList createGetSiteErrorList() {
        return new GetSiteErrorList();
    }

    public GetReqs createGetReqs() {
        return new GetReqs();
    }

    public RecuperarRequisicaoResponse createRecuperarRequisicaoResponse() {
        return new RecuperarRequisicaoResponse();
    }

    public PesquisarResponse createPesquisarResponse() {
        return new PesquisarResponse();
    }

    public GetStatusReqs createGetStatusReqs() {
        return new GetStatusReqs();
    }

    public WimFault createWimFault() {
        return new WimFault();
    }

    public GetStatusReqsResponse createGetStatusReqsResponse() {
        return new GetStatusReqsResponse();
    }

    public ReprocessarRequisicao createReprocessarRequisicao() {
        return new ReprocessarRequisicao();
    }

    public GetStatusList createGetStatusList() {
        return new GetStatusList();
    }

    public GetStatusListResponse createGetStatusListResponse() {
        return new GetStatusListResponse();
    }

    public ReprocessarRequisicaoResponse createReprocessarRequisicaoResponse() {
        return new ReprocessarRequisicaoResponse();
    }

    public RecuperarPesquisas createRecuperarPesquisas() {
        return new RecuperarPesquisas();
    }

    public RecuperarRequisicao createRecuperarRequisicao() {
        return new RecuperarRequisicao();
    }

    public GetReqsResponse createGetReqsResponse() {
        return new GetReqsResponse();
    }

    public Pesquisar createPesquisar() {
        return new Pesquisar();
    }

    public RecuperarPesquisasResponse createRecuperarPesquisasResponse() {
        return new RecuperarPesquisasResponse();
    }

    public EnviarRequisicao createEnviarRequisicao() {
        return new EnviarRequisicao();
    }

    public GetSiteErrorListResponse createGetSiteErrorListResponse() {
        return new GetSiteErrorListResponse();
    }

    public CancelarRequisicaoResponse createCancelarRequisicaoResponse() {
        return new CancelarRequisicaoResponse();
    }

    public CcfOcorrencia createCcfOcorrencia() {
        return new CcfOcorrencia();
    }

    public RepresentanteLegal createRepresentanteLegal() {
        return new RepresentanteLegal();
    }

    public ArrayOfTelefone createArrayOfTelefone() {
        return new ArrayOfTelefone();
    }

    public ArrayOfSintegra createArrayOfSintegra() {
        return new ArrayOfSintegra();
    }

    public Mosaic createMosaic() {
        return new Mosaic();
    }

    public ArrayOfSuframa createArrayOfSuframa() {
        return new ArrayOfSuframa();
    }

    public QuadroSocial createQuadroSocial() {
        return new QuadroSocial();
    }

    public ArrayOfSocioEmpresa createArrayOfSocioEmpresa() {
        return new ArrayOfSocioEmpresa();
    }

    public ArrayOfQuadroSocial createArrayOfQuadroSocial() {
        return new ArrayOfQuadroSocial();
    }

    public ArrayOfCnae createArrayOfCnae() {
        return new ArrayOfCnae();
    }

    public SocioEmpresa createSocioEmpresa() {
        return new SocioEmpresa();
    }

    public ArrayOfRepresentanteLegal createArrayOfRepresentanteLegal() {
        return new ArrayOfRepresentanteLegal();
    }

    public SimplesNacional createSimplesNacional() {
        return new SimplesNacional();
    }

    public StatusProconTelefone createStatusProconTelefone() {
        return new StatusProconTelefone();
    }

    public Suframa createSuframa() {
        return new Suframa();
    }

    public NaturezaJuridica createNaturezaJuridica() {
        return new NaturezaJuridica();
    }

    public Sintegra createSintegra() {
        return new Sintegra();
    }

    public Logradouro createLogradouro() {
        return new Logradouro();
    }

    public AtividadeConsumo createAtividadeConsumo() {
        return new AtividadeConsumo();
    }

    public Telefone createTelefone() {
        return new Telefone();
    }

    public Cnae createCnae() {
        return new Cnae();
    }

    public PessoaJuridica createPessoaJuridica() {
        return new PessoaJuridica();
    }

    public ParametersInPJ createParametersInPJ() {
        return new ParametersInPJ();
    }

    public ReqInfo createReqInfo() {
        return new ReqInfo();
    }

    public PessoaFisica createPessoaFisica() {
        return new PessoaFisica();
    }

    public SituacaoCadastralPessoaJuridica createSituacaoCadastralPessoaJuridica() {
        return new SituacaoCadastralPessoaJuridica();
    }

    public ParametersInPF createParametersInPF() {
        return new ParametersInPF();
    }

    public CodIbge createCodIbge() {
        return new CodIbge();
    }

    public RetornoPF createRetornoPF() {
        return new RetornoPF();
    }

    public StatusProconTelefoneResponse createStatusProconTelefoneResponse() {
        return new StatusProconTelefoneResponse();
    }

    public RetornoPJ createRetornoPJ() {
        return new RetornoPJ();
    }

    public Ccf createCcf() {
        return new Ccf();
    }

    public ArrayOfEndereco createArrayOfEndereco() {
        return new ArrayOfEndereco();
    }

    public BinarioWim createBinarioWim() {
        return new BinarioWim();
    }

    public Endereco createEndereco() {
        return new Endereco();
    }

    public SituacaoCadastralPessoaFisica createSituacaoCadastralPessoaFisica() {
        return new SituacaoCadastralPessoaFisica();
    }

    public CamposEntrada createPesquisaWimCamposEntrada() {
        return new CamposEntrada();
    }

    public CamposResposta createPesquisaWimCamposResposta() {
        return new CamposResposta();
    }

    public Parametros createPesquisarUnicaParametros() {
        return new Parametros();
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getSiteErrorList"
    )
    public JAXBElement<GetSiteErrorList> createGetSiteErrorList(GetSiteErrorList var1) {
        return new JAXBElement(_GetSiteErrorList_QNAME, GetSiteErrorList.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getReqs"
    )
    public JAXBElement<GetReqs> createGetReqs(GetReqs var1) {
        return new JAXBElement(_GetReqs_QNAME, GetReqs.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "recuperarRequisicaoResponse"
    )
    public JAXBElement<RecuperarRequisicaoResponse> createRecuperarRequisicaoResponse(RecuperarRequisicaoResponse var1) {
        return new JAXBElement(_RecuperarRequisicaoResponse_QNAME, RecuperarRequisicaoResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "enviarRequisicaoResponse"
    )
    public JAXBElement<EnviarRequisicaoResponse> createEnviarRequisicaoResponse(EnviarRequisicaoResponse var1) {
        return new JAXBElement(_EnviarRequisicaoResponse_QNAME, EnviarRequisicaoResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getPercentualRequisicao"
    )
    public JAXBElement<GetPercentualRequisicao> createGetPercentualRequisicao(GetPercentualRequisicao var1) {
        return new JAXBElement(_GetPercentualRequisicao_QNAME, GetPercentualRequisicao.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getPercentualRequisicaoResponse"
    )
    public JAXBElement<GetPercentualRequisicaoResponse> createGetPercentualRequisicaoResponse(GetPercentualRequisicaoResponse var1) {
        return new JAXBElement(_GetPercentualRequisicaoResponse_QNAME, GetPercentualRequisicaoResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "requisicaoWim"
    )
    public JAXBElement<RequisicaoWim> createRequisicaoWim(RequisicaoWim var1) {
        return new JAXBElement(_RequisicaoWim_QNAME, RequisicaoWim.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "cancelarRequisicao"
    )
    public JAXBElement<CancelarRequisicao> createCancelarRequisicao(CancelarRequisicao var1) {
        return new JAXBElement(_CancelarRequisicao_QNAME, CancelarRequisicao.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "pesquisarUnicaResponse"
    )
    public JAXBElement<Response> createPesquisarUnicaResponse(Response var1) {
        return new JAXBElement(_PesquisarUnicaResponse_QNAME, Response.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getStatusReqsResponse"
    )
    public JAXBElement<GetStatusReqsResponse> createGetStatusReqsResponse(GetStatusReqsResponse var1) {
        return new JAXBElement(_GetStatusReqsResponse_QNAME, GetStatusReqsResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "pesquisarUnica"
    )
    public JAXBElement<PesquisarUnica> createPesquisarUnica(PesquisarUnica var1) {
        return new JAXBElement(_PesquisarUnica_QNAME, PesquisarUnica.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "WimFault"
    )
    public JAXBElement<WimFault> createWimFault(WimFault var1) {
        return new JAXBElement(_WimFault_QNAME, WimFault.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getStatusReqs"
    )
    public JAXBElement<GetStatusReqs> createGetStatusReqs(GetStatusReqs var1) {
        return new JAXBElement(_GetStatusReqs_QNAME, GetStatusReqs.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "pesquisarResponse"
    )
    public JAXBElement<PesquisarResponse> createPesquisarResponse(PesquisarResponse var1) {
        return new JAXBElement(_PesquisarResponse_QNAME, PesquisarResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "recuperarPesquisas"
    )
    public JAXBElement<RecuperarPesquisas> createRecuperarPesquisas(RecuperarPesquisas var1) {
        return new JAXBElement(_RecuperarPesquisas_QNAME, RecuperarPesquisas.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "recuperarRequisicao"
    )
    public JAXBElement<RecuperarRequisicao> createRecuperarRequisicao(RecuperarRequisicao var1) {
        return new JAXBElement(_RecuperarRequisicao_QNAME, RecuperarRequisicao.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getReqsResponse"
    )
    public JAXBElement<GetReqsResponse> createGetReqsResponse(GetReqsResponse var1) {
        return new JAXBElement(_GetReqsResponse_QNAME, GetReqsResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "reprocessarRequisicaoResponse"
    )
    public JAXBElement<ReprocessarRequisicaoResponse> createReprocessarRequisicaoResponse(ReprocessarRequisicaoResponse var1) {
        return new JAXBElement(_ReprocessarRequisicaoResponse_QNAME, ReprocessarRequisicaoResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getStatusListResponse"
    )
    public JAXBElement<GetStatusListResponse> createGetStatusListResponse(GetStatusListResponse var1) {
        return new JAXBElement(_GetStatusListResponse_QNAME, GetStatusListResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getStatusList"
    )
    public JAXBElement<GetStatusList> createGetStatusList(GetStatusList var1) {
        return new JAXBElement(_GetStatusList_QNAME, GetStatusList.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "reprocessarRequisicao"
    )
    public JAXBElement<ReprocessarRequisicao> createReprocessarRequisicao(ReprocessarRequisicao var1) {
        return new JAXBElement(_ReprocessarRequisicao_QNAME, ReprocessarRequisicao.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "getSiteErrorListResponse"
    )
    public JAXBElement<GetSiteErrorListResponse> createGetSiteErrorListResponse(GetSiteErrorListResponse var1) {
        return new JAXBElement(_GetSiteErrorListResponse_QNAME, GetSiteErrorListResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "enviarRequisicao"
    )
    public JAXBElement<EnviarRequisicao> createEnviarRequisicao(EnviarRequisicao var1) {
        return new JAXBElement(_EnviarRequisicao_QNAME, EnviarRequisicao.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "cancelarRequisicaoResponse"
    )
    public JAXBElement<CancelarRequisicaoResponse> createCancelarRequisicaoResponse(CancelarRequisicaoResponse var1) {
        return new JAXBElement(_CancelarRequisicaoResponse_QNAME, CancelarRequisicaoResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "recuperarPesquisasResponse"
    )
    public JAXBElement<RecuperarPesquisasResponse> createRecuperarPesquisasResponse(RecuperarPesquisasResponse var1) {
        return new JAXBElement(_RecuperarPesquisasResponse_QNAME, RecuperarPesquisasResponse.class, (Class)null, var1);
    }

    @XmlElementDecl(
            namespace = "http://ws.wim.omninetworking.com.br/",
            name = "pesquisar"
    )
    public JAXBElement<Pesquisar> createPesquisar(Pesquisar var1) {
        return new JAXBElement(_Pesquisar_QNAME, Pesquisar.class, (Class)null, var1);
    }
}
