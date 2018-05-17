package net.heronation.zeyo.rest.service.category;
 
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
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;



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
  
		QSubCategory subc = QSubCategory.subCategory; 

		QueryResults<Tuple> R = query.select(subc.category.id,subc.category.name,subc.id,subc.name,subc.itemImage,subc.clothImage,subc.subCategoryFitInfoMaps.size(),subc.subCategoryMeasureMaps.size(),subc.category.createDt)
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
			
			search_R.put("cate_id", row.get(subc.category.id));
			search_R.put("cate_name", row.get(subc.category.name));
			search_R.put("subcate_name", row.get(subc.name));
			search_R.put("subcate_id", row.get(subc.id));
			search_R.put("itemImage", row.get(subc.itemImage));
			search_R.put("clothImage", row.get(subc.clothImage));
			search_R.put("subCategoryFitInfoMaps_count", row.get(subc.subCategoryFitInfoMaps.size()));
			search_R.put("subCategoryMeasureMaps_count", row.get(subc.subCategoryMeasureMaps.size()));
			search_R.put("createDt", row.get(subc.category.createDt)); 
			
			return_list.add(search_R);
		}
		
		return new PageImpl<Map<String,Object>>(return_list, page, R.getTotal());

	}
}