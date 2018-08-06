package net.heronation.zeyo.rest.warranty.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;



@Slf4j
@Service
@Transactional
public class WarrantyServiceImpl implements WarrantyService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private WarrantyRepository warrantyRepository;


}