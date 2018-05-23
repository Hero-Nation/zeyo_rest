package net.heronation.zeyo.rest.service.category;
 
import java.math.BigDecimal;
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
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.warranty.Warranty;



@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private CategoryRepository categoryRepository;


	@Autowired
	EntityManager entityManager;
	
	@Override
	public Page<Map<String,Object>> search(Predicate where, Pageable page) {

		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
  
		
		PathBuilder<SubCategory> queryPath = new PathBuilder<SubCategory>(SubCategory.class, "subCategory");

		for (Order order : page.getSort()) {
			PathBuilder<Object> path = queryPath.get(order.getProperty());
			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
		}
		
		QCategory c = QCategory.category; 
		QSubCategory sc = QSubCategory.subCategory; 
		QSubCategoryFitInfoMap scfi = QSubCategoryFitInfoMap.subCategoryFitInfoMap;
		QSubCategoryMeasureMap scmm = QSubCategoryMeasureMap.subCategoryMeasureMap;

		QueryResults<Tuple> R = query.select(
				 sc.category.id
				,sc.category.name
				,sc.id
				,sc.name
				,sc.itemImage
				,sc.clothImage 
				,sc.category.createDt
				,scfi.id.countDistinct()
				,scmm.id.countDistinct()
				)
				.from(sc)
				.rightJoin(sc.category,c).on(c.useYn.eq("Y"))
				.leftJoin(sc.subCategoryFitInfoMaps,scfi).on(scfi.useYn.eq("Y"))
				.leftJoin(sc.subCategoryMeasureMaps,scmm).on(scmm.useYn.eq("Y"))
				.groupBy(sc.id)
				.where(where) 
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		List<Tuple> search_list = R.getResults();
		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>(); 
		
		for(Tuple row : search_list) {
			Map<String,Object> search_R = new HashMap<String,Object>();
			
			search_R.put("cate_id", row.get(sc.category.id));
			search_R.put("cate_name", row.get(sc.category.name));
			search_R.put("subcate_name", row.get(sc.name));
			search_R.put("subcate_id", row.get(sc.id));
			search_R.put("itemImage", row.get(sc.itemImage));
			search_R.put("clothImage", row.get(sc.clothImage));
			search_R.put("subCategoryFitInfoMaps_count", row.get(scfi.id.countDistinct()));
			search_R.put("subCategoryMeasureMaps_count", row.get(scmm.id.countDistinct()));
			search_R.put("createDt", row.get(sc.category.createDt)); 
			
			return_list.add(search_R);
		}
		
		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());

	}

	@Override
	public Page<Map<String, Object>> pure_search(Predicate where, Pageable page) {
		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
  
		
		PathBuilder<Category> queryPath = new PathBuilder<Category>(Category.class, "category");

		for (Order order : page.getSort()) {
			PathBuilder<Object> path = queryPath.get(order.getProperty());
			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
		}
		
		QCategory c = QCategory.category;  

		QueryResults<Tuple> R = query.select(
				 c.id
				,c.name
				,c.createDt
				)
				.from(c) 
				.where(where) 
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		List<Tuple> search_list = R.getResults();
		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>(); 
		
		for(Tuple row : search_list) {
			Map<String,Object> search_R = new HashMap<String,Object>();
			
			search_R.put("cate_id", row.get(c.id));
			search_R.put("cate_name", row.get(c.name));
			search_R.put("cate_create_dt", row.get(c.createDt)); 
			
			return_list.add(search_R);
		}
		
		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());
	}
}