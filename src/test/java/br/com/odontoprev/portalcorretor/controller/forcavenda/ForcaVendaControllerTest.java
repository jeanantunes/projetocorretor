package br.com.odontoprev.portalcorretor.controller.forcavenda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.odontoprev.portalcorretor.controller.ForcaVendaController;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.ForcaVendaService;
import br.com.odontoprev.portalcorretor.service.dto.Login;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ForcaVendaController.class})
@WebAppConfiguration
public class ForcaVendaControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private ForcaVendaService forcaVendaService;

    @MockBean
    private HttpSession session;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    @Test
    @Ignore
    public void forcaVendaBloqueio() throws Exception {

        //TODO: Débito Técnico - Não sabemos como passar a session via Mock - 21/09/2018 - Jean Antunes
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        usuario.setCodigoUsuario(6);

        ResponseEntity<Login> loginResponse = ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer 6a6dffd0-00c8-342d-b8a2-3546861dfb77")
                .body(null);

        Mockito.when(forcaVendaService.verificaBloqueio(usuario.getCodigoUsuario())).thenReturn(loginResponse);
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(usuario);
        this.mockMvc.perform(get("forcavenda/bloqueio?cdForcaVenda=" + usuario.getCodigoUsuario())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isOk());
    }
}