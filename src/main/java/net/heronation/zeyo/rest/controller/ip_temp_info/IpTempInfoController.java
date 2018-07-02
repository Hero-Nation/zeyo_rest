package net.heronation.zeyo.rest.controller.ip_temp_info; 
 
import net.heronation.zeyo.rest.common.controller.BaseController; 
import net.heronation.zeyo.rest.repository.ip_temp_info.IpTempInfoRepository;
import net.heronation.zeyo.rest.repository.ip_temp_info.IpTempInfoResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


import net.heronation.zeyo.rest.service.ip_temp_info.IpTempInfoService; 

@Slf4j
@RepositoryRestController
@RequestMapping("/ip_temp_infos")
public class IpTempInfoController extends BaseController {
	
    @Autowired
    private IpTempInfoService ip_temp_infoService;
 
     @Autowired
    private IpTempInfoRepository repository; 
     @Autowired
    private IpTempInfoResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public IpTempInfoController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		log.debug("/test");
		return new ResponseEntity<>("test", HttpStatus.OK);
	}


   

 
}