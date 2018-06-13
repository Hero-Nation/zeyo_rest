package net.heronation.zeyo.rest.controller.sub_category_fit_info_map; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMapRepository;
import net.heronation.zeyo.rest.repository.sub_category_fit_info_map.SubCategoryFitInfoMapResourceAssembler;
import net.heronation.zeyo.rest.service.sub_category_fit_info_map.SubCategoryFitInfoMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/sub_category_fit_info_maps")
public class SubCategoryFitInfoMapController extends BaseController {
	
    @Autowired
    private SubCategoryFitInfoMapService sub_category_fit_info_mapService;
 
     @Autowired
    private SubCategoryFitInfoMapRepository repository; 
     @Autowired
    private SubCategoryFitInfoMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public SubCategoryFitInfoMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}