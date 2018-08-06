package net.heronation.zeyo.rest.item_shopmall_map.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemShopmallMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemShopmallMap p) {
		log.debug("ItemShopmallMap Entity @HandleBeforeLinkSave Event");
	}

}