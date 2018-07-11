package net.heronation.zeyo.rest.ip_temp_info.controller; 
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.ip_temp_info.repository.IpTempInfoRepository;
import net.heronation.zeyo.rest.ip_temp_info.repository.IpTempInfoResourceAssembler;
import net.heronation.zeyo.rest.ip_temp_info.service.IpTempInfoService; 

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