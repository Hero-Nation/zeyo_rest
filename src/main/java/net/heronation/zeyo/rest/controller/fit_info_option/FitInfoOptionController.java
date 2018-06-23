package net.heronation.zeyo.rest.controller.fit_info_option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultDto;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionRepository;
import net.heronation.zeyo.rest.repository.fit_info_option.FitInfoOptionResourceAssembler;
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
	public ResponseEntity<ResultDto> select_options(@RequestParam(name = "fitinfo", defaultValue = "") String fitinfo) {
		if (fitinfo.equals("")) {
			return return_fail("fitinfo.empty");
		}

		return return_success((Object) fit_info_optionService.select_options(fitinfo));
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(method = RequestMethod.POST, value = "/insert")
//	public ResponseEntity<ResultVO> insert(@RequestBody FitinfoOption param,
//			@AuthenticationPrincipal OAuth2Authentication auth) {
//
//		return return_success((Object) fit_info_optionService.insert(param));
//	}
//
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(method = RequestMethod.PATCH, value = "/update")
//	public ResponseEntity<ResultVO> update(@RequestBody ToggleVO param,
//			@AuthenticationPrincipal OAuth2Authentication auth) {
//
//		return return_success((Object) fit_info_optionService.update(param));
//	}
//	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
//	public ResponseEntity<ResultVO> delete(@RequestBody LIdVO param,
//			@AuthenticationPrincipal OAuth2Authentication auth) {
//
//		return return_success((Object) fit_info_optionService.delete(param));
//	}

}