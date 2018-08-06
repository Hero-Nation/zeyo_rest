package net.heronation.zeyo.rest.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.common.service.CommonService;

@Controller
@Slf4j
@RequestMapping("/test")
public class TestController extends BaseController {

	
	@Value(value = "${app.spring.profile}")
	private String app_spring_profile;
	
	@Autowired
	private CommonService commonService;

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
