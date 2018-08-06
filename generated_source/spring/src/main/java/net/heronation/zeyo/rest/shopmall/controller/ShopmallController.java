package net.heronation.zeyo.rest.shopmall.controller; 
 
import net.heronation.zeyo.rest.common.controller.BaseController; 
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.shopmall.ShopmallService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/shopmalls")
public class ShopmallController extends BaseController {
	
    @Autowired
    private ShopmallService shopmallService;
 
     @Autowired
    private ShopmallRepository repository; 
     @Autowired
    private ShopmallResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ShopmallController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}