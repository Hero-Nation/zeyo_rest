package net.heronation.zeyo.rest.controller.integrate;

import java.math.BigInteger;
import java.security.SecureRandom;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.IdNameVO;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.common.value.NameVO;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.controller.category.CategoryController;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.CategoryResourceAssembler;
import net.heronation.zeyo.rest.service.category.CategoryService;
import net.heronation.zeyo.rest.service.integrate.IntegrateSerivce;

@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthController extends BaseController {

	@Autowired
	private IntegrateSerivce integrateSerivce;

	@Autowired
	private CategoryRepository repository;
	@Autowired
	private CategoryResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public OauthController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/cafe24/callback")
	@ResponseBody
	public ResponseEntity<ResultVO> cafe24_callback(@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state) {
		log.debug("/cafe24/callback");
		log.debug(code);
		String R = integrateSerivce.update_oauth_code(code, state);
		
		if(CommonConstants.FAIL.equals(R)) {
			return return_fail(R);
		}else {
			return return_success(R);	
		}
		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/kakao/callback")
	@ResponseBody
	public ResponseEntity<ResultVO> kakao_callback(@RequestParam(value = "code", required = false) String code) {
		log.debug("/kakao/callback");
		log.debug(code);
		return return_success(code);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/naver/callback")
	@ResponseBody
	public ResponseEntity<ResultVO> naver_callback(@RequestParam(value = "code", required = false) String code) {
		log.debug("/naver/callback");
		log.debug(code);
		return return_success(code);
	}

}