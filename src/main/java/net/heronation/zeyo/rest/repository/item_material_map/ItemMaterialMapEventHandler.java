package net.heronation.zeyo.rest.repository.item_material_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemMaterialMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemMaterialMap p) {
		log.debug("ItemMaterialMap Entity @HandleBeforeLinkSave Event");
	}

}