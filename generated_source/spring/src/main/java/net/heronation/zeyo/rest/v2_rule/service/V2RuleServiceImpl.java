package net.heronation.zeyo.rest.v2_rule.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.v2_rule.V2RuleRepository;



@Slf4j
@Service
@Transactional
public class V2RuleServiceImpl implements V2RuleService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private V2RuleRepository v2_ruleRepository;


}