package net.heronation.zeyo.rest.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.heronation.zeyo.rest.common.authentication.AppUserDetailService;


@Configuration
@EnableWebSecurity
@Import(ConfigRestTemplate.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class ConfigWebSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	AppUserDetailService userService;
	
    
    @Autowired
	PasswordEncoder oPasswordEncoder;
	
	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.inMemoryAuthentication()
//		.withUser("john").password("123").roles("USER")
//		.and().withUser("tom").password("111").roles("ADMIN")
//		.and().withUser("user1").password("pass").roles("USER")
//		.and().withUser("admin").password("nimda").roles("ADMIN");
		
		auth.userDetailsService(userService).passwordEncoder(oPasswordEncoder);
		//auth.userDetailsService(userService);
		
		
	}

	
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {

		http
		.requiresChannel().anyRequest().requiresSecure()
		.and()
		.authorizeRequests()
				.antMatchers("/login").permitAll()
				.antMatchers("/oauth/token/**").permitAll()
				.antMatchers("/tokens/**").permitAll()
		
		.antMatchers("/api/*").authenticated()
				.and().csrf().disable();
		

 
	}

}