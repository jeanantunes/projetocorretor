package br.com.odontoprev.portalcorretor.config;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
public class CorretorWebConfig extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    
    @Bean
    public ViewResolver htmlViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine(htmlTemplateResolver()));
        resolver.setContentType("text/html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setViewNames(new String[]{"*.html"});
        return resolver;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**",
                "/fonts/**",
                "fragments/**",
                 "/app/**", 
                 "/config/**", 
                 "/repositorio/**",
                 "/slick/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/",
                        "classpath:/static/fonts/",
                        "classpath:fragments/",
                        "classpath:/static/app/", 
                        "classpath:/static/config/", 
                        "classpath:/static/repositorio/",
                        "classpath:/static/slick/");
    }
        
    private TemplateEngine templateEngine(ITemplateResolver templateResolver) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(templateResolver);
		engine.setTemplateEngineMessageSource(messageSource());
		return engine;
	}
    
    private ITemplateResolver htmlTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("templates/");
        resolver.setCacheable(false);
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }
    
    
    @Bean
    @Description("Spring Message Resolver")
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
    
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("pt_BR"));
        return localeResolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {        
        registry.addViewController("/login").setViewName("login"); 
        registry.addViewController("/anteriorLogado.html").setViewName("anteriorLogado");
        registry.addViewController("/cadastro_usuario.html").setViewName("cadastro_usuario");
        registry.addViewController("/cartao_pf_debito.html").setViewName("cartao_pf_debito");
        registry.addViewController("/compra_pf_boleto.html").setViewName("compra_pf_boleto");
        registry.addViewController("/compra_pf_sucesso.html").setViewName("compra_pf_sucesso");
        registry.addViewController("/fale_conosco.html").setViewName("fale_conosco");
        registry.addViewController("/fale_conosco_login.html").setViewName("fale_conosco_login");
        registry.addViewController("/fale_conosco_mensagem.html").setViewName("fale_conosco_mensagem");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/lista_proposta.html").setViewName("lista_proposta");
        registry.addViewController("/logado.html").setViewName("logado");
        registry.addViewController("/meus_dados-backup.html").setViewName("meus_dados-backup");
        registry.addViewController("/meus_dados.html").setViewName("meus_dados");
        registry.addViewController("/proposta_pme_enviada.html").setViewName("proposta_pme_enviada");
        registry.addViewController("/rede_credenciada.html").setViewName("rede_credenciada");
        registry.addViewController("/resumo_pf_proposta.html").setViewName("resumo_pf_proposta");
        registry.addViewController("/resumo_pme_proposta.html").setViewName("resumo_pme_proposta");
        registry.addViewController("/vencimento_fatura_pme.html").setViewName("vencimento_fatura_pme");
        registry.addViewController("/venda_index_pf.html").setViewName("venda_index_pf");
        registry.addViewController("/venda_index_pme.html").setViewName("venda_index_pme");
        registry.addViewController("/venda_pf_dados_dependentes.html").setViewName("venda_pf_dados_dependentes");
        registry.addViewController("/venda_pf_dados_proposta.html").setViewName("venda_pf_dados_proposta");
        registry.addViewController("/venda_pf_editar.html").setViewName("venda_pf_editar");
        registry.addViewController("/venda_pme_addPlano.html").setViewName("venda_pme_addPlano");
        registry.addViewController("/venda_pme_beneficiarios.html").setViewName("venda_pme_beneficiarios");
        registry.addViewController("/venda_pme_beneficiarios_lista.html").setViewName("venda_pme_beneficiarios_lista");
        registry.addViewController("/venda_pme_dados_proposta.html").setViewName("venda_pme_dados_proposta");
        registry.addViewController("/venda_pme_dependentes.html").setViewName("venda_pme_dependentes");
        registry.addViewController("/venda_pme_editar.html").setViewName("venda_pme_editar");
        registry.addViewController("/venda_pme").setViewName("venda_pme");
    }
}
