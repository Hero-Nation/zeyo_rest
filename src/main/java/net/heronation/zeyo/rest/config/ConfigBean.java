package net.heronation.zeyo.rest.config;

import java.util.Properties;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.Ordered;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import net.heronation.zeyo.rest.common.authentication.AppTokenEnhancer;
import net.heronation.zeyo.rest.controller.company_no_history.CompanyNoHistoryDistinctNameConverter;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryDistinctNameDto;

@Configuration
public class ConfigBean {

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean // Magic entry
	public DispatcherServlet dispatcherServlet() {
		DispatcherServlet ds = new DispatcherServlet();
		ds.setThrowExceptionIfNoHandlerFound(true);
		return ds;
	}

	@Bean
	public JavaMailSender getJavaMailSender() {
 
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setUsername("help@heronation.net");
		mailSender.setPassword("help1234"); 

 
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.starttls.enable", "false");
		properties.setProperty("mail.smtp.host", "mail.heronation.net");
		properties.setProperty("mail.smtp.auth", "false");
		properties.setProperty("mail.smtp.port", "25");
//		properties.setProperty("mail.smtp.socketFactory.port", port);
//		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		mailSender.setJavaMailProperties(properties);
		
		
		return mailSender;
		
	}

	// @Bean(name = "multipartResolver")
	// public CommonsMultipartResolver multipartResolver() {
	// CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	// multipartResolver.setMaxUploadSize(1024 * 1024 * 20);
	// return multipartResolver;
	// }

	// @Bean
	// public StandardServletMultipartResolver multipartResolver() {
	// return new StandardServletMultipartResolver();
	// }
}
