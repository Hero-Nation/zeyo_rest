package net.heronation.zeyo.rest.common.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.common.service.CommonService;

@Controller
@Slf4j
@RequestMapping("/test")
@Validated
public class TestController extends BaseController {

	
	@Value(value = "${app.spring.profile}")
	private String app_spring_profile;
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(path = "/param_test", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> param_test(
			
			 @NotEmpty(message="pint.not.empty") @RequestParam(name="pint")  String p_int,
			 @NotNull(message="pstring.not.empty") @RequestParam(name="pstring")  String p_string
			
			) {
		log.debug("/test/param_test");
		return return_success(new ResultDto());
	}
	
	
	@RequestMapping(path = "/unit_test", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> dash_board_statistic() {
		log.debug("/test/unit_test");
		commonService.unit_test();
		return return_success(new ResultDto());
	}
	
	
	@RequestMapping(path = "/active_profile", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> active_profile() {
		log.debug("/test/active_profile"); 
		return return_success(app_spring_profile);
	}
	
	@RequestMapping(path = "/random_v2_insert_category", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> random_v2_insert_category() {
		log.debug("/test/random_v2_insert_category"); 
		
		commonService.insert_random_v2_category();
		
		return return_success(app_spring_profile);
	}

}
