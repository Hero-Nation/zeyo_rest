package net.heronation.zeyo.rest.repository.item_scmm_so_value; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemScmmSoValueEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemScmmSoValue p) {
		log.debug("ItemScmmSoValue Entity @HandleBeforeLinkSave Event");
	}

}