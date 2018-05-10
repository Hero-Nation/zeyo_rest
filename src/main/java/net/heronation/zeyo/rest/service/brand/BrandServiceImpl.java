package net.heronation.zeyo.rest.service.brand;
 
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
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;



@Slf4j
@Service
@Transactional
public class BrandServiceImpl implements BrandService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private BrandRepository brandRepository;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<Brand> search(Predicate where, Pageable page) {

		JPAQuery<Brand> query = new JPAQuery<Brand>(entityManager);

		QBrand target = QBrand.brand;

		QueryResults<Brand> R = query.from(target)
				.leftJoin(target.items)
				.where(where)
				.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Brand>(R.getResults(), page, R.getTotal());

	}

}