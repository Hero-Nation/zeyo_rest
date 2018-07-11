package net.heronation.zeyo.rest.item_shopmall_map; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMap;
import net.heronation.zeyo.rest.item_shopmall_map.repository.ItemShopmallMapRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemShopmallMapRepositoryTest{

	@Autowired ItemShopmallMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			ItemShopmallMap item = new ItemShopmallMap(); 
                         item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}