package net.heronation.zeyo.rest.service.measure_item;
 
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItemRepository;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.warranty.QWarranty;



@Slf4j
@Service
@Transactional
public class MeasureItemServiceImpl implements MeasureItemService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private MeasureItemRepository measure_itemRepository;

	@Autowired
	EntityManager entityManager;
	
	@Override
	public Page<MeasureItem> search(Predicate where, Pageable page) {

		JPAQuery<MeasureItem> query = new JPAQuery<MeasureItem>(entityManager);
 
		QMeasureItem target = QMeasureItem.measureItem;

		QueryResults<MeasureItem> R = query.from(target).where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		return new PageImpl<MeasureItem>(R.getResults(), page, R.getTotal());

	}

}