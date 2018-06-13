package net.heronation.zeyo.rest.item;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.member.MemberRepositoryTest;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.*;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ItemRepositoryTest {

	@Autowired
	ItemRepository repository;

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Test
	public void password_encode() {
		log.debug(passwordEncoder.encode("icay_admin1"));
		log.debug(passwordEncoder.encode("jaja98"));
		log.debug(passwordEncoder.encode("!zeyo073502"));


		
//		2018-06-13 10:04:50.382 DEBUG 101732 --- [           main] n.h.zeyo.rest.item.ItemRepositoryTest    : $2a$10$Zp12ZzI9X9cx55b4uhvYLukdC5dKN7z1hFcUXk0TQCt9xxT8SXzMC
//		2018-06-13 10:04:50.569 DEBUG 101732 --- [           main] n.h.zeyo.rest.item.ItemRepositoryTest    : $2a$10$iGtb/QhHQd/KfdKdZx57su/dxBE2wcDK375gG9BwpyWH75/i5si5S
//		2018-06-13 10:04:50.639 DEBUG 101732 --- [           main] n.h.zeyo.rest.item.ItemRepositoryTest    : $2a$10$QEkyJWiZNw/dAZr31mw0ueyXdDe/n.pCMfvWdgEYNOA04ETFKe19S
		
	}
	
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Item item = new Item();
			item.setImageMode("imageMode_" + a);

			item.setImage("image_" + a);

			item.setSizeMeasureMode("sizeMeasureMode_" + a);

			item.setSizeMeasureImage("sizeMeasureImage_" + a);

			item.setName("name_" + a);

			item.setCode("code_" + a);

			//item.setPrice("price_" + a);

			item.setMadeinBuilder("madeinBuilder_" + a);

			//item.setMadeinDate(new Date());

			item.setLaundryYn("laundryYn_" + a);

			item.setDrycleaningYn("drycleaningYn_" + a);

			item.setIroningYn("ironingYn_" + a);

			item.setDrymethodYn("drymethodYn_" + a);

			item.setBleachYn("bleachYn_" + a);


			//item.setCreateDt(new Date());

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	@Test
	@Ignore
	public void asdf() {
//		QItem target = QItem.item;
//		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
//		QShopmall sm = QShopmall.shopmall;
		
		QItem i = QItem.item;
		QBrand b = QBrand.brand;
		QShopmall s = QShopmall.shopmall;
		QCategory c = QCategory.category;
		QMember m = QMember.member;
		QSubCategory sc = QSubCategory.subCategory;
		
		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		
		
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		 
	 
		QueryResults<Tuple> R = query.select( 
				i.name,cnh.name,b.name,c.name,sc.name,i.price,i.createDt,i.linkYn
		).from(i,cnh)
				.innerJoin(i.brand,b)
				.innerJoin(i.category,c)
				.innerJoin(i.subCategory,sc) 
				.innerJoin(i.member,m) 
				//.innerJoin(m,cnh.member).on(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)))

				.where(b.useYn.eq("Y")
						.and(c.useYn.eq("Y"))
						.and(sc.useYn.eq("Y"))
						.and(m.useYn.eq("Y")))
						 
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			
			search_R.put("item_name", row.get(i.name)); 
			search_R.put("company_name", row.get(cnh.name));
			search_R.put("brand_name", row.get(b.name));
			//search_R.put("shopmall_list", row.get(i.itemShopmallMaps.size()));
			search_R.put("category_name", row.get(c.name));
			search_R.put("sub_category_name", row.get(sc.name));
			search_R.put("price", row.get(i.price));
			search_R.put("item_change_dt", row.get(i.createDt));
			search_R.put("item_link_yn", row.get(i.linkYn));
				
 
			return_list.add(search_R);
		}
	}

	
	
	@SuppressWarnings("unchecked")
	@Test 
	@Ignore
	public void statistic_query() {
		
		
 
//		상품 현황
//		총 상품 수 : 1231
//		신규 상품 수 : 14
//		연동 상품 수 : 5
//		전일 대비 증감율: 5%
 
		
		
//		상품 현황
//		총 상품 수 : 1231

		StringBuffer query = new StringBuffer(); 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    item i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		
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
		query.append("    item i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y' ");
		query.append("        AND DATE(i.create_dt) = DATE(NOW())");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> today_new_item_r = q.getResultList();
		BigInteger today_new_item = today_new_item_r.get(0);
		log.debug(today_new_item_r.size() + " " +today_new_item);
		
		
//		상품 현황 
//		연동 상품 수 : 5
		
		query = new StringBuffer();
		 
		query.append("SELECT ");
		query.append("    COUNT(*) AS all_count ");
		query.append("FROM ");
		query.append("    item i ");
		query.append("WHERE ");
		query.append("    i.use_yn = 'Y'");
		query.append("        AND i.link_yn = 'Y' ");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> link_item_r = q.getResultList();
		BigInteger link_item = link_item_r.get(0);
		log.debug(link_item_r.size() + " " +link_item);
		
		
		
//		상품 현황 
//		전일 대비 증감율: 5%
		
		query = new StringBuffer();
		   
		query.append("SELECT ");
		query.append("    FLOOR((today.new_count / yesterday.new_count) * 100) AS increase_rate ");
		query.append("FROM ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        item i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW() - 1)) yesterday, ");
		query.append("    (SELECT ");
		query.append("        COUNT(*) AS new_count ");
		query.append("    FROM ");
		query.append("        item i ");
		query.append("    WHERE ");
		query.append("        i.use_yn = 'Y' ");
		query.append("            AND DATE(i.create_dt) = DATE(NOW())) today");
		
		q = entityManager.createNativeQuery(query.toString()); 
		List<BigInteger> increase_rate_r = q.getResultList();
		BigInteger increase_rate = increase_rate_r.get(0);
		log.debug(increase_rate_r.size() + " " +increase_rate);
			
	}
	
}