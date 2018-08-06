package net.heronation.zeyo.rest.bbs; 

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

import net.heronation.zeyo.rest.repository.bbs.*;

 
@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsRepositoryTest{

	@Autowired BbsRepository repository;

 
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {
		
		for(int a = 0 ; a < 100;a++) {
			Bbs item = new Bbs(); 
                         item.setTitle("title_"+a);




item.setBbsContent("bbsContent_"+a);




item.setReplyContent("replyContent_"+a);




item.setCreateDt("createDt_"+a);




item.setReplyDt("replyDt_"+a);




item.setStatus("status_"+a);




item.setUseYn("useYn_"+a);
			repository.save(item);	
		}
		 
	}
	
}