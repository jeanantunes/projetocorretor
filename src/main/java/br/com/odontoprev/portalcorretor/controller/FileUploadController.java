package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.FileUploadService;
import br.com.odontoprev.portalcorretor.util.BreadCrumbs;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/fileupload")
    public String fileUpload(Model model) {
        String pageTitle = "Upload file";
        BreadCrumbs.set(model, pageTitle);
        return "fileupload";
    }

    @RequestMapping(value = "/fileupload", produces = MediaType.MULTIPART_FORM_DATA_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, method = {RequestMethod.POST})
    public String importFile(@RequestParam("file") MultipartFile myFile, HttpSession session, RedirectAttributes redirectAttributes) {
        File destination = new File("C:\\Users\\Vector\\jotait\\est-portalcorretor-web", UUID.randomUUID().toString());

        if (myFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemError", "Porfavor selecione um arquivo para upload");
            return "redirect:" + "/corretora/equipe/home";
        }

        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(myFile.getBytes());
            String myString = IOUtils.toString(stream, "UTF-8");

            UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

            fileUploadService.fileUpload(new String(myFile.getBytes()), usuario.getCodigoCorretora());
            redirectAttributes.addFlashAttribute("mensagemSucesso",
                    "Upload realizado com sucesso '" + myFile.getOriginalFilename() + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:" + "/corretora/equipe/home";
        //return "redirect:/uploadSuccess";
    }

    @RequestMapping(value = "/uploadSuccess")
    public String faq(Model model) {
        String pageTitle = "File uploaded";
        BreadCrumbs.set(model, pageTitle);
        return "uploadSuccess";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }
}