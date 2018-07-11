package net.heronation.zeyo.rest.kindof.service;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.kindof.repository.KindofRepository;



@Slf4j
@Service
@Transactional
public class KindofServiceImpl implements KindofService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private KindofRepository kindofRepository;


}