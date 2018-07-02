package net.heronation.zeyo.rest.service.consumer;
 
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.consumer.ConsumerRepository;
import net.heronation.zeyo.rest.repository.consumer.Consumer;
import net.heronation.zeyo.rest.repository.consumer.QConsumer;


@Slf4j
@Service
@Transactional
public class ConsumerServiceImpl implements ConsumerService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ConsumerRepository consumerRepository;

	@Autowired
	EntityManager entityManager;

}