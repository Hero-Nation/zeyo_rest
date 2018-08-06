package net.heronation.zeyo.rest.measure_item.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class MeasureItemEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(MeasureItem p) {
		log.debug("MeasureItem Entity @HandleBeforeLinkSave Event");
	}

}