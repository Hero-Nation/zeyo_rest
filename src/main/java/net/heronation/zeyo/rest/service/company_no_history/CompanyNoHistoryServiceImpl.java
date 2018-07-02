package net.heronation.zeyo.rest.service.company_no_history;
 

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;

@Slf4j
@Service
@Transactional
public class CompanyNoHistoryServiceImpl implements CompanyNoHistoryService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CompanyNoHistoryRepository company_no_historyRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {

		
//		param.put("name", name);
//		param.put("cn1", cn1);
//		param.put("cn2", cn2);
//		param.put("cn3", cn3);  
//		param.put("start", start);	
//		param.put("end", end);	
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from (");

		StringBuffer select_query = new StringBuffer();  
		select_query.append("SELECT ");
		select_query.append("    bt.name, ");
		select_query.append("    bt.before_no, ");
		select_query.append("    bt.company_no, ");
		select_query.append("    bt.change_dt, ");
		select_query.append("    bt.member_id ");
 
		StringBuffer where_query = new StringBuffer();
		where_query.append("  FROM ");
		where_query.append("    (SELECT ");
		where_query.append("        cnh.* ");
		where_query.append("    FROM ");
		where_query.append("        company_no_history cnh ");
		where_query.append("    INNER JOIN (SELECT ");
		where_query.append("        MAX(id) AS id ");
		where_query.append("    FROM ");
		where_query.append("        company_no_history ");
		where_query.append("    GROUP BY member_id) pcnh ON cnh.id = pcnh.id) bt ");
		where_query.append("        LEFT OUTER JOIN ");
		where_query.append("    member m ON bt.member_id = m.id");
		where_query.append("   WHERE ");
		where_query.append("    m.use_yn = 'Y'");
		
		
		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   bt.name like '%" + name + "%' "); 
		}

		String cn1 = (String) param.get("cn1");
		if (cn1 != null) {
			where_query.append("        AND   bt.company_no like '" + cn1 + "%' ");
		}

		String cn2 = (String) param.get("cn2");
		if (cn2 != null) {
			where_query.append("        AND   bt.company_no like '%" + cn2 + "%' ");
		}

		String cn3 = (String) param.get("cn3");
		if (cn3 != null) {
			where_query.append("        AND   bt.company_no like '%" + cn3 + "' ");
		}

		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND bt.change_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND bt.change_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		 

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

//			select_query.append("    bt.name, ");
//			select_query.append("    bt.before_no, ");
//			select_query.append("    bt.company_no, ");
//			select_query.append("    bt.change_dt, ");
//			select_query.append("    bt.member_id ");
	 

			search_R.put("name", row[0]);
			search_R.put("before_no", row[1]);
			search_R.put("company_no", row[2]);
			search_R.put("change_dt", row[3]);
			search_R.put("member_id", row[4]); 

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

	}

	@Override
	public Map<String, Object> mylist(Map<String, Object> param, Pageable page) {
 
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) from ( ");

		StringBuffer select_query = new StringBuffer();  
		select_query.append("SELECT ");
		select_query.append("    ch.id , ");
		select_query.append("    ch.before_no , ");
		select_query.append("    ch.company_no , ");
		select_query.append("    ch.name , ");
		select_query.append("    ch.change_dt , ");
		select_query.append("    ch.member_id ");
 
		StringBuffer where_query = new StringBuffer();
		where_query.append("  FROM ");
		where_query.append("    company_no_history ch ");
 
		where_query.append("   WHERE ");
		where_query.append("   1 = 1");
		
		
		String member_id = (String) param.get("member_id");
		if (member_id != null) {
			where_query.append("        AND   ch.member_id = " + member_id + " "); 
		}
 
		
		 

		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY ch.");
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

//			select_query.append("    ch.id , ");
//			select_query.append("    ch.before_no , ");
//			select_query.append("    ch.company_no , ");
//			select_query.append("    ch.name , ");
//			select_query.append("    ch.change_dt , ");
//			select_query.append("    ch.member_id ");
	 

			
			search_R.put("beforeNo", row[1]);
			search_R.put("companyNo", row[2]);
			search_R.put("name", row[3]);
			search_R.put("changeDt", row[4]); 
			search_R.put("memberId", row[5]); 

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

		
		
//		JPAQuery<CompanyNoHistory> query = new JPAQuery<CompanyNoHistory>(entityManager);
//
//		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;
//
//		QueryResults<CompanyNoHistory> R = query.from(target)
//
//				.where(where)
//				// .orderBy(target.id.desc())
//				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize()).fetchResults();
//
//		return new PageImpl<CompanyNoHistory>(R.getResults(), page, R.getTotal());
	}

}