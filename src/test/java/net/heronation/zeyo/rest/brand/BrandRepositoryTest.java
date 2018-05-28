package net.heronation.zeyo.rest.brand;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
 
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.*;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BrandRepositoryTest {

	@Autowired
	BrandRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			Brand item = new Brand();
			item.setName("name_" + a);

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	
	@Test
	@Ignore
	public void search() {
		
//		select ism.id as ism_id,m.name as member_name,s.id as shopmall_id ,b.id as brand_id,count(i.id) as item_count ,avg(i.price) as item_price,b.create_dt
//		
//		   from item_shopmall_map ism 
//	       inner join shopmall s on ism.shopmall_id = s.id
//	       inner join item i on ism.item_id = i.id 
//	       inner join brand b on i.brand_id = b.id
//	       inner join member m on i.member_id = m.id
//	       
//	       group by b.id,s.id
	       
		

		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
		QShopmall s = QShopmall.shopmall;
		QBrand b = QBrand.brand;
		QItem i = QItem.item;
		
		QMember m = QMember.member;
		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
		
		
		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
		 
	 
		QueryResults<Tuple> R = query.select( 
				b.name,s.name,i.name
		).from(ism)
				.innerJoin(ism.shopmall,s)
				.innerJoin(ism.item,i)
				.innerJoin(i.brand,b)
				//.innerJoin(m,cnh.member).on(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)))

				.where(ism.useYn.eq("Y")
						.and(s.useYn.eq("Y"))
						.and(i.useYn.eq("Y"))
						.and(b.useYn.eq("Y"))).groupBy(b.id).fetchJoin().groupBy(s.id).fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			search_R.put("brand_name", row.get(b.name));
			search_R.put("shopmall_name", row.get(s.name));
			search_R.put("item_name", row.get(i.name));
			
//		//	search_R.put("company_name", row.get(cnh.name));
//			search_R.put("brand_name", row.get(b.name));
//			//search_R.put("shopmall_list", row.get(i.itemShopmallMaps.size()));
//			search_R.put("category_name", row.get(c.name));
//			search_R.put("sub_category_name", row.get(sc.name));
//			search_R.put("price", row.get(i.price));
//			search_R.put("item_change_dt", row.get(i.createDt));
//			search_R.put("item_link_yn", row.get(i.linkYn));
			
			log.debug(search_R.toString());
 
			return_list.add(search_R);
		}

	}
	
	@SuppressWarnings("unchecked")
	@Test  
	public void search_native() {
		
		StringBuffer  count_query = new StringBuffer();
		count_query.append("SELECT "); 
		count_query.append("    count(*) "); 
		
		
		StringBuffer  select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    (SELECT ");
		select_query.append("            cnh.name ");
		select_query.append("        FROM ");
		select_query.append("            company_no_history cnh ");
		select_query.append("        WHERE ");
		select_query.append("            cnh.id = (SELECT ");
		select_query.append("                    MAX(scnh.id) ");
		select_query.append("                FROM ");
		select_query.append("                    company_no_history scnh ");
		select_query.append("                WHERE ");
		select_query.append("                    scnh.member_id = m.id)) AS company_name, ");
		select_query.append("    ism.id AS ism_id, ");
		select_query.append("    m.name AS member_name, ");
		select_query.append("    b.name AS brand_name, ");
		select_query.append("    s.name AS shopmall_name, "); 
		select_query.append("    COUNT(i.id) AS item_count, ");
		select_query.append("    AVG(i.price) AS item_price, ");
		select_query.append("    IFNULL((SELECT ");
		select_query.append("                    COUNT(*) ");
		select_query.append("                FROM ");
		select_query.append("                    item_shopmall_map sism ");
		select_query.append("                        INNER JOIN ");
		select_query.append("                    shopmall ss ON sism.shopmall_id = ss.id ");
		select_query.append("                        INNER JOIN ");
		select_query.append("                    item si ON sism.item_id = si.id ");
		select_query.append("                        INNER JOIN ");
		select_query.append("                    brand sb ON si.brand_id = sb.id ");
		select_query.append("                        INNER JOIN ");
		select_query.append("                    member sm ON si.member_id = sm.id ");
		select_query.append("                WHERE ");
		select_query.append("                    sism.use_yn = 'Y' AND ss.use_yn = 'Y' ");
		select_query.append("                        AND si.use_yn = 'Y' ");
		select_query.append("                        AND sb.use_yn = 'Y' ");
		select_query.append("                        AND sm.use_yn = 'Y' ");
		select_query.append("                        AND si.link_yn = 'Y' ");
		select_query.append("                        AND sb.id = s.id ");
		select_query.append("                        AND ss.id = s.id ");
		select_query.append("                GROUP BY sb.id , ss.id), ");
		select_query.append("            0) AS link_count, ");
		select_query.append("    b.create_dt AS brand_create_dt ");
		
		StringBuffer  where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    item_shopmall_map ism ");
		where_query.append("        INNER JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    item i ON ism.item_id = i.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    brand b ON i.brand_id = b.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    member m ON i.member_id = m.id ");
		where_query.append("WHERE ");
		where_query.append("    ism.use_yn = 'Y' AND s.use_yn = 'Y' ");
		where_query.append("        AND i.use_yn = 'Y' ");
		where_query.append("        AND b.use_yn = 'Y' ");
		where_query.append("        AND m.use_yn = 'Y' "); 
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", "name");
		param.put("company", "name");
		param.put("brand", null);
		param.put("shopmall", null);
		param.put("link", null);
		param.put("start", null);
		param.put("end", null);
		
		
		String name = (String)param.get("name");
		if(name != null) {
			where_query.append("        AND i.name like '%"+name+"%' ");	
		}
		
		//  않됨
//		String company = (String)param.get("company");
//		if(company != null) {
//			where_query.append("        AND company_name like '%"+company+"%' ");	
//		}
		
		String brand = (String)param.get("brand");
		if(brand != null) {
			where_query.append("        AND b.name like '%"+brand+"%' ");	
		}
		
		String shopmall = (String)param.get("shopmall");
		if(shopmall != null) {
			where_query.append("        AND s.name like '%"+shopmall+"%' ");	
		}
		
		// 않됨
//		String link = (String)param.get("link");
//		if(link != null && link.equals("Y")) {
//			where_query.append("        AND link_count != 0 ");	
//		}
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND b.create_dt >= STR_TO_DATE('+start+', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND b.create_dt <= STR_TO_DATE('+end+', '%Y-%m-%d %H:%i:%s')");	
		}
		
		 
		
		where_query.append("GROUP BY b.id , s.id");
 
		
		Query count_q =   entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String,Object>> count_list = count_q.getResultList();
		log.debug("-----------------------------------------------");
		log.debug(count_list.size()+"");
		
		Query q =   entityManager.createNativeQuery(select_query.append(where_query).toString());
		 
		List<Map<String,Object>> list =q.getResultList();
		for(Map<String,Object> row : list) {
			log.debug(row.toString());
		}		
			
	}
	
	
	@Test 
	@Ignore
	public void search_iso_format() {
		DateTime a = new DateTime();
		
		log.debug(a.toString(""));
	}

}