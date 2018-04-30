package net.heronation.zeyo.rest.repository.sub_category_measure_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class SubCategoryMeasureMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(SubCategoryMeasureMap p) {
		log.debug("SubCategoryMeasureMap Entity @HandleBeforeLinkSave Event");
	}

}