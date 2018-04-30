package net.heronation.zeyo.rest.repository.warranty; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class WarrantyEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Warranty p) {
		log.debug("Warranty Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Warranty p) {
		log.debug("Warranty Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Warranty p) {
		log.debug("Warranty Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Warranty p) {
		log.debug("Warranty Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Warranty p) {
		log.debug("Warranty Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Warranty p) {
		log.debug("Warranty Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Warranty p) {
		log.debug("Warranty Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Warranty p) {
		log.debug("Warranty Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Warranty p) {
		log.debug("Warranty Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Warranty p) {
		log.debug("Warranty Entity @HandleBeforeLinkSave Event");
	}

}