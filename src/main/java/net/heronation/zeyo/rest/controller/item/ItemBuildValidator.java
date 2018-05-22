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
 
		
		ItemBuildDto p = (ItemBuildDto) obj;
 
		
		
	}

}