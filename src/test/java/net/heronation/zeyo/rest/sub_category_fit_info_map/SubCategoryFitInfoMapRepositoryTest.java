package net.heronation.zeyo.rest.sub_category_fit_info_map; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMap;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMapRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubCategoryFitInfoMapRepositoryTest{

	@Autowired SubCategoryFitInfoMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			SubCategoryFitInfoMap item = new SubCategoryFitInfoMap(); 
                         item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}