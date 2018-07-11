package net.heronation.zeyo.rest.item_bleach_map.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMapRepository;
import net.heronation.zeyo.rest.item_bleach_map.repository.ItemBleachMapResourceAssembler;
import net.heronation.zeyo.rest.item_bleach_map.service.ItemBleachMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_bleach_maps")
public class ItemBleachMapController extends BaseController {
	
    @Autowired
    private ItemBleachMapService item_bleach_mapService;
 
     @Autowired
    private ItemBleachMapRepository repository; 
     @Autowired
    private ItemBleachMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemBleachMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}