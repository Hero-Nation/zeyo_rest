package net.heronation.zeyo.rest.size_option;

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
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SizeOptionRepositoryTest {

	@Autowired
	SizeOptionRepository repository;

	@Autowired
	EntityManager entityManager;
	
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			SizeOption item = new SizeOption();
			item.setName("name_" + a);

		//	item.setCreateDt(new Date());

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}

	
	@SuppressWarnings("unchecked")
	@Test 
	public void statistic_query() {
		
		
 
//		옵션
//		총 등록 수 : 1231
//		신규 등록 수 : 14
//		관리자입력 : 5
//		직접입력 : 5
 
		
		
//		옵션
//		총 등록 수 : 1231

		StringBuffer query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		
//		옵션
//		신규 등록 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_item_r = q.getResultList();
		BigInteger today_new_item = today_new_item_r.get(0);
		log.debug(today_new_item_r.size() + " " +today_new_item);
		
		
//		옵션
//		'6', 'size_option', '기호', 'Y'
//		'7', 'size_option', '숫자', 'Y'
//		'8', 'size_option', '직접입력', 'Y'
//		'9', 'size_option', '숫자 - 하위', 'Y'

		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id = 8 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> kindof_8_r = q.getResultList();
		BigInteger kindof_8 = kindof_8_r.get(0);
		log.debug(kindof_8_r.size() + " " +kindof_8);
		
		
		
//		옵션
//		'6', 'size_option', '기호', 'Y'
//		'7', 'size_option', '숫자', 'Y'
//		'8', 'size_option', '직접입력', 'Y'
//		'9', 'size_option', '숫자 - 하위', 'Y'
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_option i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.kindof_id != 8 ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> kindof_not_8_r = q.getResultList();
		BigInteger kindof_not_8 = kindof_not_8_r.get(0);
		log.debug(kindof_not_8_r.size() + " " +kindof_not_8);
			
	}

}