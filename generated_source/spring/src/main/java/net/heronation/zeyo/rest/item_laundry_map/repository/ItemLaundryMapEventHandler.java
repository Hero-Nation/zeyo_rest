package net.heronation.zeyo.rest.item_laundry_map.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemLaundryMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemLaundryMap p) {
		log.debug("ItemLaundryMap Entity @HandleBeforeLinkSave Event");
	}

}