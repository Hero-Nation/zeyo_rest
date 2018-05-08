package net.heronation.zeyo.rest.controller.member; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.member.Member;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
import net.heronation.zeyo.rest.repository.member.MemberRepository;
import net.heronation.zeyo.rest.repository.member.MemberResourceAssembler;

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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
 
	@RequestMapping(path = "/registry",method=RequestMethod.POST)
	public ResponseEntity<ResultVO> registry(@ModelAttribute("memberRegisterDto") @Valid MemberRegisterDto memberRegisterDto, BindingResult bindingResult) {
		log.debug("/api/members/registry");
		
		if(bindingResult.hasErrors()) {
			return return_fail(bindingResult.getFieldError());
        }else {
        
        	Member new_member = memberService.registry(memberRegisterDto);
        	
        	return return_success(new_member);
        }
		
	}


	@RequestMapping(path = "/send_register_mail",method=RequestMethod.POST)
	public ResponseEntity<ResultVO> send_register_mail(@RequestParam(name="email",defaultValue="") String email) {
		log.debug("/api/members/send_register_mail");
		
		if(email.equals("")) {
			return return_fail("email.empty");
		}else {
			return return_success();	
		} 
		
	}


	@RequestMapping(path = "/find_id_by_email",method=RequestMethod.POST)
	public ResponseEntity<ResultVO> find_id_by_email(
			@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="email",defaultValue="") String email
			) {
		log.debug("/api/members/find_id_by_email");
		
		if(name.equals("")) {
			return return_fail("name.empty");
		}else if(email.equals("")) {
			return return_fail("email.empty");
		}else if(EmailValidator.getInstance().isValid(email)) {
			return return_fail("email.pattern.wrong");
		}else{
			return return_success();	
		} 
		
	}
	
	@RequestMapping(path = "/find_id_by_phone",method=RequestMethod.POST)
	public ResponseEntity<ResultVO> find_id_by_phone(
			@RequestParam(name="name",defaultValue="") String name,
			@RequestParam(name="phone",defaultValue="") String phone
			) {
		log.debug("/api/members/find_id_by_phone");
		
		if(name.equals("")) {
			return return_fail("name.empty");
		}else if(phone.equals("")) {
			return return_fail("phone.empty");
		}else{
			return return_success();	
		} 
		
	}
 
	@RequestMapping(path = "/confirm_num",method=RequestMethod.GET)
	public ResponseEntity<ResultVO> confirm_num(
			@RequestParam(name="confirm_num",defaultValue="") String confirm_num 
			) {
		log.debug("/api/members/confirm_num");
		
		if(confirm_num.equals("")) {
			return return_fail("confirm_num.empty");
		} else{
			return return_success();	
		} 
		
	}
	
	@RequestMapping(path = "/find_password",method=RequestMethod.GET)
	public ResponseEntity<ResultVO> find_password(
			@RequestParam(name="member_id",defaultValue="") String member_id,
			@RequestParam(name="member_name",defaultValue="") String member_name,
			@RequestParam(name="member_email",defaultValue="") String member_email
			) {
		log.debug("/api/members/find_password");
		
		if(member_id.equals("")) {
			return return_fail("member_id.empty");
		}else if(member_name.equals("")) {
			return return_fail("member_name.empty");
		}else if(member_email.equals("")) {
			return return_fail("member_email.empty");
		} else if(EmailValidator.getInstance().isValid(member_email)) {
			return return_fail("member_email.pattern.wrong");
		}  else{
			return return_success();	
		} 
		
	}
	
	
	// 개발용 api
	@RequestMapping(path = "info")
	public ResponseEntity<ResultVO> info(@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/members/info");
 
		// 유저 정보 가지고 오기 
		Map<String,Object> user =  (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails()).getDecodedDetails();
 
		return return_success(user);
	}
	
}