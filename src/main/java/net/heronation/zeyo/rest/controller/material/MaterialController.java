package net.heronation.zeyo.rest.controller.material; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.material.MaterialResourceAssembler;
import net.heronation.zeyo.rest.repository.material.QMaterial;
import net.heronation.zeyo.rest.repository.warranty.QWarranty;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.material.MaterialService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/materials")
public class MaterialController extends BaseController {
	
    @Autowired
    private MaterialService materialService;
 
     @Autowired
    private MaterialRepository repository; 
     @Autowired
    private MaterialResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public MaterialController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "start",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime start,
			@RequestParam(value = "end",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		if (name != null) {
			builder.and(QMaterial.material.name.containsIgnoreCase(name));
		}

		if (start != null) {
			builder.and(QMaterial.material.createDt.after(start));
		}

		if (end != null) {
			builder.and(QMaterial.material.createDt.before(end));
		}

		builder.and(QMaterial.material.useYn.eq("Y"));
		
		return return_success((Object) materialService.search(builder.getValue(), pageable));
	}
 
}