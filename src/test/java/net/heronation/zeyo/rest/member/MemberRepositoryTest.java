package net.heronation.zeyo.rest.member;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.*;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;

	
	@Autowired
	EntityManager entityManager;
	
	
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Member item = new Member();
			item.setMemberId("memberId_" + a);

			item.setName("name_" + a);

			item.setPassword("password_" + a);

			item.setPhone("phone_" + a);

			item.setEmail("email_" + a);

			item.setManager("manager_" + a);

			item.setManagerEmail("managerEmail_" + a);

			item.setManagerPhone("managerPhone_" + a);

			//item.setCreateDt(new Date());

//			item.setDeleteDt(new Date());

			item.setAdminYn("adminYn_" + a);

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	@Test 
	
	@Ignore
	public void my_brand() {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);

		
		/*
		  
		  
		   SELECT * FROM zeyo.brand_member_map;SELECT 
			    brand1_.name AS col_0_0_,
			    s.name,
			    COUNT(DISTINCT item0_.id) AS col_1_0_
			FROM
			    item item0_
			        LEFT OUTER JOIN
			    brand brand1_ ON item0_.brand_id = brand1_.id
			        AND (brand1_.use_yn = 'Y')
			        LEFT OUTER JOIN
			    item_shopmall_map m ON item0_.id = m.item_id
			        LEFT OUTER JOIN
			    shopmall s ON m.shopmall_id = s.id
			WHERE
			    brand1_.member_id = 1
			GROUP BY brand1_.id , m.shopmall_id
		  
		 */
		
		QMember m = QMember.member;
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		

		QueryResults<Tuple> R = query.select(

				b.name,ism.shopmall.name, i.id.countDistinct()

		).from(i)
				.leftJoin(i.brand, b).on(b.useYn.eq("Y"))  
				.leftJoin(i.itemShopmallMaps,ism).on(ism.useYn.eq("Y"))
				//.leftJoin(ism.shopmall,s).on(s.useYn.eq("Y"))
				
				.where(b.member.id.eq(1L))
				.groupBy(ism.shopmall.id )
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			search_R.put("brand_name", row.get(b.name));
			search_R.put("shopmall_name", row.get(ism.shopmall.name)); 
			search_R.put("item_count", row.get(i.id.countDistinct())); 

			log.debug(search_R.toString());
			return_list.add(search_R);
		}
	}
	
	
	@Test 
	@Ignore
	public void my_shopmall() {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
 
		
		QMember m = QMember.member;
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		

		QueryResults<Tuple> R = query.select(

				s.name ,b.name,i.id.countDistinct()

		).from(ism)
				.innerJoin(ism.shopmall,s)
				.innerJoin(ism.item,i)
				.innerJoin(ism.item.brand,b)
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(s.member.id.eq(2L)))
				.groupBy(b.id )
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			
			search_R.put("shopmall_name", row.get(ism.shopmall.name));
			search_R.put("brand_name", row.get(ism.item.brand.name));
			search_R.put("item_count", row.get(ism.item.id.countDistinct())); 

			log.debug(search_R.toString());
			return_list.add(search_R);
		}
	}
	
	
	
	@Test
	@Ignore
	public void my_item() {
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
 
		
		QMember m = QMember.member;
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		

		QueryResults<Tuple> R = query.select(

				i.name,s.name ,b.name

		).from(ism)
				.innerJoin(ism.shopmall,s)
				.innerJoin(ism.item,i)
				.innerJoin(ism.item.brand,b)
				.where(s.useYn.eq("Y").and(i.useYn.eq("Y")).and(b.useYn.eq("Y")).and(i.member.id.eq(2L)))
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			
			search_R.put("item_name", row.get(i.name)); 
			search_R.put("shopmall_name", row.get(s.name));
			search_R.put("brand_name", row.get(b.name));
			

			log.debug(search_R.toString());
			return_list.add(search_R);
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@Test 
	public void statistic_query() {
		
		
		
//		회원 현황
//		
//		일반회원
//		총 회원 수 : 1231
//		신규 회원 수 : 14
//		탈퇴 회원 수 : 5
//		전일 대비 증감율: 5%
//		
//		판매회원
//		총 업체회원 수 : 1231
//		신규 업체회원 수 : 14
//		탈퇴 업체회원 수 : 5
//		전일 대비 증감율: 5%
//		
//		상품 현황
//		총 상품 수 : 1231
//		신규 상품 수 : 14
//		연동 상품 수 : 5
//		전일 대비 증감율: 5%
//		
//		사이즈표
//		총 등록 수 : 1231
//		신규 등록 수 : 14
//		전일 대비 증감율: 5%
		
		
		
		// 판매회원
		// 총 회원 수 : 1231

		StringBuffer query = new StringBuffer();
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    member m ");
		query.append("WHERE ");
		query.append("    m.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(query.toString());
		List<BigInteger> all_count_r = q.getResultList();
		BigInteger all_count = all_count_r.get(0);
		log.debug(all_count_r.size() + " " +all_count);
		
//		판매회원 
//		신규 회원 수 : 14
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_new_member ");
		query.append("FROM ");
		query.append("    member m ");
		query.append("WHERE ");
		query.append("    m.use_yn = 'Y'");
		query.append("        AND DATE(m.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_member_r = q.getResultList();
		BigInteger today_new_member = today_new_member_r.get(0);
		log.debug(today_new_member_r.size() + " " +today_new_member);
		
		
//		판매회원 
//		탈퇴 회원 수 : 5	
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS today_quit_member ");
		query.append("FROM ");
		query.append("    member m ");
		query.append("WHERE ");
		query.append("    m.use_yn = 'Y'");
		query.append("        AND DATE(m.delete_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_quit_member_r = q.getResultList();
		BigInteger today_quit_member = today_quit_member_r.get(0);
		log.debug(today_quit_member_r.size() + " " +today_quit_member);
		
		
		
//		판매회원 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_member / yesterday.new_member) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_member ");
		query.append("    FROM ");
		query.append("        member m ");
		query.append("    WHERE ");
		query.append("        m.use_yn = 'Y' ");
		query.append("            AND DATE(m.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_member ");
		query.append("    FROM ");
		query.append("        member m ");
		query.append("    WHERE ");
		query.append("        m.use_yn = 'Y' ");
		query.append("            AND DATE(m.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> increase_rate_r = q.getResultList();
		BigInteger increase_rate = increase_rate_r.get(0);
		log.debug(increase_rate_r.size() + " " +increase_rate);
			
	}

}