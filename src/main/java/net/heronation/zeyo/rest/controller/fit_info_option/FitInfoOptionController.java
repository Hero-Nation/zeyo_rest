package net.heronation.zeyo.rest.controller.fit_info_option;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import net.heronation.zeyo.rest.service.fit_info_option.FitInfoOptionService;

@Slf4j
@RepositoryRestController
@RequestMapping("/fit_info_options")
public class FitInfoOptionController extends BaseController {

	@Autowired
	private FitInfoOptionService fit_info_optionService;

	@Autowired
	private FitInfoOptionRepository repository;
	@Autowired
	private FitInfoOptionResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public FitInfoOptionController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/select_options")
	@ResponseBody
	public ResponseEntity<ResultVO> select_options(@RequestParam(name = "fitinfo", defaultValue = "") String fitinfo) {
		if (fitinfo.equals("")) {
			return return_fail("fitinfo.empty");
		}

		return return_success((Object) fit_info_optionService.select_options(fitinfo));
	}

}