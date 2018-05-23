package net.heronation.zeyo.rest.service.warranty;

import java.util.ArrayList;
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
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.warranty.QWarranty;
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
	EntityManager entityManager;

	@Override
	public Page<Map<String, Object>> search(Predicate where, Pageable page) {

		JPAQuery<Madein> query = new JPAQuery<Madein>(entityManager);

		PathBuilder<Warranty> queryPath = new PathBuilder<Warranty>(Warranty.class, "warranty");

		for (Order order : page.getSort()) {
			PathBuilder<Object> path = queryPath.get(order.getProperty());
			query.orderBy(new OrderSpecifier(com.querydsl.core.types.Order.valueOf(order.getDirection().name()), path));
		}

		QItem i = QItem.item;
		QWarranty w = QWarranty.warranty;
		QKindof ko = QKindof.kindof;

		QueryResults<Tuple> R = query.select(w.id, w.scope, w.kindof.kvalue, w.createDt, i.id.countDistinct())
				.from(w)
				.where(where)
				.leftJoin(w.items, i).on(i.useYn.eq("Y"))
				.leftJoin(w.kindof, ko)
				.groupBy(w.id)
				.offset((page.getPageNumber() - 1) * page.getPageSize()).limit(page.getPageSize())
				.fetchResults();

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Tuple> db_list = R.getResults();

		for (Tuple row : db_list) {
			Map<String, Object> r_row = new HashMap<String, Object>();

			r_row.put("id", row.get(w.id));
			r_row.put("scope", row.get(w.scope));
			r_row.put("kindof", row.get(w.kindof.kvalue));
			r_row.put("createDt", row.get(w.createDt));
			r_row.put("itemCount", row.get(i.id.countDistinct()));

			list.add(r_row);

		}

		return new PageImpl<Map<String, Object>>(list, page, R.getTotal());

	}
}