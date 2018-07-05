package net.heronation.zeyo.rest.service.category;
 
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.IdNameDto;
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.common.value.NameDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;



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
	public Map<String,Object> search(Map<String,Object> param, Pageable page) {
		
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");
		 

		StringBuffer select_query = new StringBuffer();    
		select_query.append("SELECT ");
		select_query.append("    c.id							AS cate_id, ");
		select_query.append("    c.name							AS cate_name, ");
		select_query.append("    sc.id							AS subcate_id, ");
		select_query.append("    sc.name						AS subcate_name,   "); 
		select_query.append("    sc.item_image					AS itemImage, ");
		select_query.append("    sc.cloth_image					AS clothImage, ");
		select_query.append("    COUNT(scfim.fit_info_id)		AS subCategoryFitInfoMaps_count, ");
		select_query.append("    COUNT(scmm.measure_item_id)	AS subCategoryMeasureMaps_count, ");
		select_query.append("    c.create_dt 					AS createDt      ");
		
//		search_R.put("cate_id", row.get(sc.category.id));
//		search_R.put("cate_name", row.get(sc.category.name));
//		search_R.put("subcate_name", row.get(sc.name));
//		search_R.put("subcate_id", row.get(sc.id));
//		search_R.put("itemImage", row.get(sc.itemImage));
//		search_R.put("clothImage", row.get(sc.clothImage));
//		search_R.put("subCategoryFitInfoMaps_count", row.get(scfi.id.countDistinct()));
//		search_R.put("subCategoryMeasureMaps_count", row.get(scmm.id.countDistinct()));
//		search_R.put("createDt", row.get(sc.category.createDt)); 


 
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    category c ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category sc ON c.id = sc.category_id ");
		where_query.append("        AND sc.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category_fit_info_map scfim ON scfim.sub_category_id = sc.id ");
		where_query.append("        AND scfim.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category_measure_map scmm ON scmm.sub_category_id = sc.id ");
		where_query.append("        AND scmm.use_yn = 'Y' ");
		where_query.append("WHERE ");
		where_query.append("    c.use_yn = 'Y' ");
		

 
		
		
		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   c.name like '%" + name + "%' "); 
		}
		
		String cate = (String) param.get("cate");
		if (cate != null) {
			where_query.append("        AND   c.id =" + cate + " "); 
		}
		
		String subcate = (String) param.get("subcate");
		if (subcate != null) {
			where_query.append("        AND   sc.id = " + subcate + " "); 
		}
//		
//		String measure = (String) param.get("measure");
//		if (measure != null) {
//			where_query.append("        AND   c.measure like '%" + measure + "%' "); 
//		}


	 
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND c.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND c.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

 
		where_query.append("  GROUP BY c.id ,sc.id");
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY c.");
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
			
//			search_R.put("cate_id", row.get(sc.category.id));
//			search_R.put("cate_name", row.get(sc.category.name));
//			search_R.put("subcate_id", row.get(sc.id));
//			search_R.put("subcate_name", row.get(sc.name));
//			
//			search_R.put("itemImage", row.get(sc.itemImage));
//			search_R.put("clothImage", row.get(sc.clothImage));
//			search_R.put("subCategoryFitInfoMaps_count", row.get(scfi.id.countDistinct()));
//			search_R.put("subCategoryMeasureMaps_count", row.get(scmm.id.countDistinct()));
//			search_R.put("createDt", row.get(sc.category.createDt)); 
			

			search_R.put("cate_id", row[0]);
			search_R.put("cate_name", row[1]);
			search_R.put("subcate_id", row[2]);
			search_R.put("subcate_name", row[3]);
			search_R.put("itemImage", row[4]);   
			search_R.put("clothImage", row[5]);   
			search_R.put("subCategoryFitInfoMaps_count", row[6]);   
			search_R.put("subCategoryMeasureMaps_count", row[7]);   
			search_R.put("createDt", row[8]);    

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
		
		
		

