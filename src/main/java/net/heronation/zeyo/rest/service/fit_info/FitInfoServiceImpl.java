package net.heronation.zeyo.rest.service.fit_info;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoRepository;



@Slf4j
@Service
@Transactional
public class FitInfoServiceImpl implements FitInfoService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private FitInfoRepository fit_infoRepository;


}