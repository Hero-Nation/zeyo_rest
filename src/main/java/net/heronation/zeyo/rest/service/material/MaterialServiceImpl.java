package net.heronation.zeyo.rest.service.material;
 
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
import net.heronation.zeyo.rest.repository.material.Material;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.material.QMaterial;
import net.heronation.zeyo.rest.repository.warranty.QWarranty;



@Slf4j
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private MaterialRepository materialRepository;

	@Autowired
	EntityManager entityManager;

	
	@Override
	public Page<Material> search(Predicate where, Pageable page) {

		JPAQuery<Material> query = new JPAQuery<Material>(entityManager);

		QMaterial target = QMaterial.material;

		QueryResults<Material> R = query.from(target)
				//.leftJoin(wt.kindof)
				.where(where)
				.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<Material>(R.getResults(), page, R.getTotal());

	}
}