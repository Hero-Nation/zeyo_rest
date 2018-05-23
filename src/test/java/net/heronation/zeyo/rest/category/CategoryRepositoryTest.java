package net.heronation.zeyo.rest.category;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.category.*;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryRepositoryTest {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	CategoryRepository repository;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Category item = new Category();
			item.setName("name_" + a);
 

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	@Test
	public void category_list() {
		log.debug("category_list");
		
		
		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
		  
		QCategory c = QCategory.category; 
		QSubCategory sc = QSubCategory.subCategory; 
		QSubCategoryFitInfoMap scfi = QSubCategoryFitInfoMap.subCategoryFitInfoMap;
		QSubCategoryMeasureMap scmm = QSubCategoryMeasureMap.subCategoryMeasureMap;
		
		QueryResults<Tuple> R = query.select(
				 c.id
				,c.name
				,sc.id
				,sc.name
				,sc.itemImage
				,sc.clothImage 
				,sc.category.createDt
				,scfi.id.countDistinct()
				,scmm.id.countDistinct()
				)
				.from(c)
				.leftJoin(c.subCategorys,sc).on(sc.useYn.eq("Y"))
				.leftJoin(sc.subCategoryFitInfoMaps,scfi).on(scfi.useYn.eq("Y"))
				.leftJoin(sc.subCategoryMeasureMaps,scmm).on(scmm.useYn.eq("Y"))
				.groupBy(sc.id)
				.where(sc.useYn.eq("Y")).fetchResults();
				
 
		List<Tuple> search_list = R.getResults();
		 
		for(Tuple row : search_list) {
			Map<String,Object> search_R = new HashMap<String,Object>();
			
			search_R.put("cate_id", row.get(c.id));
			search_R.put("cate_name", row.get(c.name));
			search_R.put("subcate_name", row.get(sc.name));
			search_R.put("subcate_id", row.get(sc.id));
			search_R.put("itemImage", row.get(sc.itemImage));
			search_R.put("clothImage", row.get(sc.clothImage));
			search_R.put("subCategoryFitInfoMaps_count", row.get(scfi.id.countDistinct())); 
			search_R.put("subCategoryMeasureMaps_count", row.get(scmm.id.countDistinct()));
			search_R.put("createDt", row.get(sc.category.createDt)); 
			
			log.debug(search_R.toString());
		}
		
	}

}