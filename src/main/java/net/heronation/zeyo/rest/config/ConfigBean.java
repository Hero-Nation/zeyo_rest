package net.heronation.zeyo.rest.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.DispatcherServlet;

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
