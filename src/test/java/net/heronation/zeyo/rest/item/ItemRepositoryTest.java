package net.heronation.zeyo.rest.item;

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
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;

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
public class ItemRepositoryTest {

	@Autowired
	ItemRepository repository;

	@Autowired
	EntityManager entityManager;
	
	
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

}