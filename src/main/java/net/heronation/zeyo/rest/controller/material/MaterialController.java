package net.heronation.zeyo.rest.controller.material; 
 
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.Format;
import net.heronation.zeyo.rest.repository.material.MaterialRepository;
import net.heronation.zeyo.rest.repository.material.MaterialResourceAssembler;
import net.heronation.zeyo.rest.service.material.MaterialService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/materials")
public class MaterialController extends BaseController {
	
    @Autowired
    private MaterialService materialService;
 
     @Autowired
    private MaterialRepository repository; 
     @Autowired
    private MaterialResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public MaterialController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	@ResponseBody
	public ResponseEntity<ResultVO> list(
			@RequestParam(value = "name",required=false) String name,
			@RequestParam(value = "start",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime start,
			@RequestParam(value = "end",required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  DateTime end,
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
		
		return return_success((Object) materialService.search(param, pageable));
	}
 
}