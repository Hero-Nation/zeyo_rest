package net.heronation.zeyo.rest.integrate.service.common;

import java.util.Map;

import net.heronation.zeyo.rest.ip_temp_info.repository.IpTempInfo;
import net.heronation.zeyo.rest.item.repository.Item;

public interface IntegrateCommonService {

	boolean haveIpSession(String user_ip, String shop_type, String shop_id, String product_id);

	boolean haveIpSession(String user_ip);

	IpTempInfo getIpTempInfo(String user_ip);

	Map<String, Object> get_zeyo_product_id(String shop_type, String shop_id, String product_id);

	Item get_zeyo_item(long item_id);

	Map<String, Object> get_zeyo_size(long item_id);
}
