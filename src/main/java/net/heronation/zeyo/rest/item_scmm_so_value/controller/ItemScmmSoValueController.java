package net.heronation.zeyo.rest.item_scmm_so_value.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.item_scmm_so_value.repository.ItemScmmSoValueRepository;
import net.heronation.zeyo.rest.item_scmm_so_value.repository.ItemScmmSoValueResourceAssembler;
import net.heronation.zeyo.rest.item_scmm_so_value.service.ItemScmmSoValueService;

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