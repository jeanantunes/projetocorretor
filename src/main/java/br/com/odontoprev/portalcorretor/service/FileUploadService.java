package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.OutputStream;

@Service
public class FileUploadService {
    private static final Log log = LogFactory.getLog(FileUploadService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;

    @Value("${odontoprev.fileupload.metodo}")
    private String metodo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public RequestEntity<?> fileUpload(String file, Integer codigoCorretora) {
        String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", metodo + codigoCorretora);
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
                result = retorno.getBody();
                log.info("Upload realizado com sucesso " + result.getId() + " " + result.getMensagem());
                return null;
            } else {
                log.info("Erro ao realizar upload " + result.getMensagem());
                return null;
            }

        } catch (Exception e) {
            log.error("Erro ao realizar upload", e);
            return null;
        }

    }

}
