package net.heronation.zeyo.rest.repository.sub_category; 

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
public class SubCategoryEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(SubCategory p) {
		log.debug("SubCategory Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(SubCategory p) {
		log.debug("SubCategory Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(SubCategory p) {
		log.debug("SubCategory Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(SubCategory p) {
		log.debug("SubCategory Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(SubCategory p) {
		log.debug("SubCategory Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(SubCategory p) {
		log.debug("SubCategory Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(SubCategory p) {
		log.debug("SubCategory Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(SubCategory p) {
		log.debug("SubCategory Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(SubCategory p) {
		log.debug("SubCategory Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(SubCategory p) {
		log.debug("SubCategory Entity @HandleBeforeLinkSave Event");
	}

}