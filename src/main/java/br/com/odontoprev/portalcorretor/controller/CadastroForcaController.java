package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.ListaForca;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.ForcaVendaService;
import br.com.odontoprev.portalcorretor.service.dto.AtivarResponse;
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
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Controller
public class CadastroForcaController {


    @Autowired
    ForcaVendaService forcaVendaService;

    @RequestMapping(value = "corretora/equipe/adicionar", method = RequestMethod.GET)
    public ModelAndView adicionar(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        return new ModelAndView("corretora/equipe/adicionar", "forca", new ForcaVenda());
    }


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
    @RequestMapping(value = "corretora/equipe/adicionar", method = RequestMethod.POST)
    public ModelAndView salvarForca(@ModelAttribute("cadastro") ForcaVenda forcaVenda, HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");


        if (forcaVenda.getNome() == null || "".equals(forcaVenda.getNome())) {
            forcaVenda.setError("O campo NOME é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getCpf() == null || "".equals(forcaVenda.getCpf())) {
            forcaVenda.setError("O campo CPF é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getDataNascimento() == null || "".equals(forcaVenda.getDataNascimento())) {
            forcaVenda.setError("O campo data nascimento é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getEmail() == null || "".equals(forcaVenda.getEmail())) {
            forcaVenda.setError("O campo EMAIL é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getDepartamento() == null || "".equals(forcaVenda.getDepartamento())) {
            forcaVenda.setError("O campo DEPARTAMENTO é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);

        } else if (forcaVenda.getCargo() == null || "".equals(forcaVenda.getCargo())) {
            forcaVenda.setError("O campo CARGO é obrigatório");
            return new ModelAndView("corretora/equipe/adicionar", "forca", forcaVenda);


        } else {
            forcaVenda.setCorretora(new Corretora(usuario.getCodigoCorretora()));
            forcaVenda.setAtivo(true);
            forcaVenda.setCpf(forcaVenda.getCpf().replaceAll("\\D+", ""));
            forcaVenda.setCelular(forcaVenda.getCelular().replaceAll("\\D+", ""));
            forcaVendaService.Criar(forcaVenda);

            ListaForca listaForca = getListaForca(usuario.getCodigoCorretora());
            return new ModelAndView("corretora/equipe/home", "listaForca", listaForca);
        }
    }


    @RequestMapping(value = "corretora/equipe/ativar", method = RequestMethod.GET)
    public ModelAndView ativar(@RequestParam("id") String id, HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        ForcaVenda forcaVenda = forcaVendaService.ObterPorDocumento(id);
        Boolean error = false;
        String msg = "";

        if( !usuario.getCodigoCorretora().equals( forcaVenda.getCorretora().getCdCorretora())){
            msg = "Força de venda não pertence a essa corretora";
            error = true;
        }

        AtivarResponse ativar = forcaVendaService.ativar(forcaVenda.getCpf());
        if(ativar.getMessage() !=null){
            error = true;
            msg = ativar.getMessage();
        }

        ListaForca listaForca = getListaForca(usuario.getCodigoCorretora());
        listaForca.setErro(error);
        listaForca.setMessage(msg);
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

        ListaForca listaForca = getListaForca(usuario.getCodigoCorretora());

        return new ModelAndView("corretora/equipe/home", "listaForca", listaForca);
    }

    private ListaForca getListaForca(Integer documento) {
        List<ForcaVenda> lista = forcaVendaService.ObterListaPorCorretora(documento);


        ListaForca listaForca = new ListaForca();

        listaForca.setLista(lista
                .stream()
                .filter(i -> !i.getStatusForcaVenda().equals("Aguardando Aprovação"))
                .sorted(comparing(ForcaVenda::getStatusForcaVenda).thenComparing(ForcaVenda::getNome))
                .collect(Collectors.toList()));
        List<ForcaVenda> aguardandoAprovaaco = lista
                .stream()
                .filter(i -> i.getStatusForcaVenda().equals("Aguardando Aprovação"))
                .sorted(comparing(ForcaVenda::getNome))
                .collect(Collectors.toList());
        listaForca.setTotalForca(aguardandoAprovaaco.size());
        listaForca.setAguardandoAprovacao(aguardandoAprovaaco);
        return listaForca;
    }

}
