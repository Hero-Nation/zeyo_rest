package net.heronation.zeyo.rest.service.sub_category;
 
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap;



@Slf4j
@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private SubCategoryRepository sub_categoryRepository;



	@Autowired
	EntityManager entityManager;
	
//	@Override
//	public Page<SubCategory> search(Predicate where, Pageable page) {
// 
//		JPAQuery<SubCategory> query = new JPAQuery<SubCategory>(entityManager);
//		 
//		QSubCategory target = QSubCategory.subCategory;  
//		
//		QueryResults<SubCategory> R = query.from(target) 
//				.leftJoin(target.subCategoryMeasureMaps)
//				.leftJoin(target.subCategoryFitInfoMaps)
//				.where(where)
//				//.orderBy(target.id.desc())
//				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
//				.limit(page.getPageSize()).fetchResults();
//				
// 
//		return new PageImpl<SubCategory>(R.getResults(), page, R.getTotal());
//		
//	}


	@Override
	public Page<Map<String,Object>> subsearch(Predicate where, Pageable page) {
 
		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
		  
		QSubCategory subc = QSubCategory.subCategory; 

		QueryResults<Tuple> R = query.select( subc.id,subc.name,subc.itemImage,subc.clothImage,subc.subCategoryFitInfoMaps.size(),subc.subCategoryMeasureMaps.size(),subc.createDt)
				.from(subc)
				.innerJoin(subc.subCategoryFitInfoMaps)
				.innerJoin(subc.subCategoryMeasureMaps)
				.where(where)
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		List<Tuple> search_list = R.getResults();
		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>(); 
		
		for(Tuple row : search_list) {
			Map<String,Object> search_R = new HashMap<String,Object>();
			 
			search_R.put("subcate_name", row.get(subc.name));
			search_R.put("subcate_id", row.get(subc.id));
			search_R.put("itemImage", row.get(subc.itemImage));
			search_R.put("clothImage", row.get(subc.clothImage));
			search_R.put("subCategoryFitInfoMaps_count", row.get(subc.subCategoryFitInfoMaps.size()));
			search_R.put("subCategoryMeasureMaps_count", row.get(subc.subCategoryMeasureMaps.size()));
			search_R.put("createDt", row.get(subc.createDt)); 
			
			return_list.add(search_R);
		}
		
		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());
		
	}


}