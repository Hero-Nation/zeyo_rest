package net.heronation.zeyo.rest.item_laundry_map; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMap;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMapRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemLaundryMapRepositoryTest{

	@Autowired ItemLaundryMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			ItemLaundryMap item = new ItemLaundryMap(); 
                         item.setWater("water_"+a);




item.setMachine("machine_"+a);




item.setHand("hand_"+a);




item.setWaterTemp("waterTemp_"+a);




item.setIntensity("intensity_"+a);




item.setDetergent("detergent_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}