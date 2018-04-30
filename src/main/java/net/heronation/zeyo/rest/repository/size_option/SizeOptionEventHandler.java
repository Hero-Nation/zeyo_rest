package net.heronation.zeyo.rest.repository.size_option; 

import org.springframework.data.rest.core.annotation.*;

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