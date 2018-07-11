package net.heronation.zeyo.rest.company_no_history;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistory;
import net.heronation.zeyo.rest.company_no_history.repository.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.member.repository.Member;
import net.heronation.zeyo.rest.member.repository.MemberRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyNoHistoryRepositoryTest {

	@Autowired
	CompanyNoHistoryRepository repository;

	@Autowired
	MemberRepository memberRepository;

	
	@Test 
	public void initializesRepositoryWithSampleData() {
		
		Member user = memberRepository.getOne(1L);

		for (int a = 0; a < 10; a++) {
			CompanyNoHistory item = new CompanyNoHistory();
			item.setName("name_" + a); 
			item.setCompanyNo("companyNo_" + a); 
			item.setBeforeNo("beforeNo_" + a);
			item.setMember(user);
			item.setChangeDt(new DateTime());
			repository.save(item);
		}

	}

}