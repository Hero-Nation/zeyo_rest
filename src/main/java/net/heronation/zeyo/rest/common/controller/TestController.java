package net.heronation.zeyo.rest.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.common.service.CommonService;

@RepositoryRestController
@Slf4j
@RequestMapping("/test")
public class TestController extends BaseController {

	@Autowired
	private CommonService commonService;

	@RequestMapping(path = "/unit_test", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> dash_board_statistic() {
		log.debug("/test/unit_test");
		commonService.unit_test();
		return return_success(new ResultDto());
	}

}
