package net.heronation.zeyo.rest.kindof.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class KindofEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Kindof p) {
		log.debug("Kindof Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Kindof p) {
		log.debug("Kindof Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Kindof p) {
		log.debug("Kindof Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Kindof p) {
		log.debug("Kindof Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Kindof p) {
		log.debug("Kindof Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Kindof p) {
		log.debug("Kindof Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Kindof p) {
		log.debug("Kindof Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Kindof p) {
		log.debug("Kindof Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Kindof p) {
		log.debug("Kindof Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Kindof p) {
		log.debug("Kindof Entity @HandleBeforeLinkSave Event");
	}

}