package net.heronation.zeyo.rest.service.brand_member_map;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.querydsl.jpa.impl.JPAQuery;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand_member_map.BrandMemberMapRepository;
import net.heronation.zeyo.rest.repository.brand_member_map.BrandMemberMap;
import net.heronation.zeyo.rest.repository.brand_member_map.QBrandMemberMap;

@Slf4j
@Service
@Transactional
public class BrandMemberMapServiceImpl implements BrandMemberMapService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BrandMemberMapRepository brand_member_mapRepository;

	@Autowired
	EntityManager entityManager;

}