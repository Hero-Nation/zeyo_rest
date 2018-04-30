package net.heronation.zeyo.rest.repository.category; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class CategoryEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Category p) {
		log.debug("Category Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Category p) {
		log.debug("Category Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Category p) {
		log.debug("Category Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Category p) {
		log.debug("Category Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Category p) {
		log.debug("Category Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Category p) {
		log.debug("Category Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Category p) {
		log.debug("Category Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Category p) {
		log.debug("Category Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Category p) {
		log.debug("Category Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Category p) {
		log.debug("Category Entity @HandleBeforeLinkSave Event");
	}

}