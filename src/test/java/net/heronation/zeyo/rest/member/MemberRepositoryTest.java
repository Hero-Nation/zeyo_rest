package net.heronation.zeyo.rest.member;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

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

}