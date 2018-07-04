package net.heronation.zeyo.rest.service.warranty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.value.IdNameDto;
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.controller.warranty.ScopeVO;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.kindof.KindofRepository;
import net.heronation.zeyo.rest.repository.warranty.Warranty;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;

@Slf4j
@Service
@Transactional
public class WarrantyServiceImpl implements WarrantyService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WarrantyRepository warrantyRepository;
	
	@Autowired
	private KindofRepository kindofRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Map<String,Object> search(Map<String,Object> param, Pageable page) {
		 
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");
		
		
//		r_row.put("id", row.get(w.id));
//		r_row.put("scope", row.get(w.scope));
//		r_row.put("kindof", row.get(w.kindof.kvalue));
//		r_row.put("createDt", row.get(w.createDt));
//		r_row.put("itemCount", row.get(i.id.countDistinct()));

		StringBuffer select_query = new StringBuffer();   
		select_query.append("SELECT ");
		select_query.append("    w.id			AS id, ");
		select_query.append("    w.scope		AS scope, ");
		select_query.append("    k.kvalue		AS kindof, ");
		select_query.append("    COUNT(i.id) 	AS itemCount, ");
		select_query.append("    w.create_dt	AS createDt  ");

 
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    warranty w ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON w.kindof_id = k.id ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item i ON i.warranty_id = w.id AND i.use_yn = 'Y' ");
		where_query.append("WHERE ");
		where_query.append("    w.use_yn = 'Y' ");

 
		
		
		String scope = (String) param.get("scope");
		if (scope != null) {
			where_query.append("        AND   w.scope like '%" + scope + "%' "); 
		}

	 
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND w.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND w.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

 
		where_query.append("GROUP BY w.id");
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY w.");
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

//			r_row.put("id", row.get(w.id));
//			r_row.put("scope", row.get(w.scope));
//			r_row.put("kindof", row.get(w.kindof.kvalue));
//			r_row.put("createDt", row.get(w.createDt));
//			r_row.put("itemCount", row.get(i.id.countDistinct()));

			search_R.put("id", row[0]);
			search_R.put("scope", row[1]);
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
//		PathBuilder<Warranty> queryPath = new PathBuilder<Warranty>(Warranty.class, "warranty");
//
//		for (Order order : page.getSort()) {
//			PathBuilder<Object> path = queryPath.get(order.getProperty());
//			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
//		}
//
//		QItem i = QItem.item;
//		QWarranty w = QWarranty.warranty;
//		QKindof ko = QKindof.kindof;
//
//		QueryResults<Tuple> R = query.select(w.id, w.scope, w.kindof.kvalue, w.createDt, i.id.countDistinct())
//				.from(w)
//				.where(where)
//				.leftJoin(w.items, i).on(i.useYn.eq("Y"))
//				.leftJoin(w.kindof, ko)
//				.groupBy(w.id)
//				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize())
//				.fetchResults();
//
//		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//		List<Tuple> db_list = R.getResults();
//
//		for (Tuple row : db_list) {
//			Map<String, Object> r_row = new HashMap<String, Object>();
//
//			r_row.put("id", row.get(w.id));
//			r_row.put("scope", row.get(w.scope));
//			r_row.put("kindof", row.get(w.kindof.kvalue));
//			r_row.put("createDt", row.get(w.createDt));
//			r_row.put("itemCount", row.get(i.id.countDistinct()));
//
//			list.add(r_row);
//
//		}
//
//		return new PageImpl<Map<String, Object>>(list, page, R.getTotal());

	}
	
	
	@Override
	@Transactional
	public String insert(ScopeVO param) {
		// TODO Auto-generated method stub
		
		Kindof direct_input = kindofRepository.findOne(1L);
		
		Warranty iv = new Warranty();
		iv.setKindof(direct_input);
		iv.setScope(param.getScope());
		iv.setCreateDt(new DateTime());
		iv.setUseYn("Y");
		
		warrantyRepository.save(iv);
		
		return CommonConstants.SUCCESS;
	}
	
	@Override
	@Transactional
	public String update(IdNameDto param) {
		// TODO Auto-generated method stub
		
		Warranty a = warrantyRepository.findOne(param.getId());
		a.setScope(param.getName());
		
		return CommonConstants.SUCCESS;
	}

	@Override
	@Transactional
	public String delete(List<LIdDto> param) {
		
		for(LIdDto n : param) {
			Warranty a = warrantyRepository.findOne(n.getId());
			a.setUseYn("N");
		}
		

		return CommonConstants.SUCCESS;
	}

}