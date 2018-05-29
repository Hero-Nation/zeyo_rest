package net.heronation.zeyo.rest.controller.category; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.CategoryResourceAssembler;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.category.CategoryService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/categorys")
public class CategoryController extends BaseController {
	
    @Autowired
    private CategoryService categoryService;
 
     @Autowired
    private CategoryRepository repository; 
     @Autowired
    private CategoryResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public CategoryController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	 
   
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "cate",required=false) String cate,
			@RequestParam(value = "subcate",required=false) String subcate,
			@RequestParam(value = "measure",required=false) String measure, 
			@RequestParam(value = "start",required=false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end",required=false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name); 
		param.put("cate", cate); 
		param.put("subcate", subcate); 
		param.put("measure", measure); 
		
		if(start == null) {
			param.put("start", start);	
		}else {
			param.put("start", start.toString(Format.ISO_DATETIME));
		}
		if(end == null) {
			param.put("end", end);	
		}else {
			param.put("end", end.toString(Format.ISO_DATETIME));
		}
		
		return return_success(categoryService.search(param, pageable));
	}

 
	@RequestMapping(method = RequestMethod.GET, value = "/pure_list")
	@ResponseBody
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ResultVO> pure_list(
			 Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();
		QCategory target = QCategory.category; 
		 
 
		builder.and(target.useYn.eq("Y"));
		
		return return_success(categoryService.pure_search(builder.getValue(), pageable));
	}
}