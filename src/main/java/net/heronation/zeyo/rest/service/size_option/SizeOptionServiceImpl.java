package net.heronation.zeyo.rest.service.size_option;

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
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;

@Slf4j
@Service
@Transactional
public class SizeOptionServiceImpl implements SizeOptionService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SizeOptionRepository size_optionRepository;
	@Autowired
	EntityManager entityManager;
	@Override
	public Page<SizeOption> search(Predicate where, Pageable page) {
	 
		JPAQuery<SizeOption> query = new JPAQuery<SizeOption>(entityManager);

		QSizeOption target = QSizeOption.sizeOption;

		QueryResults<SizeOption> R = query.from(target)
				.leftJoin(target.kindof)
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<SizeOption>(R.getResults(), page, R.getTotal());
		
	}

}