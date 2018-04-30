package net.heronation.zeyo.rest.service.bbs;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.bbs.BbsRepository;



@Slf4j
@Service
@Transactional
public class BbsServiceImpl implements BbsService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private BbsRepository bbsRepository;


}