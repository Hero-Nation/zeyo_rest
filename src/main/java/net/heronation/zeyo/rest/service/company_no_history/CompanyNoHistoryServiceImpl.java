package net.heronation.zeyo.rest.service.company_no_history;
 
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
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.QMadein;



@Slf4j
@Service
@Transactional
public class CompanyNoHistoryServiceImpl implements CompanyNoHistoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private CompanyNoHistoryRepository company_no_historyRepository;
	@Autowired
	EntityManager entityManager;

	@Override
	public Page<CompanyNoHistory> search(Predicate where, Pageable page) {

		JPAQuery<CompanyNoHistory> query = new JPAQuery<CompanyNoHistory>(entityManager);

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		QueryResults<CompanyNoHistory> R = query.from(target)
			 
				.where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize())
				.fetchResults();
 
		return new PageImpl<CompanyNoHistory>(R.getResults(), page, R.getTotal());

	}


}