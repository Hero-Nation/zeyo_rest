package net.heronation.zeyo.rest.service.item;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.QItem;

@Slf4j
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Item> search(Predicate where, Pageable page) {

		JPAQuery<Item> query = new JPAQuery<Item>(entityManager);

		QItem target = QItem.item;

		QueryResults<Item> R = query.from(target)

				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1) * page.getPageSize())
				.limit(page.getPageSize()).fetchResults();

		return new PageImpl<Item>(R.getResults(), page, R.getTotal());

	}


}