package net.heronation.zeyo.rest.controller.integrate;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.heronation.zeyo.rest.common.controller.BaseController;
import net.heronation.zeyo.rest.common.value.ResultVO;
import net.heronation.zeyo.rest.constants.CommonConstants;
import net.heronation.zeyo.rest.repository.category.CategoryRepository;
import net.heronation.zeyo.rest.repository.category.CategoryResourceAssembler;
import net.heronation.zeyo.rest.repository.shopmall.Shopmall;
import net.heronation.zeyo.rest.service.category.CategoryService;
import net.heronation.zeyo.rest.service.integrate.IntegrateSerivce;

@Slf4j
@Controller
@RequestMapping("/zeyo_app")
public class ZeyoAppController extends BaseController {

	@Autowired
	private IntegrateSerivce integrateSerivce;

	@Autowired
	private CategoryRepository repository;
	@Autowired
	private CategoryResourceAssembler assembler;

	private final RepositoryEntityLinks entityLinks;

	@Autowired
	public ZeyoAppController(RepositoryEntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login")
	public String login(@RequestParam(value = "id", required = false) long shopmall_id, Model m,@AuthenticationPrincipal OAuth2Authentication auth) {

//		if(auth == null) {
//			return "zeyo_app/need_token";
//		}
//		
		Shopmall sm = integrateSerivce.get_shopmall_temp_identity(shopmall_id);
		m.addAttribute("state", sm.getTempIdentity());
		
		
		return "zeyo_app/login";
	}

	public String generateState() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

}