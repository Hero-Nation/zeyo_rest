package net.heronation.zeyo.rest.item_material_map; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMap;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMapRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemMaterialMapRepositoryTest{

	@Autowired ItemMaterialMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			ItemMaterialMap item = new ItemMaterialMap(); 
                         item.setContain("contain_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}