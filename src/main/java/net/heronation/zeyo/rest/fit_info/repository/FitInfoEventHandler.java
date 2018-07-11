package net.heronation.zeyo.rest.fit_info.repository; 

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
public class FitInfoEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(FitInfo p) {
		log.debug("FitInfo Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(FitInfo p) {
		log.debug("FitInfo Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(FitInfo p) {
		log.debug("FitInfo Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(FitInfo p) {
		log.debug("FitInfo Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(FitInfo p) {
		log.debug("FitInfo Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(FitInfo p) {
		log.debug("FitInfo Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(FitInfo p) {
		log.debug("FitInfo Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(FitInfo p) {
		log.debug("FitInfo Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(FitInfo p) {
		log.debug("FitInfo Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(FitInfo p) {
		log.debug("FitInfo Entity @HandleBeforeLinkSave Event");
	}

}