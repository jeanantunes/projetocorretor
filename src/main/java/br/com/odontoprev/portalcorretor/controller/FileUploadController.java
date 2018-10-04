package br.com.odontoprev.portalcorretor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.FileUploadService;
import br.com.odontoprev.portalcorretor.service.dto.FileUploadLoteDCMSResponse;
import br.com.odontoprev.portalcorretor.service.dto.FileUploadResponse;
import br.com.odontoprev.portalcorretor.util.BreadCrumbs;

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

    @RequestMapping(value = "/fileupload", method = RequestMethod.POST)
    public String importFile(@RequestParam MultipartFile file, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
        byte[] bytes = file.getBytes();
        Path destination = Paths.get("C:\\Users\\Vector\\jotait\\est-portalcorretor-web", file.getOriginalFilename());
        Files.write(destination.getFileName(), bytes);

        if (bytes.equals(null)) {
            redirectAttributes.addFlashAttribute("mensagemError", "Porfavor selecione um arquivo para upload");
            return "redirect:" + "/corretora/equipe/home";
            //return new ModelAndView("/corretora/equipe/home", "uploadCsv", uploadCsv);
        }

//        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
//        String myString = IOUtils.toString(stream, "UTF-8");

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        FileUploadResponse response = fileUploadService.fileUpload(file, usuario.getCodigoCorretora());

        if (response.getId() == 200){
            redirectAttributes.addFlashAttribute("mensagemSucesso",
                    "Upload realizado com sucesso '" + file.getOriginalFilename() + "'");
        }else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao realizar Upload, verifique seu arquivo e tente novamente.");
        }

//        Files.delete(destination.getFileName());

        return "redirect:" + "/corretora/equipe/home";
        //return new ModelAndView("/corretora/equipe/home", "uploadCsv", uploadCsv);
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
    

    //201810041500 - esert - COR-862:Controller WEB POST + TDD
    @RequestMapping(value = "/fileupload/lotedcms", method = RequestMethod.POST)
    public String fileuploadLoteDCMS(@RequestParam MultipartFile file, HttpSession session, RedirectAttributes redirectAttributes) throws IOException {
    	
        //byte[] bytes = file.getBytes();
        //Path destination = Paths.get("C:\\Users\\Vector\\jotait\\est-portalcorretor-web", file.getOriginalFilename());
        //Files.write(destination.getFileName(), bytes);

        //if (bytes.equals(null)) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemError", "Porfavor selecione um arquivo para upload Lote DCMS");
            return "redirect:" + "/corretora/equipe/home";
            //return new ModelAndView("/corretora/equipe/home", "uploadCsv", uploadCsv);
        }

        //ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        //String myString = IOUtils.toString(stream, "UTF-8");

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        FileUploadLoteDCMSResponse response = fileUploadService.fileUploadLoteDCMS(file, usuario.getCodigoCorretora());

        if (response.getId() == HttpStatus.OK.value()){
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Upload Lote DCMS realizado com sucesso '" + file.getOriginalFilename() + "'");
        }else {
            redirectAttributes.addFlashAttribute("mensagemErro", "Ocorreu um erro ao realizar Upload Lote DCMS, verifique seu arquivo e tente novamente. Cod:[" + response.getId() + "]");
        }

        //Files.delete(destination.getFileName());

        return "redirect:" + "/corretora/equipe/home";
        //return new ModelAndView("/corretora/equipe/home", "uploadCsv", uploadCsv);
        //return "redirect:/uploadSuccess";
    }

}