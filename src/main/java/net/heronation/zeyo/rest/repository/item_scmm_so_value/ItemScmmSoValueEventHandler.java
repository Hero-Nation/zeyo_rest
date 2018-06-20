package net.heronation.zeyo.rest.repository.item_scmm_so_value; 

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