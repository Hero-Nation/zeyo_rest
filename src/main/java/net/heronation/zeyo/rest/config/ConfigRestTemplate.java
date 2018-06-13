package net.heronation.zeyo.rest.config;

import java.io.IOException;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigRestTemplate {
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().customizers((RestTemplateCustomizer) restTemplate -> {
			restTemplate.setErrorHandler(responseErrorHandler());
		}).build();
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