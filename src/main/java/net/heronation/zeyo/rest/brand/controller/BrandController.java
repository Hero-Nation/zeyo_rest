package net.heronation.zeyo.rest.brand.controller;

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
import net.heronation.zeyo.rest.brand.repository.BrandRepository;
import net.heronation.zeyo.rest.brand.repository.BrandResourceAssembler;
import net.heronation.zeyo.rest.brand.service.BrandService;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.common.constants.Format;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.dto.IdNameDto;
import net.heronation.zeyo.rest.common.dto.NameDto;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.common.dto.ToggleDto;

@Slf4j
@RepositoryRestController
@RequestMapping("/brands")
public class BrandController extends BaseController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private BrandRepository repository;
	@Autowired
	private BrandResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public BrandController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultDto> list(@RequestParam(value = "name", required = false) String name,

			@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "shopmall", required = false) String shopmall,
			@RequestParam(value = "link", required = false) String link,

			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		log.debug("list");


		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		param.put("company", company);
		param.put("brand", brand);
		param.put("shopmall", shopmall);
		param.put("link", link);
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

		return return_success((Object) brandService.search(param, pageable));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	@ResponseBody
	public ResponseEntity<ResultDto> insert(
			@RequestBody NameDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("insert");
		
		
		if(param.getName() == null || param.getName().equals("") ) {
			return return_fail("name.empty");	
		}
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf((int)user.get("member_seq")));

		return return_success((Object) brandService.insert(param, seq));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/distinct_with_member_id")
	@ResponseBody
	public ResponseEntity<ResultDto> distinct_with_member_id(@AuthenticationPrincipal OAuth2Authentication auth) {
		
		log.debug("distinct_with_member_id");
		
		
		
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		return return_success((Object) brandService.distinct_with_member_id());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/use_count")
	@ResponseBody
	public ResponseEntity<ResultDto> use_count(@AuthenticationPrincipal OAuth2Authentication auth) {
		
		log.debug("use_count");
		
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		return return_success((Object) brandService.use_count());
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update_name")
	@ResponseBody
	public ResponseEntity<ResultDto> update_name(
			@RequestBody IdNameDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("update_name");
		
		
		if(param.getId() == 0) {
			return return_fail("id.empty");	
		}
		
		if(param.getName() == null || param.getName().equals("")) {
			return return_fail("name.empty");	
		}
			
		
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success((Object) brandService.update_name(param, seq));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	@ResponseBody
	public ResponseEntity<ResultDto> delete(
			@RequestBody List<ToggleDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("delete");
		
		
		if(param.size() == 0) {
			return return_fail("id.empty");	
		}
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success((Object) brandService.delete(param, seq));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/toggle_link")
	@ResponseBody
	public ResponseEntity<ResultDto> toggle_link(
			@RequestBody List<ToggleDto>  param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		log.debug("toggle_link");
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
 

		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf((int) user.get("member_seq")));

		
		if (param.size() == 0) {
			return return_fail("param.empty");
		}  else {
			return return_success((Object) brandService.toggle_link(param, seq));
		}
		 
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/client/list")
	@ResponseBody
	public ResponseEntity<ResultDto> brand_list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable, @AuthenticationPrincipal OAuth2Authentication auth) {
		
		log.debug("brand_list");
		
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);
		param.put("link", link);
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

		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		String seq = String.valueOf(user.get("member_seq"));

		param.put("member_id", seq);

		return return_success((Object) brandService.client_search(param, pageable));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/detail")
	@ResponseBody
	public ResponseEntity<ResultDto> detail(@RequestParam(value = "id", required = false) Long id,
			@AuthenticationPrincipal OAuth2Authentication auth, @RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		
		log.debug("detail");
		
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success(brandService.detail(id, seq, pageable));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/brand_company_use_list")
	@ResponseBody
	public ResponseEntity<ResultDto> brand_company_use_list(@AuthenticationPrincipal OAuth2Authentication auth,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {
		
		log.debug("brand_company_use_list");
		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success(brandService.brand_company_use_list(pageable));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/detail_info")
	@ResponseBody
	public ResponseEntity<ResultDto> detail_info(@RequestParam(value = "id", required = false) Long id) {
		log.debug("detail_info");
		
		return return_success((Object) brandService.detail_info(id));

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/findByName")
	@ResponseBody
	public ResponseEntity<ResultDto> findByName(@RequestParam(value = "name") String name,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("findByName");
		

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success(brandService.findByName(name));
	}
}