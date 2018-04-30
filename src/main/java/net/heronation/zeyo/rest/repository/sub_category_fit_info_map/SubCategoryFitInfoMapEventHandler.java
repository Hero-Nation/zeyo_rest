package net.heronation.zeyo.rest.repository.sub_category_fit_info_map; 

import org.springframework.data.rest.core.annotation.*;

import lombok.extern.slf4j.Slf4j;
 
@Slf4j
@RepositoryEventHandler
public class SubCategoryFitInfoMapEventHandler {

	@HandleAfterCreate
	public void handleAfterCreate(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleAfterCreate Event");
	}

	@HandleBeforeCreate
	public void HandleBeforeCreate(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleBeforeCreate Event");
	}

	@HandleAfterDelete
	public void handleAfterDelete(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleAfterDelete Event");
	}

	@HandleBeforeDelete
	public void HandleBeforeDelete(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleBeforeDelete Event");
	}

	@HandleAfterSave
	public void HandleAfterSave(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleAfterSave Event");
	}

	@HandleBeforeSave
	public void handleBeforeSave(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleBeforeSave Event");
	}

	@HandleAfterLinkDelete
	public void HandleAfterLinkDelete(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleAfterLinkDelete Event");
	}

	@HandleBeforeLinkDelete
	public void HandleBeforeLinkDelete(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleBeforeLinkDelete Event");
	}

	@HandleAfterLinkSave
	public void HandleAfterLinkSave(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleAfterLinkSave Event");
	}

	@HandleBeforeLinkSave
	public void HandleBeforeLinkSave(SubCategoryFitInfoMap p) {
		log.debug("SubCategoryFitInfoMap Entity @HandleBeforeLinkSave Event");
	}

}