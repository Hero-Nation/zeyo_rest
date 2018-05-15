package net.heronation.zeyo.rest.controller.warranty; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.madein.QMadein;
import net.heronation.zeyo.rest.repository.warranty.QWarranty;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;
import net.heronation.zeyo.rest.repository.warranty.WarrantyResourceAssembler;

import java.util.Date;

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


import net.heronation.zeyo.rest.service.warranty.WarrantyService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/warrantys")
public class WarrantyController extends BaseController {
	
    @Autowired
    private WarrantyService warrantyService;
 
     @Autowired
    private WarrantyRepository repository; 
     @Autowired
    private WarrantyResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public WarrantyController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "scope",required=false) String scope,
			@RequestParam(value = "start",required=false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
			@RequestParam(value = "end",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		if (scope != null) {
			builder.and(QWarranty.warranty.scope.containsIgnoreCase(scope));
		}

		if (start != null) {
			builder.and(QWarranty.warranty.createDt.after(start));
		}

		if (end != null) {
			builder.and(QWarranty.warranty.createDt.before(end));
		}

		builder.and(QWarranty.warranty.useYn.eq("Y"));
		
		return return_success((Object) warrantyService.search(builder.getValue(), pageable));
	}
 
}