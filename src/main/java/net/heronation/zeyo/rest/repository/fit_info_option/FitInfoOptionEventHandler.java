package net.heronation.zeyo.rest.repository.fit_info_option; 

import org.springframework.data.rest.core.annotation.*;

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