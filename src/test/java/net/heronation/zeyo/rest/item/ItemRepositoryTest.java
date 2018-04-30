package net.heronation.zeyo.rest.item; 

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

import net.heronation.zeyo.rest.repository.item.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRepositoryTest{

	@Autowired ItemRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			Item item = new Item(); 
                         item.setImageMode("imageMode_"+a);




item.setImage("image_"+a);




item.setSizeMeasureMode("sizeMeasureMode_"+a);




item.setSizeMeasureImage("sizeMeasureImage_"+a);




item.setName("name_"+a);




item.setCode("code_"+a);




item.setPrice("price_"+a);




item.setMadeinBuilder("madeinBuilder_"+a);




item.setMadeinDate("madeinDate_"+a);




item.setLaundryYn("laundryYn_"+a);




item.setDrycleaningYn("drycleaningYn_"+a);




item.setIroningYn("ironingYn_"+a);




item.setDrymethodYn("drymethodYn_"+a);




item.setBleachYn("bleachYn_"+a);




item.setSizeLinkYn("sizeLinkYn_"+a);




item.setCreateDt("createDt_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}