package net.heronation.zeyo.rest.controller.measure_item; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItemRepository;
import net.heronation.zeyo.rest.repository.measure_item.MeasureItemResourceAssembler;
import net.heronation.zeyo.rest.repository.measure_item.QMeasureItem;
import net.heronation.zeyo.rest.repository.warranty.QWarranty;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.measure_item.MeasureItemService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/measure_items")
public class MeasureItemController extends BaseController {
	
    @Autowired
    private MeasureItemService measure_itemService;
 
     @Autowired
    private MeasureItemRepository repository; 
     @Autowired
    private MeasureItemResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public MeasureItemController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 
 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "start",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime start,
			@RequestParam(value = "end",required=false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		if (name != null) {
			builder.and(QMeasureItem.measureItem.name.containsIgnoreCase(name));
		}

		if (start != null) {
			builder.and(QMeasureItem.measureItem.createDt.after(start));
		}

		if (end != null) {
			builder.and(QMeasureItem.measureItem.createDt.before(end));
		}

		builder.and(QMeasureItem.measureItem.useYn.eq("Y"));
		
		return return_success((Object) measure_itemService.search(builder.getValue(), pageable));
	}

 
}