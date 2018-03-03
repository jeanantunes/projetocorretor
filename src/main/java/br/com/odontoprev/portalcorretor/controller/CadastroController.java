package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Cadastro;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CadastroController {

    @RequestMapping(value = "cadastro", method = RequestMethod.GET)
    public ModelAndView home() {
        Cadastro cadastro = new Cadastro();
        cadastro.setDono(true);
        cadastro.setCnpj("23423423423");
        return new ModelAndView("cadastro/home", "cadastro", cadastro);
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


}
