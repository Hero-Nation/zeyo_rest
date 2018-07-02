package net.heronation.zeyo.rest.repository.ip_temp_info; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class IpTempInfoEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(IpTempInfo p) {
		log.debug("IpTempInfo Entity @HandleBeforeLinkSave Event");
	}

}