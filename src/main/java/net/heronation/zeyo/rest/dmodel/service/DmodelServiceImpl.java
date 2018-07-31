package net.heronation.zeyo.rest.dmodel.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.dmodel.repository.DmodelRepository;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_ratio.repository.DmodelRatioRepository;
import net.heronation.zeyo.rest.measure_item.repository.MeasureItemRepository;

@Slf4j
@Service
@Transactional
public class DmodelServiceImpl implements DmodelService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DmodelRepository dmodelRepository;
	
	
	@Autowired
	private DmodelMeasureMapRepository dmmrepo;
	
	@Autowired
	private MeasureItemRepository mirepo;
	
	@Autowired
	private DmodelRatioRepository drrepo;
	
	@Autowired
	EntityManager entityManager;
	
	
	@Override
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		
//		id bigint(20) PK 
//		controller varchar(255) 
//		create_dt datetime 
//		svgdata varchar(255) 
//		title varchar(255) 
//		update_dt datetime 
//		use_yn varchar(255)
		
		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    d.id				as id, ");
		select_query.append("    d.title			as title, ");
		select_query.append("    d.update_dt		as update_dt "); 

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    dmodel d ");
		where_query.append("WHERE ");
		where_query.append("    d.use_yn = 'Y'");

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   d.title like '%" + name + "%' ");
		}
 

		StringBuffer sort_query = new StringBuffer();

		Sort sort = page.getSort();
		if (sort != null) {
			sort_query.append("  ORDER BY d.");

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

		Query count_q = entityManager.createNativeQuery(
				count_query.append(select_query).append(where_query).append(" ) count_table ").toString());

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
 

			search_R.put("id", row[0]);
			search_R.put("title", row[1]);
			search_R.put("update_dt", row[2]); 

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
}