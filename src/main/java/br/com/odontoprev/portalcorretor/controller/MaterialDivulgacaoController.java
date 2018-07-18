package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.MateriaisDivulgacao;
import br.com.odontoprev.portalcorretor.model.MateriaisDivulgacaoResponse;
import br.com.odontoprev.portalcorretor.sendmail.SendMail;
import br.com.odontoprev.portalcorretor.service.MateriaisDivulgacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Controller
public class MaterialDivulgacaoController {

    @Autowired
    SendMail sendMail;

    @Autowired
    MateriaisDivulgacaoService materiaisDivulgacaoService;

    @RequestMapping("/material-divulgacao")
    public ModelAndView index() {

        MateriaisDivulgacaoResponse materiaisDivulgacao = materiaisDivulgacaoService.materiaisDivulgacao();

        ModelAndView modelAndView = new ModelAndView("material-divulgacao", "materiaisDivulgacao", materiaisDivulgacao);
        return modelAndView;
    }

    @RequestMapping(value = "materiaisDownload/", method = {RequestMethod.GET})
    public void materiaisDownload(@RequestParam("id") Long id, HttpServletResponse response) throws IOException {

        MateriaisDivulgacao materialResponse = materiaisDivulgacaoService.arquivoDownload(id);

        String file = materialResponse.getArquivo();

        response.setContentType(String.valueOf(MediaType.IMAGE_JPEG_VALUE));

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", materialResponse.getNome());
        response.setHeader(headerKey, headerValue);
        response.getOutputStream().write(Base64.getDecoder().decode(file.getBytes()));
    }

    @RequestMapping(value = "arquivo/", method = {RequestMethod.GET})
    @ResponseBody
    public String arquivoFull(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) throws IOException {

        MateriaisDivulgacao materialResponse = materiaisDivulgacaoService.arquivoDownload(id);

        String file = materialResponse.getArquivo();
        redirectAttributes.addFlashAttribute("file", file);

        return file;
    }

    /* Localizar por ID
    @GetMapping("/localizar/{id}")
    @ResponseBody
    public byte[] localizarImg(Model model,
                                //id of the product i need to show the picture
                                @PathVariable("id")Integer id, HttpServletResponse response) {
        //object from database
        MateriaisDivulgacaoResponse materiais = materiaisDivulgacaoService.findById(id);
        log.info("Imagem eu lhe invoco by Id");
        //return the attr of my object (blob)
        return materiais.getCategorias();
    }
    */

    /* Download por ID
    @RequestMapping(value="/download", method=RequestMethod.GET)
    @ResponseBody
    public FileSystemResource downloadFile(@Param(value="id") Long id) {
        MateriaisDivulgacaoResponse material = materiaisDivulgacaoService.findOne(id);
        return new FileSystemResource(new File(material.getCategorias());
    }
    */
}
