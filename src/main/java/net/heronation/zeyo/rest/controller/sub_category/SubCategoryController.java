package net.heronation.zeyo.rest.controller.sub_category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

import com.querydsl.core.BooleanBuilder;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.LIdVO;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.repository.sub_category.QSubCategory;
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
	public ResponseEntity<ResultVO> sublist(@RequestParam(value = "cate", required = false) Long cate,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			Pageable pageable) {

		BooleanBuilder builder = new BooleanBuilder();

		QSubCategory target = QSubCategory.subCategory;

		if (cate != null) {
			builder.and(target.category.id.eq(cate));
		}

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

		return return_success((Object) sub_categoryService.subsearch(builder.getValue(), pageable));
	}

	@PostMapping("/upload_item_image")
	@ResponseBody
	public ResponseEntity<ResultVO> upload_item_image(@RequestParam("item_image") MultipartFile item_image
	// ,RedirectAttributes redirectAttributes
	) {

		if (item_image.isEmpty()) {
			// redirectAttributes.addFlashAttribute("message", "Please select a file to
			// upload");

			return return_fail("image.empty");
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = item_image.getBytes();
			Path path = Paths.get(upload_item_path + item_image.getOriginalFilename());
			Files.write(path, bytes);
			
			log.debug(upload_item_path);
			log.debug(item_image.getOriginalFilename());
			
			
		} catch (IOException e) {
			e.printStackTrace();
			return return_fail("image.upload.exception");
		}

		return return_success("image.upload.success");
	}

	@PostMapping("/upload_cloth_image")
	@ResponseBody
	public ResponseEntity<ResultVO> upload_cloth_image(@RequestParam("cloth_image") MultipartFile cloth_image
	// ,RedirectAttributes redirectAttributes
	) {

		if (cloth_image.isEmpty()) {
			// redirectAttributes.addFlashAttribute("message", "Please select a file to
			// upload");

			return return_fail("image.empty");
		}

		try {

			// Get the file and save it somewhere
			byte[] bytes = cloth_image.getBytes();
			Path path = Paths.get(upload_cloth_path + cloth_image.getOriginalFilename());
			Files.write(path, bytes);

		} catch (IOException e) {
			e.printStackTrace();
			return return_fail("image.upload.exception");
		}

		return return_success("image.upload.success");
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/distinct_name")
	@ResponseBody
	public ResponseEntity<ResultVO> distinct_name(@RequestParam(name = "cate", defaultValue = "") String cate) {
		if (cate.equals("")) { 
			return return_fail("cate.empty");
		}

		return return_success((Object) sub_categoryService.distinct_name(cate));
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