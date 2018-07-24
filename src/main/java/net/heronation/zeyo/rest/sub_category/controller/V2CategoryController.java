package net.heronation.zeyo.rest.sub_category.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.constants.Format;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryDto;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryResourceAssembler;
import net.heronation.zeyo.rest.sub_category.service.SubCategoryService;

/**
 * @author hero
 *
 */
@Slf4j
@RepositoryRestController
@RequestMapping("/v2_categorys")
public class V2CategoryController extends BaseController {

	@Autowired
	private SubCategoryService sub_categoryService;

	@Autowired
	private SubCategoryRepository repository;
	@Autowired
	private SubCategoryResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;
	

	@Autowired
	public V2CategoryController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}
 
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultDto> sublist(@RequestParam(value = "cate", required = false) String cate,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "start", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime end,
			@RequestParam(value = "sort",  required = false) String sort,Pageable pageable) {

		if(pageable.getSort() == null && sort != null) { 
			pageable = new PageRequest(pageable.getPageNumber(),  pageable.getPageSize(), Direction.DESC, sort.split(",")[0]);
		}
		
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
 
	
	
	
	/**
	 * 
	 * v2 카테고리는 cache를 사용하여 개발한다. 
	 * 
	 * @param cate
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/refresh")
	@ResponseBody
	public ResponseEntity<ResultDto> refresh(@RequestParam(name = "cate", defaultValue = "") String cate) {
		if (cate.equals("")) { 
			return return_fail("cate.empty");
		}

		return return_success((Object) sub_categoryService.distinct_name(cate));
	}

	
	
	@RequestMapping(method = RequestMethod.GET, value = "/info")
	@ResponseBody
	public ResponseEntity<ResultDto> single_info(@RequestParam(value = "id", required = false) Long id) {
		
		if (id == null || id == 0) { 
			return return_fail("id.empty");
		}

		return return_success((Object) sub_categoryService.single_info(id));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public ResponseEntity<ResultDto> insert(@RequestBody SubCategoryDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

	 
		try { 
			return return_success((Object) sub_categoryService.insert(param));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update")
	public ResponseEntity<ResultDto> update(@RequestBody SubCategoryDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
 
		
		try { 
			return return_success((Object) sub_categoryService.update(param));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	public ResponseEntity<ResultDto> delete(@RequestBody List<LIdDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) sub_categoryService.delete(param));
	}
	

}