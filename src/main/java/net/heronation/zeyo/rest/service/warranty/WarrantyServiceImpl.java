package net.heronation.zeyo.rest.service.warranty;
 
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
import net.heronation.zeyo.rest.repository.kindof.QKindof;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.warranty.QWarranty;
import net.heronation.zeyo.rest.repository.warranty.Warranty;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;



@Slf4j
@Service
@Transactional
public class WarrantyServiceImpl implements WarrantyService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private WarrantyRepository warrantyRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Warranty> search(Predicate where, Pageable page) {

		JPAQuery<Warranty> query = new JPAQuery<Warranty>(entityManager);

		QWarranty target = QWarranty.warranty;

		QueryResults<Warranty> R = query.from(target)
				.leftJoin(target.kindof)
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Warranty>(R.getResults(), page, R.getTotal());

	}
}