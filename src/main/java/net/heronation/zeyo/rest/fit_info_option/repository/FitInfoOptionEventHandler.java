package net.heronation.zeyo.rest.fit_info_option.repository; 

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
public class FitInfoOptionEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(FitInfoOption p) {
		log.debug("FitInfoOption Entity @HandleBeforeLinkSave Event");
	}

}