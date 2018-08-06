package net.heronation.zeyo.rest.dmodel.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DmodelDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 이유를 모르겠으나.. supports 가 올바로 동작하지 않음
//		log.debug("DmodelDtoValidator : supports");
//		log.debug(DmodelDto.class.equals(clazz)+"");
//		log.debug(clazz.getCanonicalName());
//		log.debug(clazz.getTypeName());
//		log.debug(clazz.getSimpleName());
		return true; 
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.debug("DmodelDtoValidator : validate");
		// supports와 valid 주석이 제대로 동작하지 않은것같다.
		// 아래 명령어 꼼수로 해결한다. 
		if (!DmodelDto.class.equals(target.getClass()))  return;

		DmodelDto dto = (DmodelDto) target;
		if (dto.getId() < 0) {
			errors.rejectValue("id", "dmodel.id.negative");
		}

		if (dto.getTitle() == null || dto.getTitle().trim().equals("")) {
			errors.rejectValue("title", "dmodel.title.empty");
		}

		if (dto.getController() == null || dto.getController().trim().equals("")) {
			errors.rejectValue("controller", "dmodel.controller.empty");
		}

		if (dto.getSvgdata() == null || dto.getSvgdata().trim().equals("")) {
			errors.rejectValue("svgdata", "dmodel.svgdata.empty");
		}
		
		if(dto.getSubCategorys().size() == 0) {
			errors.rejectValue("subCategorys", "dmodel.subCategorys.empty");
		}
		
		if(dto.getDmodelMeasureMaps().size() == 0) {
			errors.rejectValue("dmodelMeasureMaps", "dmodel.dmodelMeasureMaps.empty");
		}
		
		if(dto.getDmodelRatios().size() == 0) {
			errors.rejectValue("dmodelRatios", "dmodel.dmodelRatios.empty");
		}

	}

}
