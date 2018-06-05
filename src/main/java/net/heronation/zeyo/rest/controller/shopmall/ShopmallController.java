package net.heronation.zeyo.rest.controller.shopmall;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.member.MemberDto;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallDto;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallResourceAssembler;

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

import net.heronation.zeyo.rest.service.shopmall.ShopmallService;

@Slf4j
@RepositoryRestController
@RequestMapping("/shopmalls")
public class ShopmallController extends BaseController {

	@Autowired
	private ShopmallService shopmallService;

	@Autowired
	private ShopmallRepository repository;
	@Autowired
	private ShopmallResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ShopmallController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

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
		return return_success((Object) shopmallService.search(param, pageable));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	@ResponseBody
	public ResponseEntity<ResultVO> insert(@RequestParam(value = "name", required = true) String name,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = (Long) user.get("member_seq");

		return return_success((Object) shopmallService.insert(name, seq));
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	@ResponseBody
	public ResponseEntity<ResultVO> delete(

			@RequestBody ShopmallDto param,

			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		
		if(param.getId() == null) {
			return return_fail("id.empty");
		}
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = (Long) user.get("member_seq");

		return return_success((Object) shopmallService.delete(param, seq));
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/update_name")
	@ResponseBody
	public ResponseEntity<ResultVO> update_name(@RequestBody ShopmallDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		
		if(param.getId() == null) {
			return return_fail("id.empty");
		}
		
		if(param.getName() == null) {
			return return_fail("name.empty");
		}
		
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success((Object) shopmallService.update_name(param, seq));
	}

	@RequestMapping(method = RequestMethod.PATCH, value = "/toggle_link")
	@ResponseBody
	public ResponseEntity<ResultVO> toggle_link(@RequestBody ShopmallDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = (Long) user.get("member_seq");

		if (param.getId() == null) {
			return return_fail("id.empty");
		} else if (param.getLinkYn() == null) {
			return return_fail("link.empty");
		} else {
			return return_success((Object) shopmallService.toggle_link(param, seq));
		}

	}

	@RequestMapping(method = RequestMethod.GET, value = "/check_unique_name")
	@ResponseBody
	public ResponseEntity<ResultVO> check_unique_name(@RequestParam(value = "name") String name) {

		if (name == null) {
			return return_fail("name.empty");
		} else {
			return return_success((Object) shopmallService.check_unique_name(name));
		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/shopmall_company_use_list")
	@ResponseBody
	public ResponseEntity<ResultVO> shopmall_company_use_list(@AuthenticationPrincipal OAuth2Authentication auth,
			Pageable pageable) {
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		return return_success(shopmallService.shopmall_company_use_list(pageable));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/detail_info")
	@ResponseBody
	public ResponseEntity<ResultVO> detail_info(@RequestParam(value = "id", required = false) Long id) {

		return return_success((Object) shopmallService.detail_info(id));

	}

	@RequestMapping(method = RequestMethod.GET, value = "/client/list")
	@ResponseBody
	public ResponseEntity<ResultVO> client_list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "link", required = false) String link,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable, @AuthenticationPrincipal OAuth2Authentication auth) {
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		String seq = String.valueOf(user.get("member_seq"));

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

		param.put("member_id", seq);

		return return_success((Object) shopmallService.client_search(param, pageable));
	}
	//

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

		return return_success(shopmallService.detail(id, seq, pageable));
	}
}