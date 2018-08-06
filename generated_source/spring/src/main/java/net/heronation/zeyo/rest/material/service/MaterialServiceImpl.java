package net.heronation.zeyo.rest.material.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;



@Slf4j
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private MaterialRepository materialRepository;


}