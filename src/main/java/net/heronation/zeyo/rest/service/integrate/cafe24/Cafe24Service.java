package net.heronation.zeyo.rest.service.integrate.cafe24;

import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

public interface Cafe24Service {
 
	
	Shopmall get_shopmall_temp_identity(Long shopmall_id);
	
	String update_oauth_code_and_get_access_token(String auth_code,String oauth_id);
	
	String get_access_token_by_refresh_token(Long shopmall_id);
	
	String sync_product(Long shopmall_id,int page,String last_id,Long member_id);
}