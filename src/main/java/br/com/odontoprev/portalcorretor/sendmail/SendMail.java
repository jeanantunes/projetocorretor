package br.com.odontoprev.portalcorretor.sendmail;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.odontoprev.api.manager.client.token.ApiManagerToken;
import br.com.odontoprev.api.manager.client.token.ApiManagerTokenFactory;
import br.com.odontoprev.api.manager.client.token.ApiToken;
import br.com.odontoprev.api.manager.client.token.enumerator.ApiManagerTokenEnum;
import br.com.odontoprev.api.manager.client.token.util.ConfigurationUtils;
import br.com.odontoprev.portalcorretor.model.FaleConoscoForcaVendasModel;
import br.com.odontoprev.portalcorretor.serviceEmail.ApiException;
import br.com.odontoprev.portalcorretor.serviceEmail.api.DefaultApi;
import br.com.odontoprev.portalcorretor.serviceEmail.model.RequestEmail;

@Component
public class SendMail {

	@Value("${SENDMAIL_ENDPOINT_URL}")
	private String SENDMAIL_ENDPOINT_URL;
	
	public void sendMail(FaleConoscoForcaVendasModel faleConoscoForcaVendasModel) {
		
		try {
			
			ApiManagerToken apiManager = ApiManagerTokenFactory.create(ApiManagerTokenEnum.WSO2, "PORTAL_CORRETOR_WEB");
			ApiToken apiToken = apiManager.generateToken();
			
			DefaultApi apiInstance = new DefaultApi();
			//String SENDMAIL_ENDPOINT_URL = "sendemail/1.0";
			apiInstance.getApiClient().setBasePath(ConfigurationUtils.getURLGetToken().replaceAll("/token","/"+ SENDMAIL_ENDPOINT_URL ));
			apiInstance.getApiClient().setAccessToken(apiToken.getAccessToken());
			//String ab = "Authorization: Bearer "+apiToken.getAccessToken() //exemplo roberto
			RequestEmail body = new RequestEmail(); // RequestEmail
			String msg = faleConoscoForcaVendasModel.getNomeCorretor() + ";";
			msg += faleConoscoForcaVendasModel.getNomeCorretora() + ";";
			msg += faleConoscoForcaVendasModel.getTipoMensagem() + ";";
			msg += faleConoscoForcaVendasModel.getMensagem() + ".";
			body.setBody("Fale conosco: "+ msg );
			body.setRecepientName("Prezados");
			body.setRecepients(Arrays.asList(new String [] {"sgoes@odontoprev.com.br","esertorio@ext.vectoritcgroup.com","duda.sertorio@gmail.com"}));
			body.setSender("arquitetura.ti@odontoprev.com.br");
			body.setSenderName("Arquitetura");
			body.setType("text/html");
			body.setSubject("ASSUNTO2");

			apiInstance.sendEmail(body);
		} catch (ApiException e) {
			//LOGGER.error(e.getResponseBody());
			System.out.println(e.getResponseBody());
			//LOGGER.error("Exception when calling DefaultApi#sendEmail");
			System.out.println("Exception when calling DefaultApi#sendEmail");
			e.printStackTrace();
		} catch(Exception ex) {
			//LOGGER.error(ex.getLocalizedMessage());
			System.out.println(ex.getLocalizedMessage());
		}
	}
}
