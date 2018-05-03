package net.heronation.zeyo.rest.warranty; 

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

import net.heronation.zeyo.rest.repository.warranty.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class WarrantyRepositoryTest{

	@Autowired WarrantyRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			Warranty item = new Warranty(); 
                         item.setScope("scope_"+a);





item.setCreateDt(new Date());



item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}