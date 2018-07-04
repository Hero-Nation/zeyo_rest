package net.heronation.zeyo.rest.sub_category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.QSubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.QSubCategoryMeasureMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SubCategoryRepositoryTest {

	@Autowired
	SubCategoryRepository repository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			SubCategory item = new SubCategory();
			item.setName("name_" + a);

			item.setItemImage("itemImage_" + a);

			item.setClothImage("clothImage_" + a);

			item.setLaundryYn("laundryYn_" + a);

			item.setDrycleaningYn("drycleaningYn_" + a);

			item.setIroningYn("ironingYn_" + a);

			item.setDrymethodYn("drymethodYn_" + a);

			item.setBleachYn("bleachYn_" + a);

			//item.setCreateDt(new Date());

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}

	
	@Test
	
	public void subsearch() {
 
		JPAQuery<Category> query = new JPAQuery<Category>(entityManager);
  
		
		PathBuilder<SubCategory> queryPath = new PathBuilder<SubCategory>(SubCategory.class, "subCategory");

 
		
		QCategory c = QCategory.category; 
		QSubCategory sc = QSubCategory.subCategory; 
		QSubCategoryFitInfoMap scfi = QSubCategoryFitInfoMap.subCategoryFitInfoMap;
		QSubCategoryMeasureMap scmm = QSubCategoryMeasureMap.subCategoryMeasureMap;

		QueryResults<Tuple> R = query.select( 
				 sc.id
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
				.groupBy(sc.id) .fetchResults();
				
 
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
			
			log.debug(search_R.toString());
		}
		
	 
	}
}