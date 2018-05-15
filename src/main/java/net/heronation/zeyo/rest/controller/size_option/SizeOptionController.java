package net.heronation.zeyo.rest.controller.size_option;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.shopmall.QShopmall;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionRepository;
import net.heronation.zeyo.rest.repository.size_option.SizeOptionResourceAssembler;

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

import net.heronation.zeyo.rest.service.size_option.SizeOptionService;

@Slf4j
@RepositoryRestController
@RequestMapping("/size_options")
public class SizeOptionController extends BaseController {

	@Autowired
	private SizeOptionService size_optionService;

	@Autowired
	private SizeOptionRepository repository;
	@Autowired
	private SizeOptionResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public SizeOptionController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "kindof", required = false) Kindof kindof,
			
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QSizeOption target = QSizeOption.sizeOption;

		if (name != null) {
			builder.and(target.name.containsIgnoreCase(name));
		}

		if (start != null) {
			builder.and(target.createDt.after(start));
		}

		if (end != null) {
			builder.and(target.createDt.before(end));
		}
		
		if (kindof != null) {
			builder.and(target.kindof.eq(kindof));
		}

		builder.and(target.useYn.eq("Y"));

		return return_success((Object) size_optionService.search(builder.getValue(), pageable));
	}

}