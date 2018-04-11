package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.api.manager.client.token.ApiManagerToken;
import br.com.odontoprev.api.manager.client.token.ApiManagerTokenFactory;
import br.com.odontoprev.api.manager.client.token.ApiToken;
import br.com.odontoprev.api.manager.client.token.enumerator.ApiManagerTokenEnum;
import br.com.odontoprev.api.manager.client.token.exception.ConnectionApiException;
import br.com.odontoprev.api.manager.client.token.exception.CredentialsInvalidException;
import br.com.odontoprev.api.manager.client.token.exception.URLEndpointNotFound;
import br.com.odontoprev.portalcorretor.model.Cadastro;
import br.com.odontoprev.portalcorretor.service.dto.TokenResponse;
import br.com.odontoprev.portalcorretor.service.dto.omninetworking.wim.ws.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.Produces;

@Controller
@RestController
public class CadastroController {

    @RequestMapping(value = "cadastro", method = RequestMethod.GET)
    public ModelAndView home() {
        Cadastro cadastro = new Cadastro();
        cadastro.setDono(true);
        cadastro.setCnpj("23423423423");
        return new ModelAndView("cadastro_usuario", "cadastro", cadastro);
    }


    @RequestMapping(value = "cnpj", method = RequestMethod.GET)
    public ResponseEntity<Cadastro> cnpj(@RequestParam("cnpj") String cnpj) {

        Cadastro cadastro = new Cadastro();
        cadastro.setCnpj(cnpj);
        cadastro.setRazaoSocial("OdontoPrev");
        cadastro.setCnae("123-12");
        cadastro.setSimplesNacional("Não");
        cadastro.setDataAbertura("01/01/2001");
        cadastro.setStatusCnpj("VÁLIDO");
        cadastro.setNomeRepresentanteLegal("Representante Legal");
        cadastro.setCpfRepresentanteLegal("123.123.123-34");
        cadastro.setTelefone("(11)2222-1234");
        cadastro.setCelular("(11)98765-1234");
        cadastro.setEmail("teste@odontoprev.com.br");
        return cnpj.equals("23.423.423/423") ? ResponseEntity.ok(cadastro) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "cep", method = RequestMethod.GET)
    public ModelAndView cep(@ModelAttribute("cadastro") Cadastro cadastro) {


        return new ModelAndView("cadastro/home", "cadastro", cadastro);
    }


    @RequestMapping(value = "cadastrar", method = RequestMethod.POST)
    public ModelAndView cadastrar(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("cadastro/termosCondicoes", "cadastro", cadastro);
    }


    @RequestMapping(value = "termosCondicoes", method = RequestMethod.POST)
    public ModelAndView termosCondicoes(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("cadastro/bemvindo", "cadastro", cadastro);
    }

    @RequestMapping(value = "bemvindo", method = RequestMethod.POST)
    public ModelAndView bemvindo(@ModelAttribute("cadastro") Cadastro cadastro) {
        return new ModelAndView("cadastro/bemvindo", "cadastro", cadastro);
    }
    @Produces("application/json")
    @RequestMapping(value = "get_token", method = RequestMethod.GET)
    public TokenResponse getToken() {
        ApiManagerToken apiManager = ApiManagerTokenFactory.create(ApiManagerTokenEnum.WSO2, "PORTAL_CORRETOR_WEB");
        ApiToken apiToken = null;
        TokenResponse token = new TokenResponse();
        try {
            apiToken = apiManager.generateToken();

            token.setToken(apiToken.getAccessToken());
        } catch (CredentialsInvalidException e) {
            e.printStackTrace();
        } catch (URLEndpointNotFound urlEndpointNotFound) {
            urlEndpointNotFound.printStackTrace();
        } catch (ConnectionApiException e) {
            e.printStackTrace();
        }

        return token;


    }

    @RequestMapping(value = "esqueci-minha-senha/{token}", method = RequestMethod.GET)
    public ModelAndView esqueciSenha(@PathVariable String token) {

        return new ModelAndView("redefinirSenha");
    }
}
