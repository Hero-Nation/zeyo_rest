package net.heronation.zeyo.rest.madein.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class MadeinEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Madein p) {
		log.debug("Madein Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Madein p) {
		log.debug("Madein Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Madein p) {
		log.debug("Madein Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Madein p) {
		log.debug("Madein Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Madein p) {
		log.debug("Madein Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Madein p) {
		log.debug("Madein Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Madein p) {
		log.debug("Madein Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Madein p) {
		log.debug("Madein Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Madein p) {
		log.debug("Madein Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Madein p) {
		log.debug("Madein Entity @HandleBeforeLinkSave Event");
	}

}