package net.heronation.zeyo.rest.dmodel.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.dmodel.DmodelRepository;



@Slf4j
@Service
@Transactional
public class DmodelServiceImpl implements DmodelService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private DmodelRepository dmodelRepository;


}