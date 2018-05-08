package net.heronation.zeyo.rest.repository.item_fit_info_option_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemFitInfoOptionMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemFitInfoOptionMap p) {
		log.debug("ItemFitInfoOptionMap Entity @HandleBeforeLinkSave Event");
	}

}