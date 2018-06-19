package net.heronation.zeyo.rest.oauth;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.controller.integrate.cafe24.dto.AccessTokenByOauthCode;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthTest {

	public class MyResponseErrorHandler implements ResponseErrorHandler {

		@Override
		public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {
			log.debug("handleError");
			log.debug(clienthttpresponse.getStatusText());

			String text = IOUtils.toString(clienthttpresponse.getBody(), StandardCharsets.UTF_8.name());
			log.debug(text);
			if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {

			}
		}

		@Override
		public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {
			log.debug("hasError");
			
			log.debug(clienthttpresponse.getStatusText());

			String text = IOUtils.toString(clienthttpresponse.getBody(), StandardCharsets.UTF_8.name());
			log.debug(text);
			
			if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {

				if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {

					return true;
				}
			}
			return false;
		}
	}

	@Test
	public void getAccessTokenByOauthCode() {
		log.debug("updateAccessTokenByOauthCode");

		
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setOutputStreaming(false);
		
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.setMessageConverters(converters);
		restTemplate.setErrorHandler(new MyResponseErrorHandler());

		String userAndPass = "hro4E2dgPtviiTY3Bez9DA:iieLmN6UCqYUvY2oTJ4irF";

		// Request 샘플
		// curl -X POST \
		// 'https://{{mallid}}.cafe24api.com/api/v2/oauth/token' \
		// -H 'Authorization: Basic
		// S3hWd2RCTjdPVk5uQjNGMHM3UzFNRDpFaEZnM0xYak1KR21BZWV5MUliaXhI' \
		// -H 'Content-Type: application/x-www-form-urlencoded' \
		// -d 'grant_type=authorization_code' \
		// -d 'code=xu2xG1rfDimVP2oe6fopRE' \
		// -d 'redirect_uri=https://test.com/oauth/callback'

		//log.debug("Basic " + Base64Utils.encode(userAndPass.getBytes()));

		String uri = "https://heronation.cafe24api.com/api/v2/oauth/token";

		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
		body.add("grant_type", "authorization_code");
		body.add("code", "lv0ZSUOTAN1inJR5MBxUXA");
		body.add("redirect_uri", "https://www.zeyo.co.kr/oauth/cafe24/callback");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Basic aHJvNEUyZGdQdHZpaVRZM0JlejlEQTppaWVMbU42VUNxWVV2WTJvVEo0aXJG");
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers); 
		
		try {

			AccessTokenByOauthCode result = restTemplate.postForObject(uri, request, AccessTokenByOauthCode.class);
			log.debug(result.toString());
		} catch (HttpClientErrorException e) {
			e.printStackTrace();
			log.error(e.getMessage() + "");
			log.error(e.getStatusCode() + "");
			log.error(e.getStatusText());

		}
 
		
		// ResourceOwnerPasswordResourceDetails resourceDetails = new
		// ResourceOwnerPasswordResourceDetails();
		// resourceDetails.setUsername("heronation");
		// resourceDetails.setPassword("ironman4$");
		// resourceDetails.setAccessTokenUri(String.format("https://%s.cafe24api.com/api/v2/oauth/token",
		// "heronation"));
		// resourceDetails.setClientId("hro4E2dgPtviiTY3Bez9DA");
		// resourceDetails.setClientSecret("iieLmN6UCqYUvY2oTJ4irF");
		// resourceDetails.setGrantType("password");
		// resourceDetails.setScope(Arrays.asList("mall.read_category",
		// "mall.read_customer", "mall.read_order",
		// "mall.read_product", "mall.read_supply"));
		// DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		//
		// OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails,
		// clientContext);
		// restTemplate.setMessageConverters(Arrays.asList(new
		// MappingJackson2HttpMessageConverter()));
		//
		// Product[] list = restTemplate.getForObject(
		// String.format("https://%s.cafe24api.com/api/v2/admin/products",
		// "heronation"), Product[].class);
		//
		// for (Product p : list) {
		// log.debug(p.toString());
		// }

		// try {
		//
		// // final ClientCredentialsResourceDetails resourceDetails = new
		// // ClientCredentialsResourceDetails();
		// // resourceDetails.setClientId("hro4E2dgPtviiTY3Bez9DA");
		// // resourceDetails.setClientSecret("iieLmN6UCqYUvY2oTJ4irF");
		// // resourceDetails.setGrantType("client_credentials");
		// // resourceDetails.setAccessTokenUri("");
		// //
		// // OAuth2RestTemplate this_template =
		// // this.getOauth2RestTemplate(resourceDetails);
		//
		// AuthorizationCodeResourceDetails resourceDetails = new
		// AuthorizationCodeResourceDetails();
		// resourceDetails
		// .setAccessTokenUri(String.format("https://%s.cafe24api.com/api/v2/oauth/token",
		// "heronation"));
		// resourceDetails.setUserAuthorizationUri(
		// String.format("https://%s.cafe24api.com/api/v2/oauth/authorize",
		// "heronation"));
		// resourceDetails.setPreEstablishedRedirectUri("https://www.zeyo.co.kr/oauth/cafe24/callback");
		// resourceDetails.setClientId("hro4E2dgPtviiTY3Bez9DA");
		// resourceDetails.setClientSecret("iieLmN6UCqYUvY2oTJ4irF");
		// resourceDetails.setGrantType("authorization_code");
		// resourceDetails.setScope(Arrays.asList("mall.read_category",
		// "mall.read_customer", "mall.read_order",
		// "mall.read_product", "mall.read_supply"));
		//
		// DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
		//
		// OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails,
		// clientContext);
		//
		// restTemplate.setMessageConverters(Arrays.asList(new
		// MappingJackson2HttpMessageConverter()));
		//
		// final Product[] list = restTemplate.getForObject(
		// String.format("https://%s.cafe24api.com/api/v2/oauth/token", "heronation"),
		// Product[].class);
		//
		// for (Product p : list) {
		// log.debug(p.toString());
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public OAuth2RestTemplate getOauth2RestTemplate(OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details);
	}

}