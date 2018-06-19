package net.heronation.zeyo.rest.service.integrate;

import java.util.List;

import net.heronation.zeyo.rest.common.value.ToggleVO;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;

public interface IntegrateSerivce {

	String sync_product(Long shopmall_id);
	
	Shopmall get_shopmall_temp_identity(Long shopmall_id);
	
	String update_oauth_code(String auth_code,String temp_identity);
	
}
