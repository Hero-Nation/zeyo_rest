package net.heronation.zeyo.rest.dmodel.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class DmodelEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Dmodel p) {
		log.debug("Dmodel Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Dmodel p) {
		log.debug("Dmodel Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Dmodel p) {
		log.debug("Dmodel Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Dmodel p) {
		log.debug("Dmodel Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Dmodel p) {
		log.debug("Dmodel Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Dmodel p) {
		log.debug("Dmodel Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Dmodel p) {
		log.debug("Dmodel Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Dmodel p) {
		log.debug("Dmodel Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Dmodel p) {
		log.debug("Dmodel Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Dmodel p) {
		log.debug("Dmodel Entity @HandleBeforeLinkSave Event");
	}

}