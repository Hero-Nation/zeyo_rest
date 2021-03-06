package net.heronation.zeyo.rest.service.cloth_color;

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
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColor;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.cloth_color.QClothColor;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap;
import net.heronation.zeyo.rest.repository.kindof.QKindof;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;

@Slf4j
@Service
@Transactional
public class ClothColorServiceImpl implements ClothColorService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ClothColorRepository cloth_colorRepository;
	@Autowired
	EntityManager entityManager;
	@Override
	public Map<String, Object> search(Map<String, Object> param, Pageable page) {
		StringBuffer count_query = new StringBuffer();
		count_query.append("SELECT ");
		count_query.append("    count(*) ");
 

		StringBuffer select_query = new StringBuffer();    
		select_query.append("SELECT ");
		select_query.append("    cc.id					as id, ");
		select_query.append("    cc.name				as name, ");
		select_query.append("    k.kvalue				as kindof, ");
		select_query.append("    COUNT(iccm.item_id)	as itemCount, ");
		select_query.append("    cc.create_dt 			as createDt  ");

//		r_row.put("id", row.get(iccm.clothColor.id));
//		r_row.put("name", row.get(iccm.clothColor.name));
//		r_row.put("kindof", row.get(iccm.clothColor.kindof.kvalue));
//		r_row.put("createDt", row.get(iccm.clothColor.createDt));
//		r_row.put("itemCount", row.get(i.id.countDistinct()));
 
		StringBuffer where_query = new StringBuffer();
		where_query.append("FROM ");
		where_query.append("    cloth_color cc ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    kindof k ON cc.kindof_id = k.id AND k.use_yn = 'Y' ");
		where_query.append("        LEFT JOIN ");
		where_query.append("    item_cloth_color_map iccm ON cc.id = iccm.cloth_color_id ");
		where_query.append("        AND iccm.use_yn = 'Y' ");
		where_query.append("        where cc.use_yn = 'Y' ");
		

 
		
		
		String name = (String) param.get("name");
		if (name != null) {
			where_query.append("        AND   cc.name like '%" + name + "%' "); 
		}

	 
		
		String start = (String)param.get("start");
		if(start != null  ) {
			where_query.append("        AND cc.create_dt >= STR_TO_DATE('"+start+"', '%Y-%m-%d %H:%i:%s')");	
		}
		
		
		String end = (String)param.get("end");
		if(end != null  ) {
			where_query.append("        AND cc.create_dt <= STR_TO_DATE('"+end+"', '%Y-%m-%d %H:%i:%s')");	
		}

 
		where_query.append("GROUP BY iccm.cloth_color_id");
		
		StringBuffer sort_query = new StringBuffer();
		sort_query.append("  ORDER BY cc.");
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

//			varname1.append("    cc.id					as id, ");
//			varname1.append("    cc.name				as name, ");
//			varname1.append("    k.kvalue				as kindof, ");
//			varname1.append("    COUNT(iccm.item_id)	as itemCount, ");
//			varname1.append("    cc.create_dt 			as createDt");

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
		
	}

//	@Override
//	public Map<String,Object> search(Map<String,Object> where, Pageable page) {
//
//		JPAQuery<Madein> query = new JPAQuery<Madein>(entityManager);
//		
//		
////		PathBuilder<ClothColor> madeinPath = new PathBuilder<ClothColor>(ClothColor.class, "clothcolor");
////		
////		for (Order order : page.getSort()) {
////		    PathBuilder<Object> path = madeinPath.get(order.getProperty());
////		    query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
////		}
//
//		QItem i = QItem.item;
//		QClothColor cc = QClothColor.clothColor;
//		QItemClothColorMap iccm = QItemClothColorMap.itemClothColorMap;
//		QKindof ko = QKindof.kindof;
//		
//		QueryResults<Tuple> R = query.select(iccm.clothColor.id,iccm.clothColor.name,iccm.clothColor.kindof.kvalue,iccm.clothColor.createDt,i.id.countDistinct())
//				.from(iccm) 
//				.where(where) 
//				.leftJoin(iccm.item,i).on(i.useYn.eq("Y"))
//				.leftJoin(iccm.clothColor,cc).on(cc.useYn.eq("Y"))
//				.leftJoin(cc.kindof,ko).on(ko.useYn.eq("Y"))
//				.groupBy(iccm.clothColor)
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
//			r_row.put("id", row.get(iccm.clothColor.id));
//			r_row.put("name", row.get(iccm.clothColor.name));
//			r_row.put("kindof", row.get(iccm.clothColor.kindof.kvalue));
//			r_row.put("createDt", row.get(iccm.clothColor.createDt));
//			r_row.put("itemCount", row.get(i.id.countDistinct()));
//			
//			
//			list.add(r_row);
//			
//		}
//		
//		return new PageImpl<Map<String,Object>>(list, page, R.getTotal());
//
//	}
}