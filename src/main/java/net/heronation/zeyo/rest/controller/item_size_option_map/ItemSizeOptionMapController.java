package net.heronation.zeyo.rest.controller.item_size_option_map; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMapRepository;
import net.heronation.zeyo.rest.repository.item_size_option_map.ItemSizeOptionMapResourceAssembler;
import net.heronation.zeyo.rest.service.item_size_option_map.ItemSizeOptionMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_size_option_maps")
public class ItemSizeOptionMapController extends BaseController {
	
    @Autowired
    private ItemSizeOptionMapService item_size_option_mapService;
 
     @Autowired
    private ItemSizeOptionMapRepository repository; 
     @Autowired
    private ItemSizeOptionMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemSizeOptionMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}