package net.heronation.zeyo.rest.v2_rule.controller; 
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.CommonConstants;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.v2_rule.repository.V2RuleRepository;
import net.heronation.zeyo.rest.v2_rule.repository.V2RuleResourceAssembler;
import net.heronation.zeyo.rest.v2_rule.service.V2RuleService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/v2_rules")
public class V2RuleController extends BaseController {
	
    @Autowired
    private V2RuleService v2_ruleService;
 
     @Autowired
    private V2RuleRepository repository; 
     @Autowired
    private V2RuleResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

	@InitBinder
	private void initBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new V2RuleDtoValidator());
	}

 	@Autowired
	public V2RuleController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "")
	@ResponseBody
	public ResponseEntity<ResultDto> list(@RequestParam(value = "name", required = false) String name,
			Pageable pageable) {
		log.debug("GET /api/v2_rules/");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("name", name);

		return return_success((Object) v2_ruleService.search(param, pageable));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@ResponseBody
	public ResponseEntity<ResultDto> single(@PathVariable(value = "id", required = true) long id) {
		log.debug("GET /api/v2_rules/{id}");
	 
		return return_success((Object) v2_ruleService.single(id));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	@ResponseBody
	public ResponseEntity<ResultDto> delete( 
			@RequestBody List<String> param, @AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("PATCH /api/v2_rules");
		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails()).getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (param == null || param.size() == 0) {
			return return_fail("target.empty");
		} else {
			return return_success(v2_ruleService.delete(param, seq));
		}

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "")
	@ResponseBody
	public ResponseEntity<ResultDto> insert(@Valid @RequestBody V2RuleDto insertDto, BindingResult bindingResult,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("POST /api/v2_rules/insert");

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		log.debug(bindingResult.getErrorCount() + "");
		log.debug(bindingResult.hasErrors() + "");

		if (bindingResult.hasErrors()) {
			log.debug("/api/v2_rules/insert bindingResult.hasErrors()");
			return return_fail(bindingResult.getFieldError());
		} else {

			log.debug(insertDto.toString());

			Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails()).getDecodedDetails();

			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			return return_success(v2_ruleService.insert(insertDto));
		}

	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PUT, value = "")
	@ResponseBody
	public ResponseEntity<ResultDto> update(@Valid @RequestBody V2RuleDto updateDto, BindingResult bindingResult,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("POST /api/v2_rules/upate");

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}

		log.debug(bindingResult.getErrorCount() + "");
		log.debug(bindingResult.hasErrors() + "");

		if (bindingResult.hasErrors()) {
			log.debug("/api/v2_rules/upate bindingResult.hasErrors()");
			return return_fail(bindingResult.getFieldError());
		} else {

			log.debug(updateDto.toString());

			Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails()).getDecodedDetails();

			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			return return_success(v2_ruleService.update(updateDto));
		}

	}

 
}