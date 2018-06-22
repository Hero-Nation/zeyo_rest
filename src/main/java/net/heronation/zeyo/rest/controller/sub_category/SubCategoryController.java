package net.heronation.zeyo.rest.controller.sub_category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryDto;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryRepository;
import net.heronation.zeyo.rest.repository.sub_category.SubCategoryResourceAssembler;
import net.heronation.zeyo.rest.service.sub_category.SubCategoryService;

@Slf4j
@RepositoryRestController
@RequestMapping("/sub_categorys")
public class SubCategoryController extends BaseController {

	@Autowired
	private SubCategoryService sub_categoryService;

	@Autowired
	private SubCategoryRepository repository;
	@Autowired
	private SubCategoryResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Value(value = "${zeyo.item.image.path}")
	private String upload_item_path;
	
	@Value(value = "${zeyo.cloth.image.path}")
	private String upload_cloth_path;
	

	@Autowired
	public SubCategoryController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	// @RequestMapping(method = RequestMethod.GET, value = "/list")
	// @ResponseBody
	// public ResponseEntity<ResultVO> list(
	// @RequestParam(value = "name",required=false) String name,
	// @RequestParam(value = "cate",required=false) Category cate,
	// @RequestParam(value = "subcate",required=false) SubCategory subcate,
	// @RequestParam(value = "measure",required=false) MeasureItem measure,
	// @RequestParam(value = "start",required=false) @DateTimeFormat(iso =
	// DateTimeFormat.ISO.DATE_TIME) DateTime start,
	// @RequestParam(value = "end",required=false) @DateTimeFormat(iso =
	// DateTimeFormat.ISO.DATE_TIME) DateTime end,
	// Pageable pageable) {
	//
	// BooleanBuilder builder = new BooleanBuilder();
	//
	// if (name != null) {
	// builder.and(QSubCategory.subCategory.category.name.containsIgnoreCase(name));
	// }
	//
	// if (cate != null) {
	// builder.and(QSubCategory.subCategory.category.eq(cate));
	// }
	//
	// if (subcate != null) {
	// builder.and(QSubCategory.subCategory.eq(subcate));
	// }
	//
	//// if (measure != null) {
	//// builder.and(QSubCategory.subCategory.subCategoryMeasureMaps..eq(measure));
	//// }
	//
	// if (start != null) {
	// builder.and(QSubCategory.subCategory.createDt.after(start));
	// }
	//
	// if (end != null) {
	// builder.and(QSubCategory.subCategory.createDt.before(end));
	// }
	//
	// builder.and(QSubCategory.subCategory.useYn.eq("Y"));
	//
	// return return_success((Object) sub_categoryService.search(builder.getValue(),
	// pageable));
	// }

	@RequestMapping(method = RequestMethod.GET, value = "/sublist")
	@ResponseBody
	public ResponseEntity<ResultVO> sublist(@RequestParam(value = "cate", required = false) String cate,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cate",cate);
		param.put("name", name);  
		if (start == null) {
			param.put("start", start);
		} else {
			param.put("start", start.toString(Format.ISO_DATETIME));
		}
		if (end == null) { 
			param.put("end", end);
		} else {
			param.put("end", end.toString(Format.ISO_DATETIME));
		}

		return return_success((Object) sub_categoryService.subsearch(param, pageable));
	}
 
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/distinct_name")
	@ResponseBody
	public ResponseEntity<ResultVO> distinct_name(@RequestParam(name = "cate", defaultValue = "") String cate) {
		if (cate.equals("")) { 
			return return_fail("cate.empty");
		}

		return return_success((Object) sub_categoryService.distinct_name(cate));
	}

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/single_info")
	@ResponseBody
	public ResponseEntity<ResultVO> single_info(@RequestParam(value = "id", required = false) Long id) {
		if (id == null || id == 0) { 
			return return_fail("id.empty");
		}

		return return_success((Object) sub_categoryService.single_info(id));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public ResponseEntity<ResultVO> insert(@RequestBody SubCategoryDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) sub_categoryService.insert(param));
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update")
	public ResponseEntity<ResultVO> update(@RequestBody SubCategoryDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) sub_categoryService.update(param));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	public ResponseEntity<ResultVO> delete(@RequestBody List<LIdVO> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) sub_categoryService.delete(param));
	}
	

}