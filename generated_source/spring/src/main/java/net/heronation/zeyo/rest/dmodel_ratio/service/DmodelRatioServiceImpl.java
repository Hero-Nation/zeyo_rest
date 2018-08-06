package net.heronation.zeyo.rest.dmodel_ratio.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.dmodel_ratio.DmodelRatioRepository;



@Slf4j
@Service
@Transactional
public class DmodelRatioServiceImpl implements DmodelRatioService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private DmodelRatioRepository dmodel_ratioRepository;


}