package net.heronation.zeyo.rest.controller.cloth_color;

import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorRepository;
import net.heronation.zeyo.rest.repository.cloth_color.ClothColorResourceAssembler;
import net.heronation.zeyo.rest.repository.cloth_color.QClothColor;
import net.heronation.zeyo.rest.repository.item_cloth_color_map.QItemClothColorMap;
import net.heronation.zeyo.rest.repository.kindof.Kindof;
import net.heronation.zeyo.rest.repository.size_option.QSizeOption;

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
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,

			Pageable pageable) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("name", name); 
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
		
		return return_success((Object) cloth_colorService.search(param, pageable));
	}

}