package net.heronation.zeyo.rest.controller.shopmall;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.brand.QBrand;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallRepository;
import net.heronation.zeyo.rest.repository.shopmall.ShopmallResourceAssembler;

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

import net.heronation.zeyo.rest.service.shopmall.ShopmallService;

@Slf4j
@RepositoryRestController
@RequestMapping("/shopmalls")
public class ShopmallController extends BaseController {

	@Autowired
	private ShopmallService shopmallService;

	@Autowired
	private ShopmallRepository repository;
	@Autowired
	private ShopmallResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ShopmallController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)  Date end, Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QShopmall target = QShopmall.shopmall;

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

		return return_success((Object) shopmallService.search(builder.getValue(), pageable));
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/client/list")
	@ResponseBody
	public ResponseEntity<ResultVO> client_list(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "sizeCp", required = false) String sizeCp,
			@RequestParam(value = "start", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
			@RequestParam(value = "end", required = false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end, Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QShopmall target = QShopmall.shopmall;

		if (name != null) {
			builder.and(target.name.containsIgnoreCase(name));
		}
		
		if (sizeCp != null) {
 			builder.and(target.sizeCpYn.eq(sizeCp));
		}

		if (start != null) {
			builder.and(target.createDt.after(start));
		}

		if (end != null) {
			builder.and(target.createDt.before(end));
		}

		builder.and(target.useYn.eq("Y"));

		return return_success((Object) shopmallService.search(builder.getValue(), pageable));
	}	
}