package net.heronation.zeyo.rest.repository.item_bleach_map; 

import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterLinkDelete;
import org.springframework.data.rest.core.annotation.HandleAfterLinkSave;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkDelete;
import org.springframework.data.rest.core.annotation.HandleBeforeLinkSave;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemBleachMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemBleachMap p) {
		log.debug("ItemBleachMap Entity @HandleBeforeLinkSave Event");
	}

}