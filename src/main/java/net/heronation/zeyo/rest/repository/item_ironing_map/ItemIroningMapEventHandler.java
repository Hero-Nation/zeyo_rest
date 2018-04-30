package net.heronation.zeyo.rest.repository.item_ironing_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemIroningMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemIroningMap p) {
		log.debug("ItemIroningMap Entity @HandleBeforeLinkSave Event");
	}

}