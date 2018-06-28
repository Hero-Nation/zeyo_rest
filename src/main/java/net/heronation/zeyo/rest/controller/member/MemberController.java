package net.heronation.zeyo.rest.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.value.ResultDto;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberDto;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.MemberResourceAssembler;
import net.heronation.zeyo.rest.repository.member.QMember;
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

	@Autowired 
	private MemberRegisterValidator memberRegisterDtoValidator;

	@InitBinder("memberRegisterDto")
	protected void initBinder(WebDataBinder binder) {
		// binder.addValidators(new MemberRegisterValidator());

		binder.addValidators(memberRegisterDtoValidator);
	}

	@Autowired
	public MemberController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(path = "/registry", method = RequestMethod.POST)
	public ResponseEntity<ResultDto> registry(@Valid @RequestBody MemberRegisterDto memberRegisterDto,
			BindingResult bindingResult) {
		log.debug("/api/members/registry");

		if (bindingResult.hasErrors()) {
			return return_fail(bindingResult.getFieldError());
		} else {

			Member new_member = memberService.registry(memberRegisterDto);

			return return_success(new_member);
		}

	}

	@RequestMapping(path = "/confirm_otp", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> confirm_otp(@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "otp", defaultValue = "") String otp) {
		log.debug("/api/members/confirm_otp");

		if (email == null && email.equals("")) {
			return return_fail("email.empty");
		} else if (otp == null && otp.equals("")) {
			return return_fail("otp.empty");
		} else if (!EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.form.wrong");
		} else {
			try {

				return return_success(memberService.confirm_otp(email, otp));

			} catch (Exception e) {

				if (e.getMessage().equals("EMAIL.NOT.EXIST")) {
					return return_fail("email.not.exist.in.db");
				} else {
					return return_fail("exception.throwed");
				}

			}

		}

	}

	@RequestMapping(path = "/send_register_mail", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> send_register_mail(@RequestParam(name = "email", defaultValue = "") String email) {
		log.debug("/api/members/send_register_mail");

		if (email.equals("")) {
			return return_fail("email.empty");
		} else if (!EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.form.wrong");
		} else {
			try {
				return return_success(memberService.send_register_mail(email));
			} catch (Exception e) {
				return return_fail("email.send.exception");
			}

		}

	}

	@RequestMapping(path = "/send_confirm_email", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> send_confirm_email(@RequestParam(name = "email", defaultValue = "") String email) {
		log.debug("/api/members/send_confirm_email");

		if (email.equals("")) {
			return return_fail("email.empty");
		} else if (!EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.form.wrong");
		} else {
			try {
				return return_success(memberService.send_confirm_email(email));
			} catch (Exception e) {
				e.printStackTrace();
				return return_fail("email.send.exception");
			}

		}

	}

	@RequestMapping(path = "/find_id_by_email", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> find_id_by_email(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email) {
		log.debug("/api/members/find_id_by_email");

		if (name.equals("")) {
			return return_fail("name.empty");
		} else if (email.equals("")) {
			return return_fail("email.empty");
		} else if (!EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.pattern.wrong");
		} else {

			String r;
			try {
				r = memberService.find_id_by_email(name, email);
				
				if (r.equals("member.not.exist")) {
					return return_fail(r);
				} else {
					return return_success(r);
				}
				
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return return_fail("sending.email.error");
			}


		}

	}

	@RequestMapping(path = "/find_id_by_email_confirm", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> find_id_by_email_confirm(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "otp", defaultValue = "") String otp

	) {
		log.debug("/api/members/find_id_by_email_confirm");

		if (name.equals("")) {
			return return_fail("name.empty");
		} else if (email.equals("")) {
			return return_fail("email.empty");
		} else if (otp.equals("")) {
			return return_fail("otp.empty");
		} else if (!EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.pattern.wrong");
		} else {

			String r = memberService.find_id_by_email_confirm(name, email, otp);
			if (r.equals("member.not.exist") || r.equals("sended.mail.not.exist") || r.equals("otp.not.equal")) {
				return return_fail(r);
			} else {
				return return_success(r);
			}

		}

	}

	@RequestMapping(path = "/find_id_by_phone", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> find_id_by_phone(@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "phone", defaultValue = "") String phone) {
		log.debug("/api/members/find_id_by_phone");

		if (name.equals("")) {
			return return_fail("name.empty");
		} else if (phone.equals("")) {
			return return_fail("phone.empty");
		} else {

			String r = memberService.find_id_by_phone(name, phone);
			if (r.equals("member.not.exist")) {
				return return_fail(r);
			} else {
				return return_success(r);
			}

		}

	}

	@RequestMapping(path = "/find_id_by_phone_confirm", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> find_id_by_phone_confirm(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "phone", defaultValue = "") String phone,
			@RequestParam(name = "otp", defaultValue = "") String otp) {
		log.debug("/api/members/find_id_by_phone_confirm");

		if (name.equals("")) {
			return return_fail("name.empty");
		} else if (phone.equals("")) {
			return return_fail("phone.empty");
		} else if (otp.equals("")) {
			return return_fail("otp.empty");
		} else {

			String r = memberService.find_id_by_phone_confirm(name, phone, otp);
			if (r.equals("member.not.exist") || r.equals("sended.phone.not.exist") || r.equals("otp.not.equal")) {
				return return_fail(r);
			} else {
				return return_success(r);
			}

		}

	}

	@RequestMapping(path = "/findByMemberId", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> findByMemberId(
			@RequestParam(name = "member_id", defaultValue = "") String member_id) {
		log.debug("/api/members/find_id_by_email");

		if (member_id.equals("")) {
			return return_fail("member_id.empty");
		} else {
			Member user = memberService.findByMemberId(member_id);

			if (user == null) {
				return return_success("user.not.exist");
			} else {
				return return_success("user.exist");
			}

		}

	}

	@RequestMapping(path = "/find_password", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> find_password(
			@RequestParam(name = "member_id", defaultValue = "") String member_id,
			@RequestParam(name = "member_name", defaultValue = "") String member_name,
			@RequestParam(name = "member_email", defaultValue = "") String member_email) {
		log.debug("/api/members/find_password");

		if (member_id.equals("")) {
			return return_fail("member_id.empty");
		} else if (member_name.equals("")) {
			return return_fail("member_name.empty");
		} else if (member_email.equals("")) {
			return return_fail("member_email.empty");
		} else if (!EmailValidator.getInstance().isValid(member_email)) {
			return return_fail("member_email.pattern.wrong");
		} else {

			String r = memberService.find_password(member_id, member_name, member_email);
			if (r.equals("member.not.exist")) {
				return return_fail(r);
			} else {
				return return_success(r);
			}

		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(path = "my_info", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> my_info(@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/my_info");

		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		BooleanBuilder builder = new BooleanBuilder();

		QMember target = QMember.member;

		builder.and(target.id.eq(seq));

		return return_success(memberService.getUserInfo(builder.getValue()));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(path = "user_info", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> user_info(@RequestParam(value = "member_id", required = false) String member_id) {
		log.debug("/api/members/user_info");

		Long id = Long.valueOf(String.valueOf(member_id));

		BooleanBuilder builder = new BooleanBuilder();

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		builder.and(target.member.id.eq(id));

		return return_success(memberService.getUserInfo(builder.getValue()));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT')")
	@RequestMapping(path = "toggle_email_noti", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> toggle_email_noti(

			@RequestBody MemberDto param,

			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/toggle_email_noti");

		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		if (param.getFlag() == null || param.getFlag().equals("")) {
			return return_fail("flag.empty");
		} else {

			Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

			return return_success(memberService.toggle_email_noti(seq, param));

		}

	}

	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "user_biz_info", method = RequestMethod.GET)
	public ResponseEntity<ResultDto> user_biz_info(
			@RequestParam(value = "member_id", required = false) String member_id) {
		log.debug("/api/members/user_biz_info");

		Long id = Long.valueOf(String.valueOf(member_id));

		BooleanBuilder builder = new BooleanBuilder();

		QMember target = QMember.member;

		builder.and(target.id.eq(id));

		return return_success(memberService.getUserBizInfo(builder.getValue()));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/my_brand")
	@ResponseBody
	public ResponseEntity<ResultDto> my_brand(@RequestParam(value = "member_id", required = false) String member_id,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();
		Long id = Long.valueOf(String.valueOf(member_id));
		QBrand target = QBrand.brand;

		builder.and(target.member.id.eq(id));

		builder.and(target.member.useYn.eq("Y"));

		return return_success(memberService.my_brand(builder.getValue(), pageable));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/my_shopmall")
	@ResponseBody
	public ResponseEntity<ResultDto> my_shopmall(@RequestParam(value = "member_id", required = false) String member_id,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();
		Long id = Long.valueOf(String.valueOf(member_id));
		QBrand target = QBrand.brand;

		builder.and(target.member.id.eq(id));

		builder.and(target.useYn.eq("Y"));

		return return_success(memberService.my_shopmall(builder.getValue(), pageable));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/my_item")
	@ResponseBody
	public ResponseEntity<ResultDto> my_item(@RequestParam(value = "member_id", required = false) String member_id,
			Pageable pageable) {

 
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("member_id", member_id); 

		return return_success(memberService.my_item(param, pageable));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultDto> list(@RequestParam(value = "identity", required = false) String identity,

			@RequestParam(value = "phone1", required = false) String phone1,
			@RequestParam(value = "phone2", required = false) String phone2,
			@RequestParam(value = "phone3", required = false) String phone3,

			@RequestParam(value = "email1", required = false) String email1,
			@RequestParam(value = "email2", required = false) String email2,

			@RequestParam(value = "cn1", required = false) String cn1,
			@RequestParam(value = "cn2", required = false) String cn2,
			@RequestParam(value = "cn3", required = false) String cn3,

			Pageable pageable) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("identity", identity);
		param.put("phone1", phone1);
		param.put("phone2", phone2);
		param.put("phone3", phone3);

		param.put("email1", email1);
		param.put("email2", email2);

		param.put("cn1", cn1);
		param.put("cn2", cn2);
		param.put("cn3", cn3);

		return return_success(memberService.search(param, pageable));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search_company_history")
	@ResponseBody
	public ResponseEntity<ResultDto> search_company_history(

			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "cn1", required = false) String cn1,
			@RequestParam(value = "cn2", required = false) String cn2,
			@RequestParam(value = "cn3", required = false) String cn3,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,

			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		if (name != null) {
			builder.and(target.name.containsIgnoreCase(name));
		}

		if (cn1 != null) {
			builder.and(target.companyNo.startsWith(cn1));
		}

		if (cn2 != null) {
			builder.and(target.companyNo.containsIgnoreCase("," + cn2 + ","));
		}

		if (cn3 != null) {
			builder.and(target.companyNo.endsWith(cn3));
		}

		return return_success(memberService.cn_history(builder.getValue(), pageable));
	}

	@RequestMapping(path = "/update_phone", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> update_phone(@RequestBody PasswordUpdateDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_phone");

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (param.getPhone() == null || param.getPhone().equals("")) {
			return return_fail("phone.empty");
		} else {
			memberService.update_phone(param, seq);
			return return_success();
		}

	}

	@RequestMapping(path = "/update_email", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> update_email(@RequestBody EmailUpdateVO param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_email");

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (param.getEmail() == null || param.getEmail().equals("")) {
			return return_fail("email.empty");
		} else if (param.getConfirm_no() == null || param.getConfirm_no().equals("")) {
			return return_fail("confirm_no.empty");
		} else {

			String R = memberService.update_email(param, seq);

			if (R.equals(CommonConstants.FAIL)) {
				return return_fail("confirm_no.wrong");
			} else {
				return return_success(CommonConstants.SUCCESS);
			}

		}

	}

	@RequestMapping(path = "/update_password", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> update_password(@RequestBody PasswordUpdateVO param,

			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_password");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		String old_pw = param.getOld_pw();
		String new_pw = param.getNew_pw();

		if (old_pw == null || old_pw.equals("")) {
			return return_fail("old_pw.empty");
		} else if (new_pw == null || new_pw.equals("")) {
			return return_fail("new_pw.empty");
		} else {
			String update_result = memberService.update_password(old_pw, new_pw, seq);
			if (update_result.equals(CommonConstants.SUCCESS)) {
				return return_success(update_result);
			} else {
				return return_fail(update_result);
			}

		}

	}

	@RequestMapping(path = "/update_cp_no", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> update_cp_no(@RequestBody CpNoUpdateDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_cp_no");

		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (param.getCp_no() == null || param.getCp_no().equals("")) {
			return return_fail("cp_no.empty");
		} else {
			memberService.update_cp_no(param, seq);
			return return_success();
		}

	}

	@RequestMapping(path = "/update_mng_name", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> update_mng_name(

			@RequestBody MngNameUpdateDto param, @AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_mng_name");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		String manager = param.getMng_name();

		if (manager.equals("")) {
			return return_fail("mng_name.empty");
		} else {
			memberService.update_mng_name(manager, seq);
			return return_success();
		}

	}

	@RequestMapping(path = "/update_mng_phone", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> update_mng_phone(@RequestBody MngPhoneUpdateDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_mng_phone");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		String managerPhone = param.getMng_phone();

		if (managerPhone.equals("")) {
			return return_fail("mng_phone.empty");
		} else {
			memberService.update_mng_phone(managerPhone, seq);
			return return_success();
		}

	}
	
	
	
	@RequestMapping(path = "/admin_update", method = RequestMethod.PATCH)
	public ResponseEntity<ResultDto> admin_update(@RequestBody AdminUpdateDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) { 
		log.debug("/api/members/admin_update");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
 
		try {
			return return_success(memberService.admin_update(param));
		}catch(Exception e) {
			return return_fail(e);
		}
		
		

	}

}