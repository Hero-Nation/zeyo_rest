package net.heronation.zeyo.rest.controller.item_drycleaning_map; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMapRepository;
import net.heronation.zeyo.rest.repository.item_drycleaning_map.ItemDrycleaningMapResourceAssembler;
import net.heronation.zeyo.rest.service.item_drycleaning_map.ItemDrycleaningMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_drycleaning_maps")
public class ItemDrycleaningMapController extends BaseController {
	
    @Autowired
    private ItemDrycleaningMapService item_drycleaning_mapService;
 
     @Autowired
    private ItemDrycleaningMapRepository repository; 
     @Autowired
    private ItemDrycleaningMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemDrycleaningMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}