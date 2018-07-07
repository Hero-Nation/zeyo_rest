package net.heronation.zeyo.rest.controller.integrate.cafe24.dto;

import lombok.Data;

@Data
public class AccessTokenByOauthCode {
	private String access_token;
	private String expires_at;
	private String refresh_token;
	private String client_id;
	private String mall_id;
	private String user_id;
	private String[] scopes;
	private String issued_at;
}
