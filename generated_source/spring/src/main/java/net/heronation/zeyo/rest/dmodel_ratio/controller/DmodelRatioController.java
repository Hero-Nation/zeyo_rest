package net.heronation.zeyo.rest.dmodel_ratio.controller; 
 
import net.heronation.zeyo.rest.common.controller.BaseController; 
import net.heronation.zeyo.rest.repository.dmodel_ratio.DmodelRatioRepository;
import net.heronation.zeyo.rest.repository.dmodel_ratio.DmodelRatioResourceAssembler;

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


import net.heronation.zeyo.rest.service.dmodel_ratio.DmodelRatioService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/dmodel_ratios")
public class DmodelRatioController extends BaseController {
	
    @Autowired
    private DmodelRatioService dmodel_ratioService;
 
     @Autowired
    private DmodelRatioRepository repository; 
     @Autowired
    private DmodelRatioResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public DmodelRatioController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}