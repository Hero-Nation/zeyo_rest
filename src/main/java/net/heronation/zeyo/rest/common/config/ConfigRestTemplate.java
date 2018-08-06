package net.heronation.zeyo.rest.common.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import net.heronation.zeyo.rest.common.LoggingRequestInterceptor;

@Configuration
public class ConfigRestTemplate {
	@Bean
	public RestTemplate restTemplate() {
		
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		BufferingClientHttpRequestFactory requestFactory = new BufferingClientHttpRequestFactory(httpRequestFactory);
		// requestFactory.setOutputStreaming(false);

		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		// converters.add(new StringHttpMessageConverter()); 
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		restTemplate.setMessageConverters(converters);
		
		restTemplate.setErrorHandler(responseErrorHandler());
		
		return restTemplate;
	}

	private ResponseErrorHandler responseErrorHandler() {

		ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler() {

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}
		};

		return errorHandler;
	}

}