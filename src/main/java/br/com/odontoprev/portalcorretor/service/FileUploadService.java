package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.model.UploadCsv;
import br.com.odontoprev.portalcorretor.service.dto.FileUploadResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {
    private static final Log log = LogFactory.getLog(FileUploadService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.fileupload.metodo}")
    private String metodo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public FileUploadResponse fileUpload(byte[] file, Integer cdCorretora) {
        FileUploadResponse fileUploadResponse = null;

        //String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", metodo + cdCorretora);
        String url = "http://localhost:9090/upload/" + cdCorretora;
        RestTemplate restTemplate = new RestTemplate();
        FileUploadResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.valueOf(MediaType.MULTIPART_FORM_DATA_VALUE));

            ObjectMapper mapper = new ObjectMapper();
            String object = mapper.writeValueAsString(file);

            log.info("JSON: \n" + new Gson().toJson(file));

            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

            ResponseEntity<FileUploadResponse> retorno = restTemplate.exchange(url, HttpMethod.POST, entityReq, FileUploadResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                fileUploadResponse = retorno.getBody();
                log.info("Upload realizado com sucesso " + result.getId() + " " + result.getMensagem());

                return fileUploadResponse;
            } else {
                log.info("Erro ao realizar upload " + result.getMensagem());
                return fileUploadResponse;
            }

        } catch (Exception e) {
            log.error("Erro ao realizar upload", e);
            return null;
        }

    }

    public List<UploadCsv> uploadCsvList(String csv, String cdCorretora, String uploadFile) {
        List<UploadCsv> csvList = new ArrayList<>();

        return csvList;
    }

}
