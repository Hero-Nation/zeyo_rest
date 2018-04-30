package net.heronation.zeyo.rest.service.fit_into_choice;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.fit_into_choice.FitIntoChoiceRepository;



@Slf4j
@Service
@Transactional
public class FitIntoChoiceServiceImpl implements FitIntoChoiceService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private FitIntoChoiceRepository fit_into_choiceRepository;


}