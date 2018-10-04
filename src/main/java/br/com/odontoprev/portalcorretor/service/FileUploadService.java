package br.com.odontoprev.portalcorretor.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.com.odontoprev.portalcorretor.service.dto.FileUploadLoteDCMSResponse;
import br.com.odontoprev.portalcorretor.service.dto.FileUploadResponse;

@Service
public class FileUploadService {
    private static final Log log = LogFactory.getLog(FileUploadService.class);

    @Value("${odontoprev.servicebase.url}")
    private String requestBaseUrl;

    @Value("${odontoprec.service.base}")
    private String requestBase;
    
    @Value("${odontoprev.corretor.api.contexto.api.url}") //201810041500 - esert - COR-860:Service WEB POST
    private String contextoApi; //201810041500 - esert - COR-860:Service WEB POST

    @Value("${odontoprev.fileupload.metodo}")
    private String metodo;
    
    @Value("${odontoprev.fileupload.lotedcms.metodo}") //201810041500 - esert - COR-860:Service WEB POST
    private String metodoFileUploadLoteDCMS; //201810041500 - esert - COR-860:Service WEB POST

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
    
    //201810041500 - esert - COR-860:Service WEB POST
    public FileUploadLoteDCMSResponse fileUploadLoteDCMS(MultipartFile file, Integer codigoCorretora) {

    	String url = requestBaseUrl + contextoApi + metodoFileUploadLoteDCMS + "/" + codigoCorretora;
    	
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
    			log.info("Upload Lote DCMS realizado com sucesso:" + retorno.getStatusCode().name());
    			return new FileUploadLoteDCMSResponse(retorno.getStatusCode().value(), "Upload realizado com sucesso!");
    		} else if (retorno.getStatusCode() == HttpStatus.BAD_REQUEST) {
    			log.info("Erro ao realizar upload Lote DCMS:" + retorno.getStatusCode().name());
    			return new FileUploadLoteDCMSResponse(retorno.getStatusCode().value(), "Falha ao realizar Upload, porfavor verifique seu arquivo.");
    		} else if (retorno.getStatusCode() == HttpStatus.NO_CONTENT) {
    			log.info("Erro ao realizar upload Lote DCMS:" + retorno.getStatusCode().name());
    			return new FileUploadLoteDCMSResponse(retorno.getStatusCode().value(), "Falha ao realizar Upload, porfavor verifique seu arquivo.");
    		} else { //ERRO INESPERADO
    			log.info("Erro ao realizar upload Lote DCMS:" + retorno.getStatusCode().name());
    			return new FileUploadLoteDCMSResponse(retorno.getStatusCode().value(), "Falha ao realizar Upload, porfavor verifique seu arquivo.");
    		}
    		
    	} catch (Exception e) {
    		log.error("Erro ao realizar upload Lote DCMS", e);
    		return new FileUploadLoteDCMSResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro ao realizar Upload Lote DCMS -> " + e);
    	}
    	
    }

}
