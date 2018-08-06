package net.heronation.zeyo.rest.v2_rule.repository; 

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
public class V2RuleEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(V2Rule p) {
		log.debug("V2Rule Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(V2Rule p) {
		log.debug("V2Rule Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(V2Rule p) {
		log.debug("V2Rule Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(V2Rule p) {
		log.debug("V2Rule Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(V2Rule p) {
		log.debug("V2Rule Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(V2Rule p) {
		log.debug("V2Rule Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(V2Rule p) {
		log.debug("V2Rule Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(V2Rule p) {
		log.debug("V2Rule Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(V2Rule p) {
		log.debug("V2Rule Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(V2Rule p) {
		log.debug("V2Rule Entity @HandleBeforeLinkSave Event");
	}

}