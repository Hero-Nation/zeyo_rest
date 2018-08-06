package net.heronation.zeyo.rest.dmodel_measure_map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapRepository;
import net.heronation.zeyo.rest.dmodel_measure_map.repository.DmodelMeasureMapResourceAssembler;
import net.heronation.zeyo.rest.dmodel_measure_map.service.DmodelMeasureMapService;

@Slf4j
@RepositoryRestController
@RequestMapping("/dmodel_measure_maps")
public class DmodelMeasureMapController extends BaseController {

	@Autowired
	private DmodelMeasureMapService dmodel_measure_mapService;

	@Autowired
	private DmodelMeasureMapRepository repository;
	@Autowired
	private DmodelMeasureMapResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public DmodelMeasureMapController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}

}