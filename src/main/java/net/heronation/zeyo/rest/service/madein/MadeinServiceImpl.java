package net.heronation.zeyo.rest.service.madein;

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

import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.IdNameVO;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.common.value.NameVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;

@Slf4j
@Service
public class MadeinServiceImpl implements MadeinService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MadeinRepository madeinRepository;

	
	@Autowired
	private KindofRepository kindofRepository;
	
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {
		
		 
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();  
		select_query.append("SELECT ");
		select_query.append("    m.id 					AS id, ");
		select_query.append("    m.name 					AS name, ");
		select_query.append("    k.kvalue 				AS kindof, ");
		select_query.append("    COUNT(DISTINCT i.id) 	AS itemCount, ");
		select_query.append("    m.create_dt 			AS createDt ");
//	      "name" : "direct_input",
//	      "kindof" : "직접입력",
//	      "id" : 58,
//	      "createDt" : "2018-05-22T10:24:49.000Z",
//	      "itemCount" : 1
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    madein m ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON m.kindof_id = k.id ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON m.id = i.madein_id ");
		where_query.append("WHERE ");
		where_query.append("    m.use_yn = 'Y' ");
 
		
		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   m.name like '%" + name + "%' "); 
		}
 
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND m.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND m.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

		where_query.append("GROUP BY m.id");

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


//			select_query.append("    m.id 					AS id, ");
//			select_query.append("    m.name 					AS name, ");
//			select_query.append("    k.kvalue 				AS kindof, ");
//			select_query.append("    COUNT(DISTINCT i.id) 	AS itemCount, ");
//			select_query.append("    m.create_dt 			AS createDt ");

			search_R.put("id", row[0]);
			search_R.put("name", row[1]);
			search_R.put("kindof", row[2]);
			search_R.put("itemCount", row[3]);
			search_R.put("createDt", row[4]);  

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
		
		
		
		

//		JPAQuery<Madein> query = new JPAQuery<Madein>(entityManager);
//
//		QMadein mi = QMadein.madein;
//
//		QueryResults<Madein> R = query.from(mi)
//				.leftJoin(mi.kindof)
//				.where(where)
//				//.orderBy(QMadein.madein.id.desc())
//				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
//				.limit(page.getPageSize())
//				.fetchResults();
// 
//		return new PageImpl<Madein>(R.getResults(), page, R.getTotal());

	}

	@Override
	@Transactional(readOnly=true)
	public Map<String, Object> use_list(Predicate where, Pageable page) {
		
		
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");

		StringBuffer select_query = new StringBuffer();  
		select_query.append("SELECT ");
		select_query.append("    m.id 					AS id, ");
		select_query.append("    m.name 					AS name, ");
		select_query.append("    k.kvalue 				AS kindof, ");
		select_query.append("    COUNT(DISTINCT i.id) 	AS itemCount, ");
		select_query.append("    m.create_dt 			AS createDt ");
//	      "name" : "direct_input",
//	      "kindof" : "직접입력",
//	      "id" : 58,
//	      "createDt" : "2018-05-22T10:24:49.000Z",
//	      "itemCount" : 1
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    madein m ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON m.kindof_id = k.id ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON m.id = i.madein_id ");
		where_query.append("WHERE ");
		where_query.append("    m.use_yn = 'Y' ");
 
		
//		String name = (String) param.get("name");
//		if (name != null) {
//			where_query.append("        AND   m.name like '%" + name + "%' "); 
//		}
// 
//		String start = (String)param.get("start");
//		if(start != null  ) {
//			where_query.append("        AND m.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
//		}
//		
//		
//		String end = (String)param.get("end");
//		if(end != null  ) {
//			where_query.append("        AND m.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
//		}

		where_query.append("GROUP BY m.id");

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


//			select_query.append("    m.id 					AS id, ");
//			select_query.append("    m.name 					AS name, ");
//			select_query.append("    k.kvalue 				AS kindof, ");
//			select_query.append("    COUNT(DISTINCT i.id) 	AS itemCount, ");
//			select_query.append("    m.create_dt 			AS createDt ");

			search_R.put("id", row[0]);
			search_R.put("name", row[1]);
			search_R.put("kindof", row[2]);
			search_R.put("itemCount", row[3]);
			search_R.put("createDt", row[4]);  

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
		
		
		
//		JPAQuery<Madein> query = new JPAQuery<Madein>(entityManager);
//		
//		
//		PathBuilder<Madein> madeinPath = new PathBuilder<Madein>(Madein.class, "madein");
//		
//		for (Order order : page.getSort()) {
//		    PathBuilder<Object> path = madeinPath.get(order.getProperty());
//		    query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//		}
//
//		QItem i = QItem.item;
//		QMadein mi = QMadein.madein;
//		QKindof ko = QKindof.kindof;
//		
//		QueryResults<Tuple> R = query.select(mi.id,mi.name,mi.kindof.kvalue,mi.createDt,i.id.countDistinct())
//				.from(mi) 
//				.where(where) 
//				.leftJoin(mi.items,i).on(i.useYn.eq("Y"))
//				.leftJoin(mi.kindof,ko)
//				.groupBy(mi.id) 
//				
//				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
//				.limit(page.getPageSize())
//				.fetchResults();
// 
//		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
//		List<Tuple> db_list = R.getResults();
//		
//		for(Tuple row : db_list) {
//			Map<String,Object>  r_row = new HashMap<String,Object>();
//			 
//			r_row.put("id", row.get(mi.id));
//			r_row.put("name", row.get(mi.name));
//			r_row.put("kindof", row.get(mi.kindof.kvalue));
//			r_row.put("createDt", row.get(mi.createDt));
//			r_row.put("itemCount", row.get(i.id.countDistinct()));
//			
//			
//			list.add(r_row);
//			
//		}
//		
//		return new PageImpl<Map<String,Object>>(list, page, R.getTotal());
	}

	// 어드민 
	@Override
	@Transactional
	public String insert(NameVO param) {
		// TODO Auto-generated method stub
		
		Kindof direct_input = kindofRepository.findOne(1L);
		
		Madein iv = new Madein();
		iv.setKindof(direct_input);
		iv.setName(param.getName());
		iv.setCreateDt(new DateTime());
		iv.setUseYn("Y");
		
		madeinRepository.save(iv);
		
		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String update(IdNameVO param) {
		// TODO Auto-generated method stub
		
		Madein a = madeinRepository.findOne(param.getId());
		a.setName(param.getName());
		
		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String delete(List<LIdVO> param) {
		for(LIdVO v :param) {
			Madein a = madeinRepository.findOne(v.getId());
			a.setUseYn("N");	
		}
		
		return CommonConstants.SUCCESS;
	}

}