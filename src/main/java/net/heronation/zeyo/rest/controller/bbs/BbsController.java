package net.heronation.zeyo.rest.controller.bbs;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.bbs.BbsClientInsertDto;
import net.heronation.zeyo.rest.repository.bbs.BbsClientValidator;
import net.heronation.zeyo.rest.repository.bbs.BbsRepository;
import net.heronation.zeyo.rest.repository.bbs.BbsResourceAssembler;
import net.heronation.zeyo.rest.service.bbs.BbsService;

@Slf4j
@RepositoryRestController
@RequestMapping("/bbss")
public class BbsController extends BaseController {

	
	@Autowired
	private BbsService bbsService;

	@Autowired
	private BbsRepository repository;
	@Autowired
	private BbsResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public BbsController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new BbsClientValidator());
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ResultVO> handleResourceNotFoundException() {
		 return return_fail();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResultVO> list(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("title", title); 
		if(start == null) {
			param.put("start", start);	
		}else {
			param.put("start", start.toString(Format.ISO_DATETIME));
		}
		if(end == null) {
			param.put("end", end);	
		}else {
			param.put("end", end.toString(Format.ISO_DATETIME));
		}

		return return_success((Object) bbsService.search(param, pageable));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(method = RequestMethod.GET, value = "/client_list")
	@ResponseBody
	public ResponseEntity<ResultVO> client_list(Pageable pageable, @AuthenticationPrincipal OAuth2Authentication auth) {

		if(auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		Map<String,Object> param = new HashMap<String,Object>(); 
		param.put("member_id", seq);
		
		return return_success((Object) bbsService.client_search(param, pageable));
	}
	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(method = RequestMethod.GET, value = "/get_stats")
//	@ResponseBody
//	public ResponseEntity<ResultVO> get_stats(Pageable pageable, @AuthenticationPrincipal OAuth2Authentication auth) {
//
//		if(auth == null) {
//			return return_fail(CommonConstants.NO_TOKEN);
//		}
//		
//		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
//				.getDecodedDetails();
//		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
//
//		BooleanBuilder builder = new BooleanBuilder();
//
//		QBbs target = QBbs.bbs;
//
//		builder.and(target.member.id.eq(seq).and(target.useYn.eq("Y")));
//
//		return return_success((Object) bbsService.search(builder.getValue(), pageable));
//	}
	
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/query")
	@ResponseBody
	public ResponseEntity<ResultVO> query(@RequestBody @Valid BbsClientInsertDto new_post, BindingResult result,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		if(auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		
		if (result.hasErrors()) {
			return return_fail(result.getFieldError());
		} else {
			Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
					.getDecodedDetails();
			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			return return_success((Object) bbsService.client_insert(new_post, seq));
		}

	}
}