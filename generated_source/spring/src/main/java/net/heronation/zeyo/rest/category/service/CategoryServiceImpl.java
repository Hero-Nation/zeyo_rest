package net.heronation.zeyo.rest.category.service;
 
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;



@Slf4j
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    	@Autowired
	private RestTemplate restTemplate; 

	@Autowired
	private CategoryRepository categoryRepository;


}