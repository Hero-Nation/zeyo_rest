package net.heronation.zeyo.rest.brand.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class BrandEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Brand p) {
		log.debug("Brand Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Brand p) {
		log.debug("Brand Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Brand p) {
		log.debug("Brand Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Brand p) {
		log.debug("Brand Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Brand p) {
		log.debug("Brand Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Brand p) {
		log.debug("Brand Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Brand p) {
		log.debug("Brand Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Brand p) {
		log.debug("Brand Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Brand p) {
		log.debug("Brand Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Brand p) {
		log.debug("Brand Entity @HandleBeforeLinkSave Event");
	}

}