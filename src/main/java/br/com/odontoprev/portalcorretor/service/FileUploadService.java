package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.FileUploadResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {
    private static final Log log = LogFactory.getLog(FileUploadService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requestBaseUrl;

    @Value("${odontoprec.service.base}")
    private String requestBase;

    @Value("${odontoprev.fileupload.metodo}")
    private String metodo;

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public FileUploadResponse fileUpload(MultipartFile file, Integer codigoCorretora) {
        //String url = ConfigurationUtils.getURLGetToken().replaceAll("/token", metodo + codigoCorretora);
        //TODO: Alterar rota para requestBase
        //String url = requestBase + metodo + codigoCorretora;
        String url = "http://localhost:9090/upload/" + codigoCorretora;

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("file", new FileSystemResource(file.getOriginalFilename()));

            List<MediaType> acceptableMediaTypes = new ArrayList<>();
            acceptableMediaTypes.add(MediaType.MULTIPART_FORM_DATA);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setAccept(acceptableMediaTypes);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);
            ResponseEntity<String> retorno = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                log.info("Upload realizado com sucesso " + retorno.getStatusCode());
                return new FileUploadResponse(200, "Upload realizado com sucesso!");
            } else {
                log.info("Erro ao realizar upload " + retorno.getStatusCode());
                return new FileUploadResponse(400, "Falha ao realizar Upload, porfavor verifique seu arquivo.");
            }

        } catch (Exception e) {
            log.error("Erro ao realizar upload", e);
            return new FileUploadResponse(400, "Erro ao realizar Upload -> " + e);
        }

    }

}
