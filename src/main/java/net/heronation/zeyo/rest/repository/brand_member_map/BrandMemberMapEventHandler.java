package net.heronation.zeyo.rest.repository.brand_member_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class BrandMemberMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(BrandMemberMap p) {
		log.debug("BrandMemberMap Entity @HandleBeforeLinkSave Event");
	}

}