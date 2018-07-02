package net.heronation.zeyo.rest.service.integrate.kakao;

public interface KakaoService {
	String update_oauth_code_and_get_access_token(String auth_code,String oauth_id);
}
