package net.heronation.zeyo.rest.controller.size_option;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionResourceAssembler;

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

import net.heronation.zeyo.rest.service.size_option.SizeOptionService;

@Slf4j
@RepositoryRestController
@RequestMapping("/size_options")

public class SizeOptionController extends BaseController {

	@Autowired
	private SizeOptionService size_optionService;

	@Autowired
	private SizeOptionRepository repository;
	@Autowired
	private SizeOptionResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public SizeOptionController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "cate", required = false) String category,
			@RequestParam(value = "sub_cate", required = false) String sub_category,
			
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "kindof", required = false) String kindof,
			
			Pageable pageable) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name); 
		param.put("category", category); 
		param.put("sub_category", sub_category); 
		param.put("kindof", kindof); 
		
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
		return return_success((Object) size_optionService.search(param, pageable));
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/single")
	@ResponseBody
	public ResponseEntity<ResultVO> single(
			@RequestParam(value = "id", required = false) String id ) {
 
		BooleanBuilder builder = new BooleanBuilder();

		if(id ==  null) {
			return return_fail("id.empty");
		}else {
			QSizeOption target = QSizeOption.sizeOption;
			Long size_option_id = Long.valueOf(id);
			builder.and(target.id.eq(size_option_id));
			builder.and(target.useYn.eq("Y"));
			
			return return_success((Object) size_optionService.single(builder.getValue()));
		}
			
		

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/category_count")
	@ResponseBody
	public ResponseEntity<ResultVO> category_count() {

		BooleanBuilder builder = new BooleanBuilder();

		QSizeOption target = QSizeOption.sizeOption;

 
		builder.and(target.useYn.eq("Y"));

		return return_success((Object) size_optionService.category_count(builder.getValue()));
	}

}