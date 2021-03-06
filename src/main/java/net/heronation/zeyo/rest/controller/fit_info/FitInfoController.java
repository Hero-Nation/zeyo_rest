package net.heronation.zeyo.rest.controller.fit_info; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoRepository;
import net.heronation.zeyo.rest.repository.fit_info.FitInfoResourceAssembler;
import net.heronation.zeyo.rest.repository.fit_info.QFitInfo;
import net.heronation.zeyo.rest.repository.fit_info_option.QFitInfoOption;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "start",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime start,
			@RequestParam(value = "end",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime end,
			Pageable pageable) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name); 
		if(start == null) {
			param.put("start", start);	
		}else {
			param.put("start", start.toString(Format.ISO_DATETIME));
		}
		if(end == null) {
			param.put("end", end);	
		}else {
			param.put("end", end.toString(Format.ISO_DATETIME));
		}
		
		return return_success((Object) fit_infoService.search(param, pageable));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/fitInfoOptions")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "fitInfoId",required=false) Long fitInfoId,
			Pageable pageable) {
		
		QFitInfoOption target = QFitInfoOption.fitInfoOption;

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(target.fitInfo.id.eq(fitInfoId));
		builder.and(target.useYn.eq("Y"));
		return return_success((Object) fit_infoService.fitInfoOptions_search(builder.getValue(), pageable));
	}
 
}