package net.heronation.zeyo.rest.controller.bbs;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.controller.member.MemberRegisterValidator;
import net.heronation.zeyo.rest.repository.bbs.Bbs;
import net.heronation.zeyo.rest.repository.bbs.BbsClientInsertDto;
import net.heronation.zeyo.rest.repository.bbs.BbsClientValidator;
import net.heronation.zeyo.rest.repository.bbs.BbsRepository;
import net.heronation.zeyo.rest.repository.bbs.BbsResourceAssembler;
import net.heronation.zeyo.rest.repository.bbs.QBbs;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;

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
	

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QBbs target = QBbs.bbs;

		if (title != null) {
			builder.and(target.title.containsIgnoreCase(title));
		}

		if (start != null) {
			builder.and(target.createDt.after(start));
		}

		if (end != null) {
			builder.and(target.createDt.before(end));
		}

		builder.and(target.useYn.eq("Y"));

		return return_success((Object) bbsService.search(builder.getValue(), pageable));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/client_list")
	@ResponseBody
	public ResponseEntity<ResultVO> client_list(Pageable pageable, @AuthenticationPrincipal OAuth2Authentication auth) {

		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		BooleanBuilder builder = new BooleanBuilder();

		QBbs target = QBbs.bbs;

		builder.and(target.member.id.eq(seq).and(target.useYn.eq("Y")));

		return return_success((Object) bbsService.search(builder.getValue(), pageable));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/query")
	@ResponseBody
	public ResponseEntity<ResultVO> query( 
			@RequestBody @Valid BbsClientInsertDto new_post,BindingResult result, @AuthenticationPrincipal OAuth2Authentication auth) {

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