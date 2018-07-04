package net.heronation.zeyo.rest.controller.consumer; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.consumer.ConsumerRepository;
import net.heronation.zeyo.rest.repository.consumer.ConsumerResourceAssembler;
import net.heronation.zeyo.rest.service.consumer.ConsumerService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/consumers")
public class ConsumerController extends BaseController {
	
    @Autowired
    private ConsumerService consumerService;
 
     @Autowired
    private ConsumerRepository repository; 
     @Autowired
    private ConsumerResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ConsumerController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}