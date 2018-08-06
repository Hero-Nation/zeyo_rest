package net.heronation.zeyo.rest.v2_rule.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class V2RuleDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) { 
		return true; 
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.debug("V2RuleDtoValidator : validate");
		// supports와 valid 주석이 제대로 동작하지 않은것같다.
		// 아래 명령어 꼼수로 해결한다. 
		log.debug(target.toString());
		log.debug(V2RuleDto.class+ " ");
		log.debug(target.getClass() + " ");
		log.debug(!V2RuleDto.class.equals(target.getClass()) + "");
		
		if (!V2RuleDto.class.equals(target.getClass()))  return;

		V2RuleDto dto = (V2RuleDto) target;
		if (dto.getId() < 0) {
			errors.rejectValue("id", "v2rule.id.empty");
		}
 
		if (dto.getFirstCt() == null) {
			errors.rejectValue("firstCt", "v2rule.first_ct.empty");
		}
 
		if (dto.getSecondCt() == null) {
			errors.rejectValue("secondCt", "v2rule.second_ct.empty");
		}
		
		if (dto.getFirstIncludeChild() == null) {
			errors.rejectValue("firstIncludeChild", "v2rule.first_include_child.empty");
		} 
		
		if (dto.getSecondIncludeChild() == null) {
			errors.rejectValue("secondIncludeChild", "v2rule.second_include_child.empty");
		}
		
		if (dto.getRuleMessage() == null) {
			errors.rejectValue("ruleMessage", "v2rule.rule_message.empty");
		}
		
		if (dto.getRuleType() == null) {
			errors.rejectValue("ruleType", "v2rule.rule_type.empty");
		}
 
		
	}

}
