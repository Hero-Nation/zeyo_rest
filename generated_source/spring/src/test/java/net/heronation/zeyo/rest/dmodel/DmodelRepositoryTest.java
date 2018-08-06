package net.heronation.zeyo.rest.dmodel; 

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

import net.heronation.zeyo.rest.repository.dmodel.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class DmodelRepositoryTest{

	@Autowired DmodelRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			Dmodel item = new Dmodel(); 
                         item.setTitle("title_"+a);




item.setController("controller_"+a);




item.setSvgdata("svgdata_"+a);




item.setCreateDt(new DateTime());



item.setUpdateDt(new DateTime());



item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}