package net.heronation.zeyo.rest.consumer.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.consumer.repository.ConsumerRepository;
import net.heronation.zeyo.rest.consumer.repository.ConsumerResourceAssembler;
import net.heronation.zeyo.rest.consumer.service.ConsumerService; 

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