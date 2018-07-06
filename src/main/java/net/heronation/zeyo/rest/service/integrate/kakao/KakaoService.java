package net.heronation.zeyo.rest.service.integrate.kakao;

import net.heronation.zeyo.rest.common.controller.CommonException;

public interface KakaoService {
	String update_oauth_code_and_get_access_token(String auth_code,String state) throws CommonException ;
	String get_access_token_by_refresh_token(Long shopmall_id) throws CommonException;
}
