package net.heronation.zeyo.rest.item_drycleaning_map; 

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

import net.heronation.zeyo.rest.repository.item_drycleaning_map.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDrycleaningMapRepositoryTest{

	@Autowired ItemDrycleaningMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			ItemDrycleaningMap item = new ItemDrycleaningMap(); 
                         item.setDrycan("drycan_"+a);




item.setStorecan("storecan_"+a);




item.setDetergent("detergent_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}