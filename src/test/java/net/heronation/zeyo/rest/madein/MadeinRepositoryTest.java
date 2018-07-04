package net.heronation.zeyo.rest.madein;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MadeinRepositoryTest {

	@Autowired
	MadeinRepository repository;
	
	

	@Autowired
	EntityManager entityManager;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Madein item = new Madein();
			item.setName("name_" + a);

//			item.setCreateDt(new Date());

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@Test 
	public void statistic_query() {
		
		
 
//		제조국
//		총 등록 수 : 1231
//		신규 등록 수 : 14
//		관리자입력 : 5
//		직접입력 : 5
 
		
		
//		제조국
//		총 등록 수 : 1231

		StringBuffer query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		
//		제조국 
//		신규 등록 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_item_r = q.getResultList();
		BigInteger today_new_item = today_new_item_r.get(0);
		log.debug(today_new_item_r.size() + " " +today_new_item);
		
		
//		제조국 
//		관리자입력 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 1 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> kindof_1_r = q.getResultList();
		BigInteger kindof_1 = kindof_1_r.get(0);
		log.debug(kindof_1_r.size() + " " +kindof_1);
		
		
		
//		제조국 
//		직접입력 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    madein i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 2 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> kindof_2_r = q.getResultList();
		BigInteger kindof_2 = kindof_2_r.get(0);
		log.debug(kindof_2_r.size() + " " +kindof_2);
			
	}

}