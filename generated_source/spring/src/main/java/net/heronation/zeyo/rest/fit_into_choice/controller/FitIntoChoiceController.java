package net.heronation.zeyo.rest.fit_into_choice.controller; 
 
import net.heronation.zeyo.rest.common.controller.BaseController; 
import net.heronation.zeyo.rest.repository.fit_into_choice.FitIntoChoiceRepository;
import net.heronation.zeyo.rest.repository.fit_into_choice.FitIntoChoiceResourceAssembler;

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


import net.heronation.zeyo.rest.service.fit_into_choice.FitIntoChoiceService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/fit_into_choices")
public class FitIntoChoiceController extends BaseController {
	
    @Autowired
    private FitIntoChoiceService fit_into_choiceService;
 
     @Autowired
    private FitIntoChoiceRepository repository; 
     @Autowired
    private FitIntoChoiceResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public FitIntoChoiceController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}