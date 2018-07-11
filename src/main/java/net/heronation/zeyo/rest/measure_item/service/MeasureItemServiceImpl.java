package net.heronation.zeyo.rest.measure_item.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemDto;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemRepository;

@Slf4j
@Service 
public class MeasureItemServiceImpl implements MeasureItemService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MeasureItemRepository measure_itemRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    mi.id, ");
		select_query.append("    mi.name, ");
		select_query.append("    mi.meta_desc, ");
		select_query.append("    mi.create_dt ");

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    measure_item mi ");
		where_query.append(" WHERE ");
		where_query.append("    mi.use_yn = 'Y'");

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   mi.name like '%" + name + "%' ");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND mi.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND mi.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		// where_query.append("GROUP BY mi.id");

		StringBuffer sort_query = new StringBuffer();
		
		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY mi.");
			String sep = "";
			for (Sort.Order order : sort) {
				sort_query.append(sep);
				sort_query.append(order.getProperty());
				sort_query.append(" ");
				sort_query.append(order.getDirection());
				sep = ", ";
			}	
		}
		

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(count_query.append(select_query).append(where_query).append(" ) count_table  ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}
		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// select_query.append(" mi.name, ");
			// select_query.append(" mi.meta_desc, ");
			// select_query.append(" mi.create_dt ");

			search_R.put("id", row[0]);
			search_R.put("name", row[1]);
			search_R.put("metaDesc", row[2]);
			search_R.put("createDt", row[3]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;

	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> detail_list(Map<String, Object> param, Pageable page) {
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT c.name as cate_name, ");
		select_query.append("       sc.name as sub_cate_name , ");
		select_query.append("       mi.create_dt  as measure_create_dt");

		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM   sub_category_measure_map scmm ");
		where_query.append("       LEFT JOIN sub_category sc ");
		where_query.append("              ON scmm.sub_category_id = sc.id ");
		where_query.append("                 AND sc.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN category c ");
		where_query.append("              ON sc.category_id = c.id ");
		where_query.append("                 AND c.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN measure_item mi ");
		where_query.append("              ON scmm.measure_item_id = mi.id ");
		where_query.append("                 AND mi.use_yn = 'Y' ");
		where_query.append(" WHERE  scmm.use_yn = 'Y' ");

		String id = (String) param.get("id");

		where_query.append("       AND mi.id = " + id);

		StringBuffer group_query = new StringBuffer();

		group_query.append(" GROUP BY c.id ");

		StringBuffer sort_query = new StringBuffer();
		
		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY c.");
			String sep = "";
			for (Sort.Order order : sort) {
				sort_query.append(sep);
				sort_query.append(order.getProperty());
				sort_query.append(" ");
				sort_query.append(order.getDirection());
				sep = ", ";
			}	
		}
		

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(count_query.append(select_query).append(where_query)
				.append(group_query).append(" ) count_table ").toString());
		BigInteger count_list = BigInteger.ZERO;

		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {

		} else {
			count_list = count_result.get(0);
		}

		Query q = entityManager.createNativeQuery(
				select_query.append(where_query).append(group_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("cate_name", row[0]);
			search_R.put("sub_cate_name", row[1]);
			search_R.put("measure_create_dt", row[2]);

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;
	}

	@Override
	@Transactional
	public String insert(MeasureItemDto param) {

		measure_itemRepository.save(param.convertToEntity());

		return CommonConstants.SUCCESS;
	}
}