package net.heronation.zeyo.rest.service.fit_info;
 
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
import net.heronation.zeyo.rest.repository.fit_info.FitInfo;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoRepository;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;



@Slf4j
@Service
@Transactional
public class FitInfoServiceImpl implements FitInfoService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private FitInfoRepository fit_infoRepository;
	@Autowired
	EntityManager entityManager;
	
	@Override
	public Page<FitInfo> search(Predicate where, Pageable page) {

		JPAQuery<FitInfo> query = new JPAQuery<FitInfo>(entityManager);
 
		QMeasureItem target = QMeasureItem.measureItem;

		QueryResults<FitInfo> R = query.from(target).where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		return new PageImpl<FitInfo>(R.getResults(), page, R.getTotal());

	}

	@Override
	public Page<FitInfoOption> fitInfoOptions_search(Predicate where, Pageable page) {
		
		JPAQuery<FitInfoOption> query = new JPAQuery<FitInfoOption>(entityManager);
		 
		QFitInfoOption target = QFitInfoOption.fitInfoOption;

		QueryResults<FitInfoOption> R = query.from(target).where(where)
				//.orderBy(target.id.desc())
				.offset((page.getPageNumber() - 1)* page.getPageSize()) 
				.limit(page.getPageSize()).fetchResults();
				
 
		return new PageImpl<FitInfoOption>(R.getResults(), page, R.getTotal());
	}

}