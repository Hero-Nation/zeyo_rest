package net.heronation.zeyo.rest.controller.item;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.repository.item.ItemBuildDto;
import net.heronation.zeyo.rest.repository.item.ItemModifyDto;
import net.heronation.zeyo.rest.repository.member.MemberRegisterDto;
@Slf4j
public class ItemModifyValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return ItemBuildDto.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		log.debug("ItemBuildValidator");
		log.debug(obj.toString());
		
		
//		ValidationUtils.rejectIfEmpty(e, "member_id", "member_id.empty"); 
 
		
		ItemModifyDto p = (ItemModifyDto) obj;
 
		
		
	}

}