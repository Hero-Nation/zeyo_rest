package net.heronation.zeyo.rest.service.shopmall;
 
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
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;



@Slf4j
@Service
@Transactional
public class ShopmallServiceImpl implements ShopmallService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ShopmallRepository shopmallRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Shopmall> search(Predicate where, Pageable page) {

		JPAQuery<Shopmall> query = new JPAQuery<Shopmall>(entityManager);

		QShopmall target = QShopmall.shopmall;

		QueryResults<Shopmall> R = query.from(target)
				.leftJoin(target.itemShopmallMaps)
				.where(where)
				.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Shopmall>(R.getResults(), page, R.getTotal());

	}

	@Override
	public Page<Shopmall> client_search(Predicate where, Pageable page) {
		JPAQuery<Shopmall> query = new JPAQuery<Shopmall>(entityManager);

		QShopmall target = QShopmall.shopmall;

		QueryResults<Shopmall> R = query.from(target)
				 
				.where(where)
				.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Shopmall>(R.getResults(), page, R.getTotal());
	}
}