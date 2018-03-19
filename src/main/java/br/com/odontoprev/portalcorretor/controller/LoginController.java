package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @RequestMapping("")
    public void home(HttpSession session, HttpServletResponse resp) throws IOException {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        if (usuario == null) {
            resp.sendRedirect("login");
        } else if (usuario.getPerfil().equals("Corretor")) {
            resp.sendRedirect("forcavenda/home");
        } else {
            resp.sendRedirect("corretora/home");
        }
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }



}
