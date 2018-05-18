package net.heronation.zeyo.rest.service.madein;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.kindof.QKindof;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;
import net.heronation.zeyo.rest.repository.madein.QMadein;

@Slf4j
@Service
public class MadeinServiceImpl implements MadeinService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MadeinRepository madeinRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Madein> search(Predicate where, Pageable page) {

		JPAQuery<Madein> query = new JPAQuery<Madein>(entityManager);

		QMadein mi = QMadein.madein;

		QueryResults<Madein> R = query.from(mi)
				.leftJoin(mi.kindof)
				.where(where)
				//.orderBy(QMadein.madein.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Madein>(R.getResults(), page, R.getTotal());

	}

	@Override
	public Page<Map<String,Object>> use_list(Predicate where, Pageable page) {
		JPAQuery<Madein> query = new JPAQuery<Madein>(entityManager);
		
		
		PathBuilder<Madein> madeinPath = new PathBuilder<Madein>(Madein.class, "madein");
		
		for (Order order : page.getSort()) {
		    PathBuilder<Object> path = madeinPath.get(order.getProperty());
		    query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
		}

		QItem i = QItem.item;
		QMadein mi = QMadein.madein;
		QKindof ko = QKindof.kindof;
		
		QueryResults<Tuple> R = query.select(mi.id,mi.name,mi.kindof.kvalue,mi.createDt,i.id.countDistinct())
				.from(mi) 
				.where(where) 
				.leftJoin(mi.items,i).on(i.useYn.eq("Y"))
				.leftJoin(mi.kindof,ko)
				.groupBy(mi.id) 
				
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Tuple> db_list = R.getResults();
		
		for(Tuple row : db_list) {
			Map<String,Object>  r_row = new HashMap<String,Object>();
			 
			r_row.put("id", row.get(mi.id));
			r_row.put("name", row.get(mi.name));
			r_row.put("kindof", row.get(mi.kindof.kvalue));
			r_row.put("createDt", row.get(mi.createDt));
			r_row.put("itemCount", row.get(i.id.countDistinct()));
			
			
			list.add(r_row);
			
		}
		
		return new PageImpl<Map<String,Object>>(list, page, R.getTotal());
	}

}