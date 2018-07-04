package net.heronation.zeyo.rest.category;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.consumer.Consumer;
import net.heronation.zeyo.rest.repository.consumer.ConsumerRepository;
import net.heronation.zeyo.rest.repository.consumer.QConsumer;
import net.heronation.zeyo.rest.service.integrate.common.IntegrateCommonService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryRepositoryTest {

	@Autowired
	EntityManager entityManager;

	@Autowired
	CategoryRepository repository;

	@Autowired
	ConsumerRepository consumerRepository;
	@Autowired
	private MappingJackson2HttpMessageConverter jacksonConverter;
	
	@Autowired
	private IntegrateCommonService integrateCommonService;
	
	@Test
	@Ignore
	public void initializesRepositoryWithSampleData() {

		QConsumer qc = QConsumer.consumer;

		Consumer c = consumerRepository.findOne(qc.now_ip.eq("1gobvtb33s9gs8g7snaog9i13p")
				.and(qc.lastAccessDt.after(DateTime.now().minusMinutes(30))).and(qc.useYn.eq("Y")));

		if (c == null) {
			log.debug("c is null");
		} else {
			log.debug(c.getLastAccessDt().toString());
			log.debug(c.getLastAccessDt().toString());
		}
	}
	
	
 
}