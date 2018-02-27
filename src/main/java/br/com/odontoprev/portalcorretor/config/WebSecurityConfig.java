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
    	//TODO: Quas as URL protegidas e as não protegidas
        http
            .authorizeRequests()
                .antMatchers("/cadastro").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .successHandler(this.successHandler)
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(this.provider);

    }

  
}