package net.heronation.zeyo.rest.item.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.item.repository.ItemBuildDto;

@Slf4j
public class ItemBuildValidator implements Validator {

	public void validate(Object obj, Errors e) {
		log.debug("ItemBuildValidator");
		log.debug(obj.toString());

		// ValidationUtils.rejectIfEmpty(e, "member_id", "member_id.empty");

		ItemBuildDto p = (ItemBuildDto) obj;

	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ItemBuildDto.class.equals(clazz);
	}

}