package net.heronation.zeyo.rest.service.sub_category;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;



@Slf4j
@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private SubCategoryRepository sub_categoryRepository;


}