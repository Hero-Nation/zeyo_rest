package net.heronation.zeyo.rest.repository.material; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class MaterialEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Material p) {
		log.debug("Material Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Material p) {
		log.debug("Material Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Material p) {
		log.debug("Material Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Material p) {
		log.debug("Material Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Material p) {
		log.debug("Material Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Material p) {
		log.debug("Material Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Material p) {
		log.debug("Material Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Material p) {
		log.debug("Material Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Material p) {
		log.debug("Material Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Material p) {
		log.debug("Material Entity @HandleBeforeLinkSave Event");
	}

}