package net.heronation.zeyo.rest.repository.size_table; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class SizeTableEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(SizeTable p) {
		log.debug("SizeTable Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(SizeTable p) {
		log.debug("SizeTable Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(SizeTable p) {
		log.debug("SizeTable Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(SizeTable p) {
		log.debug("SizeTable Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(SizeTable p) {
		log.debug("SizeTable Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(SizeTable p) {
		log.debug("SizeTable Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(SizeTable p) {
		log.debug("SizeTable Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(SizeTable p) {
		log.debug("SizeTable Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(SizeTable p) {
		log.debug("SizeTable Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(SizeTable p) {
		log.debug("SizeTable Entity @HandleBeforeLinkSave Event");
	}

}