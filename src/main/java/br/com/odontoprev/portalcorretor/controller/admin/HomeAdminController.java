package br.com.odontoprev.portalcorretor.controller.admin;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeAdminController {

    private static final Log log = LogFactory.getLog(HomeAdminController.class);

    @RequestMapping(value = "admin/home", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {        
        return new ModelAndView("admin/home");
    }

    


}
