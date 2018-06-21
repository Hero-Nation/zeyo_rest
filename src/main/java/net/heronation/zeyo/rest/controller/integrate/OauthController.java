package net.heronation.zeyo.rest.controller.integrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.CategoryResourceAssembler;
import net.heronation.zeyo.rest.service.integrate.cafe24.Cafe24Service;

@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthController extends BaseController {

	@Autowired
	private Cafe24Service cafe24Service;

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
		String R = cafe24Service.update_oauth_code_and_get_access_token(code, state);
		
		if(!CommonConstants.SUCCESS.equals(R)) {
			return return_fail(R);
		}else {
			return return_success(R);	
		}
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cafe24/refresh_access_token")
	@ResponseBody
	public ResponseEntity<ResultVO> refresh_access_token(@RequestParam(value = "id", required = false) Long id) {
		log.debug("/cafe24/refresh_access_token");
		log.debug(id+" " );
		String R = cafe24Service.get_access_token_by_refresh_token(id);
		
		if(!CommonConstants.SUCCESS.equals(R)) {
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