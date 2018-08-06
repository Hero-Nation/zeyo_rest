package net.heronation.zeyo.rest.sub_category; 

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.repository.sub_category.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubCategoryRepositoryTest{

	@Autowired SubCategoryRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			SubCategory item = new SubCategory(); 
                         item.setName("name_"+a);




item.setItemImage("itemImage_"+a);




item.setClothImage("clothImage_"+a);




item.setLaundryYn("laundryYn_"+a);




item.setDrycleaningYn("drycleaningYn_"+a);




item.setIroningYn("ironingYn_"+a);




item.setDrymethodYn("drymethodYn_"+a);




item.setBleachYn("bleachYn_"+a);




item.setCreateDt("createDt_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}