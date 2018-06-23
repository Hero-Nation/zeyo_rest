package net.heronation.zeyo.rest.controller.warranty; 
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.IdNameDto;
import net.heronation.zeyo.rest.common.value.LIdDto;
import net.heronation.zeyo.rest.common.value.NameDto;
import net.heronation.zeyo.rest.common.value.ResultDto;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.warranty.WarrantyRepository;
import net.heronation.zeyo.rest.repository.warranty.WarrantyResourceAssembler;
import net.heronation.zeyo.rest.service.warranty.WarrantyService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/warrantys")
public class WarrantyController extends BaseController {
	
    @Autowired
    private WarrantyService warrantyService;
 
     @Autowired
    private WarrantyRepository repository; 
     @Autowired
    private WarrantyResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public WarrantyController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 
 	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultDto> list(
			@RequestParam(value = "scope",required=false) String scope,
			@RequestParam(value = "start",required=false)  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) DateTime start,
			@RequestParam(value = "end",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime end,
			Pageable pageable) {

		Map<String,Object> param = new HashMap<String,Object>();
		param.put("scope", scope); 
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
		
		return return_success((Object) warrantyService.search(param, pageable));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, value = "/insert")
	public ResponseEntity<ResultDto> insert(@RequestBody ScopeVO param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) warrantyService.insert(param));
	}
	

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/update")
	public ResponseEntity<ResultDto> update(@RequestBody IdNameDto param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) warrantyService.update(param));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.PATCH, value = "/delete")
	public ResponseEntity<ResultDto> delete(@RequestBody List<LIdDto> param,
			@AuthenticationPrincipal OAuth2Authentication auth) {

		return return_success((Object) warrantyService.delete(param));
	}
 
}