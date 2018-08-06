package net.heronation.zeyo.rest.brand.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;



@Slf4j
@Service
@Transactional
public class BrandServiceImpl implements BrandService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private BrandRepository brandRepository;


}