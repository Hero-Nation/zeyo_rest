package net.heronation.zeyo.rest.service.size_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_table.QSizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;

@Slf4j
@Service
@Transactional
public class SizeTableServiceImpl implements SizeTableService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SizeTableRepository size_tableRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Map<String, Object>> search(Predicate where, Pageable page) {

		
		QSizeOption target = QSizeOption.sizeOption;
		
		
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
				i.id,i.name,b.name,c.name,sc.name,i.price,i.createDt,i.sizeTableYn
		).from(i)
				.innerJoin(i.brand,b)
				.innerJoin(i.category,c)
				.innerJoin(i.subCategory,sc) 
				.innerJoin(i.member,m) 
				//.innerJoin(m,cnh.member).on(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)))

				.where(b.useYn.eq("Y")
						.and(c.useYn.eq("Y"))
						.and(sc.useYn.eq("Y"))
						.and(m.useYn.eq("Y")).and(where))
						 
				.fetchResults();

		List<Tuple> search_list = R.getResults();
		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Tuple row : search_list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
			search_R.put("item_id", row.get(i.id));
			search_R.put("item_name", row.get(i.name)); 
		//	search_R.put("company_name", row.get(cnh.name));
			search_R.put("brand_name", row.get(b.name));
			//search_R.put("shopmall_list", row.get(i.itemShopmallMaps.size()));
			search_R.put("category_name", row.get(c.name));
			search_R.put("sub_category_name", row.get(sc.name));
			search_R.put("price", row.get(i.price));
			search_R.put("item_change_dt", row.get(i.createDt));
			search_R.put("item_size_table_yn", row.get(i.sizeTableYn));
				
 
			return_list.add(search_R);
		}
		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());

	}
}