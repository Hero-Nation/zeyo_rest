package net.heronation.zeyo.rest.repository.item_drycleaning_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemDrycleaningMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemDrycleaningMap p) {
		log.debug("ItemDrycleaningMap Entity @HandleBeforeLinkSave Event");
	}

}