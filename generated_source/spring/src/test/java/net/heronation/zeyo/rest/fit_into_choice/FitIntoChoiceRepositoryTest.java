package net.heronation.zeyo.rest.fit_into_choice; 

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

import net.heronation.zeyo.rest.repository.fit_into_choice.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class FitIntoChoiceRepositoryTest{

	@Autowired FitIntoChoiceRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			FitIntoChoice item = new FitIntoChoice(); 
                         
			repository.save(item);	
		}
		 
	}
	
}