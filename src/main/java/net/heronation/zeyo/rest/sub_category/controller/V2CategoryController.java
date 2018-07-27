package net.heronation.zeyo.rest.sub_category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.controller.CommonException;
import net.heronation.zeyo.rest.common.dto.LIdDto;
import net.heronation.zeyo.rest.common.dto.ResultDto;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryRepository;
import net.heronation.zeyo.rest.sub_category.repository.SubCategoryResourceAssembler;
import net.heronation.zeyo.rest.sub_category.service.V2CategoryService;
 
/**
 * @author hero
 *
 */
@Slf4j
@Controller
@RequestMapping("/api/v2_categorys")
public class V2CategoryController extends BaseController {

	@Autowired
	private V2CategoryService v2Service;

	@Autowired
	private SubCategoryRepository repository;
	@Autowired
	private SubCategoryResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;
	

	@Autowired
	public V2CategoryController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}
 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultDto> sublist(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "parent_id", required = false) Long parent_id ,
			Pageable pageable) {
 
		log.debug("/api/v2_categorys/list");
		
		return return_success((Object) v2Service.list(name,parent_id, pageable));
	}
 
	
	
	
	 /**
	 * 
	 * v2 카테고리는 cache를 사용하여 개발한다. 
	 * 
	 * @param cate
	 * @return
	 */ 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/refresh")
	@ResponseBody
	public ResponseEntity<ResultDto> refresh() {
		v2Service.refresh();
		return return_success();
	}


	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/info")
	@ResponseBody
	public ResponseEntity<ResultDto> single_info(@RequestParam(value = "id", required = false) Long id) {
		
		if (id == null || id == 0) { 
			return return_fail("id.empty");
		}

		return return_success((Object) v2Service.single_info(id));
	}

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public ResponseEntity<ResultDto> insert(@RequestBody V2CategoryDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

	 
		try { 
			return return_success((Object) v2Service.insert(param));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
		
	}
	
 
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update")
	public ResponseEntity<ResultDto> update(@RequestBody V2CategoryDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
 
		
		try { 
			return return_success((Object) v2Service.update(param));
		} catch (CommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return return_fail(e);
		}
	}
	 
	 
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	public ResponseEntity<ResultDto> delete(@RequestBody List<Long> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {
		v2Service.delete(param);
		return return_success();
	}
	

}