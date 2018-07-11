package net.heronation.zeyo.rest.item_drymethod_map.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMapRepository;
import net.heronation.zeyo.rest.item_drymethod_map.repository.ItemDrymethodMapResourceAssembler;
import net.heronation.zeyo.rest.item_drymethod_map.service.ItemDrymethodMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_drymethod_maps")
public class ItemDrymethodMapController extends BaseController {
	
    @Autowired
    private ItemDrymethodMapService item_drymethod_mapService;
 
     @Autowired
    private ItemDrymethodMapRepository repository; 
     @Autowired
    private ItemDrymethodMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemDrymethodMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}