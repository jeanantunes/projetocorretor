package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.service.ApiManagerTokenService;
import br.com.odontoprev.portalcorretor.service.EmpresaService;
import br.com.odontoprev.portalcorretor.service.dto.Empresa;
import br.com.odontoprev.portalcorretor.service.dto.EmpresaResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
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
        EmpresaController.class,
        EmpresaService.class,
        ApiManagerTokenService.class})

@WebAppConfiguration
public class EmpresaControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private EmpresaService empresaService;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @Ignore
    public void testOk200PutupdateEmpresa() throws Exception {
        Empresa empresa = new Empresa();
        empresa.setCdEmpresa(581L);
        //empresa.setCnpj("29.260.862/0001-35");
        //empresa.setEmpDcms("205012");
        empresa.setEmail("lpfempreiteira@hotmail.com");


        EmpresaResponse empresaResponse = new EmpresaResponse(HttpStatus.OK.value(), HttpStatus.OK.name());

        Mockito.when(empresaService.updateEmpresa(empresa)).thenReturn(empresaResponse);

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(empresa);

        this.mockMvc
                .perform(put("/empresa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInString))
                .andExpect(status().isOk());
    }

}