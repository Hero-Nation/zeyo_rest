package net.heronation.zeyo.rest.controller.member;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.bbs.QBbs;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.MemberResourceAssembler;
import net.heronation.zeyo.rest.repository.member.QMember;

import java.util.Date;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;

import net.heronation.zeyo.rest.service.member.MemberService;

@Slf4j
@RepositoryRestController
@RequestMapping("/members")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberRepository repository;
	@Autowired
	private MemberResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new MemberRegisterValidator());
	}

	@Autowired
	public MemberController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(path = "/registry", method = RequestMethod.POST)
	public ResponseEntity<ResultVO> registry(
			@ModelAttribute("memberRegisterDto") @Valid MemberRegisterDto memberRegisterDto,
			BindingResult bindingResult) {
		log.debug("/api/members/registry");

		if (bindingResult.hasErrors()) {
			return return_fail(bindingResult.getFieldError());
		} else {

			Member new_member = memberService.registry(memberRegisterDto);

			return return_success(new_member);
		}

	}

	@RequestMapping(path = "/send_register_mail", method = RequestMethod.POST)
	public ResponseEntity<ResultVO> send_register_mail(@RequestParam(name = "email", defaultValue = "") String email) {
		log.debug("/api/members/send_register_mail");

		if (email.equals("")) {
			return return_fail("email.empty");
		} else {
			return return_success();
		}

	}

	@RequestMapping(path = "/find_id_by_email", method = RequestMethod.POST)
	public ResponseEntity<ResultVO> find_id_by_email(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email) {
		log.debug("/api/members/find_id_by_email");

		if (name.equals("")) {
			return return_fail("name.empty");
		} else if (email.equals("")) {
			return return_fail("email.empty");
		} else if (EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.pattern.wrong");
		} else {
			return return_success();
		}

	}

	@RequestMapping(path = "/find_id_by_phone", method = RequestMethod.POST)
	public ResponseEntity<ResultVO> find_id_by_phone(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "phone", defaultValue = "") String phone) {
		log.debug("/api/members/find_id_by_phone");

		if (name.equals("")) {
			return return_fail("name.empty");
		} else if (phone.equals("")) {
			return return_fail("phone.empty");
		} else {
			return return_success();
		}

	}

	@RequestMapping(path = "/confirm_num", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> confirm_num(
			@RequestParam(name = "confirm_num", defaultValue = "") String confirm_num) {
		log.debug("/api/members/confirm_num");

		if (confirm_num.equals("")) {
			return return_fail("confirm_num.empty");
		} else {
			return return_success();
		}

	}

	@RequestMapping(path = "/find_password", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> find_password(@RequestParam(name = "member_id", defaultValue = "") String member_id,
			@RequestParam(name = "member_name", defaultValue = "") String member_name,
			@RequestParam(name = "member_email", defaultValue = "") String member_email) {
		log.debug("/api/members/find_password");

		if (member_id.equals("")) {
			return return_fail("member_id.empty");
		} else if (member_name.equals("")) {
			return return_fail("member_name.empty");
		} else if (member_email.equals("")) {
			return return_fail("member_email.empty");
		} else if (EmailValidator.getInstance().isValid(member_email)) {
			return return_fail("member_email.pattern.wrong");
		} else {
			return return_success();
		}

	}

	@RequestMapping(path = "my_info", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> my_info(@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/my_info");

		// 유저 정보 가지고 오기
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		BooleanBuilder builder = new BooleanBuilder();

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		builder.and(target.member.id.eq(Long.valueOf((Integer) user.get("member_seq"))));

		return return_success((Object) memberService.getCompanyInfo(builder.getValue()));
	}

	@RequestMapping(path = "user_info", method = RequestMethod.GET)
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResultVO> user_info(@AuthenticationPrincipal OAuth2Authentication auth,
			@RequestParam(value = "member_seq", required = true) Long member_seq) {
		log.debug("/api/members/user_info");
 
		BooleanBuilder builder = new BooleanBuilder();

		QMember target = QMember.member;

		builder.and(target.member.id.eq(member_seq));
		builder.and(target.member.useYn.eq("N"));

		return return_success((Object) memberService.getUserInfo(builder.getValue()));
	}
	
	
	@RequestMapping(path = "user_biz_info", method = RequestMethod.GET)
	// @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResultVO> user_biz_info(@AuthenticationPrincipal OAuth2Authentication auth,
			@RequestParam(value = "member_seq", required = true) Long member_seq) {
		log.debug("/api/members/user_biz_info");
 
		BooleanBuilder builder = new BooleanBuilder();

		QMember target = QMember.member;

		builder.and(target.member.id.eq(member_seq));
		builder.and(target.member.useYn.eq("N"));

		return return_success(  memberService.getUserBizInfo(builder.getValue()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "identity", required = false) String identity,

			@RequestParam(value = "phone1", required = false) String phone1,
			@RequestParam(value = "phone2", required = false) String phone2,
			@RequestParam(value = "phone3", required = false) String phone3,

			@RequestParam(value = "email1", required = false) String email1,
			@RequestParam(value = "email2", required = false) String email2,

			@RequestParam(value = "cn1", required = false) String cn1,
			@RequestParam(value = "cn2", required = false) String cn2,
			@RequestParam(value = "cn3", required = false) String cn3,

			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QMember target = QMember.member;

		if (identity != null) {
			builder.and(target.name.containsIgnoreCase(identity).or(target.memberId.containsIgnoreCase(identity)));
		}

		if (phone1 != null) {
			builder.and(target.phone.startsWith(phone1));
		}

		if (phone2 != null) {
			builder.and(target.phone.containsIgnoreCase("," + phone2 + ","));
		}

		if (phone3 != null) {
			builder.and(target.phone.endsWith(phone3));
		}

		if (email1 != null) {
			builder.and(target.email.startsWith(email1));
		}

		if (email2 != null) {
			builder.and(target.email.endsWith(email2));
		}

		if (cn1 != null) {
			builder.and(target.companyNoHistory.companyNo.startsWith(cn1));
		}

		if (cn2 != null) {
			builder.and(target.companyNoHistory.companyNo.containsIgnoreCase("," + cn2 + ","));
		}

		if (cn3 != null) {
			builder.and(target.companyNoHistory.companyNo.endsWith(cn3));
		}

		builder.and(target.useYn.eq("Y"));

		return return_success((Object) memberService.search(builder.getValue(), pageable));
	}

}