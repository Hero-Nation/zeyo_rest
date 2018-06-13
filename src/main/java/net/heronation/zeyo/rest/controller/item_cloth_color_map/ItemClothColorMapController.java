package net.heronation.zeyo.rest.controller.item_cloth_color_map; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMapRepository;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.ItemClothColorMapResourceAssembler;
import net.heronation.zeyo.rest.service.item_cloth_color_map.ItemClothColorMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_cloth_color_maps")
public class ItemClothColorMapController extends BaseController {
	
    @Autowired
    private ItemClothColorMapService item_cloth_color_mapService;
 
     @Autowired
    private ItemClothColorMapRepository repository; 
     @Autowired
    private ItemClothColorMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemClothColorMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}