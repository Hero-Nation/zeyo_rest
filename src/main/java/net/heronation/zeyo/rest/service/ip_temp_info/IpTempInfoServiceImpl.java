package net.heronation.zeyo.rest.service.ip_temp_info;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.ip_temp_info.IpTempInfoRepository;

@Slf4j
@Service
@Transactional
public class IpTempInfoServiceImpl implements IpTempInfoService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private IpTempInfoRepository ip_temp_infoRepository;

	@Autowired
	EntityManager entityManager;

}