package net.heronation.zeyo.rest.controller.integrate;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.CategoryResourceAssembler;
import net.heronation.zeyo.rest.service.category.CategoryService;

 

@Slf4j
@Controller
@RequestMapping("/integrate/")
public class ApiController extends BaseController {
	

	
	
    @Autowired
    private CategoryService categoryService;
 
     @Autowired
    private CategoryRepository repository; 
     @Autowired
    private CategoryResourceAssembler assembler;

    private final RepositoryEntityLinks entityLinks;

 

 	@Autowired
	public ApiController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	} 

 
	@RequestMapping(method = RequestMethod.GET, value = "/cafe24/produc/sync")
	@ResponseBody
	public ResponseEntity<ResultVO> cafe24_sync_produc(@AuthenticationPrincipal OAuth2Authentication auth) {
		log.debug("/integrate/cafe24/produc/sync");
		
		// 유저 정보 가지고 오기
		if (auth == null) {
			return return_fail(CommonConstants.NO_TOKEN);
		}
		Map<String, Object> user = (Map<String, Object>) ((OAuth2AuthenticationDetails) auth.getDetails()).getDecodedDetails();
		
		Long seq = Long.valueOf(String.valueOf(user.get("member_seq")));
		
		
		
		
		return return_success("Asdf");
	}
	
 
	
}