package net.heronation.zeyo.rest.controller.item;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
@Slf4j
public class ItemBuildValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return ItemBuildDto.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		log.debug("ItemBuildValidator");
		log.debug(obj.toString());
		
		
//		ValidationUtils.rejectIfEmpty(e, "member_id", "member_id.empty");
//		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
//		ValidationUtils.rejectIfEmpty(e, "email", "email.empty");
//		ValidationUtils.rejectIfEmpty(e, "phone", "phone.empty");
//		ValidationUtils.rejectIfEmpty(e, "password", "password.empty");
//		ValidationUtils.rejectIfEmpty(e, "manager", "manager.empty");
//		ValidationUtils.rejectIfEmpty(e, "manager_email", "manager_email.empty");
//		ValidationUtils.rejectIfEmpty(e, "manager_phone", "manager_phone.empty");
//		ValidationUtils.rejectIfEmpty(e, "company_name", "company_name.empty");
//		ValidationUtils.rejectIfEmpty(e, "company_no", "company_no.empty");
// 
		
		ItemBuildDto p = (ItemBuildDto) obj;
 
		
		
	}

}