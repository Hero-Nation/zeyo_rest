package net.heronation.zeyo.rest.dmodel_ratio.repository; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class DmodelRatioEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(DmodelRatio p) {
		log.debug("DmodelRatio Entity @HandleBeforeLinkSave Event");
	}

}