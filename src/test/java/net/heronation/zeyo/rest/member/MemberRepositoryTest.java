package net.heronation.zeyo.rest.member; 

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

import net.heronation.zeyo.rest.repository.member.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest{

	@Autowired MemberRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			Member item = new Member(); 
                         item.setMemberId("memberId_"+a);




item.setName("name_"+a);




item.setPassword("password_"+a);




item.setPhone("phone_"+a);




item.setEmail("email_"+a);




item.setManager("manager_"+a);




item.setManagerEmail("managerEmail_"+a);




item.setManagerPhone("managerPhone_"+a);




item.setCreateDt("createDt_"+a);




item.setDeleteDt("deleteDt_"+a);




item.setAdminYn("adminYn_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}