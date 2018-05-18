package net.heronation.zeyo.rest.controller.madein;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.madein.Madein;
import net.heronation.zeyo.rest.repository.madein.MadeinRepository;
import net.heronation.zeyo.rest.repository.madein.MadeinResourceAssembler;
import net.heronation.zeyo.rest.repository.madein.QMadein;

import java.sql.Date;
import java.time.LocalDate;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.slf4j.Slf4j;

import net.heronation.zeyo.rest.service.madein.MadeinService;

@Slf4j
@RepositoryRestController
@RequestMapping("/madeins")
public class MadeinController extends BaseController {

	@Autowired
	private MadeinService madeinService;

	@Autowired
	private MadeinRepository repository;
	@Autowired
	private MadeinResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public MadeinController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {
 

		BooleanBuilder builder = new BooleanBuilder();

		if (name != null) {
			builder.and(QMadein.madein.name.containsIgnoreCase(name));
		}

		if (start != null) {
			builder.and(QMadein.madein.createDt.after(start));
		}

		if (end != null) {
			builder.and(QMadein.madein.createDt.before(end));
		}

		builder.and(QMadein.madein.useYn.eq("Y"));

		return return_success((Object) madeinService.use_list(builder.getValue(), pageable));
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/use_list")
	// @ResponseBody
	// public ResponseEntity<ResultVO> list(
	// @RequestParam(value = "useYn",required=false) String useYn ,
	// Pageable pageable) {
	//
	// BooleanBuilder builder = new BooleanBuilder();
	// builder.and(QMadein.madein.useYn.eq(useYn));
	//
	// return return_success((Object) madeinService.use_list(builder.getValue(),
	// pageable));
	// }

}