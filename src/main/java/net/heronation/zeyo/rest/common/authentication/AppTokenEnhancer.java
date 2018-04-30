package net.heronation.zeyo.rest.common.authentication;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import net.heronation.zeyo.rest.repository.member.Member;

public class AppTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		 final AppUserDetails user = (AppUserDetails) authentication.getPrincipal();
        
		final Map<String, Object> additionalInfo = new HashMap<>(); 
	    additionalInfo.put("roles", user.getAuthorities());
	    additionalInfo.put("member_id", user.getUsername()); 
	    additionalInfo.put("member_seq", user.getId());
	    additionalInfo.put("member_name", user.getName());
	    
	    
	    
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
		return accessToken;
	}
}
