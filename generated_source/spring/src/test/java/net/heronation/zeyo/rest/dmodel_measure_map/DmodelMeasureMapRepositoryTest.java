package net.heronation.zeyo.rest.dmodel_measure_map; 

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

import net.heronation.zeyo.rest.repository.dmodel_measure_map.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class DmodelMeasureMapRepositoryTest{

	@Autowired DmodelMeasureMapRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			DmodelMeasureMap item = new DmodelMeasureMap(); 
                         item.setMinValue("minValue_"+a);




item.setMaxValue("maxValue_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}