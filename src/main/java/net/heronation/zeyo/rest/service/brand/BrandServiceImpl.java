package net.heronation.zeyo.rest.service.brand;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.QBrand; 
import net.heronation.zeyo.rest.repository.brand_member_map.QBrandMemberMap;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;

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
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

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

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String, Object>> count_list = count_q.getResultList();

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
	public Page<Map<String, Object>> client_search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    s.name, ");
		select_query.append("    s.create_dt, ");
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

		StringBuffer where_query = new StringBuffer();

		where_query.append("FROM ");
		where_query.append("    item_shopmall_map ism ");
		where_query.append("        INNER JOIN ");
		where_query.append("    item i ON ism.item_id = i.id ");
		where_query.append("        INNER JOIN ");
		where_query.append("    shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("WHERE ");
		where_query.append("    ism.use_yn = 'Y' AND i.use_yn = 'Y' ");
		where_query.append("        AND s.use_yn = 'Y' ");
		where_query.append("        AND i.member_id = 1 ");
		where_query.append("GROUP BY ism.shopmall_id");

		String member_id = (String) param.get("member_id");

		where_query.append("        AND m.id = " + member_id + " ");

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

		return new PageImpl<Map<String, Object>>(return_list, page, count_list.size());

	}

	@Override
	@Transactional
	public Brand insert(String name, Long member_seq) {
		// TODO Auto-generated method stub

		Member m = memberRepository.getOne(member_seq);

		Brand o = new Brand();
		o.setMember(m);
		o.setName(name);
		o.setUseYn("Y");
		o.setCreateDt(new DateTime());
		return brandRepository.save(o);
	}

	@Override
	@Transactional
	public Map<String, Object> delete(Long brand_id, Long member_seq) {

		Map<String, Object> R = new HashMap<String, Object>();

		Brand target = brandRepository.findOne(brand_id);
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.

			QItem i = QItem.item;

			JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

			Long member_count = query.select(i.member.countDistinct()).from(i).innerJoin(i.brand)
					.on(i.brand.useYn.eq("Y")).where(i.brand.id.eq(brand_id).and(i.useYn.eq("Y"))).fetchCount();

			if (member_count == 1) {

				brandRepository.delete(target);
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
	public Map<String, Object> update(Long brand_id, Long member_seq, String name) {

		Map<String, Object> R = new HashMap<String, Object>();

		Brand target = brandRepository.findOne(brand_id);
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.

			QItem i = QItem.item;

			JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

			Long member_count = query.select(i.member.countDistinct()).from(i).innerJoin(i.brand)
					.on(i.brand.useYn.eq("Y")).where(i.brand.id.eq(brand_id).and(i.useYn.eq("Y"))).fetchCount();

			if (member_count == 1) {

				target.setName(name);
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
	@Transactional(readOnly = true)
	public Map<String, Object> toggle_link(Long brand_id, Long member_seq) {
		Map<String, Object> R = new HashMap<String, Object>();

		Brand target = brandRepository.findOne(brand_id);
		Member user = memberRepository.findOne(member_seq);

		QBrand qb = QBrand.brand;
		QMember qm = QMember.member;
		QBrandMemberMap qbmm = QBrandMemberMap.brandMemberMap;

//		BrandMemberMap db_qbmm = brandMemberMapRepository
//				.findOne(qbmm.brand.id.eq(brand_id).and(qbmm.member.id.eq(member_seq)));

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		}
		// else if (!target.getMember().equals(user)) {
		// // user is not owner
		// R.put("CODE", "B");
		// }
		else {

			// 토글 데이터가 없다면 새로생성하고 연동으로 만들어서 저장한다. 무조건 존재
			// if(db_qbmm == null) {
			// BrandMemberMap new_db_qbmm = new BrandMemberMap();
			// new_db_qbmm.setBrand(target);
			// new_db_qbmm.setMember(user);
			// new_db_qbmm.setLinkYn("Y");
			// new_db_qbmm.setUseYn("Y");
			//
			// brandMemberMapRepository.save(new_db_qbmm);
			//
			// R.put("CURRENT_LINK_YN", "Y");
			//
			// }else {

//			String current_link_yn = db_qbmm.getLinkYn();
//			if (current_link_yn.equals("Y")) {
//				db_qbmm.setLinkYn("N");
//				R.put("CURRENT_LINK_YN", "N");
//			} else {
//				db_qbmm.setLinkYn("Y");
//				R.put("CURRENT_LINK_YN", "Y");
//			}

			// }

			R.put("CODE", "OK");

		}

		return R;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> detail(Long brand_id, Long member_seq, Pageable page) {

		Map<String, Object> R = new HashMap<String, Object>();

		QItemShopmallMap qism = QItemShopmallMap.itemShopmallMap;
		QItem qi = QItem.item;
		QBrand qb = QBrand.brand;
		QShopmall qs = QShopmall.shopmall;

		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

		QueryResults<Tuple> db_result = query.select(qism.shopmall.name, qism.item.countDistinct()).from(qism)
				.innerJoin(qism.item).on(qism.item.useYn.eq("Y")).innerJoin(qism.item.brand).innerJoin(qism.shopmall)
				.on(qism.shopmall.useYn.eq("Y"))
				// .innerJoin(qism.item.brand).on(qism.item.brand.useYn.eq("Y"))
				// .innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
				.where(qism.item.brand.id.eq(brand_id).and(qism.useYn.eq("Y"))).groupBy(qism.shopmall)
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		for (Tuple row : db_result.getResults()) {
			Map<String, Object> item = new HashMap<String, Object>();

			item.put("shopmall", row.get(qism.shopmall.name));
			item.put("item_count", row.get(qism.item.countDistinct()));

			list.add(item);
		}

		return new PageImpl<Map<String, Object>>(list, page, db_result.getTotal());
	}

	@Override
	public Map<String, Object> distinct_with_member_id() {

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
		Map<String, Object> R = new HashMap<String, Object>();

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT ");
		select_query.append("    count(*)  ");
		select_query.append("FROM ");
		select_query.append("    brand b ");
		select_query.append("       WHERE b.use_yn = 'Y'");

		Query q = entityManager.createNativeQuery(select_query.toString());
		BigInteger use_count =  (BigInteger)q.getSingleResult();

		R.put("contents", use_count);

		return R;
	}

}