package net.heronation.zeyo.rest.service.material;
 
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
import net.heronation.zeyo.rest.repository.material.MaterialRepository;



@Slf4j
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {
		
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");
		 

		StringBuffer select_query = new StringBuffer();    
		select_query.append("SELECT ");
		select_query.append("    m.id			as id, ");
		select_query.append("    m.name			as name, ");
		select_query.append("    m.image		as image, ");
		select_query.append("    m.meta_desc	as metaDesc, ");
		select_query.append("    m.create_dt 	as createDt  "); 
 
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    material m ");
		where_query.append("WHERE ");
		where_query.append("    m.use_yn = 'Y'");

 
		
		
		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   m.name like '%" + name + "%' "); 
		}

	 
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND m.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND m.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

 
		where_query.append("GROUP BY m.id");
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY m.");
		Sort sort = page.getSort();
		String sep = "";
		for (Sort.Order order : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			sep = ", ";
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String, Object>> count_list = count_q.getResultList();

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

//			select_query.append("    m.id			as id, ");
//			select_query.append("    m.name			as name, ");
//			select_query.append("    m.image		as image, ");
//			select_query.append("    m.meta_desc	as metaDesc, ");
//			select_query.append("    m.create_dt 	as createDt");

			search_R.put("id", row[0]);
			search_R.put("name", row[1]);
			search_R.put("image", row[2]);
			search_R.put("metaDesc", row[3]);
			search_R.put("createDt", row[4]);   

			return_list.add(search_R);
		}

		int totalPages = (count_list.size() / page.getPageSize());
		if (count_list.size() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.size());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;
	}

 
}