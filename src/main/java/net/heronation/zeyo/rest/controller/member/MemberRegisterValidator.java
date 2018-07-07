package net.heronation.zeyo.rest.controller.member;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;

@Slf4j
@Component
public class MemberRegisterValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		log.debug("MemberRegisterValidator   supports");
		log.debug(clazz.getName());
		log.debug(clazz.getCanonicalName());
		log.debug(MemberRegisterDto.class.equals(clazz) + " ");
		log.debug(MemberRegisterDto.class.isAssignableFrom(clazz) + " ");
		
		
		return MemberRegisterDto.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		log.debug("MemberRegisterValidator");
		log.debug(obj.toString());

		ValidationUtils.rejectIfEmpty(e, "member_id", "member_id.empty");
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		ValidationUtils.rejectIfEmpty(e, "email", "email.empty");
		ValidationUtils.rejectIfEmpty(e, "phone", "phone.empty");
		ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
		ValidationUtils.rejectIfEmpty(e, "manager", "manager.empty");
		ValidationUtils.rejectIfEmpty(e, "manager_email", "manager_email.empty");
		ValidationUtils.rejectIfEmpty(e, "manager_phone", "manager_phone.empty");
		ValidationUtils.rejectIfEmpty(e, "company_name", "company_name.empty");
		ValidationUtils.rejectIfEmpty(e, "company_no", "company_no.empty");

		MemberRegisterDto p = (MemberRegisterDto) obj;

	}

}