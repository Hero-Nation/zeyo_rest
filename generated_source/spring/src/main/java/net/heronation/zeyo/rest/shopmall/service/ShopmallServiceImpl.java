package net.heronation.zeyo.rest.shopmall.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;



@Slf4j
@Service
@Transactional
public class ShopmallServiceImpl implements ShopmallService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ShopmallRepository shopmallRepository;


}