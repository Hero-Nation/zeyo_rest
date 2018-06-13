package net.heronation.zeyo.rest.controller.item;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.common.value.ToggleVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemModifyDto;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.ItemResourceAssembler;
import net.heronation.zeyo.rest.service.item.ItemService;

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
	public ResponseEntity<ResultVO> list(

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
			Pageable pageable) {

		
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
	public ResponseEntity<ResultVO> clinet_list(

			@RequestParam(value = "name", required = false) String name,  
			@RequestParam(value = "size_link", required = false) String size_link,
			@RequestParam(value = "cate", required = false) String category,
			@RequestParam(value = "sub_cate", required = false) String sub_category,
			@RequestParam(value = "start_price",  required = false) String start_price,
			@RequestParam(value = "end_price",  required = false) String end_price,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@AuthenticationPrincipal OAuth2Authentication auth,
			Pageable pageable) {

		if(auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		} 
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);  
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
	public ResponseEntity<ResultVO> toggle_link(

			@RequestBody List<ToggleVO> param,
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
	public ResponseEntity<ResultVO> delete(

			@RequestBody List<ToggleVO> param,
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
	public ResponseEntity<ResultVO> shopmall_list(
			@RequestParam(value = "id", required = false, defaultValue = "0") Long item_id, Pageable pageable) {

		if (item_id == 0) {
			return return_fail("id.empty");
		} else {
			return return_success(itemService.shopmall_list(item_id, pageable));
		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/toggle_size_table")
	@ResponseBody
	public ResponseEntity<ResultVO> toggle_size_table(
			@RequestBody List<ToggleVO> param) {

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
	@RequestMapping(method = RequestMethod.PATCH, value = "/download_excel")
	@ResponseBody
	public ResponseEntity<ResultVO> download_excel(
			@RequestBody List<ToggleVO> param,
			@AuthenticationPrincipal OAuth2Authentication auth, Pageable pageable) {

 
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
	public ResponseEntity<ResultVO> build(@RequestBody @Valid ItemBuildDto itemBuildDto, BindingResult bindingResult,
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
	@RequestMapping(method = RequestMethod.POST, value = "/modify")
	@ResponseBody
	public ResponseEntity<ResultVO> modify(@RequestBody @Valid ItemModifyDto itemModifyDto, BindingResult bindingResult,
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

			Item new_item = itemService.modify(itemModifyDto, seq);

			return return_success(new_item);
		}

	}

}