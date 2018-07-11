package net.heronation.zeyo.rest.size_table.controller; 
 
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
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.common.constants.Format;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.size_table.repository.SizeTableDto;
import net.heronation.zeyo.rest.size_table.repository.SizeTableRepository;
import net.heronation.zeyo.rest.size_table.repository.SizeTableResourceAssembler;
import net.heronation.zeyo.rest.size_table.service.SizeTableService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/size_tables")
public class SizeTableController extends BaseController {
	
    @Autowired
    private SizeTableService size_tableService;
 
     @Autowired
    private SizeTableRepository repository; 
     @Autowired
    private SizeTableResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public SizeTableController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

 	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultDto> list(
			
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "shopmall", required = false) String shopmall,
		 
			@RequestParam(value = "start_price",  required = false) String start_price,
			@RequestParam(value = "end_price", required = false) String end_price,
			@RequestParam(value = "size_table", required = false) String size_table,
			
			@RequestParam(value = "start", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end, @RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("company", company);
		param.put("brand", brand);
		param.put("shopmall", shopmall);  
		param.put("start_price", start_price); 
		param.put("end_price", end_price);   
		param.put("size_table", size_table);   
		
		
		
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
		
		return return_success((Object) size_tableService.search(param, pageable));
	}
 	
 	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/get_size_count")
	@ResponseBody
	public ResponseEntity<ResultDto> get_size_count() {

		Map<String,Object> param = new HashMap<String,Object>();
 
		
		return return_success((Object) size_tableService.get_size_count(param));
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/client/list")
	@ResponseBody
	public ResponseEntity<ResultDto> clientlist(
			
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "sub_category", required = false) String sub_category,
		
			@RequestParam(value = "start_price",  required = false) String start_price,
			@RequestParam(value = "end_price", required = false) String end_price,
			@RequestParam(value = "size_table", required = false) String size_table,
			@RequestParam(value = "size_link", required = false) String size_link,
			
			@RequestParam(value = "start", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end, @RequestParam(value = "sort",  required = false) String sort,Pageable pageable,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("category", category);
		param.put("subcate", sub_category); 
		param.put("start_price", start_price); 
		param.put("end_price", end_price);   
		param.put("size_table", size_table);   
		param.put("size_link", size_link);    
		param.put("member_seq", seq);   
		
		
		
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
		
		return return_success((Object) size_tableService.client_search(param, pageable));
	}
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	@ResponseBody
	public ResponseEntity<ResultDto> delete(

			@RequestBody List<ToggleDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
		
		if (param == null|| param.size() == 0) {
			return return_fail("target.empty");
		}else { 
			return return_success(size_tableService.delete(param,  seq));
		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/batch_build")
	@ResponseBody
	public ResponseEntity<ResultDto> batch_build(

			@RequestBody List<ToggleDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
		
		if (param == null|| param.size() == 0) {
			return return_fail("target.empty");
		}else {
			 return return_success(size_tableService.batch_build(param,  seq));
		}

	}

	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/preview")
	@ResponseBody
	public ResponseEntity<ResultDto> preview(
			@RequestParam(value = "id", required = false) String id,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
 
		Long item_id = Long.valueOf(id); 
		
		
		return return_success((Object) size_tableService.preview(item_id));
	}
	

	
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/modify")
	@ResponseBody
	public ResponseEntity<ResultDto> modify(
			@RequestBody SizeTableDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
 
		
		try {
			return return_success((Object) size_tableService.modify(param));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
	}
	
	
	
	
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/create")
	@ResponseBody
	public ResponseEntity<ResultDto> create(
			@RequestBody SizeTableDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
 
		
		try {
			return return_success((Object) size_tableService.create(param));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/single_info")
	@ResponseBody
	public ResponseEntity<ResultDto> single_info(
			@RequestParam(value = "id", required = false) long id,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
 
		
		return return_success((Object) size_tableService.single_infos(id));
	}
	
	
	
}