package br.com.odontoprev.portalcorretor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomAuthenticationProvider provider;

    @Autowired
    private RefererAuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("login","index", "/cadastro/**", "/img/**", "/css/**", "/js/**", "/fonts/**","/get_token/**", "/esqueci-minha-senha/**", "/recuperar_senha", "/propertie", "/propertie/**").permitAll()
                .antMatchers( "/app/**", "/config/**", "/repositorio/**", "/slick/**", "/componente/**").permitAll()
                .antMatchers("/termoAceite/**").permitAll()
                .antMatchers("/**").hasAnyAuthority("Corretora","Corretor","Adiministrador")
                .antMatchers("/corretora/**").hasAuthority("Corretora")
                .antMatchers("/admin/**").hasAuthority("Adiministrador")
                .antMatchers("/forcavenda/**").hasAuthority("Corretor")
                .antMatchers("/get_token").permitAll()
                .antMatchers("/cadastro/**").permitAll()
                .antMatchers("/cadastro").permitAll()
                .antMatchers("/recuperar_senha").permitAll()
                .antMatchers("/propertie/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(this.successHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
                
         http.headers()
				.frameOptions().sameOrigin()
				.httpStrictTransportSecurity().disable()
				;
         http.csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.provider);

    }


}