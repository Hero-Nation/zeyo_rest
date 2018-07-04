package net.heronation.zeyo.rest.material;

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
import net.heronation.zeyo.rest.repository.material.Material;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MaterialRepositoryTest {

	@Autowired
	MaterialRepository repository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Material item = new Material();
			item.setName("name_" + a);

			item.setImage("image_" + a);

			item.setMetaDesc("metaDesc_" + a);

			//item.setCreateDt(new Date());

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	

	@SuppressWarnings("unchecked")
	@Test 
	public void statistic_query() {
		
		
 
//		소재
//		총 등록 수 : 1231
//		신규 등록 수 : 14
//		관리자입력 : 5
//		직접입력 : 5
 
		
		
//		소재
//		총 등록 수 : 1231

		StringBuffer query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    material i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		
//		소재
//		신규 등록 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    material i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_item_r = q.getResultList();
		BigInteger today_new_item = today_new_item_r.get(0);
		log.debug(today_new_item_r.size() + " " +today_new_item);
		
		

			
	}


}