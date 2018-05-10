package net.heronation.zeyo.rest.controller.fit_info; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoRepository;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoResourceAssembler;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;

import java.util.Date;

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

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.fit_info.FitInfoService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/fit_infos")
public class FitInfoController extends BaseController {
	
    @Autowired
    private FitInfoService fit_infoService;
 
     @Autowired
    private FitInfoRepository repository; 
     @Autowired
    private FitInfoResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public FitInfoController(RepositoryEntityLinks entityLinks) {
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
			@RequestParam(value = "start",required=false) Date start,
			@RequestParam(value = "end",required=false) Date end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		if (name != null) {
			builder.and(QMeasureItem.measureItem.name.containsIgnoreCase(name));
		}

		if (start != null) {
			builder.and(QMeasureItem.measureItem.createDt.after(start));
		}

		if (end != null) {
			builder.and(QMeasureItem.measureItem.createDt.before(end));
		}

		builder.and(QMeasureItem.measureItem.useYn.eq("Y"));
		
		return return_success((Object) fit_infoService.search(builder.getValue(), pageable));
	}
 
}