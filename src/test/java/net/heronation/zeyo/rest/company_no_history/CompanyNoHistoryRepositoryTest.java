package net.heronation.zeyo.rest.company_no_history; 

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

import net.heronation.zeyo.rest.repository.company_no_history.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyNoHistoryRepositoryTest{

	@Autowired CompanyNoHistoryRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			CompanyNoHistory item = new CompanyNoHistory(); 
                         item.setName("name_"+a);




item.setCompanyNo("companyNo_"+a);




item.setBeforeNo("beforeNo_"+a);





item.setChangeDt(new Date());
			repository.save(item);	
		}
		 
	}
	
}