package br.com.odontoprev.portalcorretor.controller;

import java.io.Serializable;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;

@Controller
public class UsuarioSessionController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@RequestMapping(method=RequestMethod.GET,value="/usuario_session")
	public ResponseEntity<UsuarioSession> getUsuarioSession(HttpSession session) {
		return ResponseEntity.ok((UsuarioSession)session.getAttribute("usuario"));
	}

}
