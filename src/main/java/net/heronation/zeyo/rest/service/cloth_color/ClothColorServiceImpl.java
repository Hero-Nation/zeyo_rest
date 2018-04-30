package net.heronation.zeyo.rest.service.cloth_color;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;



@Slf4j
@Service
@Transactional
public class ClothColorServiceImpl implements ClothColorService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private ClothColorRepository cloth_colorRepository;


}