package net.heronation.zeyo.rest.repository.member; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class MemberEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Member p) {
		log.debug("Member Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Member p) {
		log.debug("Member Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Member p) {
		log.debug("Member Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Member p) {
		log.debug("Member Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Member p) {
		log.debug("Member Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Member p) {
		log.debug("Member Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Member p) {
		log.debug("Member Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Member p) {
		log.debug("Member Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Member p) {
		log.debug("Member Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Member p) {
		log.debug("Member Entity @HandleBeforeLinkSave Event");
	}

}