package net.heronation.zeyo.rest.controller.item_scmm_so_value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.item_scmm_so_value.ItemScmmSoValueRepository;
import net.heronation.zeyo.rest.repository.item_scmm_so_value.ItemScmmSoValueResourceAssembler;
import net.heronation.zeyo.rest.service.item_scmm_so_value.ItemScmmSoValueService;

@Slf4j
@RepositoryRestController
@RequestMapping("/item_scmm_so_values")
public class ItemScmmSoValueController extends BaseController {

	@Autowired
	private ItemScmmSoValueService item_scmm_so_valueService;

	@Autowired
	private ItemScmmSoValueRepository repository;
	@Autowired
	private ItemScmmSoValueResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ItemScmmSoValueController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}

}