package net.heronation.zeyo.rest.controller.integrate;

import javax.servlet.http.HttpSession;

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
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.value.ResultDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.CategoryResourceAssembler;
import net.heronation.zeyo.rest.repository.ip_temp_info.IpTempInfo;
import net.heronation.zeyo.rest.service.integrate.cafe24.Cafe24Service;
import net.heronation.zeyo.rest.service.integrate.common.IntegrateCommonService;
import net.heronation.zeyo.rest.service.integrate.naver.NaverService;

@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthController extends BaseController {

	@Autowired
	private NaverService naverService;
	
	@Autowired
	private Cafe24Service cafe24Service;
	 
	@Autowired
	private IntegrateCommonService integrateCommonService;
	 

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
	public ResponseEntity<ResultDto> cafe24_callback(@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "error", required = false) String error) {
		log.debug("/cafe24/callback");
		log.debug(code);
		
		
		if(error != null) {
			return return_fail(error);
		}
		 
		String R = cafe24Service.update_oauth_code_and_get_access_token(code, state);
		
		if(!CommonConstants.SUCCESS.equals(R)) {
			return return_fail(R); 
		}else {
			return return_success(R);	
		}
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cafe24/refresh_access_token")
	@ResponseBody
	public ResponseEntity<ResultDto> refresh_access_token(@RequestParam(value = "id", required = false) Long id) {
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
	public ResponseEntity<ResultDto> kakao_callback(@RequestParam(value = "code", required = false) String code) {
		log.debug("/kakao/callback");
		log.debug(code);
		return return_success(code);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/naver/callback") 
	public String naver_callback(
			@RequestParam(value = "code", required = false) String code,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "error_description", required = false) String error_description ,
			HttpSession session
			) {
		// 세션을 사용하지 않는다. 세션은 메모리에 부담을 너무 준다.. 그래서 데이터베이스를 사용한다. 
		log.debug("/naver/callback");
		log.debug("code ");
		log.debug(code);
		log.debug("state ");
		log.debug(state);
		log.debug("error ");
		log.debug(error);
		log.debug("error_description");
		log.debug(error_description);
		log.debug("session.getId()");
		log.debug(session.getId());
		
//		      : /naver/callback
//		      : code 
//		      : CQWzbfLeKs07LcY1
//		      : state 
//		      : 
//		      : error 
//		      : null
//		      : error_description
//		      : null
		
		
		if(error == null) {
			
			try {
				
				naverService.update_oauth_code_and_get_access_token(code, state,state);
				IpTempInfo iti = integrateCommonService.getIpTempInfo(state);
				
				return "redirect:https://www.zeyo.co.kr/zeyo_app/main?shop_type=".concat(iti.getShopType()).concat("&shop_id=".concat(iti.getShopId()).concat("&product_id=".concat(iti.getProductId())));	
				
			} catch (CommonException e) {
				e.printStackTrace();
				return "redirect:https://www.zeyo.co.kr/zeyo_app/error";
			}
			
			
		}else {
			
			return "redirect:https://www.zeyo.co.kr/zeyo_app/error";
		}
		 
		
	}

}