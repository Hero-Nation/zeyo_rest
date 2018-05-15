package net.heronation.zeyo.rest.controller.cloth_color;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorResourceAssembler;
import net.heronation.zeyo.rest.repository.cloth_color.QClothColor;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;

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

import net.heronation.zeyo.rest.service.cloth_color.ClothColorService;

@Slf4j
@RepositoryRestController
@RequestMapping("/cloth_colors")
public class ClothColorController extends BaseController {

	@Autowired
	private ClothColorService cloth_colorService;

	@Autowired
	private ClothColorRepository repository;
	@Autowired
	private ClothColorResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ClothColorController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,

			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QClothColor target = QClothColor.clothColor;

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

		return return_success((Object) cloth_colorService.search(builder.getValue(), pageable));
	}

}