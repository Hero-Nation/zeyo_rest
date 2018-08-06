package net.heronation.zeyo.rest.size_option.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;



@Slf4j
@Service
@Transactional
public class SizeOptionServiceImpl implements SizeOptionService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private SizeOptionRepository size_optionRepository;


}