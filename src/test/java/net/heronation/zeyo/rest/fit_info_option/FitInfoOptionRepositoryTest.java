package net.heronation.zeyo.rest.fit_info_option; 

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitInfoOptionRepositoryTest{

	@Autowired FitInfoOptionRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			FitInfoOption item = new FitInfoOption(); 
                         item.setName("name_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}