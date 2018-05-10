package net.heronation.zeyo.rest.controller.category; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.CategoryResourceAssembler;
import net.heronation.zeyo.rest.repository.category.QCategory;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "cate",required=false) Long cate,
			@RequestParam(value = "subcate",required=false) Long subcate,
			@RequestParam(value = "measure",required=false) String measure, 
			@RequestParam(value = "start",required=false) Date start,
			@RequestParam(value = "end",required=false) Date end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		if (name != null) {
			builder.and(QCategory.category.name.containsIgnoreCase(name));
		}

		if (start != null) {
			builder.and(QCategory.category.createDt.after(start));
		}

		if (end != null) {
			builder.and(QCategory.category.createDt.before(end));
		}

		builder.and(QCategory.category.useYn.eq("Y"));
		
		return return_success((Object) categoryService.search(builder.getValue(), pageable));
	}

 
}