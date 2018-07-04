package net.heronation.zeyo.rest.sub_category_measure_map; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMap;
import net.heronation.zeyo.rest.repository.sub_category_measure_map.SubCategoryMeasureMapRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubCategoryMeasureMapRepositoryTest{

	@Autowired SubCategoryMeasureMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			SubCategoryMeasureMap item = new SubCategoryMeasureMap(); 
                         item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}