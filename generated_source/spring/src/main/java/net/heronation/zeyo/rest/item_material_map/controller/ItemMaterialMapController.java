package net.heronation.zeyo.rest.item_material_map.controller; 
 
import net.heronation.zeyo.rest.common.controller.BaseController; 
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMapRepository;
import net.heronation.zeyo.rest.repository.item_material_map.ItemMaterialMapResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.item_material_map.ItemMaterialMapService; 

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