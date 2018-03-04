package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.ListaForca;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.ForcaVendaService;
import br.com.odontoprev.portalcorretor.service.dto.Corretora;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CadastroForcaController {


    @Autowired
    ForcaVendaService forcaVendaService;

    @RequestMapping(value = "corretora/equipe/adicionar", method = RequestMethod.GET)
    public ModelAndView adicionar(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        return new ModelAndView("corretora/equipe/adicionar", "forca", new ForcaVenda());
    }

    @RequestMapping(value = "corretora/equipe/adicionar", method = RequestMethod.POST)
    public ModelAndView salvarForca(@ModelAttribute("cadastro") ForcaVenda forcaVenda, HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");


//        {
//            "nome":"Fernando Setai",
//                "celular":"11912341234",
//                "email":"fernando.mota@odontoprev.com.br",
//                "corretora":{
//            "cdCorretora":"142"
//        },
//            "cpf":"38330982877",
//                "ativo":true,
//                "departamento":"TI",
//                "cargo":"TI",
//                "dataNascimento":"11-11-1989"
//
//        }

        if (forcaVenda.getNome() == null ||  "".equals(forcaVenda.getNome())) {
            forcaVenda.setError("O campo NOME é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getCpf() == null ||  "".equals(forcaVenda.getCpf())) {
            forcaVenda.setError("O campo CPF é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getDataNascimento() == null ||  "".equals(forcaVenda.getDataNascimento())) {
            forcaVenda.setError("O campo data nascimento é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getEmail() == null ||  "".equals(forcaVenda.getEmail())) {
            forcaVenda.setError("O campo EMAIL é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getDepartamento() == null ||  "".equals(forcaVenda.getDepartamento())) {
            forcaVenda.setError("O campo DEPARTAMENTO é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getCargo() == null ||  "".equals(forcaVenda.getCargo())) {
            forcaVenda.setError("O campo CARGO é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);


        } else {
            forcaVenda.setCorretora(new Corretora(usuario.getCodigoCorretora()));
            forcaVenda.setAtivo(true);
            forcaVendaService.Criar(forcaVenda);

            ListaForca listaForca = getListaForca(usuario.getDocumento());
            return new ModelAndView("corretora/equipe/home", "listaForca", listaForca);
        }
    }


    @RequestMapping(value = "corretora/equipe/desativar", method = RequestMethod.GET)
    public ModelAndView desativar(@RequestParam("id") String id, HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        ListaForca listaForca = getListaForca(usuario.getDocumento());


        return new ModelAndView("corretora/equipe/home", "listaForca", listaForca);
    }


//    @RequestMapping(value = "corretora/equipe/editar", method = RequestMethod.GET)
//    public ModelAndView editar(@RequestParam("id") String id, HttpSession session) {
//        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
//
//        ForcaVenda forca = (getForcaVenda("joao", "ativo"));
//
//
//        return new ModelAndView("corretora/equipe/adicionar", "forca", forca);
//    }

    @RequestMapping(value = "corretora/equipe/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        ListaForca listaForca = getListaForca(usuario.getDocumento());

        return new ModelAndView("corretora/equipe/home", "listaForca", listaForca);
    }

    private ListaForca getListaForca(String documento) {
        List<ForcaVenda> lista = new ArrayList<>();
        lista.add(getForcaVenda("joao", "ativo"));
        lista.add(getForcaVenda("maria", "aguardando aprovaçao"));
        lista.add(getForcaVenda("maria", "aguardando aprovaçao"));

        forcaVendaService.ObterPorDocumento(documento);


        ListaForca listaForca = new ListaForca();

        listaForca.setTotalForca(lista.size());
        listaForca.setLista(lista);
        listaForca.setAguardandoAprovacao(lista);
        return listaForca;
    }

    private ForcaVenda getForcaVenda(String nome, String status) {
        ForcaVenda forca = new ForcaVenda();
        forca.setStatusForcaVenda(status);
        forca.setCdForcaVenda(Double.valueOf(Math.random() * 1000).longValue());
        forca.setNome(nome);
        //1 aguardando aprovaçao
        //2 ativo
        //3 inativo
        //4 pendente
        //5 pre cadastro
        return forca;
    }

}
