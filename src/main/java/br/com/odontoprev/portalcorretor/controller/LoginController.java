package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String home(HttpSession session, HttpServletResponse resp) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");


        return usuario == null ? "login" : usuario.getPerfil().equals("Corretor") ? "forcavenda/home" : "corretora/home";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/login";
    }
}
