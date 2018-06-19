package net.heronation.zeyo.rest.service.shopmall;

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

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.NameVO;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.QMember;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallDto;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;

@Slf4j
@Service
@Transactional
public class ShopmallServiceImpl implements ShopmallService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ShopmallRepository shopmallRepository;

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

		StringBuffer where_query = new StringBuffer();
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

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND i.name like '%" + name + "%' ");
		}

		// 않됨
		String company = (String) param.get("company");
		if (company != null) {
			where_query.append("        AND m.id  =" + company + " ");
		}

		String brand = (String) param.get("brand");
		if (brand != null) {
			where_query.append("        AND i.brand_id =" + brand + " ");
		}

		String shopmall = (String) param.get("shopmall");
		if (shopmall != null) {
			where_query.append("        AND ism.shopmall_id =" + shopmall + " ");
		}

		// 않됨
		// String link = (String)param.get("link");
		// if(link != null && link.equals("Y")) {
		// where_query.append(" AND link_count != 0 ");
		// }

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND s.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND s.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}

		where_query.append("GROUP BY m.id , s.id , b.id ");

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

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String, Object>> count_list = count_q.getResultList();

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			// [company_name] : [VARCHAR]) - [null]
			// [shopmall_name] : [VARCHAR]) - [shopmall5]
			// [brand_name] : [VARCHAR]) - [null]
			// [item_count] : [NUMERIC]) - [0]
			// [item_price] : [NUMERIC]) - [null]
			// [brand_create_dt] : [TIMESTAMP]) - [2018-05-24 14:12:31.0]

			// <td>{{pageData?.totalElements - ((pageData?.number-1) * listSize) - i}}</td>
			// <td>{{item.company_name}}</td>
			// <td>{{item.shopmall_name}}</td>
			// <td>{{item.brand_name}}</td>
			// <td>{{item.link_count}}</td>
			// <td>{{item.item_price}}</td>
			// <td>{{item.shopmall_create_dt | date: 'yyyy-MM-dd'}}</td>
			// <td>{{item.link_count > 0?'연동':'중지'}}</td>

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
	public Map<String, Object> client_search(Map<String, Object> param, Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();
		select_query.append("SELECT s.id             AS shopmall_id, ");
		select_query.append("       s.name           AS shopmall_name, ");
		select_query.append("       s.create_dt      AS shopmall_create_dt, ");
		select_query.append("       Count(i.link_yn) AS link_count ");
 
		
		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM   shopmall s ");
		where_query.append("       LEFT JOIN item_shopmall_map ism ");
		where_query.append("              ON ism.shopmall_id = s.id ");
		where_query.append("                 AND ism.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN item i ");
		where_query.append("              ON ism.item_id = i.id ");
		where_query.append("                 AND i.use_yn = 'Y' ");
		where_query.append(" WHERE  s.use_yn = 'Y' ");

		String member_id = (String) param.get("member_id");
		where_query.append("        AND s.member_id = " + member_id + " ");

		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND s.name like '%" + name + "%' ");
		}

		// 않됨
		// String link = (String)param.get("link");
		// if(link != null && link.equals("Y")) {
		// where_query.append(" AND link_count != 0 ");
		// }

		String start = (String) param.get("start");
		if (start != null) {
			where_query.append("        AND s.create_dt >= STR_TO_DATE('" + start + "', '%Y-%m-%d %H:%i:%s')");
		}

		String end = (String) param.get("end");
		if (end != null) {
			where_query.append("        AND s.create_dt <= STR_TO_DATE('" + end + "', '%Y-%m-%d %H:%i:%s')");
		}
		
		where_query.append(" GROUP  BY s.id ");  
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

		Query count_q = entityManager.createNativeQuery(count_query.append(where_query).toString());
		List<Map<String, Object>> count_list = count_q.getResultList();

		Query q = entityManager
				.createNativeQuery(select_query.append(where_query).append(sort_query).append(page_query).toString());
		List<Object[]> list = q.getResultList();

		List<Map<String, Object>> return_list = new ArrayList<Map<String, Object>>();

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

			search_R.put("shopmall_id", row[0]);
			search_R.put("shopmall_name", row[1]);
			search_R.put("shopmall_create_dt", row[2]);
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
	@Transactional
	public Shopmall insert(NameVO param, Long member_seq) {
		// TODO Auto-generated method stub

		Member m = memberRepository.getOne(member_seq);

		Shopmall o = new Shopmall();
		o.setMember(m);
		o.setName(param.getName());
		o.setUseYn("Y");
		o.setCreateDt(new DateTime());
		return shopmallRepository.save(o);
	}

	private BigInteger get_shopmall_use_count_of_member(Long shopmall_id) {
		StringBuffer varname1 = new StringBuffer();
		varname1.append("SELECT ");
		// varname1.append(" ism.shopmall_id, ");
		// varname1.append(" i.member_id, ");
		varname1.append("    COUNT(i.member_id) AS use_link_count ");
		varname1.append("FROM ");
		varname1.append("    item_shopmall_map ism ");
		varname1.append("        INNER JOIN ");
		varname1.append("    item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		varname1.append("        AND i.link_yn = 'Y' ");
		varname1.append("        LEFT JOIN ");
		varname1.append("    shopmall s ON ism.shopmall_id = s.id ");
		varname1.append("        AND s.use_yn = 'Y' ");
		varname1.append("        LEFT JOIN ");
		varname1.append("    member m ON i.member_id = m.id AND m.use_yn = 'Y' ");
		varname1.append("WHERE ");
		varname1.append("    ism.use_yn = 'Y' AND ism.shopmall_id = " + shopmall_id + " ");
		varname1.append("GROUP BY ism.shopmall_id , i.member_id");

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
	@Transactional
	public Map<String, Object> update_name(ShopmallDto param, Long member_seq) {

		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(param.getId());
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			BigInteger use_count = this.get_shopmall_use_count_of_member(param.getId());

			if (use_count.equals(BigInteger.ONE) || use_count.equals(BigInteger.ZERO)) {

				target.setName(param.getName());

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
	public Map<String, Object> delete(ShopmallDto param, Long member_seq) {

		Map<String, Object> R = new HashMap<String, Object>();

		Shopmall target = shopmallRepository.findOne(param.getId());
		Member user = memberRepository.findOne(member_seq);

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else if (!target.getMember().equals(user)) {
			// user is not owner
			R.put("CODE", "B");
		} else {

			// 현재 브랜드가 다른 사업자에게 사용중인지를 체크 한다.

			BigInteger use_count = this.get_shopmall_use_count_of_member(param.getId());

			if (use_count.equals(BigInteger.ONE) || use_count.equals(BigInteger.ZERO)) {

				target.setUseYn("N");
				target.setDeleteDt(new DateTime());
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
	public Map<String, Object> toggle_link(ShopmallDto param, Long member_seq) {
		log.debug("toggle_link ");
		log.debug("shopmall_id " + param.getId());
		log.debug("member_seq " + member_seq); 

		Map<String, Object> R = new HashMap<String, Object>();

		// 나의 브랜드의 상품의 연동정보를 일괄 변경시킨다.

		Shopmall target = shopmallRepository.findOne(param.getId());
		Member user = memberRepository.findOne(member_seq);

		QBrand qb = QBrand.brand;
		QMember qm = QMember.member;
		QItem qi = QItem.item;
		QItemShopmallMap ism = QItemShopmallMap.itemShopmallMap;

		if (target == null || target.getUseYn().equals("N")) {
			// brand is not exist
			R.put("CODE", "A");

		} else {
			JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);

			if (param.getLink().equals("N")) {

				queryFactory.update(qi)
						.where(qi.id
								.in(JPAExpressions.select(ism.item.id).from(ism)
										.where(ism.shopmall.id.eq(param.getId()).and(ism.useYn.eq("Y"))))
								.and(qi.member.id.eq(member_seq)).and(qi.useYn.eq("Y")))
						.set(qi.linkYn, "Y").execute();

			} else {
				queryFactory.update(qi)
						.where(qi.id
								.in(JPAExpressions.select(ism.item.id).from(ism)
										.where(ism.shopmall.id.eq(param.getId()).and(ism.useYn.eq("Y"))))
								.and(qi.member.id.eq(member_seq)).and(qi.useYn.eq("Y")))
						.set(qi.linkYn, "N").execute();
			}

			R.put("CODE", "OK");

		}

		return R;
	}

	@Override
	@Transactional(readOnly = true)
	public  Map<String, Object> detail(Long shopmall_id, Long member_seq, Pageable page) {
		
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from (");

		StringBuffer select_query = new StringBuffer();    
		select_query.append("SELECT b.name, ");
		select_query.append("       Count(i.id) ");
		
		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM   item i ");
		where_query.append("       LEFT JOIN item_shopmall_map ism ");
		where_query.append("              ON ism.item_id = i.id ");
		where_query.append("                 AND ism.use_yn = 'Y' ");
		where_query.append("       LEFT JOIN brand b ");
		where_query.append("              ON i.brand_id = b.id ");
		where_query.append("                 AND b.use_yn = 'Y' ");
		where_query.append(" WHERE  i.use_yn = 'Y' ");
		where_query.append("       AND ism.shopmall_id = "+shopmall_id);
		where_query.append("       AND i.member_id = "+member_seq);
		where_query.append(" GROUP  BY b.id");
		
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
			search_R.put("brand", row[0]);
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


//		Map<String, Object> R = new HashMap<String, Object>();
//
//		QItemShopmallMap qism = QItemShopmallMap.itemShopmallMap;
//		QItem qi = QItem.item;
//		QBrand qb = QBrand.brand;
//		QShopmall qs = QShopmall.shopmall;
//
//		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);
//
//		QueryResults<Tuple> db_result = query.select(qism.item.brand.name, qism.item.countDistinct()).from(qism)
//				.innerJoin(qism.item).on(qism.item.useYn.eq("Y")).innerJoin(qism.item.brand).innerJoin(qism.shopmall)
//				.on(qism.shopmall.useYn.eq("Y"))
//				// .innerJoin(qism.item.brand).on(qism.item.brand.useYn.eq("Y"))
//				// .innerJoin(qism.shopmall).on(qism.shopmall.useYn.eq("Y"))
//				.where(qism.shopmall.id.eq(shopmall_id).and(qism.useYn.eq("Y")).and(qism.item.brand.useYn.eq("Y")))
//				.groupBy(qism.item.brand).offset((page.getPageNumber() - 1) * page.getPageSize())
//				.limit(page.getPageSize()).fetchResults();
//
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//
//		for (Tuple row : db_result.getResults()) {
//			Map<String, Object> item = new HashMap<String, Object>();
//
//			item.put("brand", row.get(qism.item.brand.name));
//			item.put("item_count", row.get(qism.item.countDistinct()));
//
//			list.add(item);
//		}
//
//		return new PageImpl<Map<String, Object>>(list, page, db_result.getTotal());
	}

	@Override
	public Map<String, Object> check_unique_name(String name) {
		Map<String, Object> R = new HashMap<String, Object>();

		QShopmall s = QShopmall.shopmall;

		Shopmall target = shopmallRepository.findOne(s.name.eq(name).and(s.useYn.eq("Y")));

		if (target == null) {
			R.put("CODE", "A");
		} else {

			R.put("CODE", "OK");

		}

		return R;
	}

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> detail_info(Long id) {

		StringBuffer brand_count_query = new StringBuffer();
		brand_count_query.append("SELECT ");
		brand_count_query.append("    COUNT(*) AS brand_count ");
		brand_count_query.append("FROM ");
		brand_count_query.append("    (SELECT ");
		brand_count_query.append("        ism.shopmall_id AS shopmall, i.brand_id AS brand ");
		brand_count_query.append("    FROM ");
		brand_count_query.append("        item_shopmall_map ism ");
		brand_count_query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		brand_count_query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		brand_count_query.append("        AND s.use_yn = 'Y' ");
		brand_count_query.append("    WHERE ");
		brand_count_query.append("        ism.use_yn = 'Y' ");
		brand_count_query.append("    GROUP BY ism.shopmall_id , i.brand_id) ct ");
		brand_count_query.append("WHERE ");
		brand_count_query.append("    ct.shopmall = " + id + "");

		StringBuffer item_count_query = new StringBuffer();
		item_count_query.append("SELECT ");
		item_count_query.append("   count(item) AS item_count  ");
		item_count_query.append("FROM ");
		item_count_query.append("    (SELECT ");
		item_count_query.append("        ism.shopmall_id AS shopmall, i.id AS item ");
		item_count_query.append("    FROM ");
		item_count_query.append("        item_shopmall_map ism ");
		item_count_query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		item_count_query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id  AND s.use_yn = 'Y' ");
		item_count_query.append("    WHERE   ism.use_yn = 'Y' ");
		item_count_query.append("    GROUP BY ism.shopmall_id , i.id) ct ");
		item_count_query.append("WHERE ");
		item_count_query.append("   ct.shopmall = " + id + "");

		Query brand_count_q = entityManager.createNativeQuery((brand_count_query).toString());
		BigInteger brand_count = (BigInteger) brand_count_q.getSingleResult();

		Query item_count_q = entityManager.createNativeQuery((item_count_query).toString());
		BigInteger item_count = (BigInteger) item_count_q.getSingleResult();

		Map<String, Object> R = new HashMap<String, Object>();
		R.put("brand_count", brand_count);
		R.put("item_count", item_count);

		return R;

	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> shopmall_company_use_list(Pageable page) {

		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();   
		select_query.append("SELECT ");
		select_query.append("    ct.shopmall, ");
		select_query.append("    ct.create_dt, ");
		select_query.append("    COUNT(ct.member) as company_count ");


		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    (SELECT ");
		where_query.append("        s.id,s.name AS shopmall, s.create_dt, i.member_id AS member ");
		where_query.append("    FROM ");
		where_query.append("        item_shopmall_map ism ");
		where_query.append("    LEFT JOIN shopmall s ON ism.shopmall_id = s.id ");
		where_query.append("        AND s.use_yn = 'Y' ");
		where_query.append("    LEFT JOIN item i ON ism.item_id = i.id AND i.use_yn = 'Y' ");
		where_query.append("    WHERE ");
		where_query.append("        ism.use_yn = 'Y' ");
		where_query.append("    GROUP BY s.id , i.member_id) ct ");
		where_query.append("    group by ct.shopmall");

		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY ct.");
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
			//search_R.put("shopmall_name", row[0]);
			search_R.put("shopmall_name", row[0]);
			search_R.put("shopmall_create_dt", row[1]); 
			search_R.put("company_use_count", row[2]);
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
}