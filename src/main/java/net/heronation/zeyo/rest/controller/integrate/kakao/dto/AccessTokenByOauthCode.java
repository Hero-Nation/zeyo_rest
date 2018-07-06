package net.heronation.zeyo.rest.controller.integrate.kakao.dto;

import lombok.Data; 

@Data
public class AccessTokenByOauthCode {
//	"access_token":"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
//    "token_type":"bearer",
//    "refresh_token":"yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy",
//    "expires_in":43199,
//    "scope":"Basic_Profile"
	
	private String access_token;
	private String token_type;
	private String refresh_token;
	private String expires_in;
	private String scope;
	
	
}
