package net.heronation.zeyo.rest.controller.brand_member_map;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.brand_member_map.BrandMemberMapRepository;
import net.heronation.zeyo.rest.repository.brand_member_map.BrandMemberMapResourceAssembler;

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

import net.heronation.zeyo.rest.service.brand_member_map.BrandMemberMapService;

@Slf4j
@RepositoryRestController
@RequestMapping("/brand_member_maps")
public class BrandMemberMapController extends BaseController {

	@Autowired
	private BrandMemberMapService brand_member_mapService;

	@Autowired
	private BrandMemberMapRepository repository;
	@Autowired
	private BrandMemberMapResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public BrandMemberMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}

}