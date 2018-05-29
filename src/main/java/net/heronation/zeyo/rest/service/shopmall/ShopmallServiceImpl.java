package net.heronation.zeyo.rest.service.shopmall;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.shopmall_member_map.QShopmallMemberMap;
import net.heronation.zeyo.rest.repository.shopmall_member_map.ShopmallMemberMap;
import net.heronation.zeyo.rest.repository.shopmall_member_map.ShopmallMemberMapRepository;



@Slf4j
@Service
@Transactional
public class ShopmallServiceImpl implements ShopmallService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ShopmallRepository shopmallRepository;
	
	
	@Autowired
	private ShopmallMemberMapRepository shopmallMemberMapRepository;
	
	
	
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Map<String,Object> search(Map<String,Object> param, Pageable page) {
		
		
		
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
		select_query.append("                    scnh.member_id = m.id ");
		select_query.append("                GROUP BY scnh.member_id)) AS company_name, ");
		select_query.append("    s.name AS shopmall_name, ");
		select_query.append("    b.name AS brand_name, ");
		select_query.append("    COUNT(i.id) AS item_count, ");
		select_query.append("    AVG(i.price) AS item_price, ");
		select_query.append("    s.create_dt AS shopmall_create_dt ,");
		select_query.append("    (select count(ct.id) from (SELECT ");
		select_query.append("           si.id ");
		select_query.append(" FROM ");
		select_query.append("    shopmall ss ");
		select_query.append("        LEFT JOIN ");
		select_query.append("    item_shopmall_map sism ON ss.id = sism.shopmall_id ");
		select_query.append("        AND sism.use_yn = 'Y' ");
		select_query.append("        LEFT JOIN ");
		select_query.append("    item si ON sism.item_id = si.id AND si.use_yn = 'Y' ");
		select_query.append("        LEFT JOIN ");
		select_query.append("    member sm ON si.member_id = sm.id AND sm.use_yn = 'Y' ");
		select_query.append("        LEFT JOIN ");
		select_query.append("    brand sb ON si.brand_id = sb.id AND sb.use_yn = 'Y' ");
		select_query.append("        WHERE ");
		select_query.append("            1 = 1 ");
		select_query.append("                AND si.link_yn = 'Y' ");
		select_query.append("        GROUP BY  sm.id , ss.id , sb.id) ct )  AS link_count  ");
		
		StringBuffer  where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    shopmall s ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item_shopmall_map ism ON s.id = ism.shopmall_id ");
		where_query.append("        AND ism.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    brand b ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		where_query.append("WHERE ");
		where_query.append("    s.use_yn = 'Y' ");
 
		
		String name = (String)param.get("name");
		if(name != null) {
			where_query.append("        AND i.name like '%"+name+"%' ");	
		}
		
		//  않됨
		String company = (String)param.get("company");
		if(company != null) {
			where_query.append("        AND m.id  ="+company+" ");	
		}
		
		String brand = (String)param.get("brand");
		if(brand != null) {
			where_query.append("        AND i.brand_id ="+brand+" ");	
		}
		
		String shopmall = (String)param.get("shopmall");
		if(shopmall != null) {
			where_query.append("        AND ism.shopmall_id ="+shopmall+" ");	
		}
		
		// 않됨
//		String link = (String)param.get("link");
//		if(link != null && link.equals("Y")) {
//			where_query.append("        AND link_count != 0 ");	
//		}
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND s.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND s.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		 
		where_query.append("GROUP BY m.id , s.id , b.id ");
 
		
		
		StringBuffer  sort_query = new StringBuffer();
		sort_query.append("  ORDER BY s.");
		Sort sort = page.getSort();
		String sep = "";
		for(Sort.Order order   : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			 sep = ", ";
		}
		
		StringBuffer page_query  = new StringBuffer(); 
		page_query.append("  limit "); 
		page_query.append((page.getPageNumber() - 1) * page.getPageSize()); 
		page_query.append(" , ");
		page_query.append(page.getPageSize());
		 
		Query count_q =   entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String,Object>> count_list = count_q.getResultList();
		
		Query q =   entityManager.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>();
		
		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
 
//		[company_name] : [VARCHAR]) - [null]
//		[shopmall_name] : [VARCHAR]) - [shopmall5]
//		[brand_name] : [VARCHAR]) - [null]
//		[item_count] : [NUMERIC]) - [0]
//		[item_price] : [NUMERIC]) - [null]
//		[brand_create_dt] : [TIMESTAMP]) - [2018-05-24 14:12:31.0]
			
			
//            <td>{{pageData?.totalElements - ((pageData?.number-1) * listSize) - i}}</td>
//            <td>{{item.company_name}}</td>
//            <td>{{item.shopmall_name}}</td>
//            <td>{{item.brand_name}}</td>
//            <td>{{item.link_count}}</td>
//            <td>{{item.item_price}}</td>
//            <td>{{item.shopmall_create_dt | date: 'yyyy-MM-dd'}}</td>
//            <td>{{item.link_count > 0?'연동':'중지'}}</td>
			
			search_R.put("company_name", row[0]);
			search_R.put("shopmall_name", row[1]);
			search_R.put("brand_name", row[2]); 
			search_R.put("item_count", row[3]);
			search_R.put("item_price", row[4]);
			search_R.put("shopmall_create_dt", row[5]); 
			search_R.put("link_count", row[6]); 
			
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

	}


	@Override
	public
	Page<Map<String,Object>> client_search(Map<String,Object> param,Pageable page){
	
		
		StringBuffer  count_query = new StringBuffer();
		count_query.append("SELECT "); 
		count_query.append("    count(*) "); 
		
		
		StringBuffer  select_query = new StringBuffer(); 
		select_query.append("SELECT ");
		select_query.append("    s.name 		as shopmall_name, ");
		select_query.append("    s.create_dt	as shopmall_create_dt, ");
		select_query.append("    (SELECT ");
		select_query.append("            COUNT(si.id) ");
		select_query.append("        FROM ");
		select_query.append("            item_shopmall_map sism ");
		select_query.append("                INNER JOIN ");
		select_query.append("            item si ON sism.item_id = si.id ");
		select_query.append("                INNER JOIN ");
		select_query.append("            shopmall ss ON sism.shopmall_id = ss.id ");
		select_query.append("        WHERE ");
		select_query.append("            sism.use_yn = 'Y' AND si.use_yn = 'Y' ");
		select_query.append("                AND ss.use_yn = 'Y' ");
		select_query.append("                AND si.link_yn = 'Y' ");
		select_query.append("                AND ss.id = s.id ");
		select_query.append("        GROUP BY sism.shopmall_id) AS link_count ");
		
		StringBuffer  where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    item_shopmall_map ism ");
		where_query.append("        INNER JOIN ");
		where_query.append("    item i ON ism.item_id = i.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("WHERE ");
		where_query.append("    ism.use_yn = 'Y'  ");
		where_query.append("        AND i.use_yn = 'Y' ");  
		where_query.append("        AND s.use_yn = 'Y' ");  
  
		String member_id = (String)param.get("member_id");
		where_query.append("        AND i.member_id = "+member_id+" ");	
		
		String name = (String)param.get("name");
		if(name != null) {
			where_query.append("        AND i.name like '%"+name+"%' ");	
		}
  
		// 않됨
//		String link = (String)param.get("link");
//		if(link != null && link.equals("Y")) {
//			where_query.append("        AND link_count != 0 ");	
//		}
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND s.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND s.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}
		 
		where_query.append(" GROUP BY  ism.shopmall_id");
  
		StringBuffer  sort_query = new StringBuffer();
		sort_query.append("  ORDER BY s.");
		Sort sort = page.getSort();
		String sep = "";
		for(Sort.Order order   : sort) {
			sort_query.append(sep);
			sort_query.append(order.getProperty());
			sort_query.append(" ");
			sort_query.append(order.getDirection());
			 sep = ", ";
		}
		
		StringBuffer page_query  = new StringBuffer(); 
		page_query.append("  limit "); 
		page_query.append((page.getPageNumber() - 1) * page.getPageSize()); 
		page_query.append(" , ");
		page_query.append(page.getPageSize());
		 
		Query count_q =   entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String,Object>> count_list = count_q.getResultList();
		
		Query q =   entityManager.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String,Object>> return_list = new ArrayList<Map<String,Object>>();
		
		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>(); 
			
			search_R.put("shopmall_name", row[0]);
			search_R.put("shopmall_create_dt", row[1]);
			search_R.put("link_count", row[2]);  
			
			return_list.add(search_R);
		}
		
		return new PageImpl<Map<String,Object>>(return_list, page, count_list.size());
	}
	

	@Override
	@Transactional
	public Shopmall insert(String name, Long member_seq) {
		// TODO Auto-generated method stub
		
		Member m = memberRepository.getOne(member_seq);
		
		Shopmall o = new Shopmall();
		o.setMember(m);
		o.setName(name);
		o.setUseYn("Y"); 
		o.setCreateDt(new DateTime());
		return shopmallRepository.save(o);
	}

	@Override
	@Transactional
	public Map<String, Object> update(Long shopmall_id, Long member_seq, String name) {

		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(shopmall_id);
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.

			QShopmallMemberMap smm = QShopmallMemberMap.shopmallMemberMap;

			JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

			Long member_count = query.select(smm.member.countDistinct())
					.from(smm)
					.innerJoin(smm.shopmall)
					.on(smm.shopmall.useYn.eq("Y"))
					.where(smm.shopmall.id.eq(shopmall_id).and(smm.useYn.eq("Y"))).fetchCount();

			if (member_count == 1) {

				target.setName(name);
				shopmallRepository.save(target);
				R.put("CODE", "OK");

			} else {
				// Brand count in use is more than 1.
				R.put("CODE", "C");
			}

		}

		return R;
	}
	
	
	@Override
	@Transactional
	public Map<String, Object> delete(Long shopmall_id, Long member_seq) {

		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(shopmall_id);
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.


			QShopmallMemberMap smm = QShopmallMemberMap.shopmallMemberMap;

			JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

			Long member_count = query.select(smm.member.countDistinct())
					.from(smm)
					.innerJoin(smm.shopmall)
					.on(smm.shopmall.useYn.eq("Y"))
					.where(smm.shopmall.id.eq(shopmall_id).and(smm.useYn.eq("Y"))).fetchCount();

			if (member_count == 1) {

				shopmallRepository.delete(target);
				R.put("CODE", "OK");

			} else {
				// Brand count in use is more than 1.
				R.put("CODE", "C");
			}

		}

		return R;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> toggle_link(Long shopmall_id, Long member_seq) {
		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(shopmall_id);
		Member user = memberRepository.findOne(member_seq);
		
		QMember qm = QMember.member; 
		QShopmall sm = QShopmall.shopmall;
		
		QShopmallMemberMap smm = QShopmallMemberMap.shopmallMemberMap;
		
		ShopmallMemberMap db_smm = shopmallMemberMapRepository.findOne(smm.shopmall.id.eq(shopmall_id).and(smm.member.id.eq(member_seq)));

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} 
//		else if (!target.getMember().equals(user)) {
//			// user is not owner
//			R.put("CODE", "B");
//		} 
		else {
			
			// 토글 데이터가 없다면 새로생성하고 연동으로 만들어서 저장한다.  무조건 존재
//			if(db_qbmm == null) {
//				BrandMemberMap new_db_qbmm = new BrandMemberMap();
//				new_db_qbmm.setBrand(target);
//				new_db_qbmm.setMember(user);
//				new_db_qbmm.setLinkYn("Y");
//				new_db_qbmm.setUseYn("Y");
//				
//				brandMemberMapRepository.save(new_db_qbmm);
//				
//				R.put("CURRENT_LINK_YN", "Y");
//				
//			}else {
				
				String current_link_yn = db_smm.getLinkYn();
				if(current_link_yn.equals("Y")) {
					db_smm.setLinkYn("N");
					R.put("CURRENT_LINK_YN", "N");
				}else {
					db_smm.setLinkYn("Y");
					R.put("CURRENT_LINK_YN", "Y");
				}
				
				
//			}
 
			R.put("CODE", "OK");

		}

		return R;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> detail(Long shopmall_id, Long member_seq, Pageable page) {
 
		
		Map<String, Object> R = new HashMap<String, Object>();

		QItemShopmallMap qism = QItemShopmallMap.itemShopmallMap;
		QItem qi = QItem.item;
		QBrand qb = QBrand.brand;
		QShopmall qs = QShopmall.shopmall;
		
		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

		QueryResults<Tuple> db_result = query.select(qism.item.brand.name,qism.item.countDistinct())
				.from(qism)
				.innerJoin(qism.item).on(qism.item.useYn.eq("Y"))
				.innerJoin(qism.item.brand)
				.innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
				//.innerJoin(qism.item.brand).on(qism.item.brand.useYn.eq("Y"))
				//.innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
				.where(qism.shopmall.id.eq(shopmall_id).and(qism.useYn.eq("Y")).and(qism.item.brand.useYn.eq("Y"))) 
				.groupBy(qism.item.brand) 
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults(); 

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		
		for(Tuple row : db_result.getResults()) {
			Map<String,Object> item = new HashMap<String,Object>();
			
			item.put("brand", row.get(qism.item.brand.name));
			item.put("item_count", row.get(qism.item.countDistinct())); 
			
			list.add(item);
		}
		
		return new PageImpl<Map<String, Object>>(list, page, db_result.getTotal());
	}
 
}