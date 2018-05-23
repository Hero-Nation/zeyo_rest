package net.heronation.zeyo.rest.fit_info;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.fit_info.*;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOption;
import net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class FitInfoRepositoryTest {

	@Autowired
	FitInfoRepository repository;
	
	
	@Autowired
	EntityManager entityManager;
	

	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		for (int a = 0; a < 100; a++) {
			FitInfo item = new FitInfo();
			item.setName("name_" + a);

			item.setMetaDesc("metaDesc_" + a);

			//item.setCreateDt(new Date());

			item.setUseYn("useYn_" + a);
			repository.save(item);
		}

	}
	
	@Test
	public void fitInfoOptions_search() {
		JPAQuery<FitInfoOption> query = new JPAQuery<FitInfoOption>(entityManager);

		
		
		
		QFitInfo fi = QFitInfo.fitInfo;
		QFitInfoOption fio = QFitInfoOption.fitInfoOption;

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(fio.fitInfo.id.eq(1L));
		builder.and(fio.useYn.eq("Y"));

		QueryResults<Tuple> R = query.select(fi.name,fio.name).from(fio).where(builder.getValue()).fetchResults();
		
		for(Tuple row : R.getResults()) {
			log.debug(row.get(fi.name));
			log.debug(row.get(fio.name));
			
		}
		
	}

}