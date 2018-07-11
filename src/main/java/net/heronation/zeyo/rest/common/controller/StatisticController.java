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
@RequestMapping("/statistic")
public class StatisticController extends BaseController {

	@Autowired
	private CommonService commonService;

	@RequestMapping(path = "/dash_board_statistic", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> dash_board_statistic() {
		log.debug("/statistic/dash_board_statistic");

		return return_success(commonService.dash_board_statistic());
	}

}