//		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
//  
//		
//		PathBuilder<SubCategory> queryPath = new PathBuilder<SubCategory>(SubCategory.class, "subCategory");
//
//		for (Order order : page.getSort()) {
//			PathBuilder<Object> path = queryPath.get(order.getProperty());
//			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//		}
//		
//		QCategory c = QCategory.category; 
//		QSubCategory sc = QSubCategory.subCategory; 
//		QSubCategoryFitInfoMap scfi = QSubCategoryFitInfoMap.subCategoryFitInfoMap;
//		QSubCategoryMeasureMap scmm = QSubCategoryMeasureMap.subCategoryMeasureMap;
//
//		QueryResults<Tuple> R = query.select(
//				 sc.category.id
//				,sc.category.name
//				,sc.id
//				,sc.name
//				,sc.itemImage
//				,sc.clothImage 
//				,sc.category.createDt
//				,scfi.id.countDistinct()
//				,scmm.id.countDistinct()
//				)
//				.from(sc)
//				.rightJoin(sc.category,c).on(c.useYn.eq("Y"))
//				.leftJoin(sc.subCategoryFitInfoMaps,scfi).on(scfi.useYn.eq("Y"))
//				.leftJoin(sc.subCategoryMeasureMaps,scmm).on(scmm.useYn.eq("Y"))
//				.groupBy(sc.id)
//				.where(where) 
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
//			search_R.put("cate_id", row.get(sc.category.id));
//			search_R.put("cate_name", row.get(sc.category.name));
//			search_R.put("subcate_name", row.get(sc.name));
//			search_R.put("subcate_id", row.get(sc.id));
//			search_R.put("itemImage", row.get(sc.itemImage));
//			search_R.put("clothImage", row.get(sc.clothImage));
//			search_R.put("subCategoryFitInfoMaps_count", row.get(scfi.id.countDistinct()));
//			search_R.put("subCategoryMeasureMaps_count", row.get(scmm.id.countDistinct()));
//			search_R.put("createDt", row.get(sc.category.createDt)); 
//			
//			return_list.add(search_R);
//		}
//		
//		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());

	}

	@Override
	public Map<String, Object> pure_search(Map<String, Object> where, Pageable page) {
		

		
		
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");
		 

		StringBuffer select_query = new StringBuffer();    
		select_query.append("SELECT ");
		select_query.append("    c.id							AS cate_id, ");
		select_query.append("    c.name							AS cate_name, ");  
		select_query.append("    c.create_dt 					AS createDt      ");
		
//		search_R.put("cate_id", row.get(sc.category.id));
//		search_R.put("cate_name", row.get(sc.category.name));
//		search_R.put("subcate_name", row.get(sc.name));
//		search_R.put("subcate_id", row.get(sc.id));
//		search_R.put("itemImage", row.get(sc.itemImage));
//		search_R.put("clothImage", row.get(sc.clothImage));
//		search_R.put("subCategoryFitInfoMaps_count", row.get(scfi.id.countDistinct()));
//		search_R.put("subCategoryMeasureMaps_count", row.get(scmm.id.countDistinct()));
//		search_R.put("createDt", row.get(sc.category.createDt)); 


 
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    category c "); 
		where_query.append("WHERE ");
		where_query.append("    c.use_yn = 'Y' ");
 
 

 
		where_query.append("  GROUP BY c.id");
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY c.");
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
			
//			 {
//		      "cate_name" : "상위",
//		      "cate_id" : 1,
//		      "cate_create_dt" : "2018-06-12T06:02:17.694Z"
//		    } ],
			

			search_R.put("cate_id", row[0]);
			search_R.put("cate_name", row[1]);
			search_R.put("cate_create_dt", row[2]);   

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
		
		
		
//		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
//  
//		
//		PathBuilder<Category> queryPath = new PathBuilder<Category>(Category.class, "category");
//
//		for (Order order : page.getSort()) {
//			PathBuilder<Object> path = queryPath.get(order.getProperty());
//			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//		}
//		
//		QCategory c = QCategory.category;  
//
//		QueryResults<Tuple> R = query.select(
//				 c.id
//				,c.name
//				,c.createDt
//				)
//				.from(c) 
//				.where(where) 
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
//			search_R.put("cate_id", row.get(c.id));
//			search_R.put("cate_name", row.get(c.name));
//			search_R.put("cate_create_dt", row.get(c.createDt)); 
//			
//			return_list.add(search_R);
//		}
//		
//		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());
	}
	
	@Override
	@Transactional
	public String insert(NameDto param) {
		// TODO Auto-generated method stub 
		
		Category iv = new Category(); 
		iv.setName(param.getName());
		iv.setCreateDt(new DateTime());
		iv.setUseYn("Y");
		
		categoryRepository.save(iv);
		
		return CommonConstants.SUCCESS;
	}
	
	
	@Override 
	@Transactional
	public String update(IdNameDto param) {
		// TODO Auto-generated method stub
		
		Category a = categoryRepository.findOne(param.getId());
		a.setName(param.getName());
		
		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String delete(List<LIdDto> param) {
		for(LIdDto v : param) {
			Category a = categoryRepository.findOne(v.getId());
			a.setUseYn("N");
		}

		return CommonConstants.SUCCESS;
	}
}