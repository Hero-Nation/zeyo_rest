package net.heronation.zeyo.rest.service.common;

import java.util.Map;

public interface CommonService {
 
	Map<String,Object> dash_board_statistic();
	
	String send_sms(String p_from_phone,String p_to_phone,String p_msg);
}