package net.heronation.zeyo.rest.dmodel_measure_map.repository; 

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
public class DmodelMeasureMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(DmodelMeasureMap p) {
		log.debug("DmodelMeasureMap Entity @HandleBeforeLinkSave Event");
	}

}