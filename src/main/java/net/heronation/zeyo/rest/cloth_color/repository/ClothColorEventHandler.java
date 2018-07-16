package net.heronation.zeyo.rest.cloth_color.repository; 

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
public class ClothColorEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ClothColor p) {
		log.debug("ClothColor Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ClothColor p) {
		log.debug("ClothColor Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ClothColor p) {
		log.debug("ClothColor Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ClothColor p) {
		log.debug("ClothColor Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ClothColor p) {
		log.debug("ClothColor Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ClothColor p) {
		log.debug("ClothColor Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ClothColor p) {
		log.debug("ClothColor Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ClothColor p) {
		log.debug("ClothColor Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ClothColor p) {
		log.debug("ClothColor Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ClothColor p) {
		log.debug("ClothColor Entity @HandleBeforeLinkSave Event");
	}

}