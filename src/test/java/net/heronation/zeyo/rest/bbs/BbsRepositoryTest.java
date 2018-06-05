package net.heronation.zeyo.rest.bbs;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Date;
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
import net.heronation.zeyo.rest.repository.bbs.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BbsRepositoryTest {

	@Autowired
	BbsRepository repository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Bbs item = new Bbs();
			item.setTitle("title_" + a);

			item.setBbsContent("bbsContent_" + a);

			item.setReplyContent("replyContent_" + a);

			//item.setCreateDt(new Date());

			//item.setReplyDt(new Date());

			item.setStatus("status_" + a);

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	@SuppressWarnings("unchecked")
	@Test 
	public void statistic_query() {
		
		
		// 총 게시글 수 : 1231

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		
		
		
		// 신규 게시글 수 : 42
		
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_all_count ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_all_count_r = q.getResultList();
		BigInteger today_all_count = today_all_count_r.get(0);
		log.debug(today_all_count_r.size() + " " +today_all_count);
		
//		"A : 접수중
//		B : 확인중
//		C : 답변완료"
		
		// 답변 완료 : 1231
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_complete ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.status = 'C' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_complete_r = q.getResultList();
		BigInteger today_complete = today_complete_r.get(0);
		log.debug(today_complete_r.size() + " " +today_complete);
		 
		
		// 확인 중 : 42
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_ing ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.status = 'B' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_ing_r = q.getResultList();
		BigInteger today_ing = today_ing_r.get(0);
		log.debug(today_ing_r.size() + " " +today_ing);
		
		// 접수 중 : 123
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_receiving ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.status = 'A' ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_receiving_r = q.getResultList();
		BigInteger today_receiving = today_receiving_r.get(0);
		log.debug(today_receiving_r.size() + " " +today_receiving);
		
		// 제휴 문의 : 42

		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_receiving ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.kindof_id = 3 ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> kindof_3_r = q.getResultList();
		BigInteger kindof_3 = kindof_3_r.get(0);
		log.debug(kindof_3_r.size() + " " +kindof_3);
		
		// 1대1 문의 : 123
		
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_receiving ");
		query.append("FROM ");
		query.append("    bbs b ");
		query.append("WHERE ");
		query.append("    b.use_yn = 'Y' ");
		query.append("    AND b.kindof_id = 4 ");
		query.append("        AND DATE(b.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> kindof_4_r = q.getResultList();
		BigInteger kindof_4 = kindof_4_r.get(0);
		log.debug(kindof_4_r.size() + " " +kindof_4);
		
	}

}