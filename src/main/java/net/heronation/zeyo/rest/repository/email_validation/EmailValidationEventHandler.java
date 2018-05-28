package net.heronation.zeyo.rest.repository.email_validation; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class EmailValidationEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(EmailValidation p) {
		log.debug("EmailValidation Entity @HandleBeforeLinkSave Event");
	}

}