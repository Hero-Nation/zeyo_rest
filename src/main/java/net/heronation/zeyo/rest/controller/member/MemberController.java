package net.heronation.zeyo.rest.controller.member;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.bbs.QBbs;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.MemberResourceAssembler;
import net.heronation.zeyo.rest.repository.member.QMember;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.validator.routines.EmailValidator;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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

	@RequestMapping(path = "/send_register_mail", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> send_register_mail(@RequestParam(name = "email", defaultValue = "") String email) {
		log.debug("/api/members/send_register_mail");

		if (email.equals("")) {
			return return_fail("email.empty");
		}else if(!EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.form.wrong");
		} else {
			try {
				return return_success(memberService.send_register_mail(email));
			} catch (Exception e) {
				return  return_fail("email.send.exception");
			}

		}

	}
	
	@RequestMapping(path = "/confirm_otp", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> confirm_otp(@RequestParam(name = "email", defaultValue = "") String email,@RequestParam(name = "otp", defaultValue = "") String otp) {
		log.debug("/api/members/confirm_otp");

		if (email == null && email.equals("")) {
			return return_fail("email.empty");
		}else if(otp == null && otp.equals("")) {
			return return_fail("otp.empty");
		}else if(!EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.form.wrong");
		} else {
			try {
				
				return return_success(memberService.confirm_otp(email,otp));
				
			} catch (Exception e) {
				
				if(e.getMessage().equals("EMAIL.NOT.EXIST")) {
					return  return_fail("email.not.exist.in.db");
				}else {
					return  return_fail("exception.throwed");	
				}
				
			}

		}

	}
 
	
	
	@RequestMapping(path = "/find_id_by_email", method = RequestMethod.GET)
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

	@RequestMapping(path = "/findByMemberId", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> findByMemberId(
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

	@RequestMapping(path = "/find_id_by_phone", method = RequestMethod.GET)
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "my_info", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> my_info(@AuthenticationPrincipal OAuth2Authentication auth) {
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

	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "user_info", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> user_info(@RequestParam(value = "member_id", required = false) String member_id) {
		log.debug("/api/members/user_info");

		Long id = Long.valueOf(String.valueOf(member_id));

		BooleanBuilder builder = new BooleanBuilder();

		QCompanyNoHistory target = QCompanyNoHistory.companyNoHistory;

		builder.and(target.member.id.eq(id));

		return return_success(memberService.getUserInfo(builder.getValue()));
	}

	@PreAuthorize("hasRole('ROLE_CLIENT') or hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "user_biz_info", method = RequestMethod.GET)
	public ResponseEntity<ResultVO> user_biz_info(
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
	public ResponseEntity<ResultVO> my_brand(@RequestParam(value = "member_id", required = false) String member_id,
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
	public ResponseEntity<ResultVO> my_shopmall(@RequestParam(value = "member_id", required = false) String member_id,
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
	public ResponseEntity<ResultVO> my_item(@RequestParam(value = "member_id", required = false) String member_id,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();
		Long id = Long.valueOf(String.valueOf(member_id));
		QItem target = QItem.item;
		builder.and(target.member.id.eq(id));
		builder.and(target.useYn.eq("Y"));

		return return_success(memberService.my_item(builder.getValue(), pageable));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
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

		Map<String,Object> param = new HashMap<String,Object>();
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
	public ResponseEntity<ResultVO> search_company_history(

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
	public ResponseEntity<ResultVO> update_phone(@RequestParam(name = "phone", defaultValue = "") String phone,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_phone");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (phone.equals("")) {
			return return_fail("phone.empty");
		} else {
			memberService.update_phone(phone, seq);
			return return_success();
		}

	}

	@RequestMapping(path = "/update_email", method = RequestMethod.PATCH)
	public ResponseEntity<ResultVO> update_email(@RequestParam(name = "email", defaultValue = "") String phone,
			@RequestParam(name = "confirm_no", defaultValue = "") String confirm_no,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_email");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (phone.equals("")) {
			return return_fail("email.empty");
		} else if (confirm_no.equals("")) {
			return return_fail("confirm_no.empty");
		} else {

			Member R = memberService.update_email(phone, confirm_no, seq);

			if (R == null) {
				return return_fail("confirm_no.wrong");
			} else {
				return return_success();
			}

		}

	}
 

	@RequestMapping(path = "/update_password", method = RequestMethod.PATCH)
	public ResponseEntity<ResultVO> update_password(@RequestParam(name = "password", defaultValue = "") String password,

			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_password");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (password.equals("")) {
			return return_fail("password.empty");
		} else {
			memberService.update_password(password, seq);
			return return_success();
		}

	}

	@RequestMapping(path = "/update_cp_no", method = RequestMethod.PATCH)
	public ResponseEntity<ResultVO> update_cp_no(@RequestParam(name = "cp_no", defaultValue = "") String cp_no,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_cp_no");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (cp_no.equals("")) {
			return return_fail("cp_no.empty");
		} else {
			memberService.update_cp_no(cp_no, seq);
			return return_success();
		}

	}

	@RequestMapping(path = "/update_mng_name", method = RequestMethod.PATCH)
	public ResponseEntity<ResultVO> update_mng_name(@RequestParam(name = "mng_name", defaultValue = "") String mng_name,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_mng_name");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (mng_name.equals("")) {
			return return_fail("mng_name.empty");
		} else {
			memberService.update_mng_name(mng_name, seq);
			return return_success();
		}

	}

	@RequestMapping(path = "/update_mng_phone", method = RequestMethod.PATCH)
	public ResponseEntity<ResultVO> update_mng_phone(
			@RequestParam(name = "mng_phone", defaultValue = "") String mng_phone,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/update_mng_phone");
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));

		if (mng_phone.equals("")) {
			return return_fail("mng_phone.empty");
		} else {
			memberService.update_mng_phone(mng_phone, seq);
			return return_success();
		}

	}

}