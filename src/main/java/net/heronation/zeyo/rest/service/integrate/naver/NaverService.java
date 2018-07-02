package net.heronation.zeyo.rest.service.integrate.naver;

import net.heronation.zeyo.rest.common.controller.CommonException;

public interface NaverService {
	String update_oauth_code_and_get_access_token(String auth_code,String state, String user_ip) throws CommonException;
	String get_access_token_by_refresh_token(Long shopmall_id) throws CommonException;
}
