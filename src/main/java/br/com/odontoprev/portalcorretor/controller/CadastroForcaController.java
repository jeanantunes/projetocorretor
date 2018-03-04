package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.ListaForca;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CadastroForcaController {

    @RequestMapping(value = "corretora/equipe/adicionar", method = RequestMethod.GET)
    public String home() {

        return "corretora/equipe/adicionar";
    }


    @RequestMapping(value = "corretora/equipe/home", method = RequestMethod.GET)
    public ModelAndView Equipe() {


        List<ForcaVenda> lista = new ArrayList<>();
        lista.add(getForcaVenda("joao", "ativo"));
        lista.add(getForcaVenda("maria", "aguardando aprovaçao"));
        lista.add(getForcaVenda("maria", "aguardando aprovaçao"));


        ListaForca  listaForca = new ListaForca();

        listaForca.setTotalForca(lista.size());
        listaForca.setLista(lista);
        listaForca.setAguardandoAprovacao(lista);

        return new ModelAndView("corretora/equipe/home", "listaForca", listaForca);
    }

    private ForcaVenda getForcaVenda(String nome, String status) {
        ForcaVenda forca = new ForcaVenda();
        forca.setStatusForcaVenda(status);
        forca.setNome(nome);
        //1 aguardando aprovaçao
        //2 ativo
        //3 inativo
        //4 pendente
        //5 pre cadastro
        return forca;
    }

}
