package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UploadCsv;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.FileUploadService;
import br.com.odontoprev.portalcorretor.util.BreadCrumbs;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/listupload")
    public ModelAndView listUpload(Model model,
                                   @ModelAttribute("listUpload") List<UploadCsv> uploadCsvList,
                                   HttpSession session) {
        String csv = null;
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        String uploadFile = null;

        uploadCsvList= fileUploadService.uploadCsvList(csv, String.valueOf(usuario.getCodigoCorretora()), uploadFile);

        model.addAttribute("csv", csv);
        model.addAttribute("usuario", usuario.getDocumento());
        model.addAttribute("uploadFile", uploadFile);


        return new ModelAndView("corretor/equipe/home", "listUpload", uploadCsvList);
    }

    @RequestMapping(value = "/fileupload", method = {RequestMethod.POST})
    public ModelAndView importFile(Model model,
                                   @ModelAttribute("uploadCsv") UploadCsv uploadCsv,
                                   @RequestParam MultipartFile file,
                                   HttpSession session) throws IOException {
        File destination = new File("var/tmp/", UUID.randomUUID().toString());

        //Arquivo 'file' saved into var/tmp folder
        file.transferTo(destination);

        //FILE
        uploadCsv.setFile(file.getBytes());

        if (uploadCsv.getFile().equals("") != uploadCsv.getFile().equals(null)) {
            uploadCsv.setError("Porfavor selecione um arquivo para upload");
            model.addAttribute("error", uploadCsv.getError());
            return new ModelAndView("/corretor/equipe/home", "uploadCsv", uploadCsv);
        }
        else {

            try {
                ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
                String myString = IOUtils.toString(stream, "UTF-8");

                UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

                fileUploadService.fileUpload(uploadCsv.getFile(), usuario.getCodigoCorretora());

                uploadCsv.setSucesso("Upload realizado com sucesso " + file.getOriginalFilename());

                return new ModelAndView("/corretor/equipe/home", "uploadCsv", uploadCsv);

                } catch (IOException e) {
                e.printStackTrace();
            }

        }

        uploadCsv.setNome("Jack");
        uploadCsv.setCpf("22842388828");
        uploadCsv.setDataNascimento("06/07/1987");
        uploadCsv.setCelular("11962650941");
        uploadCsv.setEmail("jean.antunes@gmail.com");
        uploadCsv.setDepartamento("TI");
        uploadCsv.setCargo("Desenvolvedor");


        model.addAttribute("nome", uploadCsv.getNome());
        model.addAttribute("cpf",uploadCsv.getCpf());
        model.addAttribute("dataNascimento", uploadCsv.getDataNascimento());
        model.addAttribute("celular", uploadCsv.getCelular());
        model.addAttribute("email", uploadCsv.getEmail());
        model.addAttribute("departamento", uploadCsv.getDepartamento());
        model.addAttribute("cargo", uploadCsv.getCargo());
        model.addAttribute("error", uploadCsv.getError());
        model.addAttribute("sucesso", uploadCsv.getSucesso());

        model.addAttribute("file", uploadCsv.getFile());

        return new ModelAndView("/corretora/equipe/home", "uploadCsv", uploadCsv);
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