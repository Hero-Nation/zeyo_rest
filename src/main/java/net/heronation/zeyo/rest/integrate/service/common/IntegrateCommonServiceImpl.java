package net.heronation.zeyo.rest.integrate.service.common;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.consumer.repository.Consumer;
import net.heronation.zeyo.rest.consumer.repository.ConsumerRepository;
import net.heronation.zeyo.rest.consumer.repository.QConsumer;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.Items;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.Order;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.OrderItem;
import net.heronation.zeyo.rest.integrate.controller.cafe24.dto.Orders;
import net.heronation.zeyo.rest.integrate.service.cafe24.Cafe24Service;
import net.heronation.zeyo.rest.ip_temp_info.repository.IpTempInfo;
import net.heronation.zeyo.rest.ip_temp_info.repository.IpTempInfoRepository;
import net.heronation.zeyo.rest.ip_temp_info.repository.QIpTempInfo;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.item.repository.ItemRepository;
import net.heronation.zeyo.rest.item.repository.QItem;
import net.heronation.zeyo.rest.item_scmm_so_value.repository.ItemScmmSoValueRepository;
import net.heronation.zeyo.rest.shopmall.repository.Shopmall;
import net.heronation.zeyo.rest.shopmall.repository.ShopmallRepository;

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
	RestTemplate restTemplate;

	@Autowired
	EntityManager entityManager;

	@Autowired
	Cafe24Service cafe24Service;

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
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
	public Item get_zeyo_item(long item_id) {
		return itemRepository.findOne(item_id);
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> get_zeyo_size(long item_id) {

		StringBuffer varname1 = new StringBuffer();
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
		varname1.append("       AND issv.item_id = " + item_id);

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

	@Override
	public List<Map<String, Object>> get_ordered_item_size(long shopmall_id) {

		List<Map<String, Object>> R = new ArrayList<Map<String, Object>>();

		Shopmall this_shopmall = shopmallRepository.findOne(shopmall_id);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", String.format("Bearer %s", this_shopmall.getAccessToken()));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity entity = new HttpEntity(headers);

		// 임시로 가지고 온다.
		URI get_orders_uri = UriComponentsBuilder.newInstance().scheme("https")
				.host(String.format("www.zeyo.co.kr", "")).path(String.format("/static/order.json"))

				.build().encode().toUri();

		ResponseEntity<Orders> get_orders_response = restTemplate.exchange(get_orders_uri, HttpMethod.GET, entity,
				Orders.class);

		Order[] order_list = get_orders_response.getBody().getOrders();

		for (Order order : order_list) {

			URI get_items_uri = UriComponentsBuilder.newInstance().scheme("https")
					.host(String.format("www.zeyo.co.kr",""))
					.path(String.format("/static/%s.json", order.getOrder_id())).build().encode().toUri();

			ResponseEntity<Items> get_items_response = restTemplate.exchange(get_items_uri, HttpMethod.GET, entity,
					Items.class);

			OrderItem[] item_list = get_items_response.getBody().getItems();

			for (OrderItem order_item : item_list) {
				
				
				StringBuffer varname1 = new StringBuffer();
				varname1.append("SELECT i.* "); 
				varname1.append("FROM   item i ");
				varname1.append("WHERE  i.use_yn = 'Y' ");
				varname1.append("       AND i.shop_product_id = " +order_item.getProduct_no());

				Query item_select_q = entityManager.createNativeQuery(varname1.toString());
				List<Object[]> list = item_select_q.getResultList();
				

				for (Object[] this_item : list) {
					varname1 = new StringBuffer();
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
					varname1.append("       AND issv.item_id = " + this_item[0]);

					Query item_data_query = entityManager.createNativeQuery(varname1.toString());
					List<Object[]> item_data_list = item_data_query.getResultList();

					List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

					for (Object[] row : item_data_list) {
						Map<String, Object> search_R = new HashMap<String, Object>();

						search_R.put("input_value", row[0]);
						search_R.put("so_id", row[1]);
						search_R.put("so_name", row[2]);
						search_R.put("mi_id", row[3]);
						search_R.put("mi_name", row[4]);

						return_list.add(search_R);
					}

					Map<String, Object> item_data = new HashMap<String, Object>();
					item_data.put("size", return_list);
					item_data.put("item", this_item);

					R.add(item_data);

				}

			}
		}

		return R;
	}
}
