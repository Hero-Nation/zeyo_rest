package net.heronation.zeyo.rest.service.fit_info;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public Page<Map<String,Object>> search(Predicate where, Pageable page) {

		JPAQuery<FitInfo> query = new JPAQuery<FitInfo>(entityManager);
 
		
		PathBuilder<FitInfo> queryPath = new PathBuilder<FitInfo>(FitInfo.class, "fitInfo");

		for (Order order : page.getSort()) {
			PathBuilder<Object> path = queryPath.get(order.getProperty());
			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
		}
		
		QFitInfo fi = QFitInfo.fitInfo;

		QueryResults<Tuple> R = query.select(fi.name,fi.id,fi.createDt,fi.metaDesc).from(fi).where(where) 
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		List<Tuple> search_list = R.getResults();
		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>(); 
		
		for(Tuple row : search_list) {
			Map<String,Object> search_R = new HashMap<String,Object>();
			
			search_R.put("fitinfo_id", row.get(fi.id));
			search_R.put("fitinfo_name", row.get(fi.name));
			search_R.put("fitinfo_desc", row.get(fi.metaDesc));
			search_R.put("fitinfo_create_dt", row.get(fi.createDt)); 
			
			return_list.add(search_R);
		}
		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());

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