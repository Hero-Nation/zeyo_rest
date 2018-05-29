package net.heronation.zeyo.rest.service.fit_info;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoRepository;
import net.heronation.zeyo.rest.repository.fit_info.QFitInfo;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;



@Slf4j
@Service
@Transactional
public class FitInfoServiceImpl implements FitInfoService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private FitInfoRepository fit_infoRepository;
	@Autowired
	EntityManager entityManager;
	
	@Override
	public Map<String,Object> search(Map<String,Object> param, Pageable page) {

		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");
		
		 

		StringBuffer select_query = new StringBuffer();   
		select_query.append(" SELECT ");
		select_query.append("    fi.id, ");
		select_query.append("    fi.name, ");
		select_query.append("    fi.meta_desc, ");
		select_query.append("    fi.create_dt ");

 
		StringBuffer where_query = new StringBuffer();
		
		where_query.append(" FROM ");
		where_query.append("    fit_info fi ");
		where_query.append(" WHERE ");
		where_query.append("    fi.use_yn = 'Y'");

 
		
		
		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   fi.name like '%" + name + "%' "); 
		}

	 
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND fi.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND fi.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

 
		//where_query.append("GROUP BY mi.id");
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY fi.");
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

//			select_query.append("    fi.id, ");
//			select_query.append("    fi.name, ");
//			select_query.append("    fi.meta_desc, ");
//			select_query.append("    fi.create_dt ");

//			search_R.put("fitinfo_id", row.get(fi.id));
//			search_R.put("fitinfo_name", row.get(fi.name));
//			search_R.put("fitinfo_desc", row.get(fi.metaDesc));
//			search_R.put("fitinfo_create_dt", row.get(fi.createDt)); 
			
			
			search_R.put("fitinfo_id", row[0]);
			search_R.put("fitinfo_name", row[1]);
			search_R.put("fitinfo_desc", row[2]); 
			search_R.put("fitinfo_create_dt", row[3]);   

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

		
//		JPAQuery<FitInfo> query = new JPAQuery<FitInfo>(entityManager);
// 
//		
//		PathBuilder<FitInfo> queryPath = new PathBuilder<FitInfo>(FitInfo.class, "fitInfo");
//
//		for (Order order : page.getSort()) {
//			PathBuilder<Object> path = queryPath.get(order.getProperty());
//			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//		}
//		
//		QFitInfo fi = QFitInfo.fitInfo;
//
//		QueryResults<Tuple> R = query.select(fi.name,fi.id,fi.createDt,fi.metaDesc).from(fi).where(where) 
//				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
//				.limit(page.getPageSize()).fetchResults();
//				
// 
//		List<Tuple> search_list = R.getResults();
//		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>(); 
//		
//		for(Tuple row : search_list) {
//			Map<String,Object> search_R = new HashMap<String,Object>();
//			
//			search_R.put("fitinfo_id", row.get(fi.id));
//			search_R.put("fitinfo_name", row.get(fi.name));
//			search_R.put("fitinfo_desc", row.get(fi.metaDesc));
//			search_R.put("fitinfo_create_dt", row.get(fi.createDt)); 
//			
//			return_list.add(search_R);
//		}
//		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());

	}

	@Override
	public Page<Map<String,Object>> fitInfoOptions_search(Predicate where, Pageable page) {
		 
		JPAQuery<FitInfoOption> query = new JPAQuery<FitInfoOption>(entityManager);
		
		PathBuilder<FitInfoOption> queryPath = new PathBuilder<FitInfoOption>(FitInfoOption.class, "fitInfoOption");

		for (Order order : page.getSort()) {
			PathBuilder<Object> path = queryPath.get(order.getProperty());
			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
		}
		
		QFitInfo fi = QFitInfo.fitInfo;
		QFitInfoOption fio = QFitInfoOption.fitInfoOption;

		QueryResults<Tuple> R = query.select(fi.id,fi.name,fi.metaDesc,fio.id,fio.name).from(fio).where(where)
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
		List<Tuple> search_list = R.getResults();
		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>(); 
		
		for(Tuple row : search_list) {
			Map<String,Object> search_R = new HashMap<String,Object>();
			
			search_R.put("fit_info_id", row.get(fi.id));
			search_R.put("fit_info_name", row.get(fi.name));
			search_R.put("fit_info_desc", row.get(fi.metaDesc));
			search_R.put("fit_info_option_id", row.get(fio.id));
			search_R.put("fit_info_option_name", row.get(fio.name)); 
			
			return_list.add(search_R);
		}
		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());
	}

}