package net.heronation.zeyo.rest.repository.shopmall_member_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ShopmallMemberMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(ShopmallMemberMap p) {
		log.debug("ShopmallMemberMap Entity @HandleBeforeLinkSave Event");
	}

}