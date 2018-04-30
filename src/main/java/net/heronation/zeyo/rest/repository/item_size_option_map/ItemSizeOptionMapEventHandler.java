package net.heronation.zeyo.rest.repository.item_size_option_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemSizeOptionMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemSizeOptionMap p) {
		log.debug("ItemSizeOptionMap Entity @HandleBeforeLinkSave Event");
	}

}