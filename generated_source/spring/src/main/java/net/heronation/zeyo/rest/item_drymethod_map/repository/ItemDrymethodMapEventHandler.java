package net.heronation.zeyo.rest.item_drymethod_map.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemDrymethodMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemDrymethodMap p) {
		log.debug("ItemDrymethodMap Entity @HandleBeforeLinkSave Event");
	}

}