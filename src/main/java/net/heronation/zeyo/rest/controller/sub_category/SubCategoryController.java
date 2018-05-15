package net.heronation.zeyo.rest.controller.sub_category; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryResourceAssembler;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.sub_category.SubCategoryService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/sub_categorys")
public class SubCategoryController extends BaseController {
	
    @Autowired
    private SubCategoryService sub_categoryService;
 
     @Autowired
    private SubCategoryRepository repository; 
     @Autowired
    private SubCategoryResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public SubCategoryController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 
 
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "cate",required=false) Category cate,
			@RequestParam(value = "subcate",required=false) SubCategory subcate,
			@RequestParam(value = "measure",required=false) MeasureItem measure, 
			@RequestParam(value = "start",required=false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end",required=false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		if (name != null) {
			builder.and(QSubCategory.subCategory.category.name.containsIgnoreCase(name));
		}
		
		if (cate != null) {
			builder.and(QSubCategory.subCategory.category.eq(cate));
		}
		
		if (subcate != null) {
			builder.and(QSubCategory.subCategory.eq(subcate));
		}
		
//		if (measure != null) {
//			builder.and(QSubCategory.subCategory.subCategoryMeasureMaps..eq(measure));
//		}
		
		if (start != null) {
			builder.and(QSubCategory.subCategory.createDt.after(start));
		}

		if (end != null) {
			builder.and(QSubCategory.subCategory.createDt.before(end));
		}

		builder.and(QSubCategory.subCategory.useYn.eq("Y"));
		
		return return_success((Object) sub_categoryService.search(builder.getValue(), pageable));
	}

 
	
	@RequestMapping(method = RequestMethod.GET, value = "/sublist")
	@ResponseBody
	public ResponseEntity<ResultVO> sublist(
			@RequestParam(value = "name",required=false) String name, 
			@RequestParam(value = "start",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime start,
			@RequestParam(value = "end",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		if (name != null) {
			builder.and(QSubCategory.subCategory.name.containsIgnoreCase(name));
		}
 
		if (start != null) {
			builder.and(QSubCategory.subCategory.createDt.after(start));
		}

		if (end != null) {
			builder.and(QSubCategory.subCategory.createDt.before(end));
		}

		builder.and(QSubCategory.subCategory.useYn.eq("Y"));
		
		return return_success((Object) sub_categoryService.search(builder.getValue(), pageable));
	}
 
 
}