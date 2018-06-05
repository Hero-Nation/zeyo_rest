package net.heronation.zeyo.rest.shopmall;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.brand.BrandRepositoryTest;
import net.heronation.zeyo.rest.repository.shopmall.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ShopmallRepositoryTest {

	@Autowired
	ShopmallRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Shopmall item = new Shopmall();
			item.setName("name_" + a);

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test 
	public void statistic_query() {
		
		
 
//		브랜드  
//		전일 대비 증감율 : 5%
//		단일 사용 : 1231
//		복수 사용 : 1231
//		연동 건수 : 1231
 
		
		 
//		총 등록 수 : 1231	

		StringBuffer query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    shopmall i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		 
//		신규 등록 수 : 14	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    shopmall i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_item_r = q.getResultList();
		BigInteger today_new_item = today_new_item_r.get(0);
		log.debug(today_new_item_r.size() + " " +today_new_item);
		
		
//		삭제건수 : 5	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    shopmall i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.delete_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_delete_item_r = q.getResultList();
		BigInteger today_delete_item = today_delete_item_r.get(0);
		log.debug(today_delete_item_r.size() + " " +today_delete_item);
		
		
		
		
		 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_count / yesterday.new_count) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        shopmall i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        shopmall i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> increase_rate_r = q.getResultList();
		BigInteger increase_rate = increase_rate_r.get(0);
		log.debug(increase_rate_r.size() + " " +increase_rate);
		
		

		 
//		단일 사용 : 1231
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    COUNT(*) ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.shopmall, COUNT(ct.member) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        s.id AS shopmall, i.member_id AS member ");
		query.append("    FROM ");
		query.append("        item_shopmall_map ism ");
		query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		query.append("        AND s.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        ism.use_yn = 'Y' ");
		query.append("    GROUP BY s.id , i.member_id) ct ");
		query.append("    GROUP BY ct.shopmall) aaa ");
		query.append("WHERE ");
		query.append("    aaa.use_count = 1");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_use_count_1_r = q.getResultList();
		BigInteger brand_use_count_1 = brand_use_count_1_r.get(0);
		log.debug(brand_use_count_1_r.size() + " " +brand_use_count_1);
		

		 
//		복수 사용 : 1231
		
		query = new StringBuffer();
		  
		query.append("SELECT ");
		query.append("    COUNT(*) ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.shopmall, COUNT(ct.member) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        s.id AS shopmall, i.member_id AS member ");
		query.append("    FROM ");
		query.append("        item_shopmall_map ism ");
		query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		query.append("        AND s.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        ism.use_yn = 'Y' ");
		query.append("    GROUP BY s.id , i.member_id) ct ");
		query.append("    GROUP BY ct.shopmall) aaa ");
		query.append("WHERE ");
		query.append("    aaa.use_count > 1");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> brand_use_count_2_r = q.getResultList();
		BigInteger brand_use_count_2 = brand_use_count_2_r.get(0);
		log.debug(brand_use_count_2_r.size() + " " +brand_use_count_2);
		

		 
//		연동 상품 수 : 5
		
		query = new StringBuffer();  
		query.append("SELECT ");
		query.append("    COUNT(ct.shopmall) AS use_count ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        ct.shopmall, COUNT(ct.item) AS use_count ");
		query.append("    FROM ");
		query.append("        (SELECT ");
		query.append("        ism.shopmall_id AS shopmall, ");
		query.append("            ism.item_id AS item, ");
		query.append("            COUNT(m.id) AS use_count ");
		query.append("    FROM ");
		query.append("        item_shopmall_map ism ");
		query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		query.append("        AND i.link_yn = 'Y' ");
		query.append("    LEFT JOIN member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		query.append("    WHERE ");
		query.append("        ism.use_yn = 'Y' ");
		query.append("    GROUP BY ism.shopmall_id , ism.item_id) ct ");
		query.append("    WHERE ");
		query.append("        ct.use_count > 0 ");
		query.append("    GROUP BY ct.shopmall) ct");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> link_count_r = q.getResultList();
		BigInteger link_count = link_count_r.get(0);
		log.debug(link_count_r.size() + " " +link_count);
			
	}


}