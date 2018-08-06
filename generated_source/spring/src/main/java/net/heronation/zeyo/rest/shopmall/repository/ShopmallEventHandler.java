package net.heronation.zeyo.rest.shopmall.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ShopmallEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Shopmall p) {
		log.debug("Shopmall Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Shopmall p) {
		log.debug("Shopmall Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Shopmall p) {
		log.debug("Shopmall Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Shopmall p) {
		log.debug("Shopmall Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Shopmall p) {
		log.debug("Shopmall Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Shopmall p) {
		log.debug("Shopmall Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Shopmall p) {
		log.debug("Shopmall Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Shopmall p) {
		log.debug("Shopmall Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Shopmall p) {
		log.debug("Shopmall Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Shopmall p) {
		log.debug("Shopmall Entity @HandleBeforeLinkSave Event");
	}

}