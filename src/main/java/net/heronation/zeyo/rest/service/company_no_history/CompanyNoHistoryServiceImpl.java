package net.heronation.zeyo.rest.service.company_no_history;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistoryRepository;



@Slf4j
@Service
@Transactional
public class CompanyNoHistoryServiceImpl implements CompanyNoHistoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private CompanyNoHistoryRepository company_no_historyRepository;


}