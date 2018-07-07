package net.heronation.zeyo.rest.repository.bbs;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

 

public class BbsClientValidator implements Validator {

	@Override
	public boolean supports(Class clazz) {
		return BbsClientInsertDto.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		
		ValidationUtils.rejectIfEmpty(e, "title", "title.empty");
		ValidationUtils.rejectIfEmpty(e, "bbsContent", "bbsContent.empty"); 
		
		BbsClientInsertDto p = (BbsClientInsertDto) obj;  
	}

}