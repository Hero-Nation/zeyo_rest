package net.heronation.zeyo.rest.madein.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;



@Slf4j
@Service
@Transactional
public class MadeinServiceImpl implements MadeinService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private MadeinRepository madeinRepository;


}