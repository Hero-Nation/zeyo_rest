package net.heronation.zeyo.rest.dmodel_measure_map.controller; 
 
import net.heronation.zeyo.rest.common.controller.BaseController; 
import net.heronation.zeyo.rest.repository.dmodel_measure_map.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.repository.dmodel_measure_map.DmodelMeasureMapResourceAssembler;

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


import net.heronation.zeyo.rest.service.dmodel_measure_map.DmodelMeasureMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/dmodel_measure_maps")
public class DmodelMeasureMapController extends BaseController {
	
    @Autowired
    private DmodelMeasureMapService dmodel_measure_mapService;
 
     @Autowired
    private DmodelMeasureMapRepository repository; 
     @Autowired
    private DmodelMeasureMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public DmodelMeasureMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}