package net.heronation.zeyo.rest.repository.consumer; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class ConsumerEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(Consumer p) {
		log.debug("Consumer Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(Consumer p) {
		log.debug("Consumer Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(Consumer p) {
		log.debug("Consumer Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(Consumer p) {
		log.debug("Consumer Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(Consumer p) {
		log.debug("Consumer Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(Consumer p) {
		log.debug("Consumer Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(Consumer p) {
		log.debug("Consumer Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(Consumer p) {
		log.debug("Consumer Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(Consumer p) {
		log.debug("Consumer Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(Consumer p) {
		log.debug("Consumer Entity @HandleBeforeLinkSave Event");
	}

}