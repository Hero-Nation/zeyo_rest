package net.heronation.zeyo.rest.controller.item;

import net.heronation.zeyo.rest.common.authentication.AppUserDetails;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.controller.member.MemberRegisterValidator;
import net.heronation.zeyo.rest.repository.bbs.BbsClientInsertDto;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemDto;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.ItemResourceAssembler;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.item_shopmall_map.QItemShopmallMap;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;

import lombok.extern.slf4j.Slf4j;

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
		binder.addValidators(new ItemBuildValidator());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "download_excel")
	public ResponseEntity<ResultVO> download_excel(@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/items/download_excel");
		if(auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		} 
		return return_success();
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
	@RequestMapping(method = RequestMethod.PATCH, value = "/change_connect")
	@ResponseBody
	public ResponseEntity<ResultVO> change_connect(

			@RequestBody ItemDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		
		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
		
		
		if (param.getTarget() == null|| param.getTarget().equals("")) {
			return return_fail("target.empty");
		} else {
			return return_success(itemService.change_connect(param,seq));
		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	@ResponseBody
	public ResponseEntity<ResultVO> delete(

			@RequestBody ItemDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
		
		if (param.getTarget() == null|| param.getTarget().equals("")) {
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
	@RequestMapping(method = RequestMethod.GET, value = "/toggle_size_table")
	@ResponseBody
	public ResponseEntity<ResultVO> toggle_size_table(
			@RequestParam(value = "id", required = false, defaultValue = "0") Long item_id) {

		if (item_id == 0) {
			return return_fail("id.empty");
		} else {

			String returnValue = itemService.toggle_size_table(item_id);

			if (returnValue == null) {
				return return_fail("item.not.exist");
			} else {
				return return_success(returnValue);
			}

		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
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

}