package net.heronation.zeyo.rest.controller.item;

import net.heronation.zeyo.rest.common.authentication.AppUserDetails;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.brand.Brand;
import net.heronation.zeyo.rest.repository.category.Category;
import net.heronation.zeyo.rest.repository.company_no_history.CompanyNoHistory;
import net.heronation.zeyo.rest.repository.company_no_history.QCompanyNoHistory;
import net.heronation.zeyo.rest.repository.item.Item;
import net.heronation.zeyo.rest.repository.item.ItemRepository;
import net.heronation.zeyo.rest.repository.item.ItemResourceAssembler;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.repository.sub_category.SubCategory;

import java.util.Date;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;

import lombok.extern.slf4j.Slf4j;

import net.heronation.zeyo.rest.service.item.ItemService;

@Slf4j
@RepositoryRestController
@RequestMapping("/items")
public class ItemController extends BaseController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private ItemRepository repository;
	@Autowired
	private ItemResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ItemController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@PreAuthorize("@AppSecurityEL.hasScope('bar') and  hasRole('ROLE_ADMIN')")
	@RequestMapping(path = "download_excel")
	public ResponseEntity<ResultVO> download_excel(@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/api/items/download_excel");

		return return_success();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "company", required = false) CompanyNoHistory company,
			@RequestParam(value = "brand", required = false) Brand brand,
			@RequestParam(value = "shopmall", required = false) Shopmall shopmall,
			@RequestParam(value = "size_link", required = false) String size_link,
			@RequestParam(value = "cate", required = false) Category category,
			@RequestParam(value = "sub_cate", required = false) SubCategory sub_category,
			@RequestParam(value = "start_price",defaultValue="0", required = false) int start_price,
			@RequestParam(value = "end_price",defaultValue="0", required = false) int end_price,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  DateTime end, Pageable pageable) {

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
		
//		if (size_link != null) {
//			builder.and(target.sizeLinkYn.eq(size_link));
//		}
		
		if (category != null) {
			builder.and(target.category.eq(category));
		}
		
//		if (sub_category != null) {
//			builder.and(target.category.subCategorys.eq(sub_category));
//		}
		

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
		
		return return_success((Object) itemService.search(builder.getValue(), pageable));
	}
	


}