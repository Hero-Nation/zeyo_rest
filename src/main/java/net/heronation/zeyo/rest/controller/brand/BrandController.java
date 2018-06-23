package net.heronation.zeyo.rest.controller.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.IdNameDto;
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.common.value.NameDto;
import net.heronation.zeyo.rest.common.value.ResultDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.brand.BrandDto;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.BrandResourceAssembler;
import net.heronation.zeyo.rest.service.brand.BrandService;

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
			Pageable pageable) {
		log.debug("list");
		
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
			@RequestBody List<LIdDto> param,
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

		Long seq = (Long) user.get("member_seq");

		return return_success((Object) brandService.delete(param, seq));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/toggle_link")
	@ResponseBody
	public ResponseEntity<ResultDto> toggle_link(
			@RequestBody BrandDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		log.debug("toggle_link");
		
		
		if(param.getId() == null|| param.getId().equals("")) {
			return return_fail("id.empty");	
		}
		
		if(param.getLink() == null|| param.getLink().equals("")) {
			return return_fail("link.empty");	
		}
			
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf((int) user.get("member_seq")));

		return return_success((Object) brandService.toggle_link(param,seq));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/client/list")
	@ResponseBody
	public ResponseEntity<ResultDto> brand_list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable, @AuthenticationPrincipal OAuth2Authentication auth) {
		
		log.debug("brand_list");
		
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
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
			@AuthenticationPrincipal OAuth2Authentication auth, Pageable pageable) {
		
		log.debug("detail");
		
		
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
			Pageable pageable) {
		
		log.debug("brand_company_use_list");
		
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