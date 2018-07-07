package net.heronation.zeyo.rest.service.integrate.cafe24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.constants.CommonConstants;

@Slf4j
@Service
@Transactional
public class Cafe24Api {

	@Autowired
	private RestTemplate restTemplate;

	String getAccessTokenByOauthCode() {
		log.debug("updateAccessTokenByOauthCode");

		try {

			final ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
			resourceDetails.setClientId("myclient");
			resourceDetails.setClientSecret("mysecret");
			resourceDetails.setGrantType("client_credentials");
			resourceDetails.setAccessTokenUri("");
			 
			OAuth2RestTemplate this_template = this.getOauth2RestTemplate(resourceDetails);
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return CommonConstants.FAIL;
		}
		
		return "";
	}

	public OAuth2RestTemplate getOauth2RestTemplate(OAuth2ProtectedResourceDetails details) { 
		return new OAuth2RestTemplate(details); 
	}

	String getAccessTokenByRefreshToken() {

		log.debug("updateAccessTokenByOauthCode");

		return "";
	}

}
