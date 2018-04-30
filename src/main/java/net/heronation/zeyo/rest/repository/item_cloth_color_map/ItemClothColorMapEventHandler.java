package net.heronation.zeyo.rest.repository.item_cloth_color_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ItemClothColorMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ItemClothColorMap p) {
		log.debug("ItemClothColorMap Entity @HandleBeforeLinkSave Event");
	}

}