package net.heronation.zeyo.rest.service.bbs;
 
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
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.bbs.BbsClientInsertDto;
import net.heronation.zeyo.rest.repository.bbs.BbsRepository;
import net.heronation.zeyo.rest.repository.bbs.QBbs;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRepository; 



@Slf4j
@Service
@Transactional
public class BbsServiceImpl implements BbsService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private BbsRepository bbsRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private KindofRepository kindofRepository;
	
	
	
	

	@Autowired
	EntityManager entityManager;

	@Override
	@Transactional(readOnly=true)
	public Map<String,Object> search(Map<String,Object> param, Pageable page) {
		
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");
		
		 

		StringBuffer select_query = new StringBuffer();    
		select_query.append("SELECT ");
		select_query.append("    b.id			as id, ");
		select_query.append("    k.kvalue		as kindof, ");
		select_query.append("    b.title		as title, ");
		select_query.append("    m.name			as member_name, ");
		select_query.append("    b.create_dt	as createDt, ");
		select_query.append("    b.status 		as status");

 
        // item.kindof
  	  // item.id
        // item.title
        // item.member.name
        // item.createDt
        // item.status
		
		StringBuffer where_query = new StringBuffer();
		where_query.append(" FROM ");
		where_query.append("    bbs b ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON b.kindof_id = k.id AND k.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    member m ON b.member_id = m.id AND m.use_yn = 'Y' ");
		where_query.append(" WHERE ");
		where_query.append("    b.use_yn = 'Y'");

 
		
		
		String title = (String) param.get("title");
		if (title != null) {
			where_query.append("        AND   b.scope like '%" + title + "%' "); 
		}

	 
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND b.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND b.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

 
		where_query.append("GROUP BY b.id");
		
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
		
          // item.kindof
    	  // item.id
          // item.title
          // item.member.name
          // item.createDt
          // item.status
          

		for (Object[] row : list) {
			Map<String, Object> search_R = new HashMap<String, Object>();

//			select_query.append("    b.id			as id, ");
//			select_query.append("    k.kvalue		as kindof, ");
//			select_query.append("    b.title		as title, ");
//			select_query.append("    m.name			as member_name, ");
//			select_query.append("    b.create_dt	as createDt, ");
//			select_query.append("    b.status 		as status");

			search_R.put("id", row[0]);
			search_R.put("kindof", row[1]);
			search_R.put("title", row[2]);
			search_R.put("member_name", row[3]);
			search_R.put("createDt", row[4]);   
			search_R.put("status", row[5]);   

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
		
		

//		JPAQuery<Bbs> query = new JPAQuery<Bbs>(entityManager);
//
//		QBbs target = QBbs.bbs;
//
//		QueryResults<Bbs> R = query.from(target)
//				.where(where)
//				//.orderBy(target.id.desc())
//				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
//				.limit(page.getPageSize())
//				.fetchResults();
// 
//		return new PageImpl<Bbs>(R.getResults(), page, R.getTotal());

	}

	@Override
	@Transactional
	public Bbs client_insert(BbsClientInsertDto new_bbs,Long member_id) {
		
		
		Member client = memberRepository.getOne(member_id); 
		Kindof kind = kindofRepository.getOne((long) 4);
		
		Bbs new_post = new_bbs.getNewBbs();
		new_post.setMember(client);
		new_post.setKindof(kind);
		bbsRepository.save(new_post);
		
		return new_post;
	}
}