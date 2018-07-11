package net.heronation.zeyo.rest.size_option.repository; 

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
public class SizeOptionEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(SizeOption p) {
		log.debug("SizeOption Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(SizeOption p) {
		log.debug("SizeOption Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(SizeOption p) {
		log.debug("SizeOption Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(SizeOption p) {
		log.debug("SizeOption Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(SizeOption p) {
		log.debug("SizeOption Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(SizeOption p) {
		log.debug("SizeOption Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(SizeOption p) {
		log.debug("SizeOption Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(SizeOption p) {
		log.debug("SizeOption Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(SizeOption p) {
		log.debug("SizeOption Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(SizeOption p) {
		log.debug("SizeOption Entity @HandleBeforeLinkSave Event");
	}

}