package net.heronation.zeyo.rest.sub_category_measure_map.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMapRepository;
import net.heronation.zeyo.rest.sub_category_measure_map.repository.SubCategoryMeasureMapResourceAssembler;
import net.heronation.zeyo.rest.sub_category_measure_map.service.SubCategoryMeasureMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/sub_category_measure_maps")
public class SubCategoryMeasureMapController extends BaseController {
	
    @Autowired
    private SubCategoryMeasureMapService sub_category_measure_mapService;
 
     @Autowired
    private SubCategoryMeasureMapRepository repository; 
     @Autowired
    private SubCategoryMeasureMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public SubCategoryMeasureMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}