package net.heronation.zeyo.rest.service.shopmall_member_map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j; 

@Slf4j
@Service
@Transactional
public class ShopmallMemberMapServiceImpl implements ShopmallMemberMapService {

	@Autowired
	private RestTemplate restTemplate;
 
	@Autowired
	EntityManager entityManager;

}