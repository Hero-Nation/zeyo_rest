package net.heronation.zeyo.rest.item.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Item p) {
		log.debug("Item Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Item p) {
		log.debug("Item Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Item p) {
		log.debug("Item Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Item p) {
		log.debug("Item Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Item p) {
		log.debug("Item Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Item p) {
		log.debug("Item Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Item p) {
		log.debug("Item Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Item p) {
		log.debug("Item Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Item p) {
		log.debug("Item Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Item p) {
		log.debug("Item Entity @HandleBeforeLinkSave Event");
	}

}