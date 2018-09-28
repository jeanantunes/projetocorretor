package br.com.odontoprev.portalcorretor.controller.corretora;

import br.com.odontoprev.portalcorretor.service.*;
import br.com.odontoprev.portalcorretor.service.dto.Corretora;
import br.com.odontoprev.portalcorretor.service.dto.CorretoraResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.ws.rs.core.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        CorretoraControllerConfigTest.class,
        DashService.class,
        ForcaVendaService.class,
        EnderecoService.class,
        ApiManagerTokenService.class,
})
@WebAppConfiguration
public class CorretoraControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private CorretoraService corretoraService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testOk200PutSalvarEmailCorretora() throws Exception {
        Integer cdCorretora = 21;
        String email = "emailteste201809051902@corretora.com.br";

        Corretora corretora = new Corretora();
        corretora.setCdCorretora(cdCorretora);
        corretora.setEmail(email);

        CorretoraResponse corretoraResponse = new CorretoraResponse(HttpStatus.OK.value(), HttpStatus.OK.name());

        Mockito.when(corretoraService.salvarEmailCorretora(corretora)).thenReturn(corretoraResponse);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(corretora);

        this.mockMvc
                .perform(put("/corretora/salvaremail")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInString))
                .andExpect(status().isOk());
    }
}