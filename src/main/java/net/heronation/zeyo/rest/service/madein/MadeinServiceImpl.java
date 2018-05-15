package net.heronation.zeyo.rest.service.madein;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
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
	public Page<Madein> use_list(Predicate where, Pageable page) {
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

}