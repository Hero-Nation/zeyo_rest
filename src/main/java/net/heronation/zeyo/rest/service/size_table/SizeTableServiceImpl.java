package net.heronation.zeyo.rest.service.size_table;

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
import net.heronation.zeyo.rest.repository.size_table.QSizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTable;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;

@Slf4j
@Service
@Transactional
public class SizeTableServiceImpl implements SizeTableService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SizeTableRepository size_tableRepository;

	@Autowired
	EntityManager entityManager;

	@Override
	public Page<SizeTable> search(Predicate where, Pageable page) {

		JPAQuery<SizeTable> query = new JPAQuery<SizeTable>(entityManager);

		QSizeTable target = QSizeTable.sizeTable;

		QueryResults<SizeTable> R = query.from(target)

				.where(where).orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1) * page.getPageSize())
				.limit(page.getPageSize())
				.fetchResults();

		return new PageImpl<SizeTable>(R.getResults(), page, R.getTotal());

	}
}