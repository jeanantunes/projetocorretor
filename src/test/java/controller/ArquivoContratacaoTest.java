package controller;

import br.com.odontoprev.portalcorretor.controller.DetalhesPropostaController;
import br.com.odontoprev.portalcorretor.model.ArquivoContratacao;
import br.com.odontoprev.portalcorretor.service.PropostaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DetalhesPropostaController.class})
@WebAppConfiguration
public class ArquivoContratacaoTest {
    private MockMvc mockMvc;

    @MockBean
    private PropostaService propostaService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void test200ArqContratacao() throws Exception {
        ArquivoContratacao arqContratacao = new ArquivoContratacao();
        Long codigoEmpresa = 2547L;
        Date dataCriacao = new Date();
        String nomeArquivo = "arquivoContratacao";
        Long tamanhoArquivo = 123L;
        String tipoConteudo = "text/pdf";
        String arquivoBase64 = "";
        String caminhoCarga = "~/Documento/jotait";

        arqContratacao.setArquivoBase64(String.valueOf(codigoEmpresa));
        arqContratacao.setDataCriacao(dataCriacao);
        arqContratacao.setNomeArquivo(nomeArquivo);
        arqContratacao.setTamanhoArquivo(tamanhoArquivo);
        arqContratacao.setTipoConteudo(tipoConteudo);
        arqContratacao.setArquivoBase64(arquivoBase64);
        arqContratacao.setCaminhoCarga(caminhoCarga);
        arqContratacao.setCodigoEmpresa(2547L);

        byte[] arquivo = {12, 23, 34, 45};

        Mockito.when(propostaService.gerarArquivoContratacao(codigoEmpresa)).thenReturn(arquivo);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(arqContratacao);
        this.mockMvc.perform(get("/downloadContratacao?cdEmpresa=" + codigoEmpresa)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }
}