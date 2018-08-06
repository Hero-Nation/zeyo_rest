package net.heronation.zeyo.rest.fit_into_choice.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class FitIntoChoiceEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(FitIntoChoice p) {
		log.debug("FitIntoChoice Entity @HandleBeforeLinkSave Event");
	}

}