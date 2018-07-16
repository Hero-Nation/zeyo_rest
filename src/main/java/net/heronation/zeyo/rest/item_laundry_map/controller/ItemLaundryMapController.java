package net.heronation.zeyo.rest.item_laundry_map.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMapRepository;
import net.heronation.zeyo.rest.item_laundry_map.repository.ItemLaundryMapResourceAssembler;
import net.heronation.zeyo.rest.item_laundry_map.service.ItemLaundryMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_laundry_maps")
public class ItemLaundryMapController extends BaseController {
	
    @Autowired
    private ItemLaundryMapService item_laundry_mapService;
 
     @Autowired
    private ItemLaundryMapRepository repository; 
     @Autowired
    private ItemLaundryMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemLaundryMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}