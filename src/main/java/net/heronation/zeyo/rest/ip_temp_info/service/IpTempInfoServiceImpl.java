package net.heronation.zeyo.rest.ip_temp_info.service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.ip_temp_info.repository.IpTempInfoRepository;

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