package net.heronation.zeyo.rest.item_drymethod_map; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMap;
import net.heronation.zeyo.rest.repository.item_drymethod_map.ItemDrymethodMapRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemDrymethodMapRepositoryTest{

	@Autowired ItemDrymethodMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			ItemDrymethodMap item = new ItemDrymethodMap(); 
                         item.setMachineDry("machineDry_"+a);




item.setNatureDry("natureDry_"+a);




item.setDryMode("dryMode_"+a);




item.setHandDry("handDry_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}