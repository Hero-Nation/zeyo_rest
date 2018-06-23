
package net.heronation.zeyo.rest.service.brand;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.IdNameDto;
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.common.value.NameDto;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandDto;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.QMember;

@Slf4j
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {
		log.debug("search");

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*)  from ( ");

		StringBuffer select_query = new StringBuffer();
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
		select_query.append("                    scnh.member_id = m.id   GROUP BY scnh.member_id )) AS company_name, ");
		select_query.append("    m.name AS member_name, ");
		select_query.append("    b.name AS brand_name, ");
		select_query.append("    s.name AS shopmall_name, ");
		select_query.append("    COUNT(i.id) AS item_count, ");
		select_query.append("    AVG(i.price) AS item_price, ");
		select_query.append("    ( SELECT ");
		select_query.append("            count(si.id) ");
		select_query.append("        FROM ");
		select_query.append("            brand sb ");
		select_query.append("                LEFT JOIN ");
		select_query.append("            item si ON sb.id = si.brand_id AND si.use_yn = 'Y' ");
		select_query.append("                LEFT JOIN ");
		select_query.append("            item_shopmall_map sism ON si.id = sism.item_id ");
		select_query.append("                AND sism.use_yn = 'Y' ");
		select_query.append("                LEFT JOIN ");
		select_query.append("            shopmall ss ON sism.shopmall_id = ss.id ");
		select_query.append("                AND ss.use_yn = 'Y' ");
		select_query.append("                LEFT JOIN ");
		select_query.append("            member sm ON si.member_id = sm.id AND sm.use_yn = 'Y' ");
		select_query.append("        WHERE ");
		select_query.append("            1 = 1 AND sb.use_yn = 'Y' ");
		select_query.append("                AND sm.id = m.id ");
		select_query.append("                AND sb.id = b.id ");
		select_query.append("                AND ss.id = s.id ");
		select_query.append("                AND si.link_yn = 'Y' ");
		select_query.append("        GROUP BY sm.id , sb.id , ss.id)   AS link_count, ");
		select_query.append("    b.create_dt AS brand_create_dt ");

		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    brand b ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON b.id = i.brand_id AND i.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item_shopmall_map ism ON i.id = ism.item_id AND ism.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("        AND s.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		where_query.append("WHERE ");
		where_query.append("    b.use_yn = 'Y'  ");

		// Map<String,Object> param = new HashMap<String,Object>();
		// param.put("name", "name");
		// param.put("company", "name");
		// param.put("brand", null);
		// param.put("shopmall", null);
		// param.put("link", null);
		// param.put("start", null);
		// param.put("end", null);

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND i.name like '%" + name + "%' ");
		}

		// 업체명 대신에 member id를 넘긴다.
		String company = (String) param.get("company");
		if (company != null) {
			where_query.append("        AND m.id = " + company + " ");
		}

		String brand = (String) param.get("brand");

		if (brand != null) {
			where_query.append("        AND b.id = " + brand + " ");
		}

		String shopmall = (String) param.get("shopmall");
		if (shopmall != null) {
			where_query.append("        AND s.id = " + shopmall + " ");
		}

		// 않됨
		// String link = (String)param.get("link");
		// if(link != null && link.equals("Y")) {
		// where_query.append(" AND link_count != 0 ");
		// }

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND b.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND b.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		where_query.append(" GROUP BY  m.id , b.id , s.id ");

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY m.");
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

		Query count_q = entityManager.createNativeQuery(count_query.append(select_query).append(where_query).append(" ) count_table ").toString());
		BigInteger count_list = BigInteger.ZERO;
		
		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {
		    
		} else {
			count_list = count_result.get(0);
		}
		
		
		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// 0 [company_name] : [VARCHAR]) - [써니나라]
			// 1 [member_name] : [VARCHAR]) - [문써니]
			// 2 [brand_name] : [VARCHAR]) - [reebok]
			// 3 [shopmall_name] : [VARCHAR]) - [shopmall1]
			// 4 [item_count] : [NUMERIC]) - [4]
			// 5 [item_price] : [NUMERIC]) - [123123.0000]
			// 6 [link_count] : [NUMERIC]) - [0]
			// 7 [brand_create_dt] : [TIMESTAMP]) - [2018-05-24 14:12:31.0]

			search_R.put("company_name", row[0]);
			search_R.put("member_name", row[1]);
			search_R.put("brand_name", row[2]);
			search_R.put("shopmall_name", row[3]);
			search_R.put("item_count", row[4]);
			search_R.put("item_price", row[5]);
			search_R.put("link_count", row[6]);
			search_R.put("brand_create_dt", row[7]);

			return_list.add(search_R);
		}

		// "content" : [ ],
		// "totalPages" : 2, >> 전체 페이지수
		// "totalElements" : >> 전체 건수
		// "number" : 1, >> 현재 페이지
		// "size" : 20, >> 페이지 크기

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());
 
		return R;

	}
	
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> client_search(Map<String, Object> param, Pageable page) {
		log.debug("client_search");

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer(); 
		select_query.append("SELECT ");
		select_query.append("    b.id, ");
		select_query.append("    b.name, ");
		select_query.append("    b.create_dt, ");
		select_query.append("    IFNULL((SELECT ");
		select_query.append("                    COUNT(*) AS use_count ");
		select_query.append("                FROM ");
		select_query.append("                    item si ");
		select_query.append("                WHERE ");
		select_query.append("                    si.use_yn = 'Y' AND si.link_yn = 'Y' ");
		select_query.append("                        AND si.brand_id = b.id ");
		select_query.append("                GROUP BY si.brand_id), ");
		select_query.append("            0) AS link_count ");


		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    brand b ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON b.id = i.brand_id AND i.use_yn = 'Y' ");
		where_query.append(" WHERE ");
		where_query.append("    b.use_yn = 'Y' ");
 

		String member_id = (String) param.get("member_id");

		where_query.append("        AND b.member_id = " + member_id + " ");

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND b.name like '%" + name + "%' ");
		}

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND b.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND b.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		where_query.append(" GROUP BY b.id");

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY b.");
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
			search_R.put("brand_id", row[0]);
			search_R.put("brand_name", row[1]);
			search_R.put("brand_create_dt", row[2]);
			search_R.put("link_count", row[3]);

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
	@Transactional(readOnly = true)
	public Map<String, Object> detail(Long brand_id, Long member_seq, Pageable page) {
		log.debug("detail");

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from (");

		StringBuffer select_query = new StringBuffer(); 
		select_query.append("SELECT ");
		select_query.append("    s.name as shopmall_name, ");
		select_query.append("    COUNT(i.id) AS item_count ");


		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    item_shopmall_map ism ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("        AND s.use_yn = 'Y' ");
		where_query.append(" WHERE ");
		where_query.append("    ism.use_yn = 'Y' AND i.brand_id = "+brand_id+" "); 
		where_query.append("       AND i.member_id = "+member_seq);
		
		StringBuffer group_query = new StringBuffer();
		
		group_query.append(" GROUP BY s.id ");
		
		
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY s.");
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

		Query count_q = entityManager.createNativeQuery(count_query.append(select_query).append(where_query).append(" ) count_table ").toString());
		BigInteger count_list = BigInteger.ZERO;
		
		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {
		    
		} else {
			count_list = count_result.get(0);
		}
		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(group_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
			search_R.put("shopmall_name", row[0]);
			search_R.put("item_count", row[1]); 

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());

		return R;

		// Map<String, Object> R = new HashMap<String, Object>();
		//
		// QItemShopmallMap qism = QItemShopmallMap.itemShopmallMap;
		// QItem qi = QItem.item;
		// QBrand qb = QBrand.brand;
		// QShopmall qs = QShopmall.shopmall;
		//
		// JPAQuery<Item> query = new JPAQuery<Item>(entityManager);
		//
		// QueryResults<Tuple> db_result = query.select(qism.shopmall.name,
		// qism.item.countDistinct()).from(qism)
		// .innerJoin(qism.item).on(qism.item.useYn.eq("Y")).innerJoin(qism.item.brand).innerJoin(qism.shopmall)
		// .on(qism.shopmall.useYn.eq("Y"))
		// // .innerJoin(qism.item.brand).on(qism.item.brand.useYn.eq("Y"))
		// // .innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
		// .where(qism.item.brand.id.eq(brand_id).and(qism.useYn.eq("Y"))).groupBy(qism.shopmall)
		// .offset((page.getPageNumber() - 1) *
		// page.getPageSize()).limit(page.getPageSize()).fetchResults();
		//
		// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//
		// for (Tuple row : db_result.getResults()) {
		// Map<String, Object> item = new HashMap<String, Object>();
		//
		// item.put("shopmall", row.get(qism.shopmall.name));
		// item.put("item_count", row.get(qism.item.countDistinct()));
		//
		// list.add(item);
		// }
		//
		// return new PageImpl<Map<String, Object>>(list, page, db_result.getTotal());
	}



	@Override
	@Transactional
	public Brand insert(NameDto param, Long member_seq) { 
		log.debug("insert");

		Member m = memberRepository.getOne(member_seq);

		Brand o = new Brand();
		o.setMember(m);
		o.setName(param.getName());
		o.setUseYn("Y");
		o.setCreateDt(new DateTime());
		return brandRepository.save(o);
	}

	@Override
	@Transactional
	public Map<String, Object> delete( List<LIdDto> param, Long member_seq) {
		log.debug("delete");

		Map<String, Object> R = new HashMap<String, Object>();
		
		for(LIdDto id : param) {

			Brand target = brandRepository.findOne(id.getId());
			Member user = memberRepository.findOne(member_seq);

			if (target == null || target.getUseYn().equals("N")) {

			} else if (!target.getMember().equals(user)) {
				
				
			} else {

				// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.

				BigInteger use_count = this.get_brand_use_count_of_member(id.getId());

				if (use_count.equals(BigInteger.ONE) || use_count.equals(BigInteger.ZERO)) {

					target.setUseYn("N");
					target.setDeleteDt(new DateTime());

					R.put("CODE", "OK");

				} else {
					// Brand count in use is more than 1.
					R.put("CODE", "C");
				}

			}
			
		}



		return R;
	}

	@Override
	@Transactional
	public Map<String, Object> update_name(IdNameDto param, Long member_seq ) {
		log.debug("update_name");

		Map<String, Object> R = new HashMap<String, Object>();

		Brand target = brandRepository.findOne(param.getId());
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.

			BigInteger use_count = this.get_brand_use_count_of_member(param.getId());

			if (use_count.equals(BigInteger.ONE) || use_count.equals(BigInteger.ZERO)) {

				target.setName(param.getName());
				brandRepository.save(target);
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
	public Map<String, Object> toggle_link(BrandDto param,   Long member_seq ) {
		log.debug("toggle_link "); 

		Map<String, Object> R = new HashMap<String, Object>();

		// 나의 브랜드의 상품의 연동정보를 일괄 변경시킨다.

		Brand target = brandRepository.findOne(param.getId());
		Member user = memberRepository.findOne(member_seq);

		QBrand qb = QBrand.brand;
		QMember qm = QMember.member;
		QItem qi = QItem.item;

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else {
			JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

			if (param.getLink().equals("N")) {

				queryFactory.update(qi)
						.where(qi.member.id.eq(member_seq).and(qi.brand.id.eq(param.getId()).and(qi.useYn.eq("Y"))))
						.set(qi.linkYn, "Y").execute();

			} else {
				queryFactory.update(qi)
						.where(qi.member.id.eq(member_seq).and(qi.brand.id.eq(param.getId()).and(qi.useYn.eq("Y"))))
						.set(qi.linkYn, "N").execute();
			}

			R.put("CODE", "OK");

		}

		return R;
	}

 

	@Override
	public Map<String, Object> distinct_with_member_id() {
		log.debug("distinct_with_member_id");

		Map<String, Object> R = new HashMap<String, Object>();

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    cnh.member_id as member_id, cnh.name as company_name ");
		select_query.append("FROM ");
		select_query.append("    company_no_history cnh ");
		select_query.append("        INNER JOIN ");
		select_query.append("    (SELECT ");
		select_query.append("        MAX(st.id) AS max_id ");
		select_query.append("    FROM ");
		select_query.append("        company_no_history st ");
		select_query.append("    GROUP BY st.member_id) st ON cnh.id = st.max_id ");
		select_query.append("        INNER JOIN ");
		select_query.append("    member m ON cnh.member_id = m.id AND m.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(select_query.toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
			search_R.put("member_id", row[0]);
			search_R.put("company_name", row[1]);

			return_list.add(search_R);
		}

		R.put("contents", return_list);

		return R;
	}

	@Override
	public Map<String, Object> use_count() {
		log.debug("use_count");

		Map<String, Object> R = new HashMap<String, Object>();

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    count(*)  ");
		select_query.append("FROM ");
		select_query.append("    brand b ");
		select_query.append("       WHERE b.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(select_query.toString());
		BigInteger use_count = (BigInteger) q.getSingleResult();

		R.put("contents", use_count);

		return R;
	}

	private BigInteger get_brand_use_count_of_member(Long brand_id) {
		log.debug("get_brand_use_count_of_member");

		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT ");
		varname1.append("    b.id AS brand, COUNT(i.member_id) ");
		varname1.append("FROM ");
		varname1.append("    item i ");
		varname1.append("        inner JOIN ");
		varname1.append("    brand b ON b.id = i.brand_id AND i.use_yn = 'Y' ");
		varname1.append("        AND i.link_yn = 'Y' ");
		varname1.append("WHERE ");
		varname1.append("    b.use_yn = 'Y' ");
		varname1.append("    and b.id = " + brand_id + " ");
		varname1.append("GROUP BY b.id , i.member_id");

		Query count_q = entityManager.createNativeQuery(varname1.toString());
		
		List results = count_q.getResultList();
		if(results.isEmpty()) {
			BigInteger use_count = BigInteger.ZERO;
			return use_count;
		}else {
			BigInteger use_count = (BigInteger) count_q.getSingleResult();

			if (use_count == null)
				use_count = BigInteger.ZERO;

			return use_count;
		} 
		
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getStat() {

		// 브랜드 관리

		// 브랜드
		// 총 등록 수 : 1231
		// 단일 사용 : 1231
		// 신규 등록 수 : 14
		// 복수 사용 : 1231
		// 삭제건수 : 5
		// 연동 건수 : 1231
		// 전일 대비 증감율 : 5%

		return null;
	}

	@Override
	public Map<String, Object> findByName(String name) {
		Map<String, Object> R = new HashMap<String, Object>(); 
		R.put("contents", brandRepository.findByName(name));
		return R;
	}
 
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> brand_company_use_list(Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();  
		select_query.append("SELECT ");
		select_query.append("    b.name, ");
		select_query.append("    COUNT(i.member_id) AS company_use_count, ");
		select_query.append("    b.create_dt ");



		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    brand b ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		where_query.append(" GROUP BY b.id");
		
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY b.");
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
		BigInteger count_list = BigInteger.ZERO;
		
		List<BigInteger> count_result = count_q.getResultList();
		if (count_result.isEmpty()) {
		    
		} else {
			count_list = count_result.get(0);
		}
		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();
			search_R.put("brand_name", row[0]);
			search_R.put("company_use_count", row[1]); 
			search_R.put("brand_create_dt", row[2]); 

			return_list.add(search_R);
		}

		int totalPages = (count_list.intValue() / page.getPageSize());
		if (count_list.intValue() % page.getPageSize() > 0)
			totalPages = totalPages + 1;

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("content", return_list);
		R.put("totalPages", totalPages);
		R.put("totalElements", count_list.intValue());
		R.put("number", page.getPageNumber());
		R.put("size", return_list.size());
		return R;

		// Map<String, Object> R = new HashMap<String, Object>();
		//
		// QItemShopmallMap qism = QItemShopmallMap.itemShopmallMap;
		// QItem qi = QItem.item;
		// QBrand qb = QBrand.brand;
		// QShopmall qs = QShopmall.shopmall;
		//
		// JPAQuery<Item> query = new JPAQuery<Item>(entityManager);
		//
		// QueryResults<Tuple> db_result = query.select(qism.shopmall.name,
		// qism.item.countDistinct()).from(qism)
		// .innerJoin(qism.item).on(qism.item.useYn.eq("Y")).innerJoin(qism.item.brand).innerJoin(qism.shopmall)
		// .on(qism.shopmall.useYn.eq("Y"))
		// // .innerJoin(qism.item.brand).on(qism.item.brand.useYn.eq("Y"))
		// // .innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
		// .where(qism.item.brand.id.eq(brand_id).and(qism.useYn.eq("Y"))).groupBy(qism.shopmall)
		// .offset((page.getPageNumber() - 1) *
		// page.getPageSize()).limit(page.getPageSize()).fetchResults();
		//
		// List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//
		// for (Tuple row : db_result.getResults()) {
		// Map<String, Object> item = new HashMap<String, Object>();
		//
		// item.put("shopmall", row.get(qism.shopmall.name));
		// item.put("item_count", row.get(qism.item.countDistinct()));
		//
		// list.add(item);
		// }
		//
		// return new PageImpl<Map<String, Object>>(list, page, db_result.getTotal());
	}


	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> detail_info(Long id) {
 
		
		StringBuffer  shopmall_count_query = new StringBuffer();
		shopmall_count_query.append("SELECT ");
		shopmall_count_query.append("    COUNT(ct.shopmall) ");
		shopmall_count_query.append("FROM ");
		shopmall_count_query.append("    (SELECT ");
		shopmall_count_query.append("        ism.shopmall_id AS shopmall ");
		shopmall_count_query.append("    FROM ");
		shopmall_count_query.append("        item_shopmall_map ism ");
		shopmall_count_query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		shopmall_count_query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		shopmall_count_query.append("        AND i.use_yn = 'Y' ");
		shopmall_count_query.append("    LEFT JOIN brand b ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		shopmall_count_query.append("    WHERE ");
		shopmall_count_query.append("        ism.use_yn = 'Y' AND i.brand_id = " + id + "");
		shopmall_count_query.append("    GROUP BY i.brand_id , ism.shopmall_id) ct");

		StringBuffer item_count_query = new StringBuffer(); 
		item_count_query.append("SELECT ");
		item_count_query.append("    COUNT(ct.item) ");
		item_count_query.append("FROM ");
		item_count_query.append("    (SELECT ");
		item_count_query.append("        ism.item_id AS item ");
		item_count_query.append("    FROM ");
		item_count_query.append("        item_shopmall_map ism ");
		item_count_query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		item_count_query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		item_count_query.append("        AND i.use_yn = 'Y' ");
		item_count_query.append("    LEFT JOIN brand b ON i.brand_id = b.id AND b.use_yn = 'Y' ");
		item_count_query.append("    WHERE ");
		item_count_query.append("        ism.use_yn = 'Y' AND i.brand_id = 1 ");
		item_count_query.append("    GROUP BY i.brand_id , ism.item_id) ct");

		Query shopmall_count_q = entityManager.createNativeQuery((shopmall_count_query).toString());
		BigInteger shopmall_count = (BigInteger) shopmall_count_q.getSingleResult();

		Query item_count_q = entityManager.createNativeQuery((item_count_query).toString());
		BigInteger item_count = (BigInteger) item_count_q.getSingleResult();

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("shopmall_count", shopmall_count);
		R.put("item_count", item_count);

		return R;

	}

}