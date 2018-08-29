package br.com.odontoprev.portalcorretor.test.controller.arquivocontratacao;

import static org.springframework.http.MediaType.APPLICATION_PDF;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.odontoprev.portalcorretor.service.PropostaService;;

//201808241601 - esert - COR-620 tdd para serv disponibiliza pdf pme
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ArquivoContratacaoControllerTestDUDAConfig.class })
@WebAppConfiguration
public class ArquivoContratacaoControllerTestDUDA {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setUpClass() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		Mockito.reset(service);
	}

	@Autowired
	private PropostaService service;

	@Test
	public void testOk200GerarArquivoContratacao() throws Exception {
		// Montando Request
		Long cdEmpresaRequest = 2547L;

		// Montando Given
//		Long cdEmpresaGiven = 2547L;
//		byte[] byteArrayGiven = { 12, 23, 34, 45, 56 };

		// Mockando Service que busca no banco de dados
		//given(service.gerarArquivoContratacao(cdEmpresaGiven)).willReturn(byteArrayGiven);
		//given(service.gerarArquivoContratacao(cdEmpresaGiven)).willReturn([12, 23, 34, 45, 56]);

		// Efetua a requisição na rota e espera um status code
		mvc.perform(get("/downloadContratacao?cdEmpresa=" + cdEmpresaRequest)
				.contentType(APPLICATION_PDF))
				.andExpect(status().isOk())
				//.andExpect(MockMvcResultMatchers.content().bytes(byteArrayGiven))
				;

		// Verifica se os metódos da lógica interna foram chamados
		// BDDMockito.verify(service).getByCdEmpresa(cdEmpresaGiven, true);
	}

	@Test
	public void testNoContent204GetArquivoContratacao() throws Exception {
//		// Montando Request
//		Long cdEmpresaRequest = 9999L;
//
//		// Montando Given
//		Long cdEmpresaGiven = 2510L;
//		ArquivoContratacao dtoGiven = new ArquivoContratacao();
//		dtoGiven.setCodigoEmpresa(cdEmpresaGiven);
//		// dtoGiven.setDataCriacao(new SimpleDateFormat("yyyy-MM-dd
//		// HH:mm:ss").parse("2018-08-24T23:59:59-0300"));;
//		dtoGiven.setDataCriacao(
//				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replace(" ", "T").concat("-0300")); // 201808271608
//		dtoGiven.setNomeArquivo("out.Salão_Unissex_Black_White.20180824011157.pdf");
//		dtoGiven.setTamanhoArquivo(1751L);
//		dtoGiven.setTipoConteudo(MediaType.APPLICATION_PDF_VALUE.toString());
//		dtoGiven.setArquivoBase64(
//				"JVBERi0xLjQKJeLjz9MKMyAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDc2MT4+c3RyZWFtCnicjVfNbhMxGLz7KXwshxj/re29EpVKCEQLuVdRAxGo26hVJR6YAw/AC2Bn12t/tsnnQ5XaO7Mz33ikOM/k3Y4oQx03dHcgQmumHd0I/xHWb98LKiTdfSec8dFxTTnTgx79h+PWafpyJFfb09Pry/51/+f3iR6+0dvH/dOJfj743dPj3+OPhxO9/XT9ZveTcHokm0WCh9fLYWROeTk5i0lcjK4vWsjnF3G6McNs+XpH7sjz+U/SD/7JjX+dHegv/y9nAxej8ZwvN0RZ5hy11rDR0ikujWJG0Ufy9TJrhk3EOOYEzgKwlTVLo6zCYcb672xWMH+gK3NZYi4BbCKCCzaOOA3iEg8bD+ImaPryfIJrJnWixjVuNcd5nhqYsB08gEs8dESAmwrfl2eMTRNMuXT62jHe0c8ZtjYNYwFYavVZGm81dJixsH5G5rLEXAJY6hlGg7is18h4EDdB0339jNS4xq3muNQzlAdwWa+xEQFuKnx39VMPzGanryTTHf2cYWvTMBaApVafpfFWQ4cZC+tnZC5LzCWApZ5hNIjLeo2MB3ETNN3Xz0iNa9xqjks9Q3kAl/UaGxHgpsJ3Vz8VZyI7fWHCpQHt5wxbm4axACy1+iyNtxo6zFhYPyNzWWIuASz1DKNBXNZrZDyIm6Dpvn5GalzjVnNc6hnKA7is19iIADcVvusZl44N4RZh7cikDOc+L/2deyi1AH4GBHOWaXmJABGJMSteYhSeMoKf4y78WBBniqAqfDNaJhXdTe1NfzN/yC71V/dbcR9u7xHsgcaV+I10YZcvV/lM0MqQaa1Z7teyEsgK7n9PmH5dwQemTEO4elArK6isNOO6XzmkOYhwEEXE62ap+FFsRSNiiEcjrjTL/YasbEXcqxuTrISrBw1l1Yq4VzmkqUy4NBURr5uVomxGDPFoxJVmud+QbUbcqxuTrISrBw3lZsS9yiFNycP3fhHxulkpqmbEEI9GXGmW+w3ZZsS9ujHJSrh60FBuRnxR+R/rmXhzCmVuZHN0cmVhbQplbmRvYmoKNSAwIG9iago8PC9Db250ZW50cyAzIDAgUi9UeXBlL1BhZ2UvUmVzb3VyY2VzPDwvRm9udDw8L0YxIDEgMCBSL0YyIDIgMCBSPj4+Pi9QYXJlbnQgNCAwIFIvTWVkaWFCb3hbMCAwIDU5NSA4NDJdPj4KZW5kb2JqCjEgMCBvYmoKPDwvU3VidHlwZS9UeXBlMS9UeXBlL0ZvbnQvQmFzZUZvbnQvSGVsdmV0aWNhLUJvbGQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjIgMCBvYmoKPDwvU3VidHlwZS9UeXBlMS9UeXBlL0ZvbnQvQmFzZUZvbnQvSGVsdmV0aWNhL0VuY29kaW5nL1dpbkFuc2lFbmNvZGluZz4+CmVuZG9iago0IDAgb2JqCjw8L0tpZHNbNSAwIFJdL1R5cGUvUGFnZXMvQ291bnQgMT4+CmVuZG9iago2IDAgb2JqCjw8L1R5cGUvQ2F0YWxvZy9QYWdlcyA0IDAgUj4+CmVuZG9iago3IDAgb2JqCjw8L01vZERhdGUoRDoyMDE4MDgyNDAxMTIwMC0wMycwMCcpL0NyZWF0aW9uRGF0ZShEOjIwMTgwODI0MDExMjAwLTAzJzAwJykvUHJvZHVjZXIoaVRleHSuIDUuNS4zIKkyMDAwLTIwMTQgaVRleHQgR3JvdXAgTlYgXChBR1BMLXZlcnNpb25cKSkvVGl0bGUoT0RPTlRPUFJFViBUZXN0ZSAyM2QyMGgyM20pPj4KZW5kb2JqCnhyZWYKMCA4CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDk2NCAwMDAwMCBuIAowMDAwMDAxMDU3IDAwMDAwIG4gCjAwMDAwMDAwMTUgMDAwMDAgbiAKMDAwMDAwMTE0NSAwMDAwMCBuIAowMDAwMDAwODQzIDAwMDAwIG4gCjAwMDAwMDExOTYgMDAwMDAgbiAKMDAwMDAwMTI0MSAwMDAwMCBuIAp0cmFpbGVyCjw8L0luZm8gNyAwIFIvSUQgWzwxZTIyYWYxODU4ZjA4ODQ1NTMzNjgwYTBjNjI0NTEwMD48MWUyMmFmMTg1OGYwODg0NTUzMzY4MGEwYzYyNDUxMDA+XS9Sb290IDYgMCBSL1NpemUgOD4+CiVpVGV4dC01LjUuMwpzdGFydHhyZWYKMTQzMgolJUVPRgo=");
//
//		// Mockando Service que busca no banco de dados
//		// given(service.getByCdEmpresa(cdEmpresaGiven, true)).willReturn(dtoGiven);
//
//		// Efetua a requisição na rota e espera um status code
//		mvc.perform(get("/arquivocontratacao/empresa/" + cdEmpresaRequest + "/json").contentType(APPLICATION_JSON))
//				.andExpect(status().isNoContent());
	}

	@Test
	public void testOk200GetArquivoByteArray() throws Exception {
//		// Montando Request
//		Long cdEmpresaRequest = 2510L;
//
//		// Montando Given
//		Long cdEmpresaGiven = 2510L;
//		ArquivoContratacao dtoGiven = new ArquivoContratacao();
//		dtoGiven.setCodigoEmpresa(cdEmpresaGiven);
//		// dtoGiven.setDataCriacao(new SimpleDateFormat("yyyy-MM-dd
//		// HH:mm:ss").parse("2018-08-24T23:59:59-0300"));;
//		dtoGiven.setDataCriacao(
//				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replace(" ", "T").concat("-0300")); // 201808271608
//		dtoGiven.setNomeArquivo("out.Salão_Unissex_Black_White.20180824011157.pdf");
//		dtoGiven.setTamanhoArquivo(1751L);
//		dtoGiven.setTipoConteudo(MediaType.APPLICATION_PDF_VALUE.toString());
//		dtoGiven.setArquivoBase64(
//				"JVBERi0xLjQKJeLjz9MKMyAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDc2MT4+c3RyZWFtCnicjVfNbhMxGLz7KXwshxj/re29EpVKCEQLuVdRAxGo26hVJR6YAw/AC2Bn12t/tsnnQ5XaO7Mz33ikOM/k3Y4oQx03dHcgQmumHd0I/xHWb98LKiTdfSec8dFxTTnTgx79h+PWafpyJFfb09Pry/51/+f3iR6+0dvH/dOJfj743dPj3+OPhxO9/XT9ZveTcHokm0WCh9fLYWROeTk5i0lcjK4vWsjnF3G6McNs+XpH7sjz+U/SD/7JjX+dHegv/y9nAxej8ZwvN0RZ5hy11rDR0ikujWJG0Ufy9TJrhk3EOOYEzgKwlTVLo6zCYcb672xWMH+gK3NZYi4BbCKCCzaOOA3iEg8bD+ImaPryfIJrJnWixjVuNcd5nhqYsB08gEs8dESAmwrfl2eMTRNMuXT62jHe0c8ZtjYNYwFYavVZGm81dJixsH5G5rLEXAJY6hlGg7is18h4EDdB0339jNS4xq3muNQzlAdwWa+xEQFuKnx39VMPzGanryTTHf2cYWvTMBaApVafpfFWQ4cZC+tnZC5LzCWApZ5hNIjLeo2MB3ETNN3Xz0iNa9xqjks9Q3kAl/UaGxHgpsJ3Vz8VZyI7fWHCpQHt5wxbm4axACy1+iyNtxo6zFhYPyNzWWIuASz1DKNBXNZrZDyIm6Dpvn5GalzjVnNc6hnKA7is19iIADcVvusZl44N4RZh7cikDOc+L/2deyi1AH4GBHOWaXmJABGJMSteYhSeMoKf4y78WBBniqAqfDNaJhXdTe1NfzN/yC71V/dbcR9u7xHsgcaV+I10YZcvV/lM0MqQaa1Z7teyEsgK7n9PmH5dwQemTEO4elArK6isNOO6XzmkOYhwEEXE62ap+FFsRSNiiEcjrjTL/YasbEXcqxuTrISrBw1l1Yq4VzmkqUy4NBURr5uVomxGDPFoxJVmud+QbUbcqxuTrISrBw3lZsS9yiFNycP3fhHxulkpqmbEEI9GXGmW+w3ZZsS9ujHJSrh60FBuRnxR+R/rmXhzCmVuZHN0cmVhbQplbmRvYmoKNSAwIG9iago8PC9Db250ZW50cyAzIDAgUi9UeXBlL1BhZ2UvUmVzb3VyY2VzPDwvRm9udDw8L0YxIDEgMCBSL0YyIDIgMCBSPj4+Pi9QYXJlbnQgNCAwIFIvTWVkaWFCb3hbMCAwIDU5NSA4NDJdPj4KZW5kb2JqCjEgMCBvYmoKPDwvU3VidHlwZS9UeXBlMS9UeXBlL0ZvbnQvQmFzZUZvbnQvSGVsdmV0aWNhLUJvbGQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjIgMCBvYmoKPDwvU3VidHlwZS9UeXBlMS9UeXBlL0ZvbnQvQmFzZUZvbnQvSGVsdmV0aWNhL0VuY29kaW5nL1dpbkFuc2lFbmNvZGluZz4+CmVuZG9iago0IDAgb2JqCjw8L0tpZHNbNSAwIFJdL1R5cGUvUGFnZXMvQ291bnQgMT4+CmVuZG9iago2IDAgb2JqCjw8L1R5cGUvQ2F0YWxvZy9QYWdlcyA0IDAgUj4+CmVuZG9iago3IDAgb2JqCjw8L01vZERhdGUoRDoyMDE4MDgyNDAxMTIwMC0wMycwMCcpL0NyZWF0aW9uRGF0ZShEOjIwMTgwODI0MDExMjAwLTAzJzAwJykvUHJvZHVjZXIoaVRleHSuIDUuNS4zIKkyMDAwLTIwMTQgaVRleHQgR3JvdXAgTlYgXChBR1BMLXZlcnNpb25cKSkvVGl0bGUoT0RPTlRPUFJFViBUZXN0ZSAyM2QyMGgyM20pPj4KZW5kb2JqCnhyZWYKMCA4CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDk2NCAwMDAwMCBuIAowMDAwMDAxMDU3IDAwMDAwIG4gCjAwMDAwMDAwMTUgMDAwMDAgbiAKMDAwMDAwMTE0NSAwMDAwMCBuIAowMDAwMDAwODQzIDAwMDAwIG4gCjAwMDAwMDExOTYgMDAwMDAgbiAKMDAwMDAwMTI0MSAwMDAwMCBuIAp0cmFpbGVyCjw8L0luZm8gNyAwIFIvSUQgWzwxZTIyYWYxODU4ZjA4ODQ1NTMzNjgwYTBjNjI0NTEwMD48MWUyMmFmMTg1OGYwODg0NTUzMzY4MGEwYzYyNDUxMDA+XS9Sb290IDYgMCBSL1NpemUgOD4+CiVpVGV4dC01LjUuMwpzdGFydHhyZWYKMTQzMgolJUVPRgo=");
//
//		// Mockando Service que busca no banco de dados
//		// given(service.getByCdEmpresa(cdEmpresaGiven, true)).willReturn(dtoGiven);
//
//		// Efetua a requisição na rota e espera um status code
//		mvc.perform(get("/arquivocontratacao/empresa/" + cdEmpresaRequest + "/arquivo").contentType(APPLICATION_JSON))
//				.andExpect(status().isOk());
//
//		// Verifica se os metódos da lógica interna foram chamados
//		// BDDMockito.verify(service).getByCdEmpresa(cdEmpresaGiven, true);
	}

	@Test
	public void testNoContent204GetArquivoByteArray() throws Exception {
//		// Montando Request
//		Long cdEmpresaRequest = 9999L;
//
//		// Montando Given
//		Long cdEmpresaGiven = 2510L;
//		ArquivoContratacao dtoGiven = new ArquivoContratacao();
//		dtoGiven.setCodigoEmpresa(cdEmpresaGiven);
//		// dtoGiven.setDataCriacao(new SimpleDateFormat("yyyy-MM-dd
//		// HH:mm:ss").parse("2018-08-24T23:59:59-0300"));;
//		dtoGiven.setDataCriacao(
//				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replace(" ", "T").concat("-0300")); // 201808271608
//		dtoGiven.setNomeArquivo("out.Salão_Unissex_Black_White.20180824011157.pdf");
//		dtoGiven.setTamanhoArquivo(1751L);
//		dtoGiven.setTipoConteudo(MediaType.APPLICATION_PDF_VALUE.toString());
//		dtoGiven.setArquivoBase64(
//				"JVBERi0xLjQKJeLjz9MKMyAwIG9iago8PC9GaWx0ZXIvRmxhdGVEZWNvZGUvTGVuZ3RoIDc2MT4+c3RyZWFtCnicjVfNbhMxGLz7KXwshxj/re29EpVKCEQLuVdRAxGo26hVJR6YAw/AC2Bn12t/tsnnQ5XaO7Mz33ikOM/k3Y4oQx03dHcgQmumHd0I/xHWb98LKiTdfSec8dFxTTnTgx79h+PWafpyJFfb09Pry/51/+f3iR6+0dvH/dOJfj743dPj3+OPhxO9/XT9ZveTcHokm0WCh9fLYWROeTk5i0lcjK4vWsjnF3G6McNs+XpH7sjz+U/SD/7JjX+dHegv/y9nAxej8ZwvN0RZ5hy11rDR0ikujWJG0Ufy9TJrhk3EOOYEzgKwlTVLo6zCYcb672xWMH+gK3NZYi4BbCKCCzaOOA3iEg8bD+ImaPryfIJrJnWixjVuNcd5nhqYsB08gEs8dESAmwrfl2eMTRNMuXT62jHe0c8ZtjYNYwFYavVZGm81dJixsH5G5rLEXAJY6hlGg7is18h4EDdB0339jNS4xq3muNQzlAdwWa+xEQFuKnx39VMPzGanryTTHf2cYWvTMBaApVafpfFWQ4cZC+tnZC5LzCWApZ5hNIjLeo2MB3ETNN3Xz0iNa9xqjks9Q3kAl/UaGxHgpsJ3Vz8VZyI7fWHCpQHt5wxbm4axACy1+iyNtxo6zFhYPyNzWWIuASz1DKNBXNZrZDyIm6Dpvn5GalzjVnNc6hnKA7is19iIADcVvusZl44N4RZh7cikDOc+L/2deyi1AH4GBHOWaXmJABGJMSteYhSeMoKf4y78WBBniqAqfDNaJhXdTe1NfzN/yC71V/dbcR9u7xHsgcaV+I10YZcvV/lM0MqQaa1Z7teyEsgK7n9PmH5dwQemTEO4elArK6isNOO6XzmkOYhwEEXE62ap+FFsRSNiiEcjrjTL/YasbEXcqxuTrISrBw1l1Yq4VzmkqUy4NBURr5uVomxGDPFoxJVmud+QbUbcqxuTrISrBw3lZsS9yiFNycP3fhHxulkpqmbEEI9GXGmW+w3ZZsS9ujHJSrh60FBuRnxR+R/rmXhzCmVuZHN0cmVhbQplbmRvYmoKNSAwIG9iago8PC9Db250ZW50cyAzIDAgUi9UeXBlL1BhZ2UvUmVzb3VyY2VzPDwvRm9udDw8L0YxIDEgMCBSL0YyIDIgMCBSPj4+Pi9QYXJlbnQgNCAwIFIvTWVkaWFCb3hbMCAwIDU5NSA4NDJdPj4KZW5kb2JqCjEgMCBvYmoKPDwvU3VidHlwZS9UeXBlMS9UeXBlL0ZvbnQvQmFzZUZvbnQvSGVsdmV0aWNhLUJvbGQvRW5jb2RpbmcvV2luQW5zaUVuY29kaW5nPj4KZW5kb2JqCjIgMCBvYmoKPDwvU3VidHlwZS9UeXBlMS9UeXBlL0ZvbnQvQmFzZUZvbnQvSGVsdmV0aWNhL0VuY29kaW5nL1dpbkFuc2lFbmNvZGluZz4+CmVuZG9iago0IDAgb2JqCjw8L0tpZHNbNSAwIFJdL1R5cGUvUGFnZXMvQ291bnQgMT4+CmVuZG9iago2IDAgb2JqCjw8L1R5cGUvQ2F0YWxvZy9QYWdlcyA0IDAgUj4+CmVuZG9iago3IDAgb2JqCjw8L01vZERhdGUoRDoyMDE4MDgyNDAxMTIwMC0wMycwMCcpL0NyZWF0aW9uRGF0ZShEOjIwMTgwODI0MDExMjAwLTAzJzAwJykvUHJvZHVjZXIoaVRleHSuIDUuNS4zIKkyMDAwLTIwMTQgaVRleHQgR3JvdXAgTlYgXChBR1BMLXZlcnNpb25cKSkvVGl0bGUoT0RPTlRPUFJFViBUZXN0ZSAyM2QyMGgyM20pPj4KZW5kb2JqCnhyZWYKMCA4CjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDk2NCAwMDAwMCBuIAowMDAwMDAxMDU3IDAwMDAwIG4gCjAwMDAwMDAwMTUgMDAwMDAgbiAKMDAwMDAwMTE0NSAwMDAwMCBuIAowMDAwMDAwODQzIDAwMDAwIG4gCjAwMDAwMDExOTYgMDAwMDAgbiAKMDAwMDAwMTI0MSAwMDAwMCBuIAp0cmFpbGVyCjw8L0luZm8gNyAwIFIvSUQgWzwxZTIyYWYxODU4ZjA4ODQ1NTMzNjgwYTBjNjI0NTEwMD48MWUyMmFmMTg1OGYwODg0NTUzMzY4MGEwYzYyNDUxMDA+XS9Sb290IDYgMCBSL1NpemUgOD4+CiVpVGV4dC01LjUuMwpzdGFydHhyZWYKMTQzMgolJUVPRgo=");
//
//		// Mockando Service que busca no banco de dados
//		// given(service.getByCdEmpresa(cdEmpresaGiven, true)).willReturn(dtoGiven);
//
//		// Efetua a requisição na rota e espera um status code
//		mvc.perform(get("/arquivocontratacao/empresa/" + cdEmpresaRequest + "/arquivo").contentType(APPLICATION_JSON))
//				.andExpect(status().isNoContent());
	}

}
