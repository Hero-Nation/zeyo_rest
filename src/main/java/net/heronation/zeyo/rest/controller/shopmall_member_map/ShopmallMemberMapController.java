package net.heronation.zeyo.rest.controller.shopmall_member_map;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.repository.shopmall_member_map.ShopmallMemberMapRepository;
import net.heronation.zeyo.rest.repository.shopmall_member_map.ShopmallMemberMapResourceAssembler;

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

import net.heronation.zeyo.rest.service.shopmall_member_map.ShopmallMemberMapService;

@Slf4j
@RepositoryRestController
@RequestMapping("/shopmall_member_maps")
public class ShopmallMemberMapController extends BaseController {

	@Autowired
	private ShopmallMemberMapService shopmall_member_mapService;

	@Autowired
	private ShopmallMemberMapRepository repository;
	@Autowired
	private ShopmallMemberMapResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ShopmallMemberMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}

}