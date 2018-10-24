package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.Arquivo;
import br.com.odontoprev.portalcorretor.service.ArquivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Controller
public class ArquivoController {

    @Autowired
    ArquivoService arquivoService;

    @RequestMapping(value = "/download/infoplano", method = {RequestMethod.GET})
    public void arquivoInfoPlano(@RequestParam("cdArquivo") Long cdArquivo, HttpServletResponse response) throws IOException {

        Arquivo arquivo = arquivoService.getArquivo(cdArquivo);

        String file = arquivo.getArquivoBase64();

        response.setContentType(String.valueOf(MediaType.APPLICATION_PDF_VALUE));

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", arquivo.getNomeArquivo());
        response.setHeader(headerKey, headerValue);
        response.getOutputStream().write(Base64.getDecoder().decode(file.getBytes()));
    }

    /*
    @RequestMapping(value = "/download/infoplano", method = {RequestMethod.GET})
    @ResponseBody
    public String arquivoFull(@RequestParam("cdArquivo") Long cdArquivo, RedirectAttributes redirectAttributes) throws IOException {

        Arquivo arquivo = arquivoService.arquivoDownload(cdArquivo);

        String file = arquivo.getArquivoBase64();
        redirectAttributes.addFlashAttribute("file", file);

        return file;
    }
    */
}
