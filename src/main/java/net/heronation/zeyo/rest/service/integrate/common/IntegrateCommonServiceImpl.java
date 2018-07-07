package net.heronation.zeyo.rest.service.integrate.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.consumer.Consumer;
import net.heronation.zeyo.rest.repository.consumer.ConsumerRepository;
import net.heronation.zeyo.rest.repository.consumer.QConsumer;
import net.heronation.zeyo.rest.repository.ip_temp_info.IpTempInfo;
import net.heronation.zeyo.rest.repository.ip_temp_info.IpTempInfoRepository;
import net.heronation.zeyo.rest.repository.ip_temp_info.QIpTempInfo;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item_scmm_so_value.ItemScmmSoValueRepository;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;

@Slf4j
@Service
@Transactional
public class IntegrateCommonServiceImpl implements IntegrateCommonService {

	@Autowired
	ConsumerRepository consumerRepository;

	@Autowired
	IpTempInfoRepository ipTempInfoRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ShopmallRepository shopmallRepository;


	@Autowired
	ItemScmmSoValueRepository itemScmmSoValueRepository;

	
	
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional
	public boolean haveIpSession(String user_ip, String shop_type, String shop_id, String product_id) {
		QConsumer qc = QConsumer.consumer;

		Consumer c = consumerRepository.findOne(qc.session.eq(user_ip)
				.and(qc.lastAccessDt.after(DateTime.now().minusMinutes(30))).and(qc.useYn.eq("Y")));

		if (c == null) {
			// 세션이 종료된 상황이라면 현재 정보를 임시로 저장한다.

			QIpTempInfo qiti = QIpTempInfo.ipTempInfo;
			IpTempInfo this_iti = ipTempInfoRepository.findOne(qiti.ip.eq(user_ip));

			if (this_iti == null) {
				IpTempInfo imi = new IpTempInfo();
				imi.setIp(user_ip);
				imi.setProductId(product_id);
				imi.setShopType(shop_type);
				imi.setShopId(shop_id);
				imi.setCreateDt(new DateTime());
				ipTempInfoRepository.save(imi);
			} else {
				this_iti.setIp(user_ip);
				this_iti.setProductId(product_id);
				this_iti.setShopType(shop_type);
				this_iti.setShopId(shop_id);
			}

			return false;
		} else {
			// 세션이 살아있는 상황이라면..

 
			c.setLastAccessDt(new DateTime());
			return true;
		}

	}

	@Override
	@Transactional
	public boolean haveIpSession(String user_ip) {
		QConsumer qc = QConsumer.consumer;

		Consumer c = consumerRepository.findOne(qc.session.eq(user_ip)
				.and(qc.lastAccessDt.after(DateTime.now().minusMinutes(30))).and(qc.useYn.eq("Y")));

		if (c == null) {
			// 세션이 종료된 상황이라면 현재 정보를 임시로 저장한다.

			return false;
		} else {
			// 세션이 살아있는 상황이라면..

			c.setLastAccessDt(new DateTime());
			return true;
		}
	}

	@Override
	@Transactional
	public IpTempInfo getIpTempInfo(String user_ip) {

		QIpTempInfo qiti = QIpTempInfo.ipTempInfo;
		IpTempInfo this_iti = ipTempInfoRepository.findOne(qiti.ip.eq(user_ip));
		return this_iti;
	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> get_zeyo_product_id(String shop_type, String shop_id, String product_id) {

		StringBuffer get_query = new StringBuffer();
		get_query.append("SELECT i.id,i.link_yn ");
		get_query.append("FROM   item_shopmall_map ism ");
		get_query.append("       LEFT JOIN shopmall s ");
		get_query.append("              ON ism.shopmall_id = s.id ");
		get_query.append("                 AND s.use_yn = 'Y' ");
		get_query.append("       LEFT JOIN item i ");
		get_query.append("              ON ism.item_id = i.id ");
		get_query.append("                 AND i.use_yn = 'Y' ");
		get_query.append("WHERE  ism.use_yn = 'Y' ");
		get_query.append("       AND s.store_type = '" + shop_type + "' ");
		get_query.append("       AND s.store_id = '" + shop_id + "' ");
		get_query.append("       AND i.shop_product_id = '" + product_id + "'");

		Query q = entityManager.createNativeQuery(get_query.toString());
		List<Object[]> list = q.getResultList();
		Map<String, Object> R = new HashMap<String, Object>();
		for (Object[] row : list) { 
			R.put("id", row[0]);
			R.put("link_yn", row[1]); 
 
		}
  
 
		return R;
		
	}

	@Override
	@Transactional(readOnly=true)
	public Item get_zeyo_item(long item_id) {
		return itemRepository.findOne(item_id);
	}

	@Override
	@Transactional(readOnly=true)
	public  Map<String, Object> get_zeyo_size(long item_id) { 
		
		StringBuffer  varname1 = new StringBuffer();
		varname1.append("SELECT issv.input_value, ");
		varname1.append("       so.id 		as so_id, ");
		varname1.append("       so.name 	as so_name, ");
		varname1.append("       mi.id 		as mi_id, ");
		varname1.append("       mi.name 	as mi_name ");
		varname1.append("FROM   item_scmm_so_value issv ");
		varname1.append("       LEFT JOIN size_option so ");
		varname1.append("              ON issv.size_option_id = so.id ");
		varname1.append("                 AND so.use_yn = 'Y' ");
		varname1.append("       LEFT JOIN sub_category_measure_map scmm ");
		varname1.append("              ON issv.sub_category_measure_map_id = scmm.id ");
		varname1.append("                 AND scmm.use_yn = 'Y' ");
		varname1.append("       LEFT JOIN measure_item mi ");
		varname1.append("              ON scmm.measure_item_id = mi.id ");
		varname1.append("                 AND mi.use_yn = 'Y' ");
		varname1.append("WHERE  issv.use_yn = 'Y' ");
		varname1.append("       AND issv.item_id = "+item_id);
 
 
		Query q = entityManager.createNativeQuery(varname1.toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>(); 
			
			search_R.put("input_value", row[0]);
			search_R.put("so_id", row[1]);
			search_R.put("so_name", row[2]);			
			search_R.put("mi_id", row[3]); 
			search_R.put("mi_name", row[4]); 

			return_list.add(search_R);
		}
 
		Map<String, Object> R = new HashMap<String, Object>();
		R.put("size", return_list); 
 
		return R;
	}
}
