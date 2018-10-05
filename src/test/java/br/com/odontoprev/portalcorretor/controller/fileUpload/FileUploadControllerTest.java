package br.com.odontoprev.portalcorretor.controller.fileUpload;

import br.com.odontoprev.portalcorretor.controller.FileUploadController;
import br.com.odontoprev.portalcorretor.service.*;
import br.com.odontoprev.portalcorretor.service.dto.FileUploadLoteDCMS;
import br.com.odontoprev.portalcorretor.service.dto.FileUploadLoteDCMSResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        FileUploadControllerConfigTest.class
})
@WebAppConfiguration
public class FileUploadControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private FileUploadService fileUploadService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testOk200uploadFileDcms() throws Exception {

        String arquivoBase64 = "0M8R4KGxGuEAAAAAAAAAAAAAAAAAAAAAOwADAP7/CQAGAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAAEAAAAgAAAAEAAAD+////AAAAAAEAAAD///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////9SAG8AbwB0ACAARQBuAHQAcgB5AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFgAFAf//////////AQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMAAAAADQAAAAAAAFcAbwByAGsAYgBvAG8AawAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASAAIB////////////////AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAPMMAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP7////9/////v///wQAAAAFAAAABgAAAAcAAAAIAAAACQAAAP7/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////AQAAAAIAAAADAAAABAAAAAUAAAAGAAAABwAAAAgAAAAJAAAACgAAAAsAAAAMAAAADQAAAA4AAAAPAAAAEAAAABEAAAASAAAAEwAAABQAAAAVAAAAFgAAABcAAAAYAAAAGQAAABoAAAAbAAAAHAAAAB0AAAAeAAAAHwAAACAAAAAhAAAAIgAAACMAAAAkAAAAJQAAACYAAAAnAAAAKAAAACkAAAAqAAAAKwAAACwAAAAtAAAALgAAAC8AAAAwAAAAMQAAADIAAAAzAAAA/v////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8JCBAAAAYFANMQzAdBAAAABgAAAOEAAgCwBMEAAgAAAOIAAABcAHAABQAAYWxtZWkgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIEIAAgCwBGEBAgAAAD0BAgAAAJwAAgAOABkAAgAAABIAAgAAABMAAgAAAK8BAgAAALwBAgAAAD0AEgBoAQ4BXDq+IzgAAAAAAAEAWAJAAAIAAACNAAIAAAAiAAIAAAAOAAIAAQC3AQIAAADaAAIAAAAxABUAyAAAAP9/kAEAAAAAAAAFAEFyaWFsMQAVAMgAAAD/f5ABAAAAAAAABQBBcmlhbDEAFQDIAAAA/3+QAQAAAAAAAAUAQXJpYWwxABUAyAAAAP9/kAEAAAAAAAAFAEFyaWFsHgQaAAUAFQAAIiQiIywjIzBfKTsoIiQiIywjIzApHgQfAAYAGgAAIiQiIywjIzBfKTtbUmVkXSgiJCIjLCMjMCkeBCAABwAbAAAiJCIjLCMjMC4wMF8pOygiJCIjLCMjMC4wMCkeBCUACAAgAAAiJCIjLCMjMC4wMF8pO1tSZWRdKCIkIiMsIyMwLjAwKR4ELAAqACcAAF8oKiAjLCMjMF8pO18oKiAoIywjIzApO18oKiAiLSJfKTtfKEBfKR4ENQApADAAAF8oIiQiKiAjLCMjMF8pO18oIiQiKiAoIywjIzApO18oIiQiKiAiLSJfKTtfKEBfKR4EPQAsADgAAF8oIiQiKiAjLCMjMC4wMF8pO18oIiQiKiAoIywjIzAu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";
        String nomeArquivo = "testeOk200";
        String contentType = "application/vnd.ms-excel";
        Long tamanhoArquivo = 2255L;

        FileUploadLoteDCMS fileUploadLoteDCMS = new FileUploadLoteDCMS();
        fileUploadLoteDCMS.setArquivoBase64(arquivoBase64);
        fileUploadLoteDCMS.setNomeArquivo(nomeArquivo);
        fileUploadLoteDCMS.setTamanho(tamanhoArquivo);
        fileUploadLoteDCMS.setTipoConteudo(contentType);

        FileUploadLoteDCMSResponse fileUploadLoteDCMSResponse = new FileUploadLoteDCMSResponse();
        fileUploadLoteDCMSResponse.setArquivoBase64(arquivoBase64);
        fileUploadLoteDCMSResponse.setNomeArquivo(nomeArquivo);
        fileUploadLoteDCMSResponse.setTamanho(tamanhoArquivo);
        fileUploadLoteDCMSResponse.setTipoConteudo(contentType);


        ResponseEntity<FileUploadLoteDCMSResponse> arquivo = ResponseEntity
                .ok()
                .contentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_JSON))
                .body(fileUploadLoteDCMSResponse);

        Mockito.when(fileUploadService.fileUploadLoteDCMS(fileUploadLoteDCMS)).thenReturn(arquivo);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(fileUploadLoteDCMS);

        this.mockMvc
                .perform(post("/fileupload/lotedcms")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(jsonInString))
                .andExpect(status().isOk());
    }

    @Test
    public void testBadRequest400uploadFileDcms() throws Exception {

        this.mockMvc
                .perform(post("/fileupload/lotedcms")
                        .contentType(APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }
}