package net.heronation.zeyo.rest.controller.brand;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.brand.BrandDto;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.BrandResourceAssembler;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.madein.QMadein;

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
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;

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
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,

			@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "shopmall", required = false) String shopmall,
			@RequestParam(value = "link", required = false) String link,

			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

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
	public ResponseEntity<ResultVO> insert(
			@RequestBody BrandDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		
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
	public ResponseEntity<ResultVO> distinct_with_member_id(@AuthenticationPrincipal OAuth2Authentication auth) {
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		return return_success((Object) brandService.distinct_with_member_id());
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/use_count")
	@ResponseBody
	public ResponseEntity<ResultVO> use_count(@AuthenticationPrincipal OAuth2Authentication auth) {
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		return return_success((Object) brandService.use_count());
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update_name")
	@ResponseBody
	public ResponseEntity<ResultVO> update_name(
			@RequestBody BrandDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		if(param.getId() == null || param.getId().equals("") ) {
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
	public ResponseEntity<ResultVO> delete(
			@RequestBody BrandDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		if(param.getId() == null|| param.getId().equals("")) {
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
	public ResponseEntity<ResultVO> toggle_link(
			@RequestBody BrandDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		
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
	public ResponseEntity<ResultVO> brand_list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable, @AuthenticationPrincipal OAuth2Authentication auth) {
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
	public ResponseEntity<ResultVO> detail(@RequestParam(value = "id", required = false) Long id,
			@AuthenticationPrincipal OAuth2Authentication auth, Pageable pageable) {
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
	public ResponseEntity<ResultVO> brand_company_use_list(@AuthenticationPrincipal OAuth2Authentication auth,
			Pageable pageable) {
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
	public ResponseEntity<ResultVO> detail_info(@RequestParam(value = "id", required = false) Long id) {

		return return_success((Object) brandService.detail_info(id));

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/findByName")
	@ResponseBody
	public ResponseEntity<ResultVO> findByName(@RequestParam(value = "name") String name,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success(brandService.findByName(name));
	}
}