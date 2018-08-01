package net.heronation.zeyo.rest.dmodel.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DmodelDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		log.debug("DmodelDtoValidator : supports");
		return DmodelDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		log.debug("DmodelDtoValidator : validate"); 
		
		DmodelDto dto = (DmodelDto) target;
		if (dto.getId() < 0) {
			errors.rejectValue("id", "dmodel.id.negative");
		}

		if (dto.getTitle() == null) {
			errors.rejectValue("title", "dmodel.title.null");
		}
		
		if (dto.getController() == null) {
			errors.rejectValue("title", "dmodel.controller.null");
		}
		
		if (dto.getSvgdata() == null) {
			errors.rejectValue("title", "dmodel.svgdata.null");
		}
		
		
		if (dto.getSubCategorys().size() == 0) {
			errors.rejectValue("title", "dmodel.subCategorys.null");
		}
		
		if (dto.getDmodelMeasureMaps().size() == 0) {
			errors.rejectValue("title", "dmodel.dmodelMeasureMaps.null");
		}
		
		if (dto.getDmodelRatios().size() == 0) {
			errors.rejectValue("title", "dmodel.dmodelRatios.null");
		}

	}

}
