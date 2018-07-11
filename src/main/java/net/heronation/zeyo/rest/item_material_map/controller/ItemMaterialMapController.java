package net.heronation.zeyo.rest.item_material_map.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.item_material_map.repository.ItemMaterialMapResourceAssembler;
import net.heronation.zeyo.rest.item_material_map.service.ItemMaterialMapService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/item_material_maps")
public class ItemMaterialMapController extends BaseController {
	
    @Autowired
    private ItemMaterialMapService item_material_mapService;
 
     @Autowired
    private ItemMaterialMapRepository repository; 
     @Autowired
    private ItemMaterialMapResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ItemMaterialMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}