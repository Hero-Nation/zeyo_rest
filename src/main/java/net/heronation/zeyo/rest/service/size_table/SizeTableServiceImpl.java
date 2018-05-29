package net.heronation.zeyo.rest.service.size_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Map<String,Object> search(Map<String,Object> param, Pageable page) {

		
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();  
		select_query.append("SELECT ");
		select_query.append("    i.id				as item_id, ");
		select_query.append("    i.name				as item_name, ");
		select_query.append("    m.name				as member_name, ");
		select_query.append("    b.name				as brand_name, ");
		select_query.append("    s.name				as shopmall_name, ");
		//select_query.append("    c.name				as category_name, ");
		//select_query.append("    sc.name			as sub_category_name, ");
		//select_query.append("    i.price			as price, ");
		select_query.append("    i.create_dt		as item_create_dt, ");
		select_query.append("    i.size_table_yn			as item_size_table_yn , ");
		select_query.append("( SELECT ");
		select_query.append("    cnh.name ");
		select_query.append("FROM ");
		select_query.append("    company_no_history cnh ");
		select_query.append("        INNER JOIN ");
		select_query.append("    (SELECT ");
		select_query.append("        MAX(id) AS id ");
		select_query.append("    FROM ");
		select_query.append("        company_no_history ");
		select_query.append("    GROUP BY member_id) pcnh ON cnh.id = pcnh.id where cnh.member_id = m.id ) as company_name   ");
 
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    item i ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    brand b ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item_shopmall_map ism ON i.id = ism.item_id AND ism.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("        AND s.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    category c ON i.category_id = c.id AND c.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    sub_category sc ON i.sub_category_id = sc.id ");
		where_query.append("        AND sc.use_yn = 'Y' ");
		where_query.append("WHERE ");
		where_query.append("    i.use_yn = 'Y'");
		
//		param.put("name", name);
//		param.put("company", company);
//		param.put("brand", brand);
//		param.put("shopmall", shopmall); 
//		param.put("size_link", size_link); 
//		param.put("category", category); 
//		param.put("sub_category", sub_category); 
//		param.put("start_price", start_price); 
//		param.put("end_price", end_price);   
		
		
		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   i.name like '%" + name + "%' "); 
		}

		String company = (String) param.get("company");
		if (company != null) {
			where_query.append("        AND   m.id = " + company + " ");
		}
		
		String brand = (String) param.get("brand");
		if (brand != null) {
			where_query.append("        AND   b.id = " + brand + " ");
		}
		
		String shopmall = (String) param.get("shopmall");
		if (shopmall != null) {
			where_query.append("        AND   s.id = " + shopmall + " ");
		}

		
		String size_table = (String) param.get("size_table");
		if (size_table != null && size_table.equals("Y")) {
			where_query.append("        AND   i.size_table_yn = 'Y' ");
		}
		
		
		String start_price = (String)param.get("start_price");
		if (start_price != null) {
			where_query.append("        AND   i.price >= " + start_price + " ");
		}

		
		String end_price = (String)param.get("end_price");
		if (end_price != null) {
			where_query.append("        AND   i.price <= " + end_price + " ");
		}
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND i.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND i.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

 

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY i.");
		Sort sort = page.getSort();
		String sep = "";
		for (Sort.Order order : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			sep = ", ";
		}

		StringBuffer page_query = new StringBuffer();
		page_query.append("  limit ");
		page_query.append((page.getPageNumber() - 1) * page.getPageSize());
		page_query.append(" , ");
		page_query.append(page.getPageSize());

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String, Object>> count_list = count_q.getResultList();

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

//			select_query.append("    i.id				as item_id, ");
//			select_query.append("    i.name				as item_name, ");
//			select_query.append("    m.name				as member_name, ");
//			select_query.append("    b.name				as brand_name, ");
//			select_query.append("    s.name				as shopmall_name, "); 
//			select_query.append("    i.create_dt		as item_create_dt, ");
//			select_query.append("    i.size_table_yn			as item_size_table_yn , ");
//			select_query.append("    i.size_table_yn			as company_name , ");
			 

			search_R.put("item_id", row[0]);
			search_R.put("item_name", row[1]);
			search_R.put("member_name", row[2]);
			search_R.put("brand_name", row[3]);
			search_R.put("shopmall_name", row[4]);   
			search_R.put("item_create_dt", row[5]); 
			search_R.put("item_size_table_yn", row[6]);  
			search_R.put("company_name", row[7]);  

			return_list.add(search_R);
		}

		int totalPages = (count_list.size() / page.getPageSize());
		if (count_list.size() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.size());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;
		
		
		
//		QSizeOption target = QSizeOption.sizeOption;
//		
//		
//		QItem i = QItem.item;
//		QBrand b = QBrand.brand;
//		QShopmall s = QShopmall.shopmall;
//		QCategory c = QCategory.category;
//		QMember m = QMember.member;
//		QSubCategory sc = QSubCategory.subCategory;
//		
//		QCompanyNoHistory cnh = QCompanyNoHistory.companyNoHistory;
//		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;
//		
//		
//		JPAQuery<Member> query = new JPAQuery<Member>(entityManager);
//		 
//	 
//		QueryResults<Tuple> R = query.select( 
//				i.id,i.name,b.name,c.name,sc.name,i.price,i.createDt,i.sizeTableYn
//		).from(i)
//				.innerJoin(i.brand,b)
//				.innerJoin(i.category,c)
//				.innerJoin(i.subCategory,sc) 
//				.innerJoin(i.member,m) 
//				//.innerJoin(m,cnh.member).on(cnh.id.in(JPAExpressions.select(cnh.id.max()).from(cnh).groupBy(cnh.member.id)))
//
//				.where(b.useYn.eq("Y")
//						.and(c.useYn.eq("Y"))
//						.and(sc.useYn.eq("Y"))
//						.and(m.useYn.eq("Y")).and(where))
//						 
//				.fetchResults();
//
//		List<Tuple> search_list = R.getResults();
//		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();
//
//		for (Tuple row : search_list) {
//			Map<String, Object> search_R = new HashMap<String, Object>();
// 
//			search_R.put("item_id", row.get(i.id));
//			search_R.put("item_name", row.get(i.name)); 
//		//	search_R.put("company_name", row.get(cnh.name));
//			search_R.put("brand_name", row.get(b.name));
//			//search_R.put("shopmall_list", row.get(i.itemShopmallMaps.size()));
//			search_R.put("category_name", row.get(c.name));
//			search_R.put("sub_category_name", row.get(sc.name));
//			search_R.put("price", row.get(i.price));
//			search_R.put("item_change_dt", row.get(i.createDt));
//			search_R.put("item_size_table_yn", row.get(i.sizeTableYn));
//				
// 
//			return_list.add(search_R);
//		}
//		return new PageImpl<Map<String, Object>>(return_list, page, R.getTotal());

	}
}