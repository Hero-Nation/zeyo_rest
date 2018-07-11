package net.heronation.zeyo.rest.item_ironing_map.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMapRepository;
import net.heronation.zeyo.rest.item_ironing_map.repository.ItemIroningMapResourceAssembler;
import net.heronation.zeyo.rest.item_ironing_map.service.ItemIroningMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_ironing_maps")
public class ItemIroningMapController extends BaseController {
	
    @Autowired
    private ItemIroningMapService item_ironing_mapService;
 
     @Autowired
    private ItemIroningMapRepository repository; 
     @Autowired
    private ItemIroningMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemIroningMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}