package net.heronation.zeyo.rest.fit_info.controller;

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
import net.heronation.zeyo.rest.fit_info.repository.FitInfoDto;
import net.heronation.zeyo.rest.fit_info.repository.FitInfoRepository;
import net.heronation.zeyo.rest.fit_info.repository.FitInfoResourceAssembler;
import net.heronation.zeyo.rest.fit_info.repository.FitInfoUpdateDto;
import net.heronation.zeyo.rest.fit_info.service.FitInfoService;
import net.heronation.zeyo.rest.fit_info_option.repository.QFitInfoOption;

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
	public ResponseEntity<ResultDto> list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		if (start == null) {
			param.put("start", start);
		} else {
			param.put("start", start.toString(Format.ISO_DATETIME));
		}
		if (end == null) {
			param.put("end", end);
		} else {
			param.put("end", end.toString(Format.ISO_DATETIME)); 
		}

		return return_success((Object) fit_infoService.search(param, pageable));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/detail_list")
	@ResponseBody
	public ResponseEntity<ResultDto> detail_list(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		Map<String, Object> param = new HashMap<String, Object>();
		if(id == null) {
			return return_fail("id.empty");
		}else {
			param.put("id", id); 
			return return_success((Object) fit_infoService.detail_list(param, pageable));	
		}
		
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/fitInfoOptions")
	@ResponseBody
	public ResponseEntity<ResultDto> list(@RequestParam(value = "fitInfoId", required = false) Long fitInfoId,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		QFitInfoOption target = QFitInfoOption.fitInfoOption;

		BooleanBuilder builder = new BooleanBuilder();
		builder.and(target.fitInfo.id.eq(fitInfoId));
		builder.and(target.useYn.eq("Y"));
		return return_success((Object) fit_infoService.fitInfoOptions_search(builder.getValue(), pageable));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public ResponseEntity<ResultDto> insert(@RequestBody FitInfoDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success(fit_infoService.insert(param));
	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update")
	public ResponseEntity<ResultDto> update(@RequestBody FitInfoUpdateDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success(  fit_infoService.update(param));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	public ResponseEntity<ResultDto> delete(@RequestBody List<LIdDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success(  fit_infoService.delete(param));
	}

}