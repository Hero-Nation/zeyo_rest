package net.heronation.zeyo.rest.controller.warranty; 
 
import net.heronation.zeyo.rest.common.controller.BaseController; 
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;
import net.heronation.zeyo.rest.repository.warranty.WarrantyResourceAssembler;

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


import net.heronation.zeyo.rest.service.warranty.WarrantyService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/warrantys")
public class WarrantyController extends BaseController {
	
    @Autowired
    private WarrantyService warrantyService;
 
     @Autowired
    private WarrantyRepository repository; 
     @Autowired
    private WarrantyResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public WarrantyController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}