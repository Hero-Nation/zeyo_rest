package net.heronation.zeyo.rest.controller.size_table; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;
import net.heronation.zeyo.rest.repository.size_table.SizeTableResourceAssembler;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.size_table.SizeTableService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/size_tables")
public class SizeTableController extends BaseController {
	
    @Autowired
    private SizeTableService size_tableService;
 
     @Autowired
    private SizeTableRepository repository; 
     @Autowired
    private SizeTableResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public SizeTableController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

 
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "company", required = false) String company,
			@RequestParam(value = "brand", required = false) String brand,
			@RequestParam(value = "shopmall", required = false) String shopmall,
		 
			@RequestParam(value = "start_price",  required = false) String start_price,
			@RequestParam(value = "end_price", required = false) String end_price,
			@RequestParam(value = "size_table", required = false) String size_table,
			
			@RequestParam(value = "start", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end, Pageable pageable) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name);
		param.put("company", company);
		param.put("brand", brand);
		param.put("shopmall", shopmall);  
		param.put("start_price", start_price); 
		param.put("end_price", end_price);   
		param.put("size_table", size_table);   
		
		
		
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
		
		return return_success((Object) size_tableService.search(param, pageable));
	}
 
}