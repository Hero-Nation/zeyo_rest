package net.heronation.zeyo.rest.controller.brand;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.brand.BrandRepository;
import net.heronation.zeyo.rest.repository.brand.BrandResourceAssembler;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.item.QItem;
import net.heronation.zeyo.rest.repository.madein.QMadein;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;

import net.heronation.zeyo.rest.service.brand.BrandService;

@Slf4j
@RepositoryRestController
@RequestMapping("/brands")
public class BrandController extends BaseController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private BrandRepository repository;
	@Autowired
	private BrandResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public BrandController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
			@RequestParam(value = "end", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end, Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QBrand target = QBrand.brand;

		if (name != null) {
			builder.and(target.name.containsIgnoreCase(name));
		}

		if (start != null) {
			builder.and(target.createDt.after(start));
		}

		if (end != null) {
			builder.and(target.createDt.before(end));
		}

		builder.and(target.useYn.eq("Y"));

		return return_success((Object) brandService.search(builder.getValue(), pageable));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	@ResponseBody
	public ResponseEntity<ResultVO> unique(@RequestParam(value = "name", required = true) String name,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails())
				.getDecodedDetails();

		Long seq = (Long) user.get("member_seq");

		return return_success((Object) brandService.insert(name, seq));
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/client/list")
	@ResponseBody
	public ResponseEntity<ResultVO> brand_list( 
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date end, Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QItem target = QItem.item;

		if (name != null) {
			builder.and(target.brand.name.containsIgnoreCase(name));
		} 
		
		if(start != null) {
			builder.and(target.brand.createDt.after(start).or(target.brand.createDt.eq(start)));
		}
		
		if(end != null) {
			builder.and(target.brand.createDt.before(end).or(target.brand.createDt.eq(end)));
		}
		
		builder.and(target.useYn.eq("N"));
		builder.and(target.brand.useYn.eq("N"));
		
		return return_success((Object) brandService.client_search(builder.getValue(), pageable));
	}

}