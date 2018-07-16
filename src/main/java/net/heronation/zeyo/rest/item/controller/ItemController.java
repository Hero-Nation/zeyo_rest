package net.heronation.zeyo.rest.item.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.common.dto.ToggleDto;
import net.heronation.zeyo.rest.item.repository.Item;
import net.heronation.zeyo.rest.item.repository.ItemBuildDto;
import net.heronation.zeyo.rest.item.repository.ItemModifyDto;
import net.heronation.zeyo.rest.item.repository.ItemRepository;
import net.heronation.zeyo.rest.item.repository.ItemResourceAssembler;
import net.heronation.zeyo.rest.item.service.ItemService;

@Slf4j
@RepositoryRestController
@RequestMapping("/items")
public class ItemController extends BaseController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository repository;
	@Autowired
	private ItemResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ItemController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
	//	binder.addValidators(new ItemBuildValidator());
	}
 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultDto> list(
 
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "shopmall", required = false) String shopmall,
			@RequestParam(value = "size_link", required = false) String size_link,
			@RequestParam(value = "cate", required = false) String category,
			@RequestParam(value = "sub_cate", required = false) String sub_category,
			@RequestParam(value = "start_price",   required = false) String start_price,
			@RequestParam(value = "end_price",   required = false) String end_price,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {

		// sort 값이 않들어오는 버그가 있음. 패치 
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("company", company);
		param.put("brand", brand);
		param.put("shopmall", shopmall); 
		param.put("size_link", size_link); 
		param.put("category", category);  
		param.put("sub_category", sub_category); 
		param.put("start_price", start_price); 
		param.put("end_price", end_price);   
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
		  

		return return_success((Object) itemService.search(param, pageable));
	}

	
	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/client_list")
	@ResponseBody
	public ResponseEntity<ResultDto> clinet_list(

			@RequestParam(value = "name", required = false) String name,  
			@RequestParam(value = "size_link", required = false) String size_link,
			@RequestParam(value = "size_table", required = false) String size_table,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "sub_category", required = false) String sub_category,
			@RequestParam(value = "start_price",  required = false) String start_price,
			@RequestParam(value = "end_price",  required = false) String end_price,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@AuthenticationPrincipal OAuth2Authentication auth,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		
 
//		size_table: N
//		size_link: N
//		category: 2
//		name: ㅁㅁ
//		sub_category: 8
//		start_price: 1234
//		end_price: 12341
//		start: 2018-07-01T15:00:00.000Z
//		end: 2018-07-04T14:59:59.999Z

		if(auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(), 
					pageable.getPageSize(),
				    Direction.DESC,
				    sort.split(",")[0]);
		}
		
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);  
		param.put("size_table", size_table);
		param.put("size_link", size_link);
		param.put("category", category);
		param.put("sub_category", sub_category);
		param.put("start_price", start_price);
		param.put("end_price", end_price);
		
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
		
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		String seq = String.valueOf(user.get("member_seq"));
		
		param.put("member_id", seq);
		
		return return_success((Object) itemService.client_search(param, pageable));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/toggle_link")
	@ResponseBody
	public ResponseEntity<ResultDto> toggle_link(

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
			return return_success(itemService.toggle_link(param,seq));
		}

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
			return return_success(itemService.delete(param,  seq));
		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/shopmall_list")
	@ResponseBody
	public ResponseEntity<ResultDto> shopmall_list(
			@RequestParam(value = "id", required = false, defaultValue = "0") Long item_id, @RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		if (item_id == 0) {
			return return_fail("id.empty");
		} else {
			return return_success(itemService.shopmall_list(item_id, pageable));
		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/toggle_size_table")
	@ResponseBody
	public ResponseEntity<ResultDto> toggle_size_table(
			@RequestBody List<ToggleDto> param) {

		if (param == null|| param.size() == 0) {
			return return_fail("target.empty");
		}else { 

			String returnValue = itemService.toggle_size_table(param);

			if (returnValue == null) {
				return return_fail("item.not.exist");
			} else {
				return return_success(returnValue);
			}

		}

	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update_item_image")
	@ResponseBody
	public ResponseEntity<ResultDto> update_item_image(
			@RequestBody ItemImageUploadDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		String member_id = String.valueOf(user.get("member_id"));
 
		
		try {
			return return_success((Object) itemService.update_item_image(param,member_id));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update_size_measure_image")
	@ResponseBody
	public ResponseEntity<ResultDto> update_size_measure_image(
			@RequestBody ItemSizeMeasureImageUploadDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		String member_id = String.valueOf(user.get("member_id"));
 
		
		try {
			return return_success((Object) itemService.update_size_measure_image(param,member_id));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/download_excel")
	@ResponseBody
	public ResponseEntity<ResultDto> download_excel(
			@RequestBody List<LIdDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth, @RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {

		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		if (param == null|| param.size() == 0) {
			return return_fail("target.empty");
		}else {
			try {
				return return_success( itemService.arrayExcel(param,pageable));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return return_fail("excel.build.exception");
			}
		}
		
		
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/build")
	@ResponseBody
	public ResponseEntity<ResultDto> build(@RequestBody @Valid ItemBuildDto itemBuildDto, BindingResult bindingResult,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/items/build");
		if(auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		if (bindingResult.hasErrors()) {
			return return_fail(bindingResult.getFieldError());
		} else {

			log.debug(itemBuildDto.toString());

			Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
					.getDecodedDetails();

			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			Item new_item = itemService.build(itemBuildDto, seq);

			return return_success(new_item);
		}

	}
	
	
	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/modify")
	@ResponseBody
	public ResponseEntity<ResultDto> modify(@RequestBody @Valid ItemModifyDto itemModifyDto, BindingResult bindingResult,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/items/modify");
		if(auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		if (bindingResult.hasErrors()) {
			return return_fail(bindingResult.getFieldError());
		} else {

			log.debug(itemModifyDto.toString());

			Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
					.getDecodedDetails();

			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			Item new_item;
			try {
				new_item = itemService.modify(itemModifyDto, seq);
				return return_success(CommonConstants.OK);
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return return_success(CommonConstants.FAIL);
			}

		
		}

	}

}