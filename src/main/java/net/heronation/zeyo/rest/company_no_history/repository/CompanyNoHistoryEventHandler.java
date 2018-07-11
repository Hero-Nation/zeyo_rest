package net.heronation.zeyo.rest.company_no_history.repository; 

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
public class CompanyNoHistoryEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(CompanyNoHistory p) {
		log.debug("CompanyNoHistory Entity @HandleBeforeLinkSave Event");
	}

}