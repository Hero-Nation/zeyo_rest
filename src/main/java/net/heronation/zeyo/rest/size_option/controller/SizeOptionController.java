package net.heronation.zeyo.rest.size_option.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.Format;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.size_option.repository.QSizeOption;
import net.heronation.zeyo.rest.size_option.repository.SizeOptionDto;
import net.heronation.zeyo.rest.size_option.repository.SizeOptionRepository;
import net.heronation.zeyo.rest.size_option.repository.SizeOptionResourceAssembler;
import net.heronation.zeyo.rest.size_option.service.SizeOptionService;

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
	public ResponseEntity<ResultDto> list(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "cate", required = false) String category,
			@RequestParam(value = "sub_cate", required = false) String sub_category,
			
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "kindof", required = false) String kindof,
			
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
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
	public ResponseEntity<ResultDto> single(
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
	public ResponseEntity<ResultDto> category_count() {

		BooleanBuilder builder = new BooleanBuilder();

		QSizeOption target = QSizeOption.sizeOption;

 
		builder.and(target.useYn.eq("Y"));

		return return_success((Object) size_optionService.category_count(builder.getValue()));
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public ResponseEntity<ResultDto> insert(@RequestBody SizeOptionDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) size_optionService.insert(param));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update")
	public ResponseEntity<ResultDto> update(@RequestBody SizeOptionDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) size_optionService.update(param));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	public ResponseEntity<ResultDto> delete(@RequestBody List<LIdDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) size_optionService.delete(param));
	}

	

}