package net.heronation.zeyo.rest.controller.size_table; 
 
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.size_table.SizeTableRepository;
import net.heronation.zeyo.rest.repository.size_table.SizeTableResourceAssembler;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

import java.util.Date;

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
			@RequestParam(value = "company", required = false) CompanyNoHistory company,
			@RequestParam(value = "brand", required = false) Brand brand,
			@RequestParam(value = "shopmall", required = false) Shopmall shopmall,
		 
			@RequestParam(value = "start_price",defaultValue="0", required = false) int start_price,
			@RequestParam(value = "end_price",defaultValue="0", required = false) int end_price,
			@RequestParam(value = "start", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
			@RequestParam(value = "end", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end, Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QItem target = QItem.item;

		if (name != null) {
			builder.and(target.name.containsIgnoreCase(name));
		}

		if (company != null) {
			builder.and(target.member.companyNoHistory.eq(company));
		}

		if (brand != null) {
			builder.and(target.brand.eq(brand));
		}

		// shopmall skip
		
 

		if(start_price != 0) {
			builder.and(target.price.gt(start_price).or(target.price.eq(start_price)));
		}
		
		if(end_price != 0) {
			builder.and(target.price.lt(end_price).or(target.price.eq(end_price)));
		}
		
		
		if(start != null) {
			builder.and(target.createDt.after(start).or(target.createDt.eq(start)));
		}
		
		if(end != null) {
			builder.and(target.createDt.before(end).or(target.createDt.eq(end)));
		}
		
		return return_success((Object) size_tableService.search(builder.getValue(), pageable));
	}
 
}