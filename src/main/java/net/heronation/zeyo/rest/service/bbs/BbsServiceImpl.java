package net.heronation.zeyo.rest.service.bbs;
 
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
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.bbs.BbsRepository;
import net.heronation.zeyo.rest.repository.bbs.QBbs; 



@Slf4j
@Service
@Transactional
public class BbsServiceImpl implements BbsService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private BbsRepository bbsRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Bbs> search(Predicate where, Pageable page) {

		JPAQuery<Bbs> query = new JPAQuery<Bbs>(entityManager);

		QBbs target = QBbs.bbs;

		QueryResults<Bbs> R = query.from(target)
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Bbs>(R.getResults(), page, R.getTotal());

	}
}