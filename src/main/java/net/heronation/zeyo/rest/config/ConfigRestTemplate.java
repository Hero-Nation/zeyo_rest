package net.heronation.zeyo.rest.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

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