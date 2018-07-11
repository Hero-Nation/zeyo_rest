package net.heronation.zeyo.rest.email_validation.repository; 

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterLinkDelete;
import org.springframework.data.rest.core.annotation.HandleAfterLinkSave;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

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