package net.heronation.zeyo.rest.item_size_option_map; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMap;
import net.heronation.zeyo.rest.item_size_option_map.repository.ItemSizeOptionMapRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemSizeOptionMapRepositoryTest{

	@Autowired ItemSizeOptionMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			ItemSizeOptionMap item = new ItemSizeOptionMap(); 
                         item.setOptionValue("optionValue_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}