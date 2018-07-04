package net.heronation.zeyo.rest.size_table;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item.ItemRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SizeTableRepositoryTest {

	@Autowired
	ItemRepository repository;

	@Autowired
	EntityManager entityManager;
	
	 
	
	@SuppressWarnings("unchecked")
	@Test 
	public void statistic_query() {
		
		
 
//		사이즈표
//		총 등록 수 : 1231
//		신규 등록 수 : 14
//		전일 대비 증감율: 5%
 
		
		
//		상품 현황
//		총 상품 수 : 1231

		StringBuffer query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_table s ");
		query.append("WHERE ");
		query.append("    s.use_yn = 'Y'");
		
		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		
//		상품 현황 
//		신규 상품 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    size_table s ");
		query.append("WHERE ");
		query.append("    s.use_yn = 'Y' ");
		query.append("        AND DATE(s.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_item_r = q.getResultList();
		BigInteger today_new_item = today_new_item_r.get(0);
		log.debug(today_new_item_r.size() + " " +today_new_item);
 
		
		
		
//		상품 현황 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_count / yesterday.new_count) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        size_table s ");
		query.append("    WHERE ");
		query.append("        s.use_yn = 'Y' ");
		query.append("            AND DATE(s.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        size_table s ");
		query.append("    WHERE ");
		query.append("        s.use_yn = 'Y' ");
		query.append("            AND DATE(s.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> increase_rate_r = q.getResultList();
		BigInteger increase_rate = increase_rate_r.get(0);
		log.debug(increase_rate_r.size() + " " +increase_rate);
			
	}
	
}