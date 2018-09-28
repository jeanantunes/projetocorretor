package br.com.odontoprev.portalcorretor.controller.detalhesProposta;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.odontoprev.portalcorretor.service.PropostaService;
import br.com.odontoprev.portalcorretor.service.dto.BeneficiariosPropostaResponsePagination;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaPropostaResponse;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
		DetalhesPropostaControllerConfigTest.class      
		})

@WebAppConfiguration
public class DetalhesPropostaControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private PropostaService propostaService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    //201809281757 - esert - COR-847 : WEB - ConfigTest para DetalhesPropostaControllerTest.java
    @Test
    public void testOk200GetDownloadContratacao() throws Exception {

        Long codigoEmpresa = 2547L;
        String nomeArquivo = "arquivoContratacao";
        String arquivoBase64 = "arquivoBase64";

        ResponseEntity<String> arquivo = ResponseEntity
                .ok()
                .contentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON))
                .header(
                        "Content-Disposition",
                        String.format("attachment; filename=%s", nomeArquivo)
                )
                .body(arquivoBase64);

        Mockito.when(propostaService.gerarArquivoContratacao(codigoEmpresa)).thenReturn(arquivo);
        
        //EmpresaPropostaResponse detalhesPropostaPME = propostaService.detalhesPropostaPME(cdEmpresa);
        EmpresaPropostaResponse empresaPropostaResponse = new EmpresaPropostaResponse();
        Mockito.when(propostaService.detalhesPropostaPME(String.valueOf(codigoEmpresa))).thenReturn(empresaPropostaResponse);

        //List<BeneficiariosPropostaResponsePagination> beneficiarios = propostaService.detalhesBeneficiarioPropostaPME(detalhesPropostaPME.getCdEmpresa(), numpag, tampag);
        List<BeneficiariosPropostaResponsePagination> beneficiarios = new ArrayList<BeneficiariosPropostaResponsePagination>();
        Long numPag = 1L;
        Long tamPag = 8L;
        Mockito.when(propostaService.detalhesBeneficiarioPropostaPME(codigoEmpresa, numPag, tamPag)).thenReturn(beneficiarios);

        this.mockMvc.perform(get("/downloadContratacao?cdEmpresa=" + codigoEmpresa)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}