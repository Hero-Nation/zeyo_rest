package net.heronation.zeyo.rest.repository.bbs; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class BbsEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Bbs p) {
		log.debug("Bbs Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Bbs p) {
		log.debug("Bbs Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Bbs p) {
		log.debug("Bbs Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Bbs p) {
		log.debug("Bbs Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Bbs p) {
		log.debug("Bbs Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Bbs p) {
		log.debug("Bbs Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Bbs p) {
		log.debug("Bbs Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Bbs p) {
		log.debug("Bbs Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Bbs p) {
		log.debug("Bbs Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Bbs p) {
		log.debug("Bbs Entity @HandleBeforeLinkSave Event");
	}

}