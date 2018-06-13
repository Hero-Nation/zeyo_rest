package net.heronation.zeyo.rest.controller.item_shopmall_map; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapRepository;
import net.heronation.zeyo.rest.repository.item_shopmall_map.ItemShopmallMapResourceAssembler;
import net.heronation.zeyo.rest.service.item_shopmall_map.ItemShopmallMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_shopmall_maps")
public class ItemShopmallMapController extends BaseController {
	
    @Autowired
    private ItemShopmallMapService item_shopmall_mapService;
 
     @Autowired
    private ItemShopmallMapRepository repository; 
     @Autowired
    private ItemShopmallMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemShopmallMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}